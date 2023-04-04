package com.example.zoomarket.service;

import com.example.zoomarket.dto.DashboardResponseDto;
import com.example.zoomarket.entity.ActiveRequests;
import com.example.zoomarket.repository.ActiveRequestsRepository;
import com.example.zoomarket.repository.PostRepository;
import com.example.zoomarket.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final ActiveRequestsRepository activeRequestsRepository;

    private final ProfileRepository profileRepository;


    private final PostRepository postRepository;

    private long getAllUserCount() {
        return profileRepository.count();
    }

    private long getAllPostCount() {
        return postRepository.count();
    }

    private int getAllUserCountBetweenTwoDate(LocalDateTime date1, LocalDateTime date2) {
        return profileRepository.countAllByCreatedDateBetween(date1, date2);
    }

    private int getAllPostCountBetweenTwoDate(LocalDateTime date1, LocalDateTime date2) {
        return postRepository.countAllByCreatedDateBetween(date1, date2);
    }



    public ResponseEntity<?> getDashBoardInfo() {
        long allUserCount = getAllUserCount();
        long allPostCount = getAllPostCount();
        LocalDateTime date = LocalDateTime.now();
        int thisMonthUsersCount = getAllUserCountBetweenTwoDate(date.minusDays(30), date);
        int lastMonthUsersCount = getAllUserCountBetweenTwoDate(date.minusDays(61), date.minusDays(31));
        int thisMonthPostCount = getAllPostCountBetweenTwoDate(date.minusDays(30), date);
        int lastMonthPostsCount = getAllPostCountBetweenTwoDate(date.minusDays(61), date.minusDays(31));

        DashboardResponseDto dashboardResponse =
                DashboardResponseDto.builder()
                        .countUsers(allUserCount)
                        .countPost(allPostCount)
                        .userIncreasedPercentageOneMonth((((double) thisMonthUsersCount / lastMonthUsersCount) - 1) * 100)
                        .postIncreasedPercentageOneMonth((((double) thisMonthPostCount / lastMonthPostsCount) - 1) * 100)
                        .yesterdayUserRequestHourByHour(getDailyRequestCountList(LocalDateTime.now().minusDays(1)))
                        .todayUserRequestHourByHour(getDailyRequestCountList(LocalDateTime.now()))
                        .build();
        return ResponseEntity.ok(dashboardResponse);
    }

    private int[] getDailyRequestCountList(LocalDateTime now) {
        LocalDateTime startTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 00, 00, 01);
        LocalDateTime endTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59);
        List<ActiveRequests> allByCreatedTimeBetween =
                activeRequestsRepository.findAllByCreatedTimeBetween(startTime, endTime);
        int[] info = new int[24];
        int counter = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < allByCreatedTimeBetween.size(); j++) {
                if (allByCreatedTimeBetween.get(j).getCreatedTime().getHour() == i) {
                    counter++;
                }
            }
            info[i] = counter;
            counter = 0;
        }
        return info;
    }

}
