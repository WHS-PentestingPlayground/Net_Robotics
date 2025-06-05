package com.whs.dev2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.whs.dev2.dto.PostRequestDto;
import com.whs.dev2.dto.PostResponseDto;
import com.whs.dev2.entity.Post;
import com.whs.dev2.entity.User;
import com.whs.dev2.jwt.JwtUtil;
import com.whs.dev2.service.PostService;
import com.whs.dev2.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final String UPLOAD_DIR = "/tmp/uploads/";

    //게시글 목록 조회
    @GetMapping("/api/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostResponseDto> responseList = posts.stream()
                .map(PostResponseDto::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    //게시글 단일 조회
    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        Post post = postService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new PostResponseDto(post));
    }

    //게시글 작성(회원만)
    @PostMapping("/api/post")
    public ResponseEntity<?> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("password") String password,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestHeader("Authorization") String authHeader) {
        
        try {
            // JWT 토큰 검증
            String token = authHeader.substring(7);
            String username = jwtUtil.getUsernameFromToken(token);
            User user = userService.findByUsername(username);

            // 게시글 저장
            PostRequestDto dto = new PostRequestDto();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setAuthor(author);
            dto.setPassword(password);
            
            Post post = postService.createPost(dto, user);

            // 파일 업로드 처리
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                
                // 파일명 보안 검사
                if (originalFilename == null || !originalFilename.matches("^[A-Za-z0-9_.-]+$")) {
                    return ResponseEntity.badRequest().body("허용되지 않은 파일명입니다.");
                }
                
                // 파일 확장자 검사 (JSP 파일 업로드 방지)
                if (originalFilename.toLowerCase().endsWith(".jsp")) {
                    return ResponseEntity.badRequest().body("JSP 파일은 업로드할 수 없습니다.");
                }

                // 파일 저장
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                File destFile = new File(UPLOAD_DIR + originalFilename);
                file.transferTo(destFile);

                // 게시글에 파일 정보 업데이트
                post.setFileName(originalFilename);
                postService.updatePost(post);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(new PostResponseDto(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 작성 실패: " + e.getMessage());
        }
    }

    //게시글 수정(회원만)
    @PutMapping("/api/post/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id,
                                        @RequestBody PostRequestDto dto,
                                        @RequestHeader("Authorization") String authHeader) {
        User user = authenticate(authHeader);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");

        boolean success = postService.update(id, dto, user);
        return success ? ResponseEntity.ok("수정 완료") : ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한 없음");
    }

    //게시글 삭제(회원만)
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id,
                                        @RequestHeader("Authorization") String authHeader) {
        User user = authenticate(authHeader);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");

        boolean success = postService.delete(id, user);
        return success ? ResponseEntity.ok("삭제 완료") : ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한 없음");
    }

    // 공통 인증 메서드
    private User authenticate(String authHeader) {
        //Authorization 헤더가 없거나 "Bearer "로 시작하지 않으면 → 인증되지 않은 사용자로 간주 → null 리턴
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        //"Bearer " 다음부터 토큰 내용만 잘라냄
        String token = authHeader.substring(7);
        //토큰 유효성 확인 후 username만 뽑아내기
        String username = jwtUtil.validateAndExtractUsername(token);
        if (username == null) return null;
        return userService.findByUsername(username);
    }
}
