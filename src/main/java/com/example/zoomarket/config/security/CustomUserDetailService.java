package com.example.zoomarket.config.security;

import com.example.zoomarket.entity.ProfileEntity;
import com.example.zoomarket.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // alish
        Optional<ProfileEntity> optional = profileRepository.findByPhone(username);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("Bad Cretensional");
        }

        ProfileEntity profile = optional.get();
//        return new CustomUserDetails(profile);
        return new CustomUserDetails(profile.getId(),profile.getPhone(), profile.getRole());
    }
}
