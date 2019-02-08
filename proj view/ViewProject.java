package com.itManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itManage.DaoConn.ServiceDao;
import com.itManage.model.Project;


/**
 * Servlet implementation class ViewProject
 */
@WebServlet("/ViewProject")
public class ViewProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String action = request.getParameter("action");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		List<Project> proList=new ArrayList<Project>();
		//String action="list";
		ServiceDao dao=new ServiceDao();
		if(action!=null)
		{
			
		
			try {   
				if(action.equals("list"))
				{
					proList=ServiceDao. projectAll();

					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", proList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				
				}
				else if (action.equals("update")) {
					Project pro=new Project();
					if (request.getParameter("pro_id") != null) {
						int pro_id = Integer.parseInt(request.getParameter("pro_id"));
						pro.setP_id(pro_id);
					}
					if (request.getParameter("p_name") != null) {
						pro.setP_name(request.getParameter("p_name"));
					}

					if (request.getParameter("p_des") != null) {
						pro.setP_des(request.getParameter("p_des"));
					}

					if (request.getParameter("doj") != null) {
						pro.setDoj(request.getParameter("doj"));
					}
					
					if (request.getParameter("aggDate") != null) {
						pro.setAggDate(request.getParameter("aggDate"));
					}
					if (request.getParameter("managerDetails") != null) {
						pro.setManagerDetails(request.getParameter("managerDetails"));
					}
					if (request.getParameter("numEmp") != null) {
						pro.setNumEmp(Integer.parseInt(request.getParameter("numEmp")));
					}

					if (request.getParameter("email") != null) {
						pro.setEmail(request.getParameter("email"));
					}

						dao.updateProject(pro);
					

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", pro);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				}
				else if (action.equals("delete")) {
					if (request.getParameter("pro_id") != null) {
						int pro_id = Integer.parseInt(request.getParameter("pro_id"));
						
						dao.deleteProject(pro_id);
						JSONROOT.put("Result", "OK");
           
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
					}
				
			}
				}
			catch (Exception e) {
				System.out.println(e);// TODO: handle exception
			}
		}
		//doGet(request, response);
	}

}
