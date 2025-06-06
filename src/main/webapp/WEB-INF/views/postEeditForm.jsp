<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글 수정 - 화햇 로보틱스</title>
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/postForm.css">
</head>
<body>
<%@ include file="header.jsp" %>

<!-- 로그인 사용자 없을 경우 -->
<c:if test="${empty loginUsername}">
    <script>
        alert("로그인 후 이용 가능합니다.");
        location.href = "/login";
    </script>
</c:if>

<!-- 로그인된 경우만 렌더링 -->
<c:if test="${not empty loginUsername}">
    <div class="container">
        <div class="form-container">
            <h2 class="form-title">글 수정</h2>
            <form action="/board/editPost" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${post.id}" />

                <div class="form-group">
                    <label for="title" class="form-label">제목</label>
                    <input type="text" id="title" name="title" class="form-input" value="${post.title}" required />
                </div>

                <div class="form-group">
                    <label for="content" class="form-label">내용</label>
                    <textarea id="content" name="content" class="form-input" required>${post.content}</textarea>
                </div>

                <div class="form-group">
                    <label for="file" class="form-label">파일 첨부 (선택)</label>
                    <input type="file" id="file" name="file" class="form-input" />
                    <c:if test="${not empty post.fileName}">
                        <p class="mt-1">현재 파일: ${post.fileName}</p>
                    </c:if>
                </div>

                <div class="form-group">
                    <div class="form-label">작성자: <b>${loginUsername}</b></div>
                </div>

                <button type="submit" class="form-button">수정 완료</button>
            </form>
        </div>
    </div>
</c:if>

</body>
</html>
