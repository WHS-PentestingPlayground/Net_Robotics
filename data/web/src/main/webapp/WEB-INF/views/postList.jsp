<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>게시글 목록</title>
</head>
<body>
<h2>📋 게시글 목록</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>
    <c:forEach var="post" items="${posts}">
        <tr>
            <td>${post.id}</td>
            <td><a href="/post/${post.id}">${post.title}</a></td>
            <td>${post.author}</td>
            <td>${post.createdAt}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="/post/new">✏️ 새 글 쓰기</a></p>
</body>
</html>
