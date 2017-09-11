package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pojo.Booksinfo;

public class BooksinfoDao {
	public ArrayList<Booksinfo> getAllBooks() {
		ArrayList<Booksinfo> list = new ArrayList<Booksinfo>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_booksinfo order by bid "; 
																
			PreparedStatement pre = con.prepareStatement(sql);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Booksinfo info = new Booksinfo();
				
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTname(rs.getString(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSname(rs.getString(9));
				
				list.add(info);
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
	//根据书名
	public ArrayList<Booksinfo> selBook(String dname){
		ArrayList<Booksinfo> list = new ArrayList<Booksinfo>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_booksinfo where bname like ?"; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			String name = "%" + dname +"%";
			pre.setString(1,name );
			
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Booksinfo info = new Booksinfo();
				
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTname(rs.getString(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSname(rs.getString(9));
				
				list.add(info);
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
	//根据出版社
	public ArrayList<Booksinfo> selBookByCon(String dname){
		ArrayList<Booksinfo> list = new ArrayList<Booksinfo>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_booksinfo where bconcern like ?"; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			String name = "%" + dname +"%";
			pre.setString(1,name );
			
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Booksinfo info = new Booksinfo();
				
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTname(rs.getString(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSname(rs.getString(9));
				
				list.add(info);
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
	
	//根据作者
	public ArrayList<Booksinfo> selBookByA(String dname){
		ArrayList<Booksinfo> list = new ArrayList<Booksinfo>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_booksinfo where bauthor like ?"; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			String name = "%" + dname +"%";
			pre.setString(1,name);
			
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Booksinfo info = new Booksinfo();
				
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTname(rs.getString(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSname(rs.getString(9));
				
				list.add(info);
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
	
	
	public ArrayList<Booksinfo> selBookByT(String dname){
		ArrayList<Booksinfo> list = new ArrayList<Booksinfo>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_booksinfo where tname like ?"; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			String name = "%" + dname +"%";
			pre.setString(1,name);
			
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Booksinfo info = new Booksinfo();
				
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTname(rs.getString(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSname(rs.getString(9));
				
				list.add(info);
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
	
	public ArrayList<Booksinfo> selBookByIsbn(String bisbn){
		ArrayList<Booksinfo> list = new ArrayList<Booksinfo>();
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_booksinfo where bisbn like ?"; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			String name = "%" + bisbn +"%";
			pre.setString(1,name);
			
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				Booksinfo info = new Booksinfo();
				
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTname(rs.getString(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSname(rs.getString(9));
				
				list.add(info);
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
	
	public Booksinfo selBookById(int bid){
		Booksinfo info = null ;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from View_booksinfo where bid=?"; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1,bid );
			
			ResultSet rs = pre.executeQuery();
			info = new Booksinfo();
			
			while (rs.next()) {
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTname(rs.getString(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSname(rs.getString(9));
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
		return info;
	}
	
	public Booksinfo selBookByName(String bname){
		Booksinfo info = null ;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "select * from booksinfo where bname=?"; 
																
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, bname);
			
			ResultSet rs = pre.executeQuery();
			
			info = new Booksinfo();
			
			if (rs.next()) {
				info.setBid(rs.getInt(1));
				info.setBname(rs.getString(2));
				info.setBauthor(rs.getString(3));
				info.setBisbn(rs.getString(4));
				info.setBconcern(rs.getString(5));
				info.setTid(rs.getInt(6));
				info.setBprice(rs.getDouble(7));
				info.setBnum(rs.getInt(8));
				info.setSid(rs.getInt(9));
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
		return info;
	}
	

	public int addBooksinfo(Booksinfo info){
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "insert into booksinfo values(?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement pre = con.prepareStatement(sql);
			
			pre.setString(1, info.getBname());
			pre.setString(2, info.getBauthor());
			pre.setString(3, info.getBisbn());
			pre.setString(4, info.getBconcern());
			pre.setInt(5, info.getTid());
			pre.setDouble(6, info.getBprice());
			pre.setInt(7, info.getBnum());
			pre.setInt(8, info.getSid());
			
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
	
	public int updateBooksinfo(int bid, String bname, String bauthor, String bisbn,  String bconcern, int  tid, Double bprice, int bnum, int sid) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "update booksinfo set bname=?, bauthor=?, bisbn=?, bconcern=?, tid=?, bprice=?, bnum=?, sid=? where bid=?";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setString(1, bname);
			pre.setString(2, bauthor);
			pre.setString(3, bisbn);
			pre.setString(4, bconcern);
			pre.setInt(5, tid);
			pre.setDouble(6, bprice);
			pre.setInt(7, bnum);
			pre.setInt(8, sid);
			pre.setInt(9, bid);
			
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
	
	public int updateBooksinfo(int bid, int bnum) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "update booksinfo set bnum=bnum-?  where bid=?";
			PreparedStatement pre = con.prepareStatement(sql);
			
			pre.setInt(1, bnum);
			pre.setInt(2, bid);
			
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
	
	
	public int delBooksinfo(int bid) {
		int n = 0;
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookstore";
			con = DriverManager.getConnection(url, "sa", "sasa");

			String sql = "delete from booksinfo where bid=?";
			PreparedStatement pre = con.prepareStatement(sql);

			pre.setInt(1, bid);
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
