<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>자유게시판 상세</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/post-detail.css">
</head>

<body>
  <%--
    자유게시판 상세도
    비회원 + 일반회원은 접근 가능,
    기업회원은 접근 불가
  --%>
  <c:choose>
    <c:when test="${empty sessionScope.userType or sessionScope.userType == '일반회원'}">

      <!--
        Controller 예시
        request.setAttribute("post", postDetailDTO);

        PostDetailDTO 기준
        - postNumber
        - userNumber
        - postTitle
        - userId
        - postDate
        - editDate
        - viewCount
        - postContent
      -->

      <main class="cmd-wrap">
        <div class="cmd-top">
          <h2 class="cmd-top-ttl">자유게시판</h2>

          <%--
            현재는 JSP 직접 이동
            나중에 Controller가 생기면 목록 컨트롤러 경로로 변경
          --%>
          <a href="${pageContext.request.contextPath}/app/main/community/post-list.jsp" class="cmd-top-mv">
            자유게시판 목록으로 이동하기
          </a>
        </div>

        <div class="cmd-pnl">
          <c:choose>
            <c:when test="${not empty post}">

              <div class="cmd-post">
                <div class="cmd-head">
                  <div class="cmd-head-top">

                    <%-- 게시글 제목 출력 --%>
                    <div class="cmd-post-ttl" id="cmdPostTitle">
                      <c:out value="${post.postTitle}" />
                    </div>

                    <div class="cmd-act" id="cmdPostActions">
                      <%--
                        현재 범위에서는 수정 기능 없음
                        삭제 버튼만 작성자 본인에게 보여줌

                        비회원은 sessionScope.userNumber가 없으므로
                        자동으로 버튼이 안 보임.
                      --%>
                      <c:if test="${sessionScope.userNumber == post.userNumber}">
                        <button
                          type="button"
                          class="cmd-act-btn cmd-act-del"
                          id="cmdBtnDelete"
                          data-post-number="${post.postNumber}">
                          삭제
                        </button>
                      </c:if>
                    </div>
                  </div>

                  <div class="cmd-info">
                    <div class="cmd-info-it" id="cmdPostWriter">
                      ID : <c:out value="${post.userId}" />
                    </div>
                    <div class="cmd-info-it" id="cmdPostViews">
                      | 조회수 : <c:out value="${post.viewCount}" />
                    </div>
                    <div class="cmd-info-it" id="cmdPostDate">
                      | 작성일 : <c:out value="${post.postDate}" />
                    </div>
                  </div>
                </div>

                <%-- 게시글 내용 출력 --%>
                <div class="cmd-cont" id="cmdPostContent">
                  <c:out value="${post.postContent}" />
                </div>
              </div>

            </c:when>

            <c:otherwise>
              <div class="cmd-post">
                <div class="cmd-post-ttl">존재하지 않는 게시글입니다.</div>
              </div>
            </c:otherwise>
          </c:choose>
        </div>
      </main>
    </c:when>

    <c:otherwise>
      <script>
        alert("자유게시판은 일반회원만 이용할 수 있습니다.");
        location.href = "${pageContext.request.contextPath}/";
      </script>
    </c:otherwise>
  </c:choose>

  <script>
    const contextPath = "${pageContext.request.contextPath}";
    const postNumber = "${not empty post ? post.postNumber : ''}";
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/main/community/post-detail.js"></script>
</body>
</html>