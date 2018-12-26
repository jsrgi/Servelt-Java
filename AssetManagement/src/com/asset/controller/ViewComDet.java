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
import com.asset.model.Complaint;
import com.google.gson.Gson;

@WebServlet("/ViewComDet")
public class ViewComDet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
    
    public ViewComDet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String empid=request.getParameter("id");
		//String empid="emp003";
		ServiceProvider provide=new ServiceProvider();
		List<Complaint> comList = new ArrayList<Complaint>();
		try {
			comList=provide.getEmpComplaint(empid);
			response.setContentType("application/json");
			
            Gson gson=new Gson();
			
			JSONROOT.put("Results", "OK");
			JSONROOT.put("Record", comList);
			
			String jsonRec=gson.toJson(JSONROOT);
			response.getWriter().print(jsonRec);
			
			
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
