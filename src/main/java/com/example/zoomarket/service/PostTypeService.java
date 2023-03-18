package com.example.zoomarket.service;

import com.example.zoomarket.dto.post.type.PostTypeCreateDTO;
import com.example.zoomarket.dto.post.type.PostTypeResponseDTO;
import com.example.zoomarket.entity.PostTypeEntity;
import com.example.zoomarket.repository.PostTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class PostTypeService {
    private final PostTypeRepository postTypeRepository;

    public PostTypeService(PostTypeRepository postTypeRepository) {
        this.postTypeRepository = postTypeRepository;
    }

    public Boolean isPostTypeExists(Long postTypeId) {
        return postTypeRepository.existsById(postTypeId);
    }

    public PostTypeResponseDTO create(PostTypeCreateDTO dto) {
        PostTypeEntity postTypeEntity = new PostTypeEntity();
        postTypeEntity.setName(dto.getName());
        postTypeEntity.setType(dto.getType());
        postTypeRepository.save(postTypeEntity);

        PostTypeResponseDTO response = new PostTypeResponseDTO();
        response.setId(postTypeEntity.getId());
        response.setName(postTypeEntity.getName());
        response.setType(postTypeEntity.getType());
        return response;
    }
}