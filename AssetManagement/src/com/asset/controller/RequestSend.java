package com.asset.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asset.dbConnect.DbConnect;
import com.asset.model.Request;


@WebServlet("/RequestSend")
public class RequestSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RequestSend() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Request req=null;
		Connection conn=null;
		try {
			
			req=new Request();
			//req.setReqId(request.getParameter("reqId"));
			req.setEmpId(request.getParameter("empId"));
			req.setEmpName(request.getParameter("empName"));
			req.setReqType(request.getParameter("reqType"));
			req.setReqTypeId(request.getParameter("reqTypeId"));
			req.setReqName(request.getParameter("reqName"));
			req.setReqVer(request.getParameter("reqVer"));
			req.setReqQry(request.getParameter("reqQry"));
			
			String sql="insert into reqtable(empId,empName,reqType,reqTypeId,reqName,reqVer,reqQry) values(?,?,?,?,?,?,?)";
			conn=DbConnect.getSqlConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, req.getEmpId());
			pst.setString(2, req.getEmpName());
			pst.setString(3, req.getReqType());
			pst.setString(4, req.getReqTypeId());
			pst.setString(5, req.getReqName());
			pst.setString(6, req.getReqVer());
			pst.setString(7, req.getReqQry());
			
			pst.executeUpdate();
			
			request.getRequestDispatcher("employee.html").include(request, response);	
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
