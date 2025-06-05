<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <title>게시글 작성 - 화햇 로보틱스</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<div class="container container-form">
    <div class="card card-form">
        <h2 class="text-center">✏️ 게시글 작성</h2>
        <form method="post" action="/post" enctype="multipart/form-data" class="form-content">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" required class="input-full">
            </div>

            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="content" name="content" required class="input-full textarea-content"></textarea>
            </div>

            <div class="form-group">
                <label for="author">작성자</label>
                <input type="text" id="author" name="author" required class="input-full">
            </div>

            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" required class="input-full">
            </div>

            <div class="form-group">
                <label for="file">파일 첨부</label>
                <input type="file" id="file" name="file" class="input-full">
            </div>

            <div class="form-actions">
                <button type="submit" class="main-btn btn-full">등록하기</button>
                <a href="/posts" class="sub-btn btn-full">목록으로</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>