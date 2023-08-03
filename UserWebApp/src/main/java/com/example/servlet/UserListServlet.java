package com.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import com.example.db.PostgresqlConn;
import com.example.interf.DBConnection;

/**
 * Servlet implementation class UserListServlet
 */
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		DBConnection postgre = new PostgresqlConn();
		Connection connectin = postgre.connectToDb();
		ResultSet result = postgre.listUser();

		ServletContext context = getServletContext();
		context.setAttribute("resultset", result);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/UserList.jsp");
	    dispatcher.forward(request, response);
	    postgre.closeconnection();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
