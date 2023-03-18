package com.example.zoomarket.service;

import com.example.zoomarket.dto.post.PostCreateDTO;
import com.example.zoomarket.dto.post.PostResponseDTO;
import com.example.zoomarket.entity.PostEntity;
import com.example.zoomarket.exp.post.type.PostTypeNotFoundException;
import com.example.zoomarket.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    private final PostTypeService postTypeService;
    
    private final PostPhotoService postPhotoService;

    public PostService(PostRepository postRepository, PostTypeService postTypeService, PostPhotoService postPhotoService) {
        this.postRepository = postRepository;
        this.postTypeService = postTypeService;
        this.postPhotoService = postPhotoService;
    }

    public PostResponseDTO create(Long profileId, PostCreateDTO dto) {
        PostEntity postEntity = new PostEntity();

        if (!postTypeService.isPostTypeExists(dto.getTypeId())) {
            throw new PostTypeNotFoundException("Post type not found");
        }
        postEntity.setTypeId(dto.getTypeId());
        postEntity.setTitle(dto.getTitle());
        postEntity.setPrice(dto.getPrice());;
        postEntity.setPhone(dto.getPhone());
        postEntity.setLocation(dto.getLocation());
        postEntity.setDescription(dto.getDescription());
        postEntity.setProfileId(profileId);

        postRepository.save(postEntity);


        List<String> attachId = dto.getAttachId();
        for (String attach : attachId) {
            postPhotoService.create(postEntity.getId(), attach);
        }

        PostResponseDTO response = new PostResponseDTO();
        response.setId(postEntity.getId());
        response.setTypeId(postEntity.getTypeId());
        response.setTitle(postEntity.getTitle());
        response.setPrice(postEntity.getPrice());
        response.setPhone(postEntity.getPhone());
        response.setLocation(postEntity.getLocation());
        response.setDescription(postEntity.getDescription());
        response.setAttachId(attachId);
        response.setProfileId(postEntity.getProfileId());
        return response;
    }
}
