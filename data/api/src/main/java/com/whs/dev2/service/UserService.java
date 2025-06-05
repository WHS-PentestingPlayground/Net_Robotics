package com.whs.dev2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.whs.dev2.dto.UserRequestDto;
import com.whs.dev2.entity.User;
import com.whs.dev2.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입 처리 메서드
    public void register(UserRequestDto dto) {
        //중복 처리
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        
        //새로운 User 엔티티 생성
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); //pw 평문 저장 => 추후 BCrypt로 암호화 기능 추가 필요
        user.setEmail(dto.getEmail());
        
        //DB에 튜플 저장
        userRepository.save(user);
    }

    //로그인 인증 처리 메서드
    public boolean authenticate(String username, String password) {
        //id, pw 존재하면 true 반환
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    //사용자 조회 메서드
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

}
