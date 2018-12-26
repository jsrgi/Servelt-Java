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
import com.asset.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/ReteriveEmpDetails")
public class ReteriveEmpDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();  
	private ServiceProvider provide;
       
  
    public ReteriveEmpDetails() {
        super();
        provide=new ServiceProvider();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id=request.getParameter("id");
		
		List<Employee> emp=new ArrayList<Employee>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		if (id != null) {
			try {
				emp=provide.getEmpProfile(id);

				JSONROOT.put("Result", "OK");
				JSONROOT.put("Records", emp);

				// Convert Java Object to Json
				String jsonArray = gson.toJson(JSONROOT);

				response.getWriter().print(jsonArray);
				
			}
			catch(Exception e){
				System.out.println(e);}
			}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
