package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.controller.AdminLoginController;
import com.ccc.admin.controller.AdminLoginOkController;
import com.ccc.common.Result;

public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminFrontController() {
		super();
	}

	/**
	 * GET 요청도 doProcess로 보낸다.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * POST 요청도 doProcess로 보낸다.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * 실제 요청을 처리하는 메소드이다.
	 */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 현재 요청 주소를 가져온다.
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("관리자 프론트 컨트롤러 진입 : " + target);

		Result result = null;

		switch (target) {

		// 관리자 로그인 페이지 요청
		case "/admin/login.adfc":
			System.out.println("관리자 로그인 페이지 요청");
			result = new AdminLoginController().execute(request, response);
			System.out.println("관리자 로그인 페이지 이동 완료");
			break;

		// 관리자 로그인 처리 요청
		case "/admin/loginOk.adfc":
			System.out.println("관리자 로그인 처리 요청");
			result = new AdminLoginOkController().execute(request, response);
			System.out.println("관리자 로그인 처리 완료");
			break;
		}

		// 이동 정보가 있으면 forward 또는 redirect 처리한다.
		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}