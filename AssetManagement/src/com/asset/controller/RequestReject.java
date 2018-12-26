package com.asset.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.asset.dao.ServiceProvider;
import com.asset.dbConnect.DbConnect;

@WebServlet("/RequestReject")
public class RequestReject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private PreparedStatement pst;

	 public RequestReject() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String reqId=request.getParameter("reqId");
		try {
			//String status="accept";
			//ServiceProvider provide=new ServiceProvider();
			Connection con=DbConnect.getSqlConnection();
			String qry="UPDATE `reqtable` SET `reqStatus` = 'rejected',reqStat=2 WHERE `reqtable`.`reqId` = ?";
			pst=con.prepareStatement(qry);
			pst.setString(1,reqId);
			pst.executeUpdate();
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
