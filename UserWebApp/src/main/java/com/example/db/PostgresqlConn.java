package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.interf.DBConnection;

public class PostgresqlConn implements DBConnection {

	String url = "jdbc:postgresql:test";
	String userName = "postgres";
	String password = "postgres";
	private static Connection conn = null;
	private PreparedStatement ps;
	ResultSet rs = null;
	String query;

	@Override
	public Connection connectToDb() {
		try {
			Class.forName("org.postgresql.Driver");
			this.conn = DriverManager.getConnection(url, userName, password);
			System.out.println("bağlantı basarili");

			return conn;
		} catch (Exception e) {
			System.out.println("bağlantı hatası");
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean closeconnection() {
		try {
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addToDb(String name, String surname, int age) {
		query = "INSERT INTO users (user_name,user_surname,age) values(?,?,?)";
		if (conn == null) {
			System.out.println("no connection");

		} else {
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, name);
				ps.setString(2, surname);
				ps.setInt(3, age);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println("hata2");
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet listUser() {

		query = "SELECT * FROM users";

		try {
			this.conn = DriverManager.getConnection(url, userName, password);
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	@Override
	public ResultSet listUserById(int id) {
		query = "SELECT user_name,user_surname,age FROM users WHERE id=?";

		try {

			// this.conn = DriverManager.getConnection(url, userName, password);

			ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			return rs;
		} catch (Exception e) {
			System.out.println("listUserById hata");
			System.out.println(e.getMessage());

		}
		return rs;

	}

	@Override
	public boolean update(String name, String surname, int age, int id) {
		query = " UPDATE users SET user_name = ?, user_surname=?,age=? WHERE id = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setInt(3, age);
			ps.setInt(4, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("failed update");
		}
		return false;
	}

	/*
	 * void test() { query =
	 * "SELECT user_name,user_surname,age FROM users WHERE ID=?";
	 * 
	 * try {
	 * 
	 * //this.conn = DriverManager.getConnection(url, userName, password);
	 * 
	 * ps = conn.prepareStatement(query);
	 * 
	 * ps.setInt(1, 2);
	 * 
	 * rs = ps.executeQuery(); // burada bir hata var
	 * 
	 * while(rs.next()) { System.out.println(rs.getString("user_name")); }
	 * 
	 * } catch (Exception e) { System.out.println("hata");
	 * System.out.println(e.getMessage());
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * 
	 * public static void main(String[] args) { PostgresqlConn conn = new
	 * PostgresqlConn(); conn.connectToDb();
	 * 
	 * conn.test(); }
	 * 
	 * 
	 * public Connection connectToDb() { try {
	 * Class.forName("org.postgresql.Driver"); this.conn =
	 * DriverManager.getConnection(url, userName, password);
	 * System.out.println("basarili");
	 * 
	 * return conn; } catch (Exception e) { System.out.println("hata");
	 * System.out.println(e.getMessage()); return null; } }
	 * 
	 * 
	 * public boolean closeconnection() { try { conn.close(); return true; } catch
	 * (Exception e) { e.printStackTrace(); return false; }
	 * 
	 * }
	 * 
	 * public static void main(String[] args) { PostgresqlConn conn = new
	 * PostgresqlConn(); conn.connectToDb(); }
	 * 
	 * 
	 * public boolean addToDb(String name, String surname, int age) { query =
	 * "INSERT INTO users (user_name,user_surname,age) values(?,?,?)"; if (conn ==
	 * null) { System.out.println("no connection");
	 * 
	 * } else { try { ps = conn.prepareStatement(query); ps.setString(1, name);
	 * ps.setString(2, surname); ps.setInt(3, age); return true; } catch
	 * (SQLException e) { System.out.println("hata2"); e.printStackTrace(); } }
	 * return false; }
	 */

}
