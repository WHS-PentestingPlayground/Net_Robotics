<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 - WHS Dev2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/postList.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <main class="main-content">
        <div class="container">
            <div class="board-container">
                <div class="board-header">
                    <h2 class="board-title">게시판</h2>
                    <a href="${pageContext.request.contextPath}/board/newPost" class="new-post-btn">새 글 작성</a>
                </div>

                <div class="post-list">
                    <c:choose>
                        <c:when test="${empty posts}">
                            <div class="empty-message">
                                아직 작성된 게시글이 없습니다.<br>
                                첫 번째 게시글을 작성해보세요!
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="post" items="${posts}">
                                <div class="post-item">
                                    <a href="${pageContext.request.contextPath}/board/posts/${post.id}" class="post-title">${post.title}</a>
                                    <div class="post-meta">
                                        <span class="post-author">${userNamesByBoardId[post.id]}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </main>
</body>
</html>