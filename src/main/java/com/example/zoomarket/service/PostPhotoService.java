package com.example.zoomarket.service;

import com.example.zoomarket.entity.PostPhotoEntity;
import com.example.zoomarket.repository.PostPhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class PostPhotoService {
    private final PostPhotoRepository postPhotoRepository;

    public PostPhotoService(PostPhotoRepository postPhotoRepository) {
        this.postPhotoRepository = postPhotoRepository;
    }

    public Long create(Long postId, String attachId) {
        PostPhotoEntity postPhoto = new PostPhotoEntity();
        postPhoto.setAttachId(attachId);
        postPhoto.setPostId(postId);
        postPhotoRepository.save(postPhoto);

        return postPhoto.getId();
    }
}
