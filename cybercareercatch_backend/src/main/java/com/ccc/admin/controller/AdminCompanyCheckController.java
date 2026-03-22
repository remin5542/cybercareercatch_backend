package com.ccc.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.admin.dto.AdminCompanyListDTO;
import com.ccc.common.Execute;
import com.ccc.common.Result;

/*
 * 관리자 기업회원 승인/반려 목록 페이지로 이동시키는 컨트롤러이다.
 * 목록 조회와 페이징 처리를 담당한다.
 */
public class AdminCompanyCheckController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String temp = request.getParameter("page");

		int page = 1;
		int rowCount = 10;
		int pageCount = 5;

		/*
		 * 사용자가 페이지 번호를 넘겼으면
		 * 해당 페이지 번호로 처리한다.
		 */
		if (temp != null && !temp.trim().equals("")) {
			page = Integer.parseInt(temp);
		}

		/* 전체 기업회원 승인/반려 대상 수 조회 */
		int total = adminDAO.getCompanyApproveTotal();

		/* 실제 마지막 페이지 */
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

		/* Oracle ROWNUM용 시작행, 끝행 */
		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);

		/* 현재 페이지 기업회원 승인/반려 목록 조회 */
		List<AdminCompanyListDTO> companyList = adminDAO.selectCompanyApproveList(pageMap);

		/* 페이지네이션 시작/끝 번호 */
		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;

		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("companyList", companyList);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		result.setPath("/app/admin/member-management/company-check.jsp");
		result.setRedirect(false);

		return result;
	}
}