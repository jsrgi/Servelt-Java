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
import com.itManage.model.Task;


/**
 * Servlet implementation class ViewProject
 */
@WebServlet("/ViewAssignTaskTL")
public class ViewAssignTaskTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAssignTaskTL() {
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
		String emp_id = request.getParameter("emp_id");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		List<Task> taskList=new ArrayList<Task>();
		//String action="list";
		ServiceDao dao=new ServiceDao();
		if(action!=null)
		{
			
		
			try {   
				if(action.equals("list"))
				{
					taskList=ServiceDao.AssignTaskAll(emp_id);

					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", taskList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				
				}
				else if (action.equals("update")) {
					Task t=new Task();
					
					
					if (request.getParameter("task_id") != null) {
						int task_id = Integer.parseInt(request.getParameter("task_id"));
						t.setT_id(task_id);
					}
					if (request.getParameter("t_name") != null) {
						t.setT_name(request.getParameter("t_name"));
					}

					if (request.getParameter("t_des") != null) {
						t.setT_des(request.getParameter("t_des"));
					}

					if (request.getParameter("t_assignee") != null) {
						t.setAssignee(request.getParameter("t_assignee"));
					}
					
					if (request.getParameter("t_ddate") != null) {
						t.setDdate(request.getParameter("t_ddate"));
					}
					
						dao.updateAssignedTask(t);
					

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", t);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				}
				else if (action.equals("delete")) {
					if (request.getParameter("task_id") != null) {
						int task_id = Integer.parseInt(request.getParameter("task_id"));
						
						dao.daleteAssignedTask(task_id);
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
		
	}

}
