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
import com.asset.model.Employee;




@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Employee emp=null;
		Connection conn=null;
		
		try {
			emp=new Employee();
			
			emp.setEmpId(request.getParameter("empId"));
			emp.setEmpName(request.getParameter("empName"));
			emp.setEmpDob(request.getParameter("empDob"));
			emp.setEmpGen(request.getParameter("empGen"));
			emp.setEmpMobile(request.getParameter("empMobile"));
			emp.setEmpDoj(request.getParameter("empDoj"));
			emp.setEmpMail(request.getParameter("empMail"));
			emp.setEmpPwd(request.getParameter("empPwd"));
			emp.setEmpConPwd(request.getParameter("empConPwd"));
			emp.setEmpSal(request.getParameter("empSal"));
			emp.setEmpAdrs(request.getParameter("empAdrs"));
			
			
			String sql="update emptable set empName=?,empDob=?,empGen=?,empMobile=?,empDoj=?,empMail=?,empPwd=?,empConPwd=?,empSal=?,empAdrs=? where empId=?";
			String str="update usertable set userName=?,userMail=?,userPwd=? where userId=?";
			
			 conn=DbConnect.getSqlConnection();
			 PreparedStatement pst=conn.prepareStatement(sql);
			
			 pst.setString(1, emp.getEmpName());
			 pst.setString(2, emp.getEmpDob());
			 pst.setString(3, emp.getEmpGen());
			 pst.setString(4, emp.getEmpMobile());
			 pst.setString(5, emp.getEmpDoj());
			 pst.setString(6, emp.getEmpMail());
			 pst.setString(7, emp.getEmpPwd());
			 pst.setString(8, emp.getEmpConPwd());
			 pst.setString(9, emp.getEmpSal());
			 pst.setString(10, emp.getEmpAdrs());
			 pst.setString(11, emp.getEmpId());
			 
			pst.executeUpdate();
			pst.close();
			
			PreparedStatement pstmt=conn.prepareStatement(str);
				
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpMail());
			pstmt.setString(3, emp.getEmpPwd());
			pstmt.setString(4, emp.getEmpId());
				
			pstmt.executeUpdate();
			pstmt.close();
			 
			 
			/* PrintWriter out=response.getWriter();
				System.out.println("You have successfully registered....!");
				out.println("You have successfully registered....!");*/
			 request.getRequestDispatcher("EmpProfile.html").include(request, response);
			 
			 
			 
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
	}

}
