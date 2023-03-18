package com.example.zoomarket.service;

import com.example.zoomarket.dto.post.PostResponseDTO;
import com.example.zoomarket.entity.PostLikeEntity;
import com.example.zoomarket.exp.post.like.PostAlreadyLikedException;
import com.example.zoomarket.repository.PostLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    public PostLikeService(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    public Boolean create(Long profileId, Long postId) {
        Optional<PostLikeEntity> byPostIdAndProfileId = postLikeRepository.findByPostIdAndProfileId(postId, profileId);
        if (byPostIdAndProfileId.isPresent()) {
            throw new PostAlreadyLikedException("Post already liked");
        }

        // TODO 
        PostLikeEntity postLikeEntity = new PostLikeEntity();
        postLikeEntity.setPostId(postId);
        postLikeEntity.setProfileId(profileId);
        postLikeRepository.save(postLikeEntity);
        return true;
    }
}
