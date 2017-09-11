package com.pojo;

public class Bookin {
	private int id;
	private int bid;
	private int aid;
	private double iprice;
	private int inum;
	private String itime;
	private String aname;
	private String bname;

	public int getId() {
		return id;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public double getIprice() {
		return iprice;
	}

	public void setIprice(double iprice) {
		this.iprice = iprice;
	}

	public int getInum() {
		return inum;
	}

	public void setInum(int inum) {
		this.inum = inum;
	}

	public String getItime() {
		return itime;
	}

	public void setItime(String itime) {
		this.itime = itime;
	}
}
