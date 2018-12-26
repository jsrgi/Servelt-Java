package com.asset.controller;

import java.io.IOException;
//import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.swing.JOptionPane;

@WebServlet("/ComplaintStatUpdate")
public class ComplaintStatUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private PreparedStatement pst;
   
    public ComplaintStatUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.setContentType("text/html");
		//String comId=request.getParameter("comId");
		//String stat=JOptionPane.showInputDialog(null,"What is your name?","Enter your name",JOptionPane.QUESTION_MESSAGE);
		try {
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
