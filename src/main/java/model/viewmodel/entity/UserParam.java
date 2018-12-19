package model.viewmodel.entity;

import com.iflytek.mybatis.page.dialect.PageBase;

public class UserParam extends PageBase{
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
