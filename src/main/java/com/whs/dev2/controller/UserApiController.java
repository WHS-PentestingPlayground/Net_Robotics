package com.whs.dev2.controller;

import com.whs.dev2.dto.LoginRequestDto;
import com.whs.dev2.dto.RegisterRequestDto;
import com.whs.dev2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//postman이나 프론트 Ajax로 요청할 수 있는 JSON기반 API

@RestController //JSON응답 반환
@RequiredArgsConstructor //생성자 자동 생성
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;

    //회원가입 API
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto dto) {
        userService.register(dto);
        return ResponseEntity.ok().build();
    }

    //로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto, HttpSession session) {
        boolean result = userService.login(dto);
        if (result) {
            session.setAttribute("user", userService.findByUsername(dto.getUsername()));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
