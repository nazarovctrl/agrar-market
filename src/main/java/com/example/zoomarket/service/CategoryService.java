package com.example.zoomarket.service;

import com.example.zoomarket.dto.post.category.CategoryCreateDTO;
import com.example.zoomarket.dto.post.category.CategoryResponseDTO;
import com.example.zoomarket.entity.AttachEntity;
import com.example.zoomarket.entity.CategoryEntity;
import com.example.zoomarket.enums.Type;
import com.example.zoomarket.exp.attach.FileNotFoundException;
import com.example.zoomarket.exp.post.category.CategoryNotFoundException;
import com.example.zoomarket.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final AttachService attachService;

    public Boolean isPostTypeExists(Long postTypeId) {
        return categoryRepository.existsById(postTypeId);
    }

    public CategoryResponseDTO create(CategoryCreateDTO dto) {
        CategoryEntity postTypeEntity = new CategoryEntity();
        postTypeEntity.setName(dto.getName());
        postTypeEntity.setType(dto.getType());

//      checking attach is valid
        attachService.findById(dto.getAttachId());

        postTypeEntity.setAttachId(dto.getAttachId());
        categoryRepository.save(postTypeEntity);

        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(postTypeEntity.getId());
        response.setName(postTypeEntity.getName());
        response.setType(postTypeEntity.getType());
        return response;
    }

    public List<CategoryResponseDTO> getAnimalTypes() {
        List<CategoryEntity> byType = categoryRepository.findByType(Type.ANIMAL);
        List<CategoryResponseDTO> result = new LinkedList<>();
        for (CategoryEntity categoryEntity : byType) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(categoryEntity.getId());
            categoryResponseDTO.setName(categoryEntity.getName());
            categoryResponseDTO.setType(categoryEntity.getType());
            result.add(categoryResponseDTO);
        }
        return result;
    }


    public List<CategoryResponseDTO> getPlantTypes() {
        List<CategoryEntity> byType = categoryRepository.findByType(Type.PLANT);
        List<CategoryResponseDTO> result = new LinkedList<>();
        for (CategoryEntity categoryEntity : byType) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(categoryEntity.getId());
            categoryResponseDTO.setName(categoryEntity.getName());
            categoryResponseDTO.setType(categoryEntity.getType());
            result.add(categoryResponseDTO);
        }
        return result;
    }

    public CategoryEntity findById(Long id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Category Not Found");
        }
        return optional.get();
    }

}