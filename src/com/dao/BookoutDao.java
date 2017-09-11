package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.pojo.Bookin;
import com.pojo.Bookout;

public class BookoutDao {
	public ArrayList<Bookout> getAllBookout() {
		ArrayList<Bookout> list = new ArrayList<Bookout>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_bookout order by oid "; 
																
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Bookout out = new Bookout();
				out.setOid(rs.getInt(1));
				out.setBname(rs.getString(2));
				out.setAname(rs.getString(3));
				out.setOprice(rs.getDouble(4));
				out.setOnum(rs.getInt(5));
				out.setOtime(rs.getString(6));
				out.setBid(rs.getInt(7));
		
				list.add(out);
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
	
	public int addBookout(Bookout out){
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "insert into bookout values(?, ?, ?, ?, ?)"; 
			PreparedStatement pre = con.prepareStatement(sql);
			
			pre.setInt(1, out.getBid());
			pre.setInt(2, out.getAid());	
			pre.setInt(3, out.getOnum());
			pre.setString(4, out.getOtime());
			pre.setDouble(5, out.getOprice());
			
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
	
	public int getAllBookout(int bid) {
		Connection con = null;
		int a=0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select sum(onum) from bookout where bid=? "; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, bid);
			
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				String str = rs.getString(1);
				if(str != null){
					a = new Integer(str);
				}else
					a=0;
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
		return a;
	}
}
