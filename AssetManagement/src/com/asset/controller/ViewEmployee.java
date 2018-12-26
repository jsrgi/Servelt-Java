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


@WebServlet("/ViewEmployee")
public class ViewEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
   
    public ViewEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		List<Employee> empList=new ArrayList<Employee>();
		 ServiceProvider provide=new ServiceProvider();
		 
		 if(action!=null)
		 {
			 try{
				 
				 if(action.equals("list"))
				 {
					empList = provide.getAllEmp(); 
					
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", empList);

					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
					
				 }
				 else if(action.equals("create")|| action.equals("update"))
				 {
					 Employee emp=new Employee();
					 if(request.getParameter("empId")!=null)
					 {
						 String emp_id=request.getParameter("empId");
						 emp.setEmpId(emp_id);
					 }
					 
					 if(request.getParameter("empName")!=null)
					 {
						 emp.setEmpName(request.getParameter("empName"));
					 }
					 
					 if(request.getParameter("empDob")!=null)
					 {
						 emp.setEmpDob(request.getParameter("empDob"));
					 }
					 
					 if(request.getParameter("empGen")!=null)
					 {
						 emp.setEmpGen(request.getParameter("empGen"));
					 }
					 
					 if(request.getParameter("empMobile")!=null)
					 {
						 emp.setEmpMobile(request.getParameter("empMobile"));
					 }
					 
					 if(request.getParameter("empDoj")!=null)
					 {
						 emp.setEmpDoj(request.getParameter("empDoj"));
					 }
					 
					 if(request.getParameter("empMail")!=null)
					 {
						 emp.setEmpMail(request.getParameter("empMail"));
					 }
					 
					 if(request.getParameter("empPwd")!=null)
					 {
						 emp.setEmpPwd(request.getParameter("empPwd"));
					 }
					
					 if(request.getParameter("empConPwd")!=null)
					 {
						 emp.setEmpConPwd(request.getParameter("empConPwd"));
					 }
					 
					 if(request.getParameter("empSal")!=null)
					 {
						 emp.setEmpSal(request.getParameter("empSal"));
					 }
					 
					 if(request.getParameter("empAdrs")!=null)
					 {
						 emp.setEmpAdrs(request.getParameter("empAdrs"));
					 }
					
					 if(action.equals("create"))
					 {
						 provide.addEmp(emp);
					 }
					 else if(action.equals("update")){
						 provide.updateEmp(emp);
					 }
					 
					 
					// Return in the format required by jTable plugin
					 JSONROOT.put("Result", "OK");
					 JSONROOT.put("Record", emp);
					 
					// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
		
					 
				 }
				 else if(action.equals("delete"))
				 {
					 /*Delete a Record*/
					 
					 
					 if(request.getParameter("empId")!=null)
					 {
						 String emp_id=request.getParameter("empId");
						 provide.deleteEmp(emp_id);
						 
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
