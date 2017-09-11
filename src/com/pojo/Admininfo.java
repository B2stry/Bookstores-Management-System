package com.pojo;

public class Admininfo {
	private int aid;
	private int did;
	private String aname;
	private String aphone;
	private String adress;
	private String asfz;
	private String atutas;
	private String dname;
	private String atime;
	
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAphone() {
		return aphone;
	}
	public void setAphone(String aphone) {
		this.aphone = aphone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getAsfz() {
		return asfz;
	}
	public void setAsfz(String asfz) {
		this.asfz = asfz;
	}
	public String getAtutas() {
		return atutas;
	}
	public void setAtutas(String atutas) {
		this.atutas = atutas;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.aname;
	}
}
