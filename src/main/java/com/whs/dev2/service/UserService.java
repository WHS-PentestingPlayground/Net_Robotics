package com.whs.dev2.service;

import com.whs.dev2.dto.LoginRequestDto;
import com.whs.dev2.dto.RegisterRequestDto;
import com.whs.dev2.entity.User;
import com.whs.dev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자명입니다.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
    }

    public boolean login(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElse(null);
        
        if (user == null) {
            return false;
        }

        return user.getPassword().equals(dto.getPassword());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}

