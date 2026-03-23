package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDTO;

public class AdminUpdateCompanyStateController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		CompanyDTO companyDTO = new CompanyDTO();
		Result result = new Result();

		String companyNumberText = request.getParameter("companyNumber");
		String companyState = request.getParameter("companyState");

		if (companyNumberText == null || companyNumberText.trim().equals("")
				|| companyState == null || companyState.trim().equals("")) {
			result.setPath(request.getContextPath() + "/admin/companyCheck.adfc");
			result.setRedirect(true);
			return result;
		}

		if (!("승인".equals(companyState) || "반려".equals(companyState))) {
			result.setPath(request.getContextPath() + "/admin/companyCheck.adfc");
			result.setRedirect(true);
			return result;
		}

		int companyNumber = Integer.parseInt(companyNumberText);

		companyDTO.setCompanyNumber(companyNumber);
		companyDTO.setCompanyState(companyState);

		adminDAO.updateCompanyState(companyDTO);

		String resultMessage = "승인".equals(companyState) ? "approve" : "reject";

		result.setPath(
			request.getContextPath()
			+ "/admin/companyCheckDetail.adfc?companyNumber="
			+ companyNumber
			+ "&result="
			+ resultMessage
		);
		result.setRedirect(true);

		return result;
	}
}