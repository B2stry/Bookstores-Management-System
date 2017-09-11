package com.pojo;

public class Dept {
	private int did;
	private String dname;
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.dname;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.did;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Dept){
			Dept dept = (Dept)obj;
			if(dept.getDname().equals(this.dname) && dept.getDid() == this.did){
				return true;
			}else
				return false;
		}else
			return false;
	}

}
