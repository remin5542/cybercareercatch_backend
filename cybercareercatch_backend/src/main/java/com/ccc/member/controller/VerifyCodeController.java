package com.ccc.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class VerifyCodeController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		BufferedReader reader = request.getReader();
		String body = reader.lines().collect(Collectors.joining());
		JsonObject json = JsonParser.parseString(body).getAsJsonObject();

		String userCode = json.get("code").getAsString();
		HttpSession session = request.getSession();

		String sessionCode = (String) session.getAttribute("verificationCode");

		JsonObject responseJson = new JsonObject();

		if (sessionCode != null && sessionCode.equals(userCode)) {
			responseJson.addProperty("success", true);
		} else {
			responseJson.addProperty("success", false);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseJson.toString());

		return null;
	}
}