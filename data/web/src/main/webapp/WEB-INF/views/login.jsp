<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <title>로그인 - 화햇 로보틱스</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<div class="container container-login">
    <div class="card card-login">
        <h2 class="text-center">로그인</h2>
        <% if (request.getParameter("error") != null) { %>
            <div class="text-error">아이디 또는 비밀번호가 올바르지 않습니다.</div>
        <% } %>
        <form action="/login" method="post" class="form-content">
            <div class="form-group">
                <label for="username">아이디</label>
                <input type="text" id="username" name="username" required class="input-full">
            </div>

            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" required class="input-full">
            </div>

            <div class="form-actions">
                <button type="submit" class="main-btn btn-full">로그인</button>
                <a href="/register" class="sub-btn btn-full">회원가입</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
