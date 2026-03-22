package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dao.AdminDAO;
import com.ccc.admin.dto.AdminMemberListDTO;
import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.member.dto.MemberDTO;

/*
 * 관리자 일반회원 상세 페이지로 이동시키는 컨트롤러이다.
 * 목록에서 클릭한 회원의 상세 정보를 조회한다.
 */
public class AdminMemberInfoDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String temp = request.getParameter("userNumber");

		/*
		 * userNumber가 없으면
		 * 목록 페이지로 다시 보낸다.
		 */
		if (temp == null || temp.trim().equals("")) {
			result.setPath(request.getContextPath() + "/admin/memberInfo.adfc");
			result.setRedirect(true);
			return result;
		}

		int userNumber = Integer.parseInt(temp);

		/* 사용자 기본 정보 조회 */
		AdminMemberListDTO userInfo = adminDAO.selectMemberUserInfo(userNumber);

		/* 사용자 상세 정보 조회 */
		MemberDTO memberInfo = adminDAO.selectMemberDetail(userNumber);

		/*
		 * 기본 정보가 없으면
		 * 존재하지 않는 회원으로 보고 목록으로 보낸다.
		 */
		if (userInfo == null) {
			result.setPath(request.getContextPath() + "/admin/memberInfo.adfc");
			result.setRedirect(true);
			return result;
		}

		request.setAttribute("userInfo", userInfo);
		request.setAttribute("memberInfo", memberInfo);
		request.setAttribute("preferredJobName", getJobName(memberInfo));

		result.setPath("/app/admin/member-management/member-infodetail.jsp");
		result.setRedirect(false);

		return result;
	}

	/*
	 * 회원의 선호 직군 번호를 직군명으로 바꿔준다.
	 */
	private String getJobName(MemberDTO memberInfo) {
		if (memberInfo == null) {
			return "-";
		}

		switch (memberInfo.getJobNumber()) {
		case 1:
			return "보안컨설팅";
		case 2:
			return "시스템/네트워크 엔지니어";
		case 3:
			return "보안관제";
		case 4:
			return "침해사고/디지털 포렌식";
		default:
			return "-";
		}
	}
}