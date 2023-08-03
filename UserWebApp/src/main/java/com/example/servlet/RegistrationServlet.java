package com.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import com.example.db.PostgresqlConn;
import com.example.interf.DBConnection;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// not register başarılı olduktan sonra başka sayfaya yönlendir
		RequestDispatcher ds;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		DBConnection postgre = new PostgresqlConn();
		Connection connectin = postgre.connectToDb();

		String name = request.getParameter("name");
		String surName = request.getParameter("surname");
		int age = 0;
		try {
			age = Integer.parseInt(request.getParameter("age"));

			boolean kontrol = postgre.addToDb(name, surName, age);
			if (kontrol) {

				out.print("registration succesful");
				ds = request.getRequestDispatcher("/home.html");
				ds.include(request, response);
				postgre.closeconnection();
			}else {
				out.print("registration failed");
			
				ds = request.getRequestDispatcher("/home.html");
				ds.include(request, response);
			}

		} catch (Exception e) {
			out.print("Sorry UserName or Password Error!");
			ds = request.getRequestDispatcher("/home.html");
			ds.include(request, response);
		}

		/*
		 * String url = "jdbc:postgresql:test"; String userName = "postgres"; String
		 * password = "postgres"; Connection conn = null; PreparedStatement ps; String
		 * query; try { Class.forName("org.postgresql.Driver"); conn =
		 * DriverManager.getConnection(url, userName, password);
		 * System.out.println("basarili");
		 * 
		 * } catch (Exception e) { System.out.println("hata");
		 * System.out.println(e.getMessage());
		 * 
		 * }
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
