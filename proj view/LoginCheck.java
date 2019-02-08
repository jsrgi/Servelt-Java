package com.itManage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.itManage.DaoConn.ServiceDao;
import com.itManage.model.Employee;
import com.itManage.model.Task;
import com.itManage.model.User;
import com.itMange.db.DbConnection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceDao dao=null;
		try {
			dao=new ServiceDao();
			Connection conn = DbConnection.getSqlConnection();
			Statement stmt = conn.createStatement();
			// String uname=;
			// String password=
			User user = new User();
			user.setPassword(request.getParameter("u_pass").trim());
			user.setUser_name(request.getParameter("u_name").trim());
			ResultSet rs = stmt.executeQuery("select * from user_account where user_name='" + user.getUser_name()
					+ "'and user_password='" + user.getPassword() + "' ");
			String result="";
			String role = "";
			HttpSession session = request.getSession();
			if (user.getUser_name().equals("admin") && user.getPassword().equals("admin")) {
				 session = request.getSession();
				session.setAttribute("name", user.getUser_name());
				session.setAttribute("pass", user.getPassword());
				
				request.getRequestDispatcher("admin.html").include(request, response);
			} else {
				 result = "fail";
			
				while (rs.next()) {
					role = rs.getString("user_role");
					result = "success";
					System.out.println(role);
					user.setEmp_id(rs.getInt("emp_id"));
				}
				if(result.equals("success"))
				{
					rs = stmt.executeQuery("select * from project where pro_id=(select pro_id from projectempdetail where emp_id='" + user.getEmp_id() + "' )");
					
					session.setAttribute("project_id", "NA");
					session.setAttribute("project name", "Siemens vertical Pool");
					while (rs.next()) {
					session.setAttribute("project_id", rs.getInt("pro_id"));
					session.setAttribute("project name", rs.getString("p_name"));
					}
				}
				if (role.equals("team leader") && result.equals("success")) {
					// request.setAttribute("name",user.getUser_name());
					
					session.setAttribute("name", user.getUser_name());
					session.setAttribute("pass", user.getPassword());
					
					request.getRequestDispatcher("TeamLeader.html").include(request, response);

				} else if (role.equals("employee") && result.equals("success")) {
					
					 session = request.getSession();
					session.setAttribute("name", user.getUser_name());
					session.setAttribute("pass", user.getPassword());
					
					request.getRequestDispatcher("empuser.html").include(request, response);

				}
                  else if (role.equals("manager") && result.equals("success")) {
					
					 session = request.getSession();
					session.setAttribute("name", user.getUser_name());
					session.setAttribute("pass", user.getPassword());					
					
					request.getRequestDispatcher("TeamLeader.html").include(request, response);

				}

				else if(result.equals("fail"))
				{
                      request.getRequestDispatcher("error.html").forward(request, response);
				
				}
			
			
			
			
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			// e.printStackTrace();
		}

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
