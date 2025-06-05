package com.whs.dev2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.whs.dev2.entity.common.BaseEntity;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fileName;  // 첨부 파일 이름

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //user_id와 게시글 id가 같을 때에만 수정 가능하도록 할 것임
}
