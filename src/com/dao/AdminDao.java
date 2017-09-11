package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pojo.Admin;

public class AdminDao {
	//登录查询
	public Admin checkLogin(String adname, String adpwd) {
		Admin admin = new Admin();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");
			String sql = "select top 1 adid, aid, adname, adpwd, adrole from admin where adname=? and adpwd=?";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, adname);
			pre.setString(2, adpwd);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				admin.setAdid(rs.getInt(1));
				admin.setAid(rs.getInt(2));
				admin.setAdname(rs.getString(3));
				admin.setAdpwd(rs.getString(4));
				admin.setAdrole(rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return admin;
	}
	
	//查询
	public ArrayList<Admin> getAllAdmin() {
		ArrayList<Admin> list = new ArrayList<Admin>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");
			String sql = "select adid, aid, adname, adpwd, adrole from admin";
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Admin admin = new Admin();
				
				admin.setAdid(rs.getInt(1));
				admin.setAid(rs.getInt(2));
				admin.setAdname(rs.getString(3));
				admin.setAdpwd(rs.getString(4));
				admin.setAdrole(rs.getString(5));
				
				list.add(admin);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
    //新增
	public int addAdmin(Admin admin) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");
			String sql = "insert into admin values(?,?,?,?)";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setInt(1, admin.getAid());
			pre.setString(2, admin.getAdname());
			pre.setString(3, admin.getAdpwd());
			pre.setString(4, admin.getAdrole());

			n = pre.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return n;
	}
	
	public int updateAdmin(String adname, String adpwd, String adrole, int adid) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "update admin set  adname=?, adpwd=?, adrole=?  where adid=?";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setString(1, adname);
			pre.setString(2, adpwd);
			pre.setString(3, adrole);
			pre.setInt(4, adid);
			
			n = pre.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
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
	
	public Admin getAdmin(int adid) {
		Admin admin = new Admin();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");
			String sql = "select top 1 adid, aid, adname, adpwd, adrole from admin where adid=?";
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, adid);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				admin.setAdid(rs.getInt(1));
				admin.setAid(rs.getInt(2));
				admin.setAdname(rs.getString(3));
				admin.setAdpwd(rs.getString(4));
				admin.setAdrole(rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return admin;
	}

}
