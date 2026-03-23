package com.ccc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.common.service.SmsService;

public class JoinSMSController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String phoneNumber = request.getParameter("phoneNumber");
		SmsService smsService = new SmsService();
		Result result = new Result();

		try {
			System.out.println("===인증코드 발송 요청===");

			String verificationCode = smsService.sendVerificationSms(phoneNumber);

			HttpSession session = request.getSession();
			session.setAttribute("verificationCode", verificationCode);

			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("인증번호가 발송되었습니다.");
				return null;
			}

			String referer = request.getHeader("Referer");
			result.setPath(referer != null ? referer : request.getContextPath() + "/mainpage/mainpage.mafc");
			result.setRedirect(true);

		} catch (Exception e) {
			e.printStackTrace();

			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("SMS 발송 실패: " + e.getMessage());
				return null;
			}

			result.setPath("/error.jsp");
			result.setRedirect(true);
		}

		return result;
	}
}