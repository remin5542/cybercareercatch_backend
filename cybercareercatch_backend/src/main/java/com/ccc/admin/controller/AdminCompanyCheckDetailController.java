package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.common.Execute;
import com.ccc.common.Result;

/*
 * 관리자 기업회원 승인/반려 상세 페이지로 이동시키는 컨트롤러이다.
 * 목록에서 클릭한 기업의 상세 정보를 조회한다.
 */
public class AdminCompanyCheckDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String temp = request.getParameter("companyNumber");

		/*
		 * companyNumber가 없으면
		 * 목록 페이지로 다시 보낸다.
		 */
		if (temp == null || temp.trim().equals("")) {
			result.setPath(request.getContextPath() + "/admin/companyCheck.adfc");
			result.setRedirect(true);
			return result;
		}

		int companyNumber = Integer.parseInt(temp);

		/* 기업 상세 정보 조회 */
		CompanyDetailDTO companyDetail = adminDAO.selectCompanyApproveDetail(companyNumber);

		/*
		 * 상세 정보가 없으면
		 * 존재하지 않는 기업으로 보고 목록으로 보낸다.
		 */
		if (companyDetail == null) {
			result.setPath(request.getContextPath() + "/admin/companyCheck.adfc");
			result.setRedirect(true);
			return result;
		}

		request.setAttribute("companyDetail", companyDetail);

		result.setPath("/app/admin/member-management/company-checkdetail.jsp");
		result.setRedirect(false);

		return result;
	}
}