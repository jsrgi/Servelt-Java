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
import com.google.gson.GsonBuilder;

@WebServlet("/ViewHardware")
public class ViewHardware extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
   
    public ViewHardware() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		
		List<Hardware> hardList = new ArrayList<Hardware>();
		
		ServiceProvider provide=new ServiceProvider();
		
		if(action!=null)
		{
			try
			{
				if(action.equals("list"))
				{ 
					hardList=provide.getAllHard();
					
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", hardList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				}
				else if(action.equals("create")||action.equals("update"))
				{
					Hardware hard=new Hardware();
					if(request.getParameter("hardId")!=null)
					{
						hard.setHardId(request.getParameter("hardId"));
					}
					if(request.getParameter("hardCat")!=null)
					{
						hard.setHardCat(request.getParameter("hardCat"));
					}
					if(request.getParameter("hardBrand")!=null)
					{
						hard.setHardBrand(request.getParameter("hardBrand"));
					}
					if(request.getParameter("hardDes")!=null)
					{
						hard.setHardDes(request.getParameter("hardDes"));
					}
					if(request.getParameter("hardStock")!=null)
					{
						hard.setHardStock(request.getParameter("hardStock"));
					}
					if(request.getParameter("hardWar")!=null)
					{
						hard.setHardWar(request.getParameter("hardWar"));
					}
					if(action.equals("create"))
					 {
						 provide.addHard(hard);
					 }
					 else if(action.equals("update")){
						 provide.updateHard(hard);
					 }
					
					
					
					// Return in the format required by jTable plugin
					 JSONROOT.put("Result", "OK");
					 JSONROOT.put("Record", hard);
					 
					// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
		
					
					
				}
				else if(action.equals("delete"))
				{
					 
					 if(request.getParameter("hardId")!=null)
					 {
						 String hard_id=request.getParameter("hardId");
						 provide.deleteHard(hard_id);
						 
						// Return in the format required by jTable plugin
						 JSONROOT.put("Result", "OK");
					 
						// Convert Java Object to Json
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
					 
					 }
				
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
