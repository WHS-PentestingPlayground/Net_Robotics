package com.whs.dev2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.whs.dev2.entity.Post;
import com.whs.dev2.service.PostService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class PostPageController {

    private final PostService postService;

    @GetMapping("/posts")
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "postList";
    }

    @GetMapping("/post/new")
    public String showPostForm() {
        return "postForm"; // → /WEB-INF/views/postForm.jsp
    }

    @PostMapping("/post")
    public String createPost(@RequestParam String title,
                             @RequestParam String content,
                             @RequestParam String author,
                             @RequestParam String password) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setPassword(password);

        return "redirect:/posts";
    }
}