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
<title>기업회원 정보 조회</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/recruiter-info.css">
</head>

<body class="recruiter-info-body">

	<div class="recruiter-info-container">

		<header class="recruiter-info-header">

			<div class="recruiter-info-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="recruiter-info-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="recruiter-info-logout">로그아웃</a>

		</header>

		<main class="recruiter-info-main">

			<aside class="recruiter-info-sidebar">

				<div class="recruiter-info-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원
						정보 조회</a>
				</div>

				<div class="recruiter-info-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원
						승인, 반려</a>
				</div>

				<div class="recruiter-info-sidebar-item recruiter-info-active">
					<a
						href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원
						정보 조회</a>
				</div>
				<div class="recruiter-info-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문
						답변 조회</a>
				</div>
			</aside>

			<section class="recruiter-info-content">

				<h2 class="recruiter-info-content-title">기업회원 정보 조회</h2>

				<form
					action="${pageContext.request.contextPath}/admin/recruiterInfo.adfc"
					method="get" class="recruiter-info-search-box">
					<span class="recruiter-info-search-label">- 검색</span> <select
						class="recruiter-info-search-select" name="keywordType">
						<option value="userName"
							${keywordType eq 'userName' ? 'selected' : ''}>이름</option>
						<option value="userId"
							${keywordType eq 'userId' ? 'selected' : ''}>아이디</option>
					</select> <input type="text" class="recruiter-info-search-input"
						name="keyword" value="${keyword}" placeholder="검색어 입력">

					<button type="submit" class="recruiter-info-search-btn">검색</button>
				</form>

				<form
					action="${pageContext.request.contextPath}/admin/deleteRecruiter.adfc"
					method="post" onsubmit="return validateRecruiterDelete();">

					<input type="hidden" name="page" value="${page}">

					<div class="recruiter-info-table">

						<div class="recruiter-info-table-header">
							<div class="recruiter-info-col recruiter-info-col-check">선택</div>
							<div class="recruiter-info-col recruiter-info-col-num">번호</div>
							<div class="recruiter-info-col recruiter-info-col-id">아이디</div>
							<div class="recruiter-info-col recruiter-info-col-name">이름</div>
							<div class="recruiter-info-col recruiter-info-col-manage">관리</div>
						</div>

						<c:choose>
							<c:when test="${empty recruiterList}">
								<div class="recruiter-info-empty">등록된 기업회원이 없습니다.</div>
							</c:when>

							<c:otherwise>
								<c:forEach var="recruiter" items="${recruiterList}"
									varStatus="status">
									<div class="recruiter-info-row-wrap">
										<div class="recruiter-info-col recruiter-info-col-check">
											<input type="checkbox" name="userNumber"
												value="${recruiter.userNumber}">
										</div>

										<a
											href="${pageContext.request.contextPath}/admin/recruiterInfoDetail.adfc?userNumber=${recruiter.userNumber}"
											class="recruiter-info-row-link">
											<div class="recruiter-info-row">
												<div class="recruiter-info-col recruiter-info-col-num">${(page - 1) * rowCount + status.index + 1}</div>
												<div class="recruiter-info-col recruiter-info-col-id">${recruiter.userId}</div>
												<div class="recruiter-info-col recruiter-info-col-name">${recruiter.userName}</div>
												<div class="recruiter-info-col recruiter-info-col-manage">상세조회</div>
											</div>
										</a>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>

						<div class="recruiter-info-bottom">
							<button type="submit" class="recruiter-info-delete-btn">선택
								삭제</button>
						</div>

						<c:if test="${total > 0}">
							<div class="recruiter-info-pagination">

								<c:choose>
									<c:when test="${prev}">
										<a
											href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc?page=${startPage - 1}&keywordType=${keywordType}&keyword=${keyword}">&lt;</a>
									</c:when>
									<c:otherwise>
										<span>&lt;</span>
									</c:otherwise>
								</c:choose>

								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:choose>
										<c:when test="${i == page}">
											<a class="recruiter-info-page recruiter-info-page-active">${i}</a>
										</c:when>
										<c:otherwise>
											<a class="recruiter-info-page"
												href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc?page=${i}&keywordType=${keywordType}&keyword=${keyword}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${next}">
										<a
											href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc?page=${endPage + 1}&keywordType=${keywordType}&keyword=${keyword}">&gt;</a>
									</c:when>
									<c:otherwise>
										<span>&gt;</span>
									</c:otherwise>
								</c:choose>

							</div>
						</c:if>

					</div>
				</form>

			</section>

		</main>

	</div>

	<script>
		function validateRecruiterDelete() {
			const checkedList = document
					.querySelectorAll("input[name='userNumber']:checked");

			if (checkedList.length === 0) {
				alert("삭제할 기업회원을 선택하세요.");
				return false;
			}

			return confirm("선택한 기업회원 계정만 삭제하시겠습니까?");
		}
	</script>

</body>
</html>