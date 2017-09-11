package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pojo.Type;

public class TypeDao {
	public ArrayList<Type> getAllType() {
		ArrayList<Type> list = new ArrayList<Type>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select tid, tname from type order by tid ";
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Type type = new Type();
				type.setTid(rs.getInt(1));
				type.setTname(rs.getString(2));

				list.add(type);
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
	
	
	// 新增类型
	public int addType(String tname) {
		int n = 0;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "insert into type values(?) ";
			PreparedStatement pre = con.prepareStatement(sql);

				pre.setString(1, tname);
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

		// 删除部门
		public int delType(int tid) {
			int n = 0;
			Connection con = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
				con = DriverManager.getConnection(url, "sa", "sasa");

				String sql = "delete from type where tid = ?";
				PreparedStatement pre = con.prepareStatement(sql);

				pre.setInt(1, tid);
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

		// 修改类型
		public int updateType(String tname, int tid) {
			int n = 0;
			Connection con = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
				con = DriverManager.getConnection(url, "sa", "sasa");

				String sql = "update type set tname=?  where tid=?";
				PreparedStatement pre = con.prepareStatement(sql);

				pre.setString(1, tname);
				pre.setInt(2, tid);
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
		
		//根据类型ID得到唯一的类型对象
		public Type getTypeById(int tid){
			Type type = new Type();
			Connection con = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url="jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
				 con=DriverManager.getConnection(url,"sa","sasa");
				
				String sql="select tid, tname from type where tid = ? order by tid ";
				PreparedStatement pre=con.prepareStatement(sql);
				pre.setInt(1, tid);
				ResultSet rs=pre.executeQuery();
				
				while(rs.next()){
					type.setTid(rs.getInt(1));
					type.setTname(rs.getString(2));
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
			
			return type;
		}

}
