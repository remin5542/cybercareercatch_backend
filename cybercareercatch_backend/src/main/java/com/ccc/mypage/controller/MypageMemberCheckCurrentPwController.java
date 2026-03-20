package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberCheckCurrentPwController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MypageMemberCheckPwController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");

		// 비로그인
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// 일반회원 아님
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}

		String currentUserPw = request.getParameter("currentUserPw");

		// 현재 비밀번호 공백 체크
		if (currentUserPw == null || currentUserPw.trim().isEmpty()) {
			request.setAttribute("currentPwMessage", "현재 비밀번호를 입력해주세요.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		currentUserPw = currentUserPw.trim();

		// 현재 비밀번호 일치 여부 확인
		boolean isCorrectPw = mypageDAO.checkMemberPw(userNumber, currentUserPw);

		if (!isCorrectPw) {
			request.setAttribute("currentPwMessage", "현재 비밀번호가 일치하지 않습니다.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 확인 성공
		request.setAttribute("currentPwMessage", "현재 비밀번호가 확인되었습니다.");
		request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));

		// 선택: 확인 성공 여부를 세션에 저장
		session.setAttribute("memberPwChecked", true);

		result.setPath("/app/mypage/mypage-member-edit.jsp");
		result.setRedirect(false);
		return result;
	}
}