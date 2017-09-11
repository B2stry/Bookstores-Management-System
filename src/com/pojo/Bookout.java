package com.pojo;

public class Bookout  {
	private int oid;
	private int bid;
	private int aid;
	private int onum;
	private String otime;
	private double oprice;
	private String aname;
	private String bname;
	
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
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
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
	public int getOnum() {
		return onum;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public String getOtime() {
		return otime;
	}
	public void setOtime(String otime) {
		this.otime = otime;
	}
	public double getOprice() {
		return oprice;
	}
	public void setOprice(double oprice) {
		this.oprice = oprice;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.bname;
	}
	
/*	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.bid;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Bookout){
			Bookout out =(Bookout)obj;
			if(out.getBname().equals(this.bname) && out.getBid() == this.bid){
				return true;
			}else
				return false;
		}else
			return false;
	}*/
}
