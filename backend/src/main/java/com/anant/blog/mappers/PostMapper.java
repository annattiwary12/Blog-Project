package com.anant.blog.mappers;


import com.anant.blog.domain.CreatePostRequest;
import com.anant.blog.domain.UpdatePostRequest;
import com.anant.blog.domain.dtos.CreatePostRequestDto;
import com.anant.blog.domain.dtos.PostDto;
import com.anant.blog.domain.dtos.UpdatePostRequestDto;
import com.anant.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "status", source = "status")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto post);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto post);
}
