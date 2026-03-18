<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>기업 QnA 공지사항</title>

  <%--
    기업 QnA 공지 상세도 사용자 화면에서는 "조회만" 필요합니다.
    CSS는 기존 공지 상세 스타일을 재사용해도 됩니다.
  --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/post-notification.css">
</head>

<body>
  <!--
    이 페이지도 비회원 접근 불가

    중요:
    이 페이지는 관리자가 작성한 QNA_NOTICE 공지 상세를 보여주는 페이지입니다.
    사용자는 읽기만 가능하고,
    작성/수정/삭제는 관리자 페이지에서 처리해야 합니다.

    NoticeDTO 기준
    - postNumber
    - postType
    - adminNumber
    - adminId
    - postTitle
    - postContent
    - postDate
    - editDate
    - viewCount

    Controller 예시
    request.setAttribute("notice", noticeDTO);
  -->
  <c:choose>
    <c:when test="${not empty sessionScope.userNumber}">

      <main class="cnd-wrap">
        <div class="cnd-top">
          <h2 class="cnd-top-ttl">기업 QnA 공지사항</h2>
          <a href="${pageContext.request.contextPath}/app/main/qna/qna-list.jsp" class="cnd-top-mv">
            기업 QnA로 이동하기
          </a>
        </div>

        <div class="cnd-pnl">
          <c:choose>
            <c:when test="${not empty notice}">

              <div class="cnd-head">
                <h3 class="cnd-head-ttl" id="cndNoticeTitle">
                  <c:out value="${notice.postTitle}" />
                </h3>

                <div class="cnd-head-info">
                  <span class="cnd-head-info-it" id="cndNoticeWriter">
                    작성자: <c:out value="${notice.adminId}" />
                  </span>
                  <span class="cnd-head-info-it" id="cndNoticeDate">
                    | 작성일: <c:out value="${notice.postDate}" />
                  </span>
                  <span class="cnd-head-info-it" id="cndNoticeViews">
                    | 조회수: <c:out value="${notice.viewCount}" />
                  </span>
                </div>
              </div>

              <%-- 공지 내용 출력 --%>
              <div class="cnd-cont" id="cndNoticeContent">
                <c:out value="${notice.postContent}" />
              </div>

              <%--
                사용자 화면에서는 공지에 대한 수정/삭제 버튼을 두지 않습니다.
                이유:
                이 공지는 관리자 페이지에서 작성/관리하기 때문입니다.
              --%>

            </c:when>

            <c:otherwise>
              <div class="cnd-cont">존재하지 않는 공지사항입니다.</div>
            </c:otherwise>
          </c:choose>

          <div class="cnd-btm">
            <a href="${pageContext.request.contextPath}/app/main/qna/qna-list.jsp" class="cnd-btn">목록으로</a>
          </div>
        </div>
      </main>
    </c:when>

    <c:otherwise>
      <script>
        alert("기업 QnA 게시판은 로그인 후 이용할 수 있습니다.");
        location.href = "${pageContext.request.contextPath}/app/main/account/login.jsp";
      </script>
    </c:otherwise>
  </c:choose>
</body>
</html>