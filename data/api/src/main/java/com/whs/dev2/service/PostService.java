package com.whs.dev2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.whs.dev2.dto.PostRequestDto;
import com.whs.dev2.entity.Post;
import com.whs.dev2.entity.User;
import com.whs.dev2.repository.PostRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 전체 게시글 목록 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .filter(post -> !post.isDeleted()) // deletedAt == null인 게시글만 반환
                .toList();
    }

    // 게시글 저장 (작성)
    public Post createPost(PostRequestDto dto, User user) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        post.setPassword(dto.getPassword());
        post.setUser(user);
        return postRepository.save(post);
    }

    // 게시글 단일 조회
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    // 게시글 수정
    public boolean update(Long id, PostRequestDto dto, User user) {
        Post post = findById(id);
        if (!post.getUser().getId().equals(user.getId())) return false;
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post);
        return true;
    }

    // 게시글 삭제
    public boolean delete(Long id, User user) {
        Post post = findById(id);
        if (!post.getUser().getId().equals(user.getId())) return false;
        //postRepository.delete(post); hard delete방식(위험)
        post.markDeleted();
        postRepository.save(post);// 실제 삭제 대신 플래그만 저장(Soft delete 적용
        return true;
    }

    // 게시글 업데이트
    public void updatePost(Post post) {
        postRepository.save(post);
    }
}
