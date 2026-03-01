package com.anant.blog.services.impl;

import com.anant.blog.domain.CreatePostRequest;
import com.anant.blog.domain.PostStatus;
import com.anant.blog.domain.UpdatePostRequest;
import com.anant.blog.domain.entities.Category;
import com.anant.blog.domain.entities.Post;
import com.anant.blog.domain.entities.Tag;
import com.anant.blog.domain.entities.User;
import com.anant.blog.repositories.PostRepository;
import com.anant.blog.services.CategoryService;
import com.anant.blog.services.PostService;
import com.anant.blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;

@Service
@RequiredArgsConstructor
public class PostServiceImpl  implements PostService {


    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private static final int WORD_PER_MINUTE = 200;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);

            return postRepository.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag

            );
        }
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            postRepository.findAllByStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }

        if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(
                    PostStatus.PUBLISHED, tag
            );
        }
        return postRepository.findAllByStatus(PostStatus.PUBLISHED);

    }

    @Override
    public List<Post> getDraftPost(User user) {
        return postRepository.findAllAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest createPostRequestDto) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequestDto.getTitle());
        newPost.setContent(createPostRequestDto.getContent());
        newPost.setStatus(createPostRequestDto.getStatus());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateReadingTime(createPostRequestDto.getContent()));

        Category category = categoryService.getCategoryById(createPostRequestDto.getCategoryId());
        newPost.setCategory(category);

        Set<UUID> tagIds = createPostRequestDto.getTagIds();
        List<Tag> tags = tagService.getTagByIds(tagIds);
        newPost.setTags(new HashSet<>(tags));


        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest) {

        Post existingPost = postRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Post does not exist with id " + id)
                );

        existingPost.setTitle(updatePostRequest.getTitle());

        String postContent = updatePostRequest.getContent();
        existingPost.setContent(postContent);
        existingPost.setStatus(updatePostRequest.getStatus());
        existingPost.setReadingTime(calculateReadingTime(postContent));

        // Update category if changed
        UUID updateCategoryId = updatePostRequest.getCategoryId();

        if (!existingPost.getCategory().getId().equals(updateCategoryId)) {
            Category newCategory = categoryService.getCategoryById(updateCategoryId);
            existingPost.setCategory(newCategory);
        }

        // Update tags if changed
        Set<UUID> existingTagIds = existingPost.getTags()
                .stream()
                .map(Tag::getId)
                .collect(java.util.stream.Collectors.toSet());

        Set<UUID> updatedTagIds = updatePostRequest.getTagIds();

        if (!existingTagIds.equals(updatedTagIds)) {
            List<Tag> newTags = tagService.getTagByIds(updatedTagIds);
            existingPost.setTags(new HashSet<>(newTags));
        }

        return postRepository.save(existingPost);
    }

    @Override
    public Post getPost(UUID id) {
      return   postRepository.findById(id).orElseThrow(() ->
        new EntityNotFoundException("Post does not exist with id " + id));
    }

    @Override
    public void  deletePost(UUID id) {
        Post post  = getPost(id);
        postRepository.delete(post);
    }

    private Integer calculateReadingTime(String content){
        if(content == null || content.isEmpty()){
            return 0;
        }
          int wordCount = content.trim().split("\\s+").length;
         return ( int) Math.ceil((double) wordCount / WORD_PER_MINUTE);
    }
}


