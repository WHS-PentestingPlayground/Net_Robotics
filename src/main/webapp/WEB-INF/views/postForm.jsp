<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>새 글 작성 - 화햇 로보틱스</title>
    <link rel="stylesheet" href="/css/header.css">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/postForm.css">
</head>
<body>
<%@ include file="header.jsp" %>

<!-- 로그인 사용자 없을 경우: JS로 추가 방어 -->
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
            <h2 class="form-title">새 글 작성</h2>
            <form action="/board/newPost" method="post">
                <div class="form-group">
                    <label for="title" class="form-label">제목</label>
                    <input type="text" id="title" name="title" class="form-input" required>
                </div>

                <div class="form-group">
                    <label for="content" class="form-label">내용</label>
                    <textarea id="content" name="content" class="form-input" required></textarea>
                </div>

                <input type="hidden" id="userId" name="userId" value="${loginUserId}" />

                <div class="form-group">
                    <div class="form-label">작성자: <b>${loginUsername}</b></div>
                </div>

                <button type="submit" class="form-button">등록</button>
            </form>
        </div>
    </div>
</c:if>

</body>
</html>
