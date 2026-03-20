package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberEditPwController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberEditPwController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		// 로그인 정보 확인
		Integer userNumber = (Integer) session.getAttribute("userNumber");
		System.out.println("로그인한 회원 번호 : " + userNumber);

		String userType = (String) session.getAttribute("userType");
		System.out.println("로그인한 회원 타입 : " + userType);

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

		// 입력값 받기
		String currentUserPw = request.getParameter("currentUserPw");
		String newUserPw = request.getParameter("newUserPw");
		String newUserPwConfirm = request.getParameter("newUserPwConfirm");

		// 현재 비밀번호 공백 체크
		if (currentUserPw == null || currentUserPw.trim().isEmpty()) {
			request.setAttribute("currentPwMessage", "현재 비밀번호를 입력해주세요.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 새 비밀번호 공백 체크
		if (newUserPw == null || newUserPw.trim().isEmpty()) {
			request.setAttribute("newPwMessage", "변경할 비밀번호를 입력해주세요.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}
		
		Boolean memberPwChecked = (Boolean) session.getAttribute("memberPwChecked");

		if (memberPwChecked == null || !memberPwChecked) {
			request.setAttribute("currentPwMessage", "먼저 현재 비밀번호 확인을 진행해주세요.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 새 비밀번호 확인 공백 체크
		if (newUserPwConfirm == null || newUserPwConfirm.trim().isEmpty()) {
			request.setAttribute("newPwConfirmMessage", "변경할 비밀번호 확인을 입력해주세요.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		
		// 현재 비밀번호 일치 여부 확인
		currentUserPw = currentUserPw.trim();
		newUserPw = newUserPw.trim();
		newUserPwConfirm = newUserPwConfirm.trim();
		boolean isCorrectPw = mypageDAO.checkMemberPw(userNumber, currentUserPw);

		if (!isCorrectPw) {
			request.setAttribute("currentPwMessage", "현재 비밀번호가 일치하지 않습니다.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 새 비밀번호 확인 일치 체크
		if (!newUserPw.equals(newUserPwConfirm)) {
			request.setAttribute("newPwConfirmMessage", "변경할 비밀번호가 일치하지 않습니다.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMyPageInfo(userNumber));
			result.setPath("/app/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 비밀번호 수정
		mypageDAO.updateMemberPw(userNumber, newUserPw);
		System.out.println("비밀번호 변경 완료");
		
		session.removeAttribute("memberPwChecked");

		// 수정 후 마이페이지로 이동
		result.setPath(request.getContextPath() + "/member/mypage.mpfc");
		result.setRedirect(true);

		return result;
	}
}