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
import com.asset.model.OpSys;



@WebServlet("/AddOpSys")
public class AddOpSys extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddOpSys() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		OpSys os=null;
		Connection conn=null;
		try {
			 os=new OpSys();
			 os.setOsId(request.getParameter("osId"));
			 os.setOsType(request.getParameter("osType"));
			 os.setOsVer(request.getParameter("osVer"));
			 os.setOsLice(request.getParameter("osLice"));
			 os.setOsBit(request.getParameter("osBit"));
			 os.setOsCost(request.getParameter("osCost"));
			 os.setOsAvl(request.getParameter("osAvl"));
			
			String sql="INSERT INTO ostable(osId,osType,osVer,osLice,osBit,osCost,osAvl) VALUES (?,?,?,?,?,?,?)";
			
			conn=DbConnect.getSqlConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, os.getOsId());
			pst.setString(2, os.getOsType());
			pst.setString(3, os.getOsVer());
			pst.setString(4, os.getOsLice());
			pst.setString(5, os.getOsBit());
			pst.setString(6, os.getOsCost());
			pst.setString(7, os.getOsAvl());
			
			pst.executeUpdate();
			
			/* PrintWriter out=response.getWriter();
				System.out.println("You have successfully registered....!");
				out.println("You have successfully registered....!");*/
			
			request.getRequestDispatcher("addOpSys.html").include(request, response);
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		
		
		
	}

}
