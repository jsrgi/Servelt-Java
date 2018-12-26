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
import com.asset.model.OpSys;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/ViewOpSys")
public class ViewOpSys extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
   
    public ViewOpSys() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
String action = request.getParameter("action");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		
		List<OpSys> osList = new ArrayList<OpSys>();
		
		ServiceProvider provide=new ServiceProvider();
		
		if(action!=null)
		{
			try
			{
				if(action.equals("list"))
				{ 
					osList=provide.getAllOpSys();
					
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", osList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				}
				else if(action.equals("create")||action.equals("update"))
				{
					OpSys os=new OpSys();
					if(request.getParameter("osId")!=null)
					{
						os.setOsId(request.getParameter("osId"));
					}
					if(request.getParameter("osType")!=null)
					{
						os.setOsType(request.getParameter("osType"));
					}
					if(request.getParameter("osVer")!=null)
					{
						os.setOsVer(request.getParameter("osVer"));
					}
					if(request.getParameter("osLice")!=null)
					{
						os.setOsLice(request.getParameter("osLice"));
					}
					if(request.getParameter("osBit")!=null)
					{
						os.setOsBit(request.getParameter("osBit"));
					}
					if(request.getParameter("osCost")!=null)
					{
						os.setOsCost(request.getParameter("osCost"));
					}
					if(request.getParameter("osAvl")!=null)
					{
						os.setOsAvl(request.getParameter("osAvl"));
					}
					
					if(action.equals("create"))
					 {
						 provide.addOpSys(os);;
					 }
					 else if(action.equals("update")){
						 provide.updateOpSys(os);;
					 }
					
					
					
					// Return in the format required by jTable plugin
					 JSONROOT.put("Result", "OK");
					 JSONROOT.put("Record", os);
					 
					// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
		
					
					
				}
				else if(action.equals("delete"))
				{
					 
					 if(request.getParameter("osId")!=null)
					 {
						 String os_id=request.getParameter("osId");
						 provide.deleteHard(os_id);
						 
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
