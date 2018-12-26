package com.asset.controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asset.dbConnect.DbConnect;
import com.asset.model.Software;



@WebServlet("/AddSoftware")
public class AddSoftware extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddSoftware() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Software soft=null;
		Connection conn=null;
		
		try {
			
			soft = new Software();
			
			soft.setSoftId(request.getParameter("softId"));
			soft.setSoftName(request.getParameter("softName"));
			soft.setSoftVer(request.getParameter("softVer"));
			soft.setSoftLice(request.getParameter("softLice"));
			soft.setSoftPlat(request.getParameter("softPlat"));
			soft.setSoftAvl(request.getParameter("softAvl"));
			
			String sql="INSERT INTO softtable(softId,softName,softVer,softLice,softPlat,softAvl) VALUES(?,?,?,?,?,?) ";
			
			conn=DbConnect.getSqlConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, soft.getSoftId());
			pst.setString(2, soft.getSoftName());
			pst.setString(3, soft.getSoftVer());
			pst.setString(4, soft.getSoftLice());
			pst.setString(5, soft.getSoftPlat());
			pst.setString(6, soft.getSoftAvl());
			
			
			pst.executeUpdate();
			
			 /*PrintWriter out=response.getWriter();
				System.out.println("You have successfully registered....!");
				out.println("You have successfully registered....!");*/
			
			request.getRequestDispatcher("addSoftware.html").include(request, response);

			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
				
	}

}
