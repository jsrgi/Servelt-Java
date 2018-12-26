package com.asset.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asset.dao.ServiceProvider;
import com.asset.model.Count;
import com.google.gson.Gson;

@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
   
    public AdminDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ServiceProvider provide = new ServiceProvider();
		List<Count> cntList=new ArrayList<Count>();
		try
		{
			cntList=provide.getAllCount();	
			
			
			 response.setContentType("application/json");
			
			 Gson gson=new Gson();
			 JSONROOT.put("Results", "OK");
			 JSONROOT.put("Record", cntList);
				
			 String jsonRec=gson.toJson(JSONROOT);
			 response.getWriter().print(jsonRec);
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
