package com.example.zoomarket.service;

import com.example.zoomarket.dto.post.type.PostTypeCreateDTO;
import com.example.zoomarket.dto.post.type.PostTypeResponseDTO;
import com.example.zoomarket.entity.PostTypeEntity;
import com.example.zoomarket.enums.Type;
import com.example.zoomarket.repository.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PostTypeService {
    private final PostTypeRepository postTypeRepository;
    @Autowired
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

    public List<PostTypeResponseDTO> getAnimalTypes() {
        List<PostTypeEntity> byType = postTypeRepository.findByType(Type.ANIMAL);
        List<PostTypeResponseDTO> result = new LinkedList<>();
        for (PostTypeEntity postTypeEntity : byType) {
            PostTypeResponseDTO postTypeResponseDTO = new PostTypeResponseDTO();
            postTypeResponseDTO.setId(postTypeEntity.getId());
            postTypeResponseDTO.setName(postTypeEntity.getName());
            postTypeResponseDTO.setType(postTypeEntity.getType());
            result.add(postTypeResponseDTO);
        }
        return result;
    }


    public List<PostTypeResponseDTO> getPlantTypes() {
        List<PostTypeEntity> byType = postTypeRepository.findByType(Type.PLANT);
        List<PostTypeResponseDTO> result = new LinkedList<>();
        for (PostTypeEntity postTypeEntity : byType) {
            PostTypeResponseDTO postTypeResponseDTO = new PostTypeResponseDTO();
            postTypeResponseDTO.setId(postTypeEntity.getId());
            postTypeResponseDTO.setName(postTypeEntity.getName());
            postTypeResponseDTO.setType(postTypeEntity.getType());
            result.add(postTypeResponseDTO);
        }
        return result;
    }
}