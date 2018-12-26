package com.asset.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asset.dao.ServiceProvider;
import com.asset.dbConnect.DbConnect;

@WebServlet("/RequestAccept")
public class RequestAccept extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PreparedStatement pst;
	
    public RequestAccept() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String reqId=request.getParameter("reqId");
		//System.out.println(reqId);
		String reqType=request.getParameter("reqType");
		//System.out.println(reqType);
		String reqTypeId=request.getParameter("reqTypeId");
		//System.out.println(reqTypeId);
		//System.out.println(reqId);
		try {
			String status="accept";
			ServiceProvider provide=new ServiceProvider();
			Connection con=DbConnect.getSqlConnection();
			String qry="UPDATE `reqtable` SET `reqStatus` = 'accepted',reqStat = 0 WHERE `reqtable`.`reqId` = ?";
			pst=con.prepareStatement(qry);
			pst.setString(1,reqId);
			pst.executeUpdate();
			
			if(status.equals("accept"))
			{
				if(reqType.equals("hardware"))
				{
					//System.out.println("hardware");
					provide.updateHardStatus(reqTypeId);
					
				}
				if(reqType.equals("software"))
				{
				//System.out.println("software");
					provide.updateSoftStatus(reqTypeId);
				
				}
				if(reqType.equals("opsys"))
				{
				//System.out.println("opsys");
					provide.updateOpSysStatus(reqTypeId);
					
				}
			}
			
			
			
			
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
			}

}
