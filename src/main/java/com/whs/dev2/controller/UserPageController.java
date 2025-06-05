package com.whs.dev2.controller;

import com.whs.dev2.dto.LoginRequestDto;
import com.whs.dev2.dto.RegisterRequestDto;
import com.whs.dev2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestDto dto) {
        userService.register(dto);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto dto, HttpSession session) {
        boolean result = userService.login(dto);
        if (result) {
            session.setAttribute("user", userService.findByUsername(dto.getUsername()));
            return "redirect:/";
        }
        return "redirect:/login?error=true";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
