package com.ccc.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.expo.dao.ExpoDAO;
import com.ccc.expo.dto.ExpoListDTO;

public class AdminExpoScheduleController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ExpoDAO expoDAO = new ExpoDAO();
		Result result = new Result();

		String temp = request.getParameter("page");

		int page = 1;
		int rowCount = 10;
		int pageCount = 5;

		if (temp != null && !temp.trim().equals("")) {
			page = Integer.parseInt(temp);
		}

		int total = expoDAO.getExpoTotal();
		int realEndPage = (int) Math.ceil(total / (double) rowCount);

		if (realEndPage == 0) {
			realEndPage = 1;
		}

		if (page > realEndPage) {
			page = realEndPage;
		}

		if (page < 1) {
			page = 1;
		}

		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);

		List<ExpoListDTO> expoList = expoDAO.selectExpoList(pageMap);

		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;

		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("expoList", expoList);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		result.setPath("/app/admin/community-management/expo-schedule.jsp");
		result.setRedirect(false);

		return result;
	}
}