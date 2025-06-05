package com.whs.dev2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.whs.dev2.dto.UserRequestDto;
import com.whs.dev2.service.UserService;

@Controller
public class UserPageController {

    private final UserService userService;

    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam(required = false) String email, Model model) {
        UserRequestDto dto = new UserRequestDto();
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setEmail(email);

        try {
            userService.register(dto);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        boolean result = userService.authenticate(username, password);
        if (result) {
            model.addAttribute("username", username);
            return "redirect:/";
        } else {
            model.addAttribute("error", "로그인 실패");
            return "login";
        }
    }
} 