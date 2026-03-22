package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;

public class AdminDeleteCompanyQnaController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		String[] postNumbers = request.getParameterValues("postNumber");
		String page = request.getParameter("page");
		String companyNumber = request.getParameter("companyNumber");

		if (postNumbers != null) {
			for (String postNumberText : postNumbers) {
				if (postNumberText == null || postNumberText.trim().equals("")) {
					continue;
				}

				Long postNumber = Long.parseLong(postNumberText);

				qnaDAO.deleteCommentsByPostNumber(postNumber);
				qnaDAO.deleteQna(postNumber);
			}
		}

		if (page == null || page.trim().equals("")) {
			page = "1";
		}

		String movePath = request.getContextPath() + "/admin/companyQnaManagement.adfc?page=" + page;

		if (companyNumber != null && !companyNumber.trim().equals("")) {
			movePath += "&companyNumber=" + companyNumber;
		}

		result.setPath(movePath);
		result.setRedirect(true);

		return result;
	}
}