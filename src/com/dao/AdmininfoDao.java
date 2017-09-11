package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.pojo.Admininfo;
import com.pojo.Dept;

public class AdmininfoDao {

	// 查询员工信息
	public ArrayList<Admininfo> getAllAdmininfo() {
		ArrayList list = new ArrayList();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_admininfo order by aid ";
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Admininfo info = new Admininfo();

				info.setAid(rs.getInt(1));
				info.setDname(rs.getString(2));
				info.setAname(rs.getString(3));
				info.setAphone(rs.getString(4));
				info.setAdress(rs.getString(5));
				info.setAsfz(rs.getString(6));
				info.setAtutas(rs.getString(7));
				info.setAtime(rs.getString(8));

				list.add(info);
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

	// 添加员工
	public int addAdmininfo(Admininfo info) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "insert into admininfo values(?, ?, ?, ?, ?, ?,?)";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setInt(1, info.getDid());
			pre.setString(2, info.getAname());
			pre.setString(3, info.getAphone());
			pre.setString(4, info.getAdress());
			pre.setString(5, info.getAsfz());
			pre.setString(6, info.getAtutas());
			pre.setString(7, info.getAtime());
			
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

	// 修改员工信息
	public int updateAdmininfo(int did, String aname, String aphone, String address,  String asfz, String atutas, String atime, int aid) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "update admininfo set did=?,  aname=?, aphone=?, adress=?, asfz=?, atutas=?, atime=?  where aid=?";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setInt(1, did);
			pre.setString(2, aname);
			pre.setString(3, aphone);
			pre.setString(4, address);
			pre.setString(5, asfz);
			pre.setString(6, atutas);
			pre.setString(7, atime);
			pre.setInt(8, aid);
			
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

	// 删除员工信息
	public int delAdmininfo(int aid) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "delete from admininfo where aid=?";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setInt(1, aid);
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

	public Admininfo getAdmininfo(int aid) {
		Admininfo info = new Admininfo();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select *  from admininfo where  aid=? ";

			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, aid);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				info.setAid(rs.getInt(1));
				info.setDid(rs.getInt(2));
				info.setAname(rs.getString(3));
				info.setAphone(rs.getString(4));
				info.setAdress(rs.getString(5));
				info.setAsfz(rs.getString(6));
				info.setAtutas(rs.getString(7));
				info.setAtime(rs.getString(8));
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
		return info;
	}

	public Admininfo getAdmininfoByname(String aname) {
		Admininfo info = new Admininfo();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select *  from admininfo where  aname=? ";

			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, aname);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				info.setAid(rs.getInt(1));
				info.setDid(rs.getInt(2));
				info.setAname(rs.getString(3));
				info.setAphone(rs.getString(4));
				info.setAdress(rs.getString(5));
				info.setAsfz(rs.getString(6));
				info.setAtutas(rs.getString(7));
				info.setAtime(rs.getString(8));
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
		return info;
	}
	
	public ArrayList<Admininfo> getAdmininfoByName(String aname) {
		ArrayList list = new ArrayList();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_admininfo where aname like ? or dname like ? or adress like ?";
			PreparedStatement pre = con.prepareStatement(sql);
			String name = "%" + aname + "%";
			pre.setString(1, name);
			pre.setString(2, name);
			pre.setString(3, name);
			
			ResultSet rs = pre.executeQuery();

			while(rs.next()) {
				Admininfo info = new Admininfo();

				info.setAid(rs.getInt(1));
				info.setDname(rs.getString(2));
				info.setAname(rs.getString(3));
				info.setAphone(rs.getString(4));
				info.setAdress(rs.getString(5));
				info.setAsfz(rs.getString(6));
				info.setAtutas(rs.getString(7));
				info.setAtime(rs.getString(8));

				list.add(info);
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

	public int updateAdmin(String atutas, int aid) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "update admininfo set  atutas=?  where aid=?";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setString(1, atutas);
			pre.setInt(2, aid);
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
}