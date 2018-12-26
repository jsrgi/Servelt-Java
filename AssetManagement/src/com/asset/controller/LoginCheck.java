package com.asset.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asset.dbConnect.DbConnect;
import com.asset.model.User;


@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			
			Connection conn = DbConnect.getSqlConnection();
			Statement stmt=conn.createStatement();
			
			User user = new User();
			user.setUserMail(request.getParameter("email").trim());
			user.setUserPwd(request.getParameter("pass").trim());
			
			ResultSet rs=stmt.executeQuery("select * from usertable where userMail='"+user.getUserMail()+"'and userPwd='"+user.getUserPwd()+"'");
			
			if(user.getUserMail().equals("admin@gmail.com") && user.getUserPwd().equals("admin"))
			{
				request.getRequestDispatcher("adminDashboard.html").include(request, response);
			}
			else{
				
				String result="fail";
				String status="0";
				String uname="";
				String uid="";
				while(rs.next()){
					uname=rs.getString("userName");
					uid=rs.getString("userId");
				    status=rs.getString("userStatus");
					result="success";
					
					System.out.println(uname);
					System.out.println(uid);
					System.out.println(status);
				}
				if(result.equals("success") && status.equals("1"))
					
				{
					response.getWriter().println("<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>ASSET MANAGEMENT</title><span class='userid' style='display:none'>"+uid+"</span><span class='uname' style='display:none'>"+uname+"</span>");
					request.getRequestDispatcher("employee.html").include(request, response);
				}
				else
				{
					//response.getWriter().println();
					//System.out.println("invalid user");
					request.getRequestDispatcher("loginError.html").include(request, response);
				}
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
			
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
