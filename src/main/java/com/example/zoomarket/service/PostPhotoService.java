package com.example.zoomarket.service;

import com.example.zoomarket.entity.PostPhotoEntity;
import com.example.zoomarket.repository.PostPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostPhotoService {
    private final PostPhotoRepository postPhotoRepository;
    @Autowired
    public PostPhotoService(PostPhotoRepository postPhotoRepository) {
        this.postPhotoRepository = postPhotoRepository;
    }

    public Long create(Long postId, String attachId) {
        Optional<PostPhotoEntity> byPostIdAndAndAttachId = postPhotoRepository.findByPostIdAndAttachId(postId, attachId);
        if (byPostIdAndAndAttachId.isPresent()) {
            return byPostIdAndAndAttachId.get().getId();
        }

        PostPhotoEntity postPhoto = new PostPhotoEntity();
        postPhoto.setAttachId(attachId);
        postPhoto.setPostId(postId);
        postPhotoRepository.save(postPhoto);

        return postPhoto.getId();
    }

    public Boolean deletePhotosByPostId(Long postId) {
        // TODO HOTIRADAN HAM O'CHIRISH
        postPhotoRepository.deleteByPostId(postId);
        return true;
    }

    public List<String> getPhotosByPostId(Long postId) {
        List<PostPhotoEntity> byPostId = postPhotoRepository.findByPostId(postId);
        List<String> response = new LinkedList<>();

        for (PostPhotoEntity postPhotoEntity : byPostId) {
            response.add(postPhotoEntity.getAttachId());
        }

        return response;
    }
}
