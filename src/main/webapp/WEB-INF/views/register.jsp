<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 - 화햇 로보틱스</title>
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/register.css">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="login-section">
    <div class="login-container">
        <h2 class="form-title">회원가입</h2>
        <% if (request.getAttribute("error") != null) { %>
        <div class="error-message">${error}</div>
        <% } %>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username" class="form-label">아이디</label>
                <input type="text" id="username" name="username" class="form-input" required>
            </div>
            <div class="form-group">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" id="password" name="password" class="form-input" required>
                <div class="password-requirements">
                    영문, 숫자, 특수문자를 포함한 8자 이상
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="form-label">이메일</label>
                <input type="email" id="email" name="email" class="form-input" required>
            </div>
            <button type="submit" class="form-button">회원가입</button>
        </form>
        <a href="/login" class="form-link">로그인으로 돌아가기</a>
    </div>
</div>
</body>
</html>
