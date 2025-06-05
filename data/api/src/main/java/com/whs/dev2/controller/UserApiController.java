package com.whs.dev2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.whs.dev2.dto.UserRequestDto;
import com.whs.dev2.jwt.JwtUtil;
import com.whs.dev2.service.UserService;

//postman이나 프론트 Ajax로 요청할 수 있는 JSON기반 API

@RestController //JSON응답 반환
@RequiredArgsConstructor //생성자 자동 생성
public class UserApiController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    //회원가입 API
    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto dto) {
        try {
            userService.register(dto);//JSON으로 받은 dto 요청 service로 넘겨서 처리
            return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공");//200
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패: " + e.getMessage());//400
        }
    }

    //로그인 API
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto dto) {
        try {
            boolean result = userService.authenticate(dto.getUsername(), dto.getPassword());
            if (result) {//id, pw 인증 성공시 jwt 토큰 반환
                String token = jwtUtil.generateToken(dto.getUsername());
                return ResponseEntity.status(HttpStatus.OK).body(token);//200 JSON으로 토큰 반환(프론트 단에서 받아서 이후 요청마다 Authorization 헤더에 실어 보냄)
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 잘못된 정보");//401
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 오류 발생");//500
        }
    }
}
