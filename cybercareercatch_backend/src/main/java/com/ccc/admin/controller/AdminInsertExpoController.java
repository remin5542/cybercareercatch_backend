package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.expo.dao.ExpoDAO;
import com.ccc.expo.dto.ExpoDTO;

public class AdminInsertExpoController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ExpoDAO expoDAO = new ExpoDAO();
		Result result = new Result();

		Object adminNumberObj = request.getSession().getAttribute("adminNumber");

		String expoName = request.getParameter("expoName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String location = request.getParameter("location");

		if (adminNumberObj != null
				&& expoName != null && !expoName.trim().equals("")
				&& startDate != null && !startDate.trim().equals("")
				&& endDate != null && !endDate.trim().equals("")
				&& location != null && !location.trim().equals("")) {

			ExpoDTO expoDTO = new ExpoDTO();
			expoDTO.setAdminNumber(Integer.parseInt(String.valueOf(adminNumberObj)));
			expoDTO.setExpoName(expoName);
			expoDTO.setStartDate(startDate);
			expoDTO.setEndDate(endDate);
			expoDTO.setLocation(location);

			expoDAO.insertExpo(expoDTO);
		}

		result.setPath(request.getContextPath() + "/admin/expoSchedule.adfc");
		result.setRedirect(true);

		return result;
	}
}