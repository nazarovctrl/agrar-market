package com.example.zoomarket.service;

import com.example.zoomarket.dto.post.PostCreateDTO;
import com.example.zoomarket.dto.post.PostResponseDTO;
import com.example.zoomarket.dto.post.PostUpdateDTO;
import com.example.zoomarket.enums.Type;
import com.example.zoomarket.exp.post.PostDeleteNotAllowedException;
import com.example.zoomarket.exp.post.PostNotFoundException;
import com.example.zoomarket.exp.post.PostUpdateNotAllowedException;
import com.example.zoomarket.exp.post.category.CategoryNotFoundException;
import com.example.zoomarket.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.zoomarket.entity.PostEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    private final TypeService typeService;
    private final AttachService attachService;

    private final PostPhotoService postPhotoService;
    private final PostLikeService postLikeService;

    public PostService(PostRepository postRepository, TypeService typeService, AttachService attachService, PostPhotoService postPhotoService, PostLikeService postLikeService) {
        this.postRepository = postRepository;
        this.typeService = typeService;
        this.attachService = attachService;
        this.postPhotoService = postPhotoService;
        this.postLikeService = postLikeService;
    }

    public PostResponseDTO create(Long profileId, PostCreateDTO dto) {
        PostEntity postEntity = new PostEntity();

        if (!typeService.isPostTypeExists(dto.getTypeId())) {
            throw new CategoryNotFoundException("Post type not found");
        }

        postEntity.setTypeId(dto.getTypeId());
        postEntity.setTitle(dto.getTitle());
        postEntity.setPrice(dto.getPrice());
        postEntity.setCurrency(dto.getCurrency());
        postEntity.setPhone(dto.getPhone());
        postEntity.setLocation(dto.getLocation());
        postEntity.setDescription(dto.getDescription());
        postEntity.setProfileId(profileId);

        postRepository.save(postEntity);


        List<String> attachIdList = dto.getAttachIdList();
        for (String attach : attachIdList) {
            postPhotoService.create(postEntity.getId(), attach);
        }

        PostResponseDTO response = new PostResponseDTO();
        response.setId(postEntity.getId());
        response.setTypeId(postEntity.getTypeId());
        response.setTitle(postEntity.getTitle());
        response.setPrice(postEntity.getPrice());
        response.setCurrency(postEntity.getCurrency());
        response.setPhone(postEntity.getPhone());
        response.setLocation(postEntity.getLocation());
        response.setDescription(postEntity.getDescription());
        response.setAttachLinks(attachService.getUrl(attachIdList));
        response.setProfileId(postEntity.getProfileId());
        response.setLikeCount(postEntity.getLikeCount());
        response.setIsLiked(false);
        response.setCreatedDate(postEntity.getCreatedDate());
        return response;
    }

    public Boolean delete(Long profileId, Long postId) {
        Optional<PostEntity> byId = postRepository.findByIdAndVisibleTrue(postId);
        if (byId.isEmpty()) {
            throw new PostNotFoundException("Post not found");
        }

        PostEntity postEntity = byId.get();
        if (!postEntity.getProfileId().equals(profileId)) {
            throw new PostDeleteNotAllowedException("Post delete not allowed");
        }

        postEntity.setVisible(false);
        postRepository.save(postEntity);
        return true;
    }

    public Page<PostResponseDTO> getAll(Integer page, Integer size, Long profileId) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PostEntity> pageObj = postRepository.findByVisibleTrueOrderByCreatedDateDescLikeCountDesc(pageable);

        List<PostEntity> content = pageObj.getContent();

        List<PostResponseDTO> result = new LinkedList<>();
        for (PostEntity postEntity : content) {
            result.add(toPostResponse(postEntity, profileId));
        }

        return new PageImpl<>(result, pageable, pageObj.getTotalElements());
    }

    public Page<PostResponseDTO> getAllByType(Integer page, Integer size, Long profileId, Type type) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PostEntity> pageObj = postRepository.findByVisibleTrueAndTypeCategoryTypeOrderByCreatedDateDescLikeCountDesc(pageable, type);

        List<PostEntity> content = pageObj.getContent();

        List<PostResponseDTO> result = new LinkedList<>();
        for (PostEntity postEntity : content) {
            result.add(toPostResponse(postEntity, profileId));
        }

        return new PageImpl<>(result, pageable, pageObj.getTotalElements());
    }

    public Page<PostResponseDTO> getProfilePostsByType(Integer page, Integer size, Long profileId, Type type) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PostEntity> pageObj = postRepository.findByVisibleTrueAndProfileIdAndTypeCategoryTypeOrderByCreatedDateDescLikeCountDesc(pageable, profileId, type);

        List<PostEntity> content = pageObj.getContent();

        List<PostResponseDTO> result = new LinkedList<>();
        for (PostEntity postEntity : content) {
            result.add(toPostResponse(postEntity, profileId));
        }

        return new PageImpl<>(result, pageable, pageObj.getTotalElements());
    }

    private PostResponseDTO toPostResponse(PostEntity postEntity, Long profileId) {
        PostResponseDTO response = new PostResponseDTO();
        response.setId(postEntity.getId());
        response.setTypeId(postEntity.getTypeId());
        response.setTitle(postEntity.getTitle());
        response.setPrice(postEntity.getPrice());
        response.setCurrency(postEntity.getCurrency());
        response.setPhone(postEntity.getPhone());
        response.setLocation(postEntity.getLocation());
        response.setDescription(postEntity.getDescription());
        response.setAttachLinks(attachService.getUrl(postPhotoService.getPhotosByPostId(postEntity.getId())));
        response.setProfileId(postEntity.getProfileId());
        response.setLikeCount(postEntity.getLikeCount());
        response.setIsLiked(postLikeService.isLiked(profileId, postEntity.getId()));
        response.setCreatedDate(postEntity.getCreatedDate());
        return response;
    }

    public PostResponseDTO update(Long postId, Long profileId, PostUpdateDTO dto) {
        Optional<PostEntity> byId = postRepository.findById(postId);
        if (byId.isEmpty()) {
            throw new PostNotFoundException("Post not found");
        }

        PostEntity postEntity = byId.get();
        if (!postEntity.getProfileId().equals(profileId)) {
            throw new PostUpdateNotAllowedException("Post update not allowed");
        }


        if (dto.getTypeId() != null) {
            postEntity.setTypeId(dto.getTypeId());
        }

        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            postEntity.setTitle(dto.getTitle());
        }

        if (dto.getPrice() != null && dto.getPrice() >= 0) {
            postEntity.setPrice(dto.getPrice());
        }

        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            postEntity.setPhone(dto.getPhone());
        }

        if (dto.getLocation() != null && !dto.getLocation().isBlank()) {
            postEntity.setLocation(dto.getLocation());
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            postEntity.setDescription(dto.getDescription());
        }

        postRepository.save(postEntity);


        List<String> attachId = dto.getAttachId();
        if (attachId != null && !attachId.isEmpty()) {
            postPhotoService.deletePhotosByPostId(postId);
            for (String attach : attachId) {
                postPhotoService.create(postEntity.getId(), attach);
            }
        }

        return toPostResponse(postEntity, profileId);
    }

    public PostResponseDTO getById(Long id, Long profileId) {
        Optional<PostEntity> byId = postRepository.findById(id);
        if (byId.isEmpty()) {
            throw new PostNotFoundException("Post not found");
        }


        return toPostResponse(byId.get(), profileId);
    }
}