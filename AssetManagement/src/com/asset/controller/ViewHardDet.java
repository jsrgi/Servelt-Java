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
import com.asset.model.Hardware;
import com.google.gson.Gson;


@WebServlet("/ViewHardDet")
public class ViewHardDet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
    
    public ViewHardDet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ServiceProvider provide=new ServiceProvider();
		List<Hardware> hardList = new ArrayList<Hardware>();
		
		try {
			
			hardList=provide.getAllHard();
			response.setContentType("application/json");
			
            Gson gson=new Gson();
			
			JSONROOT.put("Results", "OK");
			JSONROOT.put("Record", hardList);
			
			String jsonRec=gson.toJson(JSONROOT);
			response.getWriter().print(jsonRec);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
