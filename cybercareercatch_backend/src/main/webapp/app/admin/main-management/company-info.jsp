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
<title>기업 정보페이지 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/main-management/company-info.css">
</head>

<body class="companyInfo-body">

	<div class="companyInfo-ctr">

		<header class="companyInfo-hdr">

			<div class="companyInfo-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
			</div>

			<nav class="companyInfo-nav">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="companyInfo-logout">로그아웃</a>

		</header>

		<main class="companyInfo-main">

			<aside class="companyInfo-leftbar">

				<div class="companyInfo-left-item">
					<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">질의문 관리</a>
				</div>

				<div class="companyInfo-left-item companyInfo-active">
					<a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc">기업 정보페이지</a>
				</div>

				<div class="companyInfo-left-item">
					<a href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc?jobNumber=1">로드맵 관리</a>
				</div>

			</aside>

			<section class="companyInfo-ct">

				<div class="companyInfo-topbox">
					<h2 class="companyInfo-subtitle">기업 정보 목록</h2>
				</div>

				<form action="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc"
					method="get" class="companyInfo-searchForm">
					<div class="companyInfo-search">
						<input type="text" name="keyword" value="${keyword}" placeholder="기업명 검색">
						<button type="submit">검색</button>
					</div>
				</form>

				<form id="companyDeleteForm"
					action="${pageContext.request.contextPath}/admin/deleteCompanyInfo.adfc"
					method="post">

					<input type="hidden" name="page" value="${page}">
					<input type="hidden" name="keyword" value="${keyword}">

					<div class="companyInfo-tblHead">
						<div class="col1">선택</div>
						<div class="col2">번호</div>
						<div class="col3">기업명</div>
						<div class="col4">상태</div>
						<div class="col5">주소</div>
					</div>

					<c:choose>
						<c:when test="${empty companyList}">
							<div class="companyInfo-empty">등록된 기업 정보페이지가 없습니다.</div>
						</c:when>

						<c:otherwise>
							<c:forEach var="company" items="${companyList}" varStatus="status">
								<div class="companyInfo-row">
									<div class="col1">
										<input type="checkbox" name="companyNumber"
											value="${company.companyNumber}">
									</div>
									<div class="col2">${(page - 1) * 10 + status.index + 1}</div>
									<div class="col3">${company.companyName}</div>
									<div class="col4">${company.companyState}</div>
									<div class="col5">${company.companyAddress}</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<c:if test="${total > 0}">
						<div class="companyInfo-page">

							<c:choose>
								<c:when test="${prev}">
									<a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc?page=${startPage - 1}&keyword=${keyword}">&lt;</a>
								</c:when>
								<c:otherwise>
									<span>&lt;</span>
								</c:otherwise>
							</c:choose>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:choose>
									<c:when test="${i == page}">
										<span class="active">${i}</span>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc?page=${i}&keyword=${keyword}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${next}">
									<a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc?page=${endPage + 1}&keyword=${keyword}">&gt;</a>
								</c:when>
								<c:otherwise>
									<span>&gt;</span>
								</c:otherwise>
							</c:choose>

						</div>
					</c:if>

					<div class="companyInfo-del">
						<button type="submit">삭제</button>
					</div>

				</form>

			</section>

		</main>
	</div>

	<script
		src="${pageContext.request.contextPath}/assets/js/admin/main-management/company-info.js"></script>
</body>

</html>