package com.example.zoomarket.service;

import com.example.zoomarket.dto.post.type.TypeCreateDTO;
import com.example.zoomarket.dto.post.type.TypeResponseDTO;
import com.example.zoomarket.entity.TypeEntity;
import com.example.zoomarket.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;
    private final AttachService attachService;
    private final CategoryService categoryService;

    public Boolean isPostTypeExists(Long postTypeId) {
        return typeRepository.existsById(postTypeId);
    }

    public TypeResponseDTO create(TypeCreateDTO dto) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName(dto.getName());

//      checking CategoryId is valid
        categoryService.findById(dto.getCategoryId());
        typeEntity.setCategoryId(dto.getCategoryId());

//      checking attach is valid
        attachService.findById(dto.getAttachId());
        typeEntity.setAttachId(dto.getAttachId());

        typeRepository.save(typeEntity);

        TypeResponseDTO response = new TypeResponseDTO();
        response.setId(typeEntity.getId());
        response.setName(typeEntity.getName());
        response.setCategoryId(typeEntity.getCategoryId());
        response.setAttachId(typeEntity.getAttachId());
        return response;
    }

    public List<TypeResponseDTO> getByCategoryId(Long categoryId) {
        List<TypeEntity> byType = typeRepository.findByCategoryId(categoryId);
        List<TypeResponseDTO> result = new LinkedList<>();
        for (TypeEntity typeEntity : byType) {
            TypeResponseDTO response = new TypeResponseDTO();
            response.setId(typeEntity.getId());
            response.setName(typeEntity.getName());
            response.setCategoryId(typeEntity.getCategoryId());
            response.setAttachId(typeEntity.getAttachId());
            result.add(response);
        }
        return result;
    }
}