package com.pojo;

public class Supplier {
	private int sid;
	private String sname;
	private String slink;
	private String asddress;
	private String sphone;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSlink() {
		return slink;
	}
	public void setSlink(String slink) {
		this.slink = slink;
	}
	public String getAsddress() {
		return asddress;
	}
	public void setAsddress(String asddress) {
		this.asddress = asddress;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.sname;
	}
	

}
