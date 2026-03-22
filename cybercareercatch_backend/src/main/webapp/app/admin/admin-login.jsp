<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>관리자 로그인 페이지</title>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/admin/admin-login.css">
</head>

<body>

	<div class="container">

		<div class="logo">
			CyberCareerCatch <span>Admin</span>
		</div>

		<form class="login-box"
			action="${pageContext.request.contextPath}/admin/loginOk.adfc"
			method="post">

			<div class="form-group">
				<label for="adminId">아이디</label>
				<input type="text"
					   id="adminId"
					   name="adminId"
					   value="${param.adminId}"
					   placeholder="Admin ID">
			</div>

			<div class="form-group">
				<label for="adminPw">비밀번호</label>
				<input type="password"
					   id="adminPw"
					   name="adminPw"
					   placeholder="Admin PW">
			</div>

			<div class="form-group remember-row" style="display:flex; align-items:center; gap:8px; margin-top:-4px;">
				<input type="checkbox" id="remember" name="remember">
				<label for="remember" style="margin:0;">아이디 저장</label>
			</div>

			<%
				String login = request.getParameter("login");
			%>

			<% if ("fail".equals(login)) { %>
				<div class="error" style="margin-top:12px; color:red;">
					아이디 또는 비밀번호가 올바르지 않습니다.
				</div>
			<% } %>

			<button type="submit" class="login-btn">로그인</button>

		</form>

		<div class="footer">
			© 2026 CyberCareerCatch Company. All rights reserved.
		</div>

	</div>

</body>
</html>