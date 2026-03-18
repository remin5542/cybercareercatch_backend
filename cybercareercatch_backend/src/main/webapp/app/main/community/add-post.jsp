<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>자유게시판 글쓰기</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/add-post.css">
</head>

<body>
  <%--
    자유게시판 글쓰기는 일반회원만 가능
    비회원 불가
    기업회원 불가
  --%>
  <c:choose>
    <c:when test="${sessionScope.userType == '일반회원'}">

      <!--
        PostWriteDTO 기준
        - postType
        - userNumber
        - postTitle
        - postContent
      -->

      <main class="cmw-wrap">
        <div class="cmw-top">
          <h2 class="cmw-top-ttl">자유게시판</h2>

          <a href="${pageContext.request.contextPath}/app/main/community/post-list.jsp" class="cmw-top-mv">
            자유게시판 목록으로 이동하기
          </a>
        </div>

        <div class="cmw-pnl">

          <%--
            현재는 action="#"
            나중에 Controller가 생기면 자유게시판 등록 처리 경로로 변경
          --%>
          <form action="#" method="post" class="cmw-frm" id="postWriteForm">

            <%-- 자유게시판 일반글 고정값 --%>
            <input type="hidden" name="postType" value="FREE_POST">

            <%-- 로그인한 일반회원 번호 --%>
            <input type="hidden" name="userNumber" value="${sessionScope.userNumber}">

            <div class="cmw-row">
              <label class="cmw-lbl" for="postTitle">제목</label>
              <input
                type="text"
                id="postTitle"
                name="postTitle"
                class="cmw-inp"
                placeholder="제목을 입력해주세요"
              >
            </div>

            <div class="cmw-row">
              <label class="cmw-lbl" for="postContent">내용</label>
              <textarea
                id="postContent"
                name="postContent"
                class="cmw-ta"
                placeholder="자유롭게 내용을 입력하세요"
              ></textarea>
            </div>

            <div class="cmw-btm">
              <button type="submit" class="cmw-btn" id="savePostBtn">등록완료</button>
            </div>
          </form>
        </div>
      </main>
    </c:when>

    <c:otherwise>
      <script>
        alert("자유게시판 글쓰기는 일반회원만 가능합니다.");
        location.href = "${pageContext.request.contextPath}/app/main/community/post-list.jsp";
      </script>
    </c:otherwise>
  </c:choose>

  <script src="${pageContext.request.contextPath}/assets/js/main/community/add-post.js"></script>
</body>
</html>