package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pojo.Supplier;

public class SupplierDao {
	
	public ArrayList<Supplier> getAllSupplier() {
		ArrayList list = new ArrayList();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from supplier order by sid ";
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Supplier su = new Supplier();
				su.setSid(rs.getInt(1));
				su.setSname(rs.getString(2));
				su.setSlink(rs.getString(3));
				su.setAsddress(rs.getString(4));
				su.setSphone(rs.getString(5));

				list.add(su);
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
	
	// 新增供应商
	public int addSupplier(Supplier su) {
		int n = 0;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "insert into supplier values(?,?,?,?) ";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setString(1, su.getSname());
			pre.setString(2, su.getSlink());
			pre.setString(3, su.getAsddress());
			pre.setString(4, su.getSphone());

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

		// 删除供应商
		public int delSupplier(int sid) {
			int n = 0;
			Connection con = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
				con = DriverManager.getConnection(url, "sa", "sasa");

				String sql = "delete from type where tid = ?";
				PreparedStatement pre = con.prepareStatement(sql);

				pre.setInt(1, sid);
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

		// 修改供应商
		public int updateSupplier(Supplier su) {
			int n = 0;
			Connection con = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
				con = DriverManager.getConnection(url, "sa", "sasa");

				String sql = "update supplier set sname=? ,slink=?, sadress=? ,sphone=? where sid=?";
				PreparedStatement pre = con.prepareStatement(sql);

				pre.setString(1, su.getSname());
				pre.setString(2, su.getSlink());
				pre.setString(3, su.getAsddress());
				pre.setString(4, su.getSphone());
				pre.setInt(5, su.getSid());
				
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
		
		//根据供应商ID得到唯一的供应商对象
		public Supplier getSupplierById(int sid){
			Supplier su = new Supplier();
			Connection con = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url="jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
				 con=DriverManager.getConnection(url,"sa","sasa");
				
				String sql="select *  from supplier where sid = ? order by sid ";
				PreparedStatement pre=con.prepareStatement(sql);
				pre.setInt(1, sid);
				ResultSet rs=pre.executeQuery();
				
				while(rs.next()){
					su.setSid(rs.getInt(1));
					su.setSname(rs.getString(2));
					su.setSlink(rs.getString(3));
					su.setAsddress(rs.getString(4));
					su.setSphone(rs.getString(5));
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return su;
		}

}
