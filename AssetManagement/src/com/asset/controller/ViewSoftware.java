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
import com.asset.model.Software;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/ViewSoftware")
public class ViewSoftware extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
       
  
    public ViewSoftware() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		
		List<Software> softList = new ArrayList<Software>();
		
		ServiceProvider provide=new ServiceProvider();
		
		if(action!=null)
		{
			try
			{
				if(action.equals("list"))
				{ 
					softList=provide.getAllSoft();
					
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", softList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				}
				
				else if(action.equals("create")||action.equals("update"))
				{
					Software soft=new Software();
					if(request.getParameter("softId")!=null)
					{
						soft.setSoftId(request.getParameter("softId"));
					}
					if(request.getParameter("softName")!=null)
					{
						soft.setSoftName(request.getParameter("softName"));
					}
					if(request.getParameter("softVer")!=null)
					{
						soft.setSoftVer(request.getParameter("softVer"));
						
					}
					if(request.getParameter("softLice")!=null)
					{
						soft.setSoftLice(request.getParameter("softLice"));
					}
					if(request.getParameter("softPlat")!=null)
					{
						soft.setSoftPlat(request.getParameter("softPlat"));
					}
					if(request.getParameter("softAvl") !=null)
					{
						soft.setSoftAvl(request.getParameter("softAvl"));
					}
					
					if(action.equals("create"))
					 {
						 provide.addSoft(soft);;
					 }
					 else if(action.equals("update")){
						 provide.updateSoft(soft);
					 }
					
					
					
					// Return in the format required by jTable plugin
					 JSONROOT.put("Result", "OK");
					 JSONROOT.put("Record", soft);
					 
					// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
		
					
					
				}
				else if(action.equals("delete"))
				{
					 
					 if(request.getParameter("softId")!=null)
					 {
						 String soft_id=request.getParameter("softId");
						 provide.deleteHard(soft_id);
						 
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
