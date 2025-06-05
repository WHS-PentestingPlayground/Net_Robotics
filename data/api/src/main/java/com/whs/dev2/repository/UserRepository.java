package com.whs.dev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whs.dev2.entity.User;

import java.util.Optional;

//Spring JPA에서 제공하는 기본 CRUD 기능이 구현된 JpaRepository 상속 받은 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
} //매개변수 User 엔티티, User엔티티의 기본키 id의 타입 Long
