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
import com.asset.model.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/ViewRequest")
public class ViewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
       
    
    public ViewRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action=request.getParameter("action");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		List<Request> reqList=new ArrayList<Request>();
		 ServiceProvider provide=new ServiceProvider();
		 if(action != null)
		 {
			 try {
				
				if(action.equals("list")) 
				{
					reqList = provide.getAllRequest(); 
					
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", reqList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				}
				 
			} catch (Exception e) {
				System.err.println(e);
			}
		 }
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
