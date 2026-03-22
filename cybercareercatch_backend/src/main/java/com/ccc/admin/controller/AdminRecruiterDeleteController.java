package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.common.Execute;
import com.ccc.common.Result;

/*
 * 관리자 기업회원 삭제를 처리하는 컨트롤러이다.
 * 목록에서 체크한 기업회원 계정만 삭제한다.
 */
public class AdminRecruiterDeleteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String[] userNumbers = request.getParameterValues("userNumber");
		String page = request.getParameter("page");

		if (userNumbers != null) {
			for (String userNumberText : userNumbers) {
				if (userNumberText == null || userNumberText.trim().equals("")) {
					continue;
				}

				int userNumber = Integer.parseInt(userNumberText);

				/*
				 * 기업회원 삭제 순서
				 * 1. 해당 계정이 작성한 댓글 삭제
				 * 2. 기업회원 사용자 계정 삭제
				 *
				 * 회사와 회사 게시글은 건드리지 않는다.
				 */
				adminDAO.deleteRecruiterComments(userNumber);
				adminDAO.deleteRecruiter(userNumber);
			}
		}

		if (page == null || page.trim().equals("")) {
			page = "1";
		}

		result.setPath(request.getContextPath() + "/admin/recruiterInfo.adfc?page=" + page);
		result.setRedirect(true);

		return result;
	}
}