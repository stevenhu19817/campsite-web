package com.camporder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsitetentstatus.model.CampsiteTentStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/availibleDate")
public class AvailibleDate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		Integer dateList[] = objectMapper.readValue(json, Integer[].class);
		CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
		ArrayList<String> unavilibleDate = new ArrayList<String>();
		try {
			unavilibleDate = CTSSvc.getUnavailibleDatewithGuestNumberOnly(dateList[0], dateList[1]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String unavilibleDateString = unavilibleDate.toString();
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(unavilibleDateString);
		
	}
}
