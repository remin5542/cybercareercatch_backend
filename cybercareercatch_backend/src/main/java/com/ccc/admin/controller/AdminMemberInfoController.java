package com.ccc.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.admin.dto.AdminMemberListDTO;
import com.ccc.common.Execute;
import com.ccc.common.Result;

/*
 * 관리자 일반회원 목록 페이지로 이동시키는 컨트롤러이다.
 * 목록 조회, 검색, 페이징 처리를 담당한다.
 */
public class AdminMemberInfoController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String temp = request.getParameter("page");
		String keywordType = request.getParameter("keywordType");
		String keyword = request.getParameter("keyword");

		int page = 1;
		int rowCount = 10;
		int pageCount = 5;

		if (temp != null && !temp.trim().equals("")) {
			page = Integer.parseInt(temp);
		}

		if (keywordType == null || keywordType.trim().equals("")) {
			keywordType = "userName";
		}

		if (keyword == null) {
			keyword = "";
		} else {
			keyword = keyword.trim();
		}

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("keywordType", keywordType);
		searchMap.put("keyword", keyword);

		int total = adminDAO.getMemberListTotal(searchMap);

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

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("keywordType", keywordType);
		pageMap.put("keyword", keyword);

		List<AdminMemberListDTO> memberList = adminDAO.selectMemberList(pageMap);

		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;

		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("memberList", memberList);
		request.setAttribute("page", page);
		request.setAttribute("rowCount", rowCount);
		request.setAttribute("total", total);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("keywordType", keywordType);
		request.setAttribute("keyword", keyword);

		result.setPath("/app/admin/member-management/member-info.jsp");
		result.setRedirect(false);

		return result;
	}
}