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
import com.google.gson.GsonBuilder;

@WebServlet("/ViewComplaint")
public class ViewComplaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
    
    public ViewComplaint() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		List<Complaint> comList=new ArrayList<Complaint>();
		 ServiceProvider provide=new ServiceProvider();
		
		 if(action != null)
		 {
			 try
			 {
				 
				 if(action.equals("list"))
				 {
					comList = provide.getAllComplaint(); 
					
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", comList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
					
				 }
				 if(action.equals("update"))
				 {
					 Complaint com=new Complaint();
					 if(request.getParameter("comStat")!=null)
					 {
						 String comstat=request.getParameter("comStat");
						 com.setComStat(comstat);
					 }
					 if(request.getParameter("comId")!=null)
					 {
						 com.setComId(request.getParameter("comId"));
					 }				 
					 	provide.updateComStat(com);
						// Return in the format required by jTable plugin
						 JSONROOT.put("Result", "OK");
						 JSONROOT.put("Record", com);
						 
						// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
				 }
			 }
			 catch(Exception e)
			 {
				 System.err.println(e);
			 }
		 }
		 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
