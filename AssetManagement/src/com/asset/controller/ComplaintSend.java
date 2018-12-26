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
import com.asset.model.Complaint;

@WebServlet("/ComplaintSend")
public class ComplaintSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ComplaintSend() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//System.out.println("successfully send");
		
		Complaint com = null;
		Connection conn=null;
		try {
			com=new Complaint();
			//com.setComId(request.getParameter("comId"));
			com.setEmpId(request.getParameter("empId"));
			com.setEmpName(request.getParameter("empName"));
			com.setComType(request.getParameter("comType"));
			com.setComRecDt(request.getParameter("comRecDt"));
			com.setComTarDt(request.getParameter("comTarDt"));
			String str=request.getParameter("textedit");
			str=str.replaceAll("<p>", "");
			str=str.replaceAll("</p>", "");
					
			com.setComDesc(str);
			
			//com.setComDesc(request.("comDesc"));
			
			//var comDesc =tinymce.get('comDesc').getContent();
			
			
			String sql="insert into comtable(empId,empName,comType,comRecDt,comTarDt,comDesc) values(?,?,?,?,?,?)";
			conn=DbConnect.getSqlConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			
		//	pst.setString(1, com.getComId());
			pst.setString(1, com.getEmpId());
			pst.setString(2, com.getEmpName());
			pst.setString(3, com.getComType());
			pst.setString(4, com.getComRecDt());
			pst.setString(5, com.getComTarDt());
			pst.setString(6, com.getComDesc());
			
			pst.executeUpdate();
			//System.out.println("successfully send");
			
			request.getRequestDispatcher("empComplaint.html").include(request, response);
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

}
