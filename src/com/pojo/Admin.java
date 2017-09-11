package com.pojo;

public class Admin {
	private int adid;
	private int aid;
	private String adname;
	private String adpwd;
	private String adrole;
	
	public int getAdid() {
		return adid;
	}
	public void setAdid(int adid) {
		this.adid = adid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getAdpwd() {
		return adpwd;
	}
	public void setAdpwd(String adpwd) {
		this.adpwd = adpwd;
	}
	public String getAdrole() {
		return adrole;
	}
	public void setAdrole(String adrole) {
		this.adrole = adrole;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.adname;
	}
}
