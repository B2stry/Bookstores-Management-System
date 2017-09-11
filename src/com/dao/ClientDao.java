package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pojo.Client;

public class ClientDao {
	public ArrayList<Client> getAllClient() {
		ArrayList list = new ArrayList();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from client order by cid ";
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Client client = new Client();
				client.setCid(rs.getInt(1));
				client.setCname(rs.getString(2));
				client.setCsex(rs.getString(3));
				client.setCphone(rs.getString(4));
				client.setCnum(rs.getInt(5));
				
				list.add(client);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//ÃÌº”ª·‘±
	public int addClient(Client client){
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "insert into client values(?, ?, ?, 0)"; 
			PreparedStatement pre = con.prepareStatement(sql);
			
			pre.setString(1, client.getCname());
			pre.setString(2, client.getCsex());
			pre.setString(3, client.getCphone());
			
			n= pre.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return n;
	}
	
	
	public Client getClientById(int cid) {
		Client client = new Client();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from client where cid = ? ";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setInt(1, cid);
			ResultSet rs = pre.executeQuery();
		
			if (rs.next()) {
		
				client.setCid(rs.getInt(1));
				client.setCname(rs.getString(2));
				client.setCsex(rs.getString(3));
				client.setCphone(rs.getString(4));
				client.setCnum(rs.getInt(5));
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return client;
	}
	
	public int UpdateCnum(int sum, int cid){
		int n = 0;
		
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "update client set cnum=cnum+?  where cid = ? ";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setInt(1, sum/10);
			pre.setInt(2, cid);
			n = pre.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return n;
		
	}
}
