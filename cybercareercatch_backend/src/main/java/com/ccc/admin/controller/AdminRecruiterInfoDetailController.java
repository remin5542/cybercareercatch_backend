package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.admin.dto.AdminMemberListDTO;
import com.ccc.common.Execute;
import com.ccc.common.Result;

/*
 * 관리자 기업회원 상세 페이지로 이동시키는 컨트롤러이다.
 * 목록에서 클릭한 기업회원의 상세 정보를 조회한다.
 */
public class AdminRecruiterInfoDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String temp = request.getParameter("userNumber");

		/*
		 * userNumber가 없으면
		 * 목록 페이지로 다시 보낸다.
		 */
		if (temp == null || temp.trim().equals("")) {
			result.setPath(request.getContextPath() + "/admin/recruiterInfo.adfc");
			result.setRedirect(true);
			return result;
		}

		int userNumber = Integer.parseInt(temp);

		/* 기업회원 기본 정보 조회 */
		AdminMemberListDTO recruiterInfo = adminDAO.selectRecruiterUserInfo(userNumber);

		/*
		 * 기본 정보가 없으면
		 * 존재하지 않는 회원으로 보고 목록으로 보낸다.
		 */
		if (recruiterInfo == null) {
			result.setPath(request.getContextPath() + "/admin/recruiterInfo.adfc");
			result.setRedirect(true);
			return result;
		}

		request.setAttribute("recruiterInfo", recruiterInfo);

		result.setPath("/app/admin/member-management/recruiter-infodetail.jsp");
		result.setRedirect(false);

		return result;
	}
}