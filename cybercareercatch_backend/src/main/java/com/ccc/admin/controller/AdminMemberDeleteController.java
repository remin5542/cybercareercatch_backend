package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.common.Execute;
import com.ccc.common.Result;

/*
 * 관리자 일반회원 삭제를 처리하는 컨트롤러이다.
 * 목록에서 체크한 일반회원을 바로 삭제한다.
 */
public class AdminMemberDeleteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		/*
		 * 목록에서 선택한 회원 번호들을 배열로 받는다.
		 */
		String[] userNumbers = request.getParameterValues("userNumber");
		String page = request.getParameter("page");

		if (userNumbers != null) {
			for (String userNumberText : userNumbers) {
				if (userNumberText == null || userNumberText.trim().equals("")) {
					continue;
				}

				int userNumber = Integer.parseInt(userNumberText);

				/*
				 * 일반회원 삭제 순서
				 * 1. 댓글 삭제
				 * 2. 게시글 삭제
				 * 3. 사용자 삭제
				 *
				 * 게시글은 회원을 참조하고,
				 * 댓글은 사용자/게시글을 참조하므로 순서가 중요하다.
				 */
				adminDAO.deleteMemberComments(userNumber);
				adminDAO.deleteMemberPosts(userNumber);
				adminDAO.deleteMember(userNumber);
			}
		}

		/*
		 * 삭제 후 다시 일반회원 목록으로 이동한다.
		 */
		if (page == null || page.trim().equals("")) {
			page = "1";
		}

		result.setPath(request.getContextPath() + "/admin/memberInfo.adfc?page=" + page);
		result.setRedirect(true);

		return result;
	}
}