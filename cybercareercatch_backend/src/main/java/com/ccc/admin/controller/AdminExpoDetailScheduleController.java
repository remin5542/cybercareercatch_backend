package com.ccc.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.expo.dao.ExpoDAO;
import com.ccc.expo.dto.ExpoDTO;
import com.ccc.expo.dto.ExpoDetailDTO;

public class AdminExpoDetailScheduleController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ExpoDAO expoDAO = new ExpoDAO();
		Result result = new Result();

		String temp = request.getParameter("expoNumber");

		if (temp == null || temp.trim().equals("")) {
			result.setPath(request.getContextPath() + "/admin/expoSchedule.adfc");
			result.setRedirect(true);
			return result;
		}

		int expoNumber = Integer.parseInt(temp);

		/* 선택한 박람회 기본 정보 */
		ExpoDTO expoInfo = expoDAO.selectExpoInfo(expoNumber);

		if (expoInfo == null) {
			result.setPath(request.getContextPath() + "/admin/expoSchedule.adfc");
			result.setRedirect(true);
			return result;
		}

		/* 현재 이 박람회에 연결된 기업 목록 */
		List<ExpoDetailDTO> expoDetailList = expoDAO.selectExpoDetailList(expoNumber);

		/* 아직 이 박람회에 추가되지 않은 승인 기업 목록 */
		List<CompanyDTO> availableCompanyList = expoDAO.selectAvailableCompanyList(expoNumber);

		request.setAttribute("expoInfo", expoInfo);
		request.setAttribute("expoDetailList", expoDetailList);
		request.setAttribute("availableCompanyList", availableCompanyList);

		result.setPath("/app/admin/community-management/expodetail-schedule.jsp");
		result.setRedirect(false);

		return result;
	}
}