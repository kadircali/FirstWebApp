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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.db.PostgresqlConn;
import com.example.interf.DBConnection;
import com.example.users.Users;

/**
 * Servlet implementation class EditServlet
 */
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the id
		String idParam = request.getParameter("id");
		int id = Integer.parseInt(idParam);
		List<Users> userList = new ArrayList<>();
		DBConnection postgre = new PostgresqlConn();
		ServletContext ct = request.getServletContext();
		RequestDispatcher dp = request.getRequestDispatcher("/edit.jsp");

		postgre.connectToDb();
		try {
			ResultSet rs = postgre.listUserById(id);
			while (rs.next()) {

				Users user = new Users();
				user.setId(id);
				user.setUserName(rs.getString("user_name"));
				user.setSurName(rs.getString("user_surname"));
				user.setAge(rs.getInt("age"));
				// userList.add(user);
				postgre.closeconnection();
				ct.setAttribute("liste", user);
				 dp.forward(request, response); //redirect dene
				//response.sendRedirect("edit.jsp");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("hata 2");
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		DBConnection postgre = new PostgresqlConn();
		PrintWriter out = response.getWriter();
		RequestDispatcher ds = request.getRequestDispatcher("/home.html");
		
		String userName = request.getParameter("name");
		String surName = request.getParameter("surname");
		int id = Integer.parseInt(request.getParameter("id"));
		int age = 0;
		try {
			age = Integer.parseInt(request.getParameter("age"));

			boolean kontrol = postgre.update(userName, surName, age, id); // burada update metodu çağır

			if (kontrol) {
				System.out.println(kontrol);
				out.write("update succesful");

				ds.include(request, response);
				postgre.closeconnection();
			} else {
				out.print("update failed");

				ds.include(request, response);

			}

		} catch (Exception e) {
			out.print("update error");
			ds = request.getRequestDispatcher("/home.html");
			ds.include(request, response);
		}
	}
}
