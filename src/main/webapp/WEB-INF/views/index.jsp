<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>WHS Dev2 - 메인</title>
    <link rel="stylesheet" href="/css/header.css" />
    <link rel="stylesheet" href="/css/index.css" />
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<main class="main-content">
    <div class="hero-section">
        <h1 class="hero-title">WHS Dev2</h1>
        <p class="hero-subtitle">혁신적인 개발 플랫폼에 오신 것을 환영합니다</p>
        
        <div class="cta-buttons">
            <a href="/board/posts" class="cta-button primary">
                <span class="button-icon">📝</span>
                게시판
            </a>
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <a href="/login" class="cta-button secondary">
                        <span class="button-icon">🔑</span>
                        로그인
                    </a>
                    <a href="/register" class="cta-button secondary">
                        <span class="button-icon">👤</span>
                        회원가입
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="/board/newPost" class="cta-button secondary">
                        <span class="button-icon">✏️</span>
                        글쓰기
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="features-section">
        <div class="feature-card">
            <div class="feature-icon">🚀</div>
            <h3>빠른 개발</h3>
            <p>최신 기술을 활용한 빠른 개발 환경</p>
        </div>
        <div class="feature-card">
            <div class="feature-icon">🔒</div>
            <h3>안전한 보안</h3>
            <p>강력한 보안 시스템으로 안전한 서비스</p>
        </div>
        <div class="feature-card">
            <div class="feature-icon">💡</div>
            <h3>혁신적인 기능</h3>
            <p>사용자 중심의 혁신적인 기능 제공</p>
        </div>
    </div>
</main>
</body>
</html>
