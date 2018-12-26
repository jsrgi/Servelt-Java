package com.asset.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asset.dbConnect.DbConnect;

@WebServlet("/ForgotPass")
public class ForgotPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String dbUsername=null;
	String Password=null;
	String Mail=null;
	String result=null;
	int count=0;
	boolean status=false;
   
    public ForgotPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			
			dbUsername=request.getParameter("email");
			Connection con=DbConnect.getSqlConnection();
			Statement stmt=con.createStatement();
			stmt.executeQuery("SELECT userMail,userPwd FROM `usertable` WHERE userMail='"+dbUsername+"'");
			ResultSet rs=stmt.getResultSet();
			System.out.println(dbUsername);
			while(rs.next())
			{
				Mail=rs.getString("userMail");
				Password=rs.getString("userPwd");
				count++;
			}
			if(count>0)
			{
				Mailer mail=new Mailer();
				mail.send("gayu.deepi23@gmail.com", "deepika23", Mail, "Forgot Password?", Password);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Password sucessfully sent to your email');");
				out.println("</script>");
			}
			else{
				 out.println("<script type=\"text/javascript\">");
				    out.println("alert('Email id doesnot exist');");
				    out.println("</script>");
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		
		
		
	}

}
