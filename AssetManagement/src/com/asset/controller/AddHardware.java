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
import com.asset.model.Hardware;




@WebServlet("/AddHardware")
public class AddHardware extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddHardware() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Hardware hard=null;
		Connection conn=null;
		try {
			
			hard=new Hardware();
			
			hard.setHardId(request.getParameter("hardId"));
			hard.setHardCat(request.getParameter("hardCat"));
			hard.setHardBrand(request.getParameter("hardBrand"));
			hard.setHardDes(request.getParameter("hardDes"));
			hard.setHardStock(request.getParameter("hardStock"));
			hard.setHardWar(request.getParameter("hardWar"));
			
			String sql="insert into hardtable(hardId,hardCat,hardBrand,hardDes,hardStock,hardWar) values(?,?,?,?,?,?)";
			conn=DbConnect.getSqlConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, hard.getHardId());
			pst.setString(2, hard.getHardCat());
			pst.setString(3, hard.getHardBrand());
			pst.setString(4, hard.getHardDes());
			pst.setString(5, hard.getHardStock());
			pst.setString(6, hard.getHardWar());
			pst.executeUpdate();
			
			
			 /*PrintWriter out=response.getWriter();
				System.out.println("You have successfully registered....!");
				out.println("You have successfully registered....!");*/
			
			request.getRequestDispatcher("addHardware.html").include(request, response);

			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
