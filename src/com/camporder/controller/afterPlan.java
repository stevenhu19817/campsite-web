package com.camporder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camporder.model.CampOrderVO;
import com.customerplan.model.CustomerPlanVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/plan_selected")
public class afterPlan extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		if (br != null) {
			json = br.readLine();
			System.out.println(json);
		}
		List<CustomerPlanVO> list = objectMapper.readValue(json, new TypeReference<List<CustomerPlanVO>>(){});
		session.setAttribute("planList", list);
	}
}
