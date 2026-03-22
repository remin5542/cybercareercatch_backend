package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDTO;

/*
 * 관리자 기업회원 승인/반려 상태 변경을 처리하는 컨트롤러이다.
 */
public class AdminUpdateCompanyStateController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		CompanyDTO companyDTO = new CompanyDTO();
		Result result = new Result();

		String companyNumberText = request.getParameter("companyNumber");
		String companyState = request.getParameter("companyState");

		/*
		 * 필수값이 없으면 목록으로 다시 보낸다.
		 */
		if (companyNumberText == null || companyNumberText.trim().equals("")
				|| companyState == null || companyState.trim().equals("")) {
			result.setPath(request.getContextPath() + "/admin/companyCheck.adfc");
			result.setRedirect(true);
			return result;
		}

		int companyNumber = Integer.parseInt(companyNumberText);

		companyDTO.setCompanyNumber(companyNumber);
		companyDTO.setCompanyState(companyState);

		/*
		 * 기업 상태를 승인 또는 반려로 변경한다.
		 */
		adminDAO.updateCompanyState(companyDTO);

		/*
		 * 처리 후 목록 페이지로 이동한다.
		 */
		result.setPath(request.getContextPath() + "/admin/companyCheck.adfc");
		result.setRedirect(true);

		return result;
	}
}