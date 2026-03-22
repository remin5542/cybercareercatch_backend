package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.expo.dao.ExpoDAO;

public class AdminDeleteExpoController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ExpoDAO expoDAO = new ExpoDAO();
		Result result = new Result();

		String[] expoNumbers = request.getParameterValues("expoNumber");
		String page = request.getParameter("page");

		if (expoNumbers != null) {
			for (String expoNumberText : expoNumbers) {
				if (expoNumberText == null || expoNumberText.trim().equals("")) {
					continue;
				}

				int expoNumber = Integer.parseInt(expoNumberText);
				expoDAO.deleteExpo(expoNumber);
			}
		}

		if (page == null || page.trim().equals("")) {
			page = "1";
		}

		result.setPath(request.getContextPath() + "/admin/expoSchedule.adfc?page=" + page);
		result.setRedirect(true);

		return result;
	}
}