package com.core.library;

public class LoginUserModel {
	private int Id;
	private String Account;
	private String Name;
/*	private  Date CreateDate;*/
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getName() {
		return Name;
	}
	public void setRealName(String name) {
		Name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
/*	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}*/
}
