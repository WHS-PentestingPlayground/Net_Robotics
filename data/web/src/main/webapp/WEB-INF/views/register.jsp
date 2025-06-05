<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<h2>회원가입 페이지</h2>
<!-- 에러 메시지 출력 -->
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>

<form method="post" action="/register">
    <label>아이디: <input type="text" name="username" required></label><br>
    <label>비밀번호: <input type="password" name="password" required></label><br>
    <label>이메일: <input type="email" name="email"></label><br>
    <button type="submit">회원가입</button>
</form>
</body>
</html>
