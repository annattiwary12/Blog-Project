package com.anant.blog.services;

import com.anant.blog.domain.CreatePostRequest;
import com.anant.blog.domain.UpdatePostRequest;
import com.anant.blog.domain.entities.Post;
import com.anant.blog.domain.entities.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public  interface PostService  {

    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPost(User user);
    Post  createPost(User  user , CreatePostRequest createPostRequestDto);
    Post updatePost(UUID id , UpdatePostRequest updatePostRequest);
    Post getPost(UUID id);
    void deletePost(UUID id);


}
