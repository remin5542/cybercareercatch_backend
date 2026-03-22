package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.post.dao.PostDAO;

/*
 * 관리자 자유게시판 게시글 삭제를 처리하는 컨트롤러이다.
 */
public class AdminDeleteFreePostController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PostDAO postDAO = new PostDAO();
		Result result = new Result();

		String[] postNumbers = request.getParameterValues("postNumber");
		String page = request.getParameter("page");

		if (postNumbers != null) {
			for (String postNumberText : postNumbers) {
				if (postNumberText == null || postNumberText.trim().equals("")) {
					continue;
				}

				int postNumber = Integer.parseInt(postNumberText);
				postDAO.deleteAdminFreePost(postNumber);
			}
		}

		if (page == null || page.trim().equals("")) {
			page = "1";
		}

		result.setPath(request.getContextPath() + "/admin/communityManagement.adfc?page=" + page);
		result.setRedirect(true);

		return result;
	}
}