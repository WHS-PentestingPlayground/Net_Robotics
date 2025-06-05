<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/postDetail.css">
</head>
<body>
<%@ include file="header.jsp" %>

<main class="main-content">
    <div class="container container-post">
        <div class="card card-post mb-2">
            <h1 class="post-detail-title">${board.title}</h1>
            <p class="post-detail-meta">
                작성자: ${username} / ${board.createdAt}
            </p>

            <div class="post-detail-content">
                <c:if test="${not empty attachedFiles}">
                    <div class="attached-files">
                        <h4>첨부 파일:</h4>
                        <c:forEach var="file" items="${attachedFiles}">
                            <div class="attached-file-item">
                                <img src="${pageContext.request.contextPath}/uploads/${file.filePath}" alt="첨부 이미지" class="img-attach" />
                                <p>${file.originalFileName}</p>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <p>${board.content}</p>
            </div>

            <div class="post-detail-actions mt-2">
                <c:if test="${loginUserId == board.user.id}">
                    <form action="${pageContext.request.contextPath}/board/deletePost" method="post" class="d-inline">
                        <input type="hidden" name="id" value="${board.id}" />
                        <button type="submit" class="btn btn-danger">삭제</button>
                    </form>
                    <c:choose>
                        <c:when test="${board.notice}">
                            <a href="${pageContext.request.contextPath}/board/editNotice?id=${board.id}" class="btn btn-secondary ml-8">수정</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/board/editPost?id=${board.id}" class="btn btn-secondary ml-8">수정</a>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <a href="${pageContext.request.contextPath}/board/posts" class="btn btn-primary">목록으로</a>
            </div>
        </div>
    </div>
</main>
</body>
</html> 