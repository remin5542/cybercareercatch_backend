<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>기업 QnA 목록</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/qna/qna-list.css" />
</head>

<body>
  <!--
    이 페이지는 비회원 접근 불가 페이지입니다.
    즉 로그인한 일반회원만 접근 가능하도록 막습니다.

    현재는 sessionScope.userNumber 를 로그인 체크 기준으로 가정했습니다.
    만약 실제 로그인 세션명이 다르면 아래 부분만 바꾸면 됩니다.
  -->
  <c:choose>
    <c:when test="${not empty sessionScope.userNumber}">

      <!--
        Controller가 나중에 넘겨주면 좋은 값 이름

        1. qnaNoticeList : 기업 QnA 공지 목록 (List<NoticeDTO>)
           -> 관리자가 관리자 페이지에서 작성한 공지
           -> 사용자 화면에서는 조회만

        2. qnaList : 기업 QnA 일반글 목록 (List<QnaListDTO>)
           -> 일반회원이 작성한 질문글 목록

        3. pageInfo : 페이지 정보 (PostPageDTO)

        4. companyList : 기업 선택용 목록 (List<CompanyDTO>)
      -->

      <main class="qna-list-main">
        <div class="qna-list-top">
          <h2 class="qna-list-title">기업 QnA</h2>

          <div class="qna-list-top-actions">
            <%--
              글쓰기 버튼은 일반회원 질문글 작성 페이지로 이동
              공지 작성 버튼은 넣지 않습니다.
              이유:
              QnA 공지는 관리자 페이지에서만 작성하기 때문입니다.
            --%>
            <a href="${pageContext.request.contextPath}/app/main/qna/add-qna.jsp" class="qna-list-search-btn qna-list-search-btn-write">
              글쓰기
            </a>

            <%--
              정렬 select는 현재 UI만 준비
              나중에 sort 파라미터와 Controller 연결 가능
            --%>
            <select class="qna-list-sort-sel" id="sortSelect" name="sort">
              <option value="recent">최신순</option>
              <option value="name">기업명순</option>
            </select>
          </div>
        </div>

        <!-- 기업 필터 -->
        <div class="qna-list-filter">
          <select class="qna-list-filter-sel" id="companyFilter" name="companyNumber">
            <option value="">기업선택</option>

            <%--
              companyList는 CompanyDTO 목록이라고 가정
              - companyNumber
              - companyName
            --%>
            <c:if test="${not empty companyList}">
              <c:forEach var="company" items="${companyList}">
                <option value="${company.companyNumber}">
                  <c:out value="${company.companyName}" />
                </option>
              </c:forEach>
            </c:if>
          </select>
        </div>

        <div class="qna-list-tbl">
          <!-- 테이블 헤더 -->
          <div class="qna-list-tbl-row qna-list-tbl-header">
            <div class="qna-list-tbl-num">번호</div>
            <div class="qna-list-tbl-title">제목</div>
            <div class="qna-list-tbl-company">기업명</div>
            <div class="qna-list-tbl-date">작성일</div>
            <div class="qna-list-tbl-views">조회</div>
          </div>

          <%-- =========================
               1. 기업 QnA 공지 출력
               NoticeDTO 기준
               - postNumber
               - adminId
               - postTitle
               - postDate
               - viewCount

               중요:
               이 공지는 일반회원이 작성하는 글이 아니라
               관리자 페이지에서 작성된 공지입니다.
             ========================= --%>
          <c:if test="${not empty qnaNoticeList}">
            <c:forEach var="notice" items="${qnaNoticeList}">
              <div class="qna-list-tbl-row qna-list-tbl-notice">
                <div class="qna-list-tbl-num">공지</div>

                <div class="qna-list-tbl-title">
                  <%--
                    현재는 JSP 직접 이동
                    나중에 Controller가 생기면 공지 상세 컨트롤러 경로로 변경 가능
                  --%>
                  <a href="${pageContext.request.contextPath}/app/main/qna/qna-notification.jsp?postNumber=${notice.postNumber}">
                    <c:out value="${notice.postTitle}" />
                  </a>
                </div>

                <div class="qna-list-tbl-company">
                  관리자
                </div>

                <div class="qna-list-tbl-date">
                  <c:out value="${notice.postDate}" />
                </div>

                <div class="qna-list-tbl-views">
                  <c:out value="${notice.viewCount}" />
                </div>
              </div>
            </c:forEach>
          </c:if>

          <%-- =========================
               2. 기업 QnA 일반글 출력
               QnaListDTO 기준
               - postNumber
               - postTitle
               - companyName
               - userId
               - postDate
               - viewCount
               - answerStatus
             ========================= --%>
          <c:choose>
            <c:when test="${not empty qnaList}">
              <c:forEach var="qna" items="${qnaList}">
                <div class="qna-list-tbl-row">
                  <div class="qna-list-tbl-num">
                    <c:out value="${qna.postNumber}" />
                  </div>

                  <div class="qna-list-tbl-title">
                    <%--
                      현재는 JSP 직접 이동
                      나중에 Controller가 생기면 QnA 상세 컨트롤러 경로로 변경
                    --%>
                    <a href="${pageContext.request.contextPath}/app/main/qna/qna-detail.jsp?postNumber=${qna.postNumber}">
                      <c:out value="${qna.postTitle}" />
                    </a>

                    <%-- 답변 상태도 함께 보여줌 --%>
                    <span>[<c:out value="${qna.answerStatus}" />]</span>
                  </div>

                  <div class="qna-list-tbl-company">
                    <c:out value="${qna.companyName}" />
                  </div>

                  <div class="qna-list-tbl-date">
                    <c:out value="${qna.postDate}" />
                  </div>

                  <div class="qna-list-tbl-views">
                    <c:out value="${qna.viewCount}" />
                  </div>
                </div>
              </c:forEach>
            </c:when>

            <c:otherwise>
              <div class="qna-list-tbl-row">
                <div class="qna-list-tbl-title" style="grid-column: 1 / 6; text-align: center;">
                  등록된 QnA 게시글이 없습니다.
                </div>
              </div>
            </c:otherwise>
          </c:choose>
        </div>

        <%-- 페이지네이션 --%>
        <div class="qna-list-pg" id="qnaPagination">
          <c:if test="${not empty pageInfo}">
            <c:if test="${pageInfo.prev}">
              <a href="?page=${pageInfo.startPage - 1}">&lt;</a>
            </c:if>

            <c:forEach var="pageNum" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
              <a href="?page=${pageNum}" class="${pageInfo.page == pageNum ? 'active' : ''}">
                <c:out value="${pageNum}" />
              </a>
            </c:forEach>

            <c:if test="${pageInfo.next}">
              <a href="?page=${pageInfo.endPage + 1}">&gt;</a>
            </c:if>
          </c:if>
        </div>

        <%-- 검색 폼 --%>
        <form action="#" method="get" class="qna-list-search">
          <%--
            현재는 action="#"
            나중에 Controller가 생기면 목록 컨트롤러 경로로 변경
          --%>
          <select class="qna-list-search-sel" name="searchType">
            <option value="title" ${param.searchType == 'title' ? 'selected' : ''}>제목</option>
            <option value="content" ${param.searchType == 'content' ? 'selected' : ''}>내용</option>
            <option value="writer" ${param.searchType == 'writer' ? 'selected' : ''}>작성자</option>
            <option value="company" ${param.searchType == 'company' ? 'selected' : ''}>기업명</option>
          </select>

          <input
            class="qna-list-search-inp"
            type="text"
            placeholder="검색할 내용을 입력하세요"
            id="searchInput"
            name="keyword"
            value="${param.keyword}" />

          <button class="qna-list-search-btn" id="btnSearch" type="submit">검색</button>
        </form>
      </main>
    </c:when>

    <c:otherwise>
      <%--
        비회원 차단 처리
        지금은 JSP에서 1차 차단
        나중에 Controller에서도 반드시 한 번 더 막아야 합니다.
      --%>
      <script>
        alert("기업 QnA 게시판은 로그인 후 이용할 수 있습니다.");
        location.href = "${pageContext.request.contextPath}/app/main/account/login.jsp";
      </script>
    </c:otherwise>
  </c:choose>

  <script src="${pageContext.request.contextPath}/assets/js/main/qna/qna-list.js"></script>
</body>
</html>