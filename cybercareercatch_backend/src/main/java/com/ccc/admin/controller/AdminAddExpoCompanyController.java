package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.expo.dao.ExpoDAO;

public class AdminAddExpoCompanyController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ExpoDAO expoDAO = new ExpoDAO();
		Result result = new Result();

		String expoNumberText = request.getParameter("expoNumber");
		String companyNumberText = request.getParameter("companyNumber");

		if (expoNumberText != null && !expoNumberText.trim().equals("")
				&& companyNumberText != null && !companyNumberText.trim().equals("")) {

			int expoNumber = Integer.parseInt(expoNumberText);
			int companyNumber = Integer.parseInt(companyNumberText);

			expoDAO.insertExpoCompany(expoNumber, companyNumber);

			result.setPath(request.getContextPath() + "/admin/expoDetailSchedule.adfc?expoNumber=" + expoNumber);
			result.setRedirect(true);
			return result;
		}

		result.setPath(request.getContextPath() + "/admin/expoSchedule.adfc");
		result.setRedirect(true);

		return result;
	}
}