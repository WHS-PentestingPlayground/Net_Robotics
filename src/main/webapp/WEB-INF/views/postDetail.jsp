<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/postDetail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<%@ include file="header.jsp" %>

<main class="main-content">
    <div class="post-detail-container">
        <div class="post-detail-header">
            <h1 class="post-detail-title">${board.title}</h1>
            <div class="post-detail-meta">
                <span class="post-detail-author">${username}</span> /
                <span>${board.createdAt}</span>
            </div>
        </div>

        <div class="post-detail-content">
            <c:if test="${not empty board.fileName}">
                <div class="post-detail-file">
                    <strong>첨부파일:</strong>
                    <a href="${pageContext.request.contextPath}/uploads/${board.fileName}" download>
                        <c:out value="${fn:substringAfter(board.fileName, '_')}" />
                    </a>
                </div>
            </c:if>

            <div>
                <p>${board.content}</p>
            </div>
        </div>

        <div class="post-detail-actions">
            <c:if test="${loginUserId == board.user.id}">
                <form action="${pageContext.request.contextPath}/board/deletePost" method="post">
                    <input type="hidden" name="id" value="${board.id}" />
                    <button type="submit" class="delete-btn">삭제</button>
                </form>

                <c:choose>
                    <c:when test="${board.notice}">
                        <a href="${pageContext.request.contextPath}/board/editNotice?id=${board.id}" class="edit-btn">수정</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/board/editPost?id=${board.id}" class="edit-btn">수정</a>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <a href="${pageContext.request.contextPath}/board/posts" class="back-btn">목록으로</a>
        </div>
    </div>
</main>
</body>
</html>
