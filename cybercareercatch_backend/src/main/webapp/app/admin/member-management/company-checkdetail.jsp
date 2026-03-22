<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
/*
 * 로그인하지 않은 관리자는
 * 기업회원 승인/반려 상세 페이지에 접근할 수 없도록 막는다.
 */
if (session.getAttribute("adminNumber") == null) {
	response.sendRedirect(request.getContextPath() + "/admin/login.adfc");
	return;
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>기업회원 승인 / 반려 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/member-management/company-checkdetail.css">
</head>

<body class="company-checkdetail-body">

<div class="company-checkdetail-container">

	<header class="company-checkdetail-header">

		<div class="company-checkdetail-title">
			<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
		</div>

		<nav class="company-checkdetail-menu">
			<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
			<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
			<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
		</nav>

		<a href="${pageContext.request.contextPath}/admin/logout.adfc" class="company-checkdetail-logout">로그아웃</a>

	</header>

	<main class="company-checkdetail-main">

		<!-- 왼쪽 사이드바 -->
		<aside class="company-checkdetail-sidebar">

			<div class="company-checkdetail-sidebar-item">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원 정보 조회</a>
			</div>

			<div class="company-checkdetail-sidebar-item company-checkdetail-active">
				<a href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원 승인, 반려</a>
			</div>

			<div class="company-checkdetail-sidebar-item">
				<a href="${pageContext.request.contextPath}/app/admin/member-management/recruiter-info.jsp">기업회원 정보 조회</a>
			</div>
				<div class="company-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문
						답변 조회</a>
				</div>
		</aside>

		<!-- 오른쪽 콘텐츠 -->
		<section class="company-checkdetail-content">

			<div class="company-checkdetail-top">
				<h2 class="company-checkdetail-page-title">기업회원 상세 정보</h2>
				<a href="${pageContext.request.contextPath}/admin/companyCheck.adfc" class="company-checkdetail-back-btn">목록으로</a>
			</div>

			<div class="company-checkdetail-box">

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">기업번호</div>
					<div class="company-checkdetail-value">${companyDetail.companyNumber}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">기업명</div>
					<div class="company-checkdetail-value">${companyDetail.companyName}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">사업자등록번호</div>
					<div class="company-checkdetail-value">${companyDetail.companyBrn}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">상태</div>
					<div class="company-checkdetail-value">${companyDetail.companyState}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">주소</div>
					<div class="company-checkdetail-value">${companyDetail.companyAddress}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">대표자명</div>
					<div class="company-checkdetail-value">${companyDetail.compCeoName}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">설립연도</div>
					<div class="company-checkdetail-value">${companyDetail.compFndYear}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">사원수</div>
					<div class="company-checkdetail-value">${companyDetail.compEmplCnt}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">매출액</div>
					<div class="company-checkdetail-value">${companyDetail.compRev}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">자본금</div>
					<div class="company-checkdetail-value">${companyDetail.compCap}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">기업형태</div>
					<div class="company-checkdetail-value">${companyDetail.compType}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">한줄소개</div>
					<div class="company-checkdetail-value">${companyDetail.compSummary}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">대표기술</div>
					<div class="company-checkdetail-value">${companyDetail.compTech}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">주요사업</div>
					<div class="company-checkdetail-value">${companyDetail.compMainBiz}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">기업소개</div>
					<div class="company-checkdetail-value company-checkdetail-multi">${companyDetail.compInfo}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">서비스 운영 이력</div>
					<div class="company-checkdetail-value company-checkdetail-multi">${companyDetail.compSvcHist}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">인재상</div>
					<div class="company-checkdetail-value company-checkdetail-multi">${companyDetail.jobPostProfile}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">채용 공고</div>
					<div class="company-checkdetail-value company-checkdetail-multi">${companyDetail.jobPostContent}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">채용 절차</div>
					<div class="company-checkdetail-value company-checkdetail-multi">${companyDetail.jobPostProcess}</div>
				</div>

				<div class="company-checkdetail-row">
					<div class="company-checkdetail-label">지원 방법</div>
					<div class="company-checkdetail-value company-checkdetail-multi">${companyDetail.jobPostMethod}</div>
				</div>

			</div>

			<!-- 승인 / 반려 버튼 -->
			<form action="${pageContext.request.contextPath}/admin/updateCompanyState.adfc" method="post" class="company-checkdetail-btn-wrap">
				<input type="hidden" name="companyNumber" value="${companyDetail.companyNumber}">

				<button type="submit"
						name="companyState"
						value="승인"
						class="company-checkdetail-btn company-checkdetail-approve-btn">
					승인
				</button>

				<button type="submit"
						name="companyState"
						value="반려"
						class="company-checkdetail-btn company-checkdetail-reject-btn">
					반려
				</button>
			</form>

		</section>

	</main>

</div>
</body>
</html>