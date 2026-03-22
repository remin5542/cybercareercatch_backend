<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
if (session.getAttribute("adminNumber") == null) {
	response.sendRedirect(request.getContextPath() + "/admin/login.adfc");
	return;
}
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>질의문 답변 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/job-checkdetail.css">
</head>

<body class="job-checkdetail-body">

	<div class="job-checkdetail-container">

		<!-- ================= 상단 헤더 ================= -->
		<header class="job-checkdetail-header">

			<div class="job-checkdetail-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
			</div>

			<nav class="job-checkdetail-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="job-checkdetail-logout">로그아웃</a>

		</header>

		<!-- ================= 메인 영역 ================= -->
		<main class="job-checkdetail-main">

			<!-- 왼쪽 사이드바 -->
			<aside class="job-checkdetail-sidebar">

				<div class="job-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원 정보 조회</a>
				</div>

				<div class="job-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원 승인, 반려</a>
				</div>

				<div class="job-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원 정보 조회</a>
				</div>

				<div class="job-checkdetail-sidebar-item job-checkdetail-active">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문 답변 조회</a>
				</div>

			</aside>

			<!-- 오른쪽 콘텐츠 -->
			<section class="job-checkdetail-content">

				<div class="job-checkdetail-top">
					<h2>질의문 답변 상세</h2>
				</div>

				<form
					action="${pageContext.request.contextPath}/admin/judgeJobResult.adfc"
					method="post">

					<input type="hidden" name="jobResultNumber"
						value="${detailInfo.jobResultNumber}">

					<!-- 사용자 정보 -->
					<div class="job-checkdetail-userbox">
						<div><strong>아이디:</strong> ${detailInfo.userId}</div>
						<div><strong>이름:</strong> ${detailInfo.userName}</div>
						<div><strong>상태:</strong> ${detailInfo.jobResultStatus}</div>
						<div><strong>제출일:</strong> ${detailInfo.jobResultCreatedDate}</div>
					</div>

					<!-- 질문 / 답변 목록 -->
					<c:forEach var="detail" items="${detailList}">
						<div class="job-checkdetail-question">
							<div class="job-checkdetail-qtitle">질문 ${detail.jobQuestionNumber}</div>
							<div class="job-checkdetail-qcontent">${detail.jobQuestionContentSnapshot}</div>
							<div class="job-checkdetail-acontent">${detail.answerContent}</div>
						</div>
					</c:forEach>

					<!-- 직군 선택 -->
					<div class="job-checkdetail-jobbox">
						<div class="job-checkdetail-jobtitle">직군 선택</div>

						<label class="job-checkdetail-radio">
							<input type="radio" name="jobNumber" value="1"> 보안컨설팅
						</label>

						<label class="job-checkdetail-radio">
							<input type="radio" name="jobNumber" value="2"> 시스템/네트워크 엔지니어
						</label>

						<label class="job-checkdetail-radio">
							<input type="radio" name="jobNumber" value="3"> 보안관제
						</label>

						<label class="job-checkdetail-radio">
							<input type="radio" name="jobNumber" value="4"> 침해사고/디지털포렌식
						</label>
					</div>

					<!-- 하단 버튼 -->
					<div class="job-checkdetail-btnbox">
						<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc"
							class="job-checkdetail-back">목록</a>
						<button type="submit" class="job-checkdetail-save">저장</button>
					</div>

				</form>

			</section>

		</main>

	</div>

	<script
		src="${pageContext.request.contextPath}/assets/js/admin/member-management/job-checkdetail.js"></script>
</body>

</html>