package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pojo.Bookin;
import com.pojo.Booksinfo;

public class BookinDao {
	public int addBookin(Bookin in){
		int n = 0;

		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "insert into bookin values(?, ?, ?, ?, ?)"; 
			PreparedStatement pre = con.prepareStatement(sql);
			
			pre.setInt(1, in.getBid());
			pre.setInt(2, in.getAid());
			pre.setDouble(3, in.getIprice());
			pre.setInt(4, in.getInum());
			pre.setString(5, in.getItime());
			
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
	
	public ArrayList<Bookin> getAllBookin() {
		ArrayList<Bookin> list = new ArrayList<Bookin>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_bookin order by id "; 
																
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Bookin in = new Bookin();
				in.setId(rs.getInt(1));
				in.setBname(rs.getString(2));
				in.setAname(rs.getString(3));
				in.setIprice(rs.getDouble(4));
				in.setInum(rs.getInt(5));
				in.setItime(rs.getString(6));
		
				list.add(in);
			}
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
		return list;
	}
	
	
	public Bookin getBookin(int bid) {
		Bookin in = new Bookin();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from bookin where bid=? "; 
															
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, bid);
			
			ResultSet rs = pre.executeQuery();

			if(rs.next()) {
				in.setId(rs.getInt(1));
				in.setBid(rs.getInt(2));
				in.setAid(rs.getInt(3));
				in.setIprice(rs.getDouble(4));
				in.setInum(rs.getInt(5));
				in.setItime(rs.getString(6));
		}
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
		return in;
	}

}
