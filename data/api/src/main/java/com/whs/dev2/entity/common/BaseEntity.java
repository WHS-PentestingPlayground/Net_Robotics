package com.whs.dev2.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @CreationTimestamp // INSERT 시 자동 생성
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // UPDATE 시 자동 갱신
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Soft delete 처리를 위한 필드
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // 삭제 시 호출: 현재 시간으로 deleted_at 설정
    public void markDeleted() {
        this.deletedAt = LocalDateTime.now(); // ← Timestamp.from(Instant.now()) 대신
    }

    // 삭제 여부 확인
    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}