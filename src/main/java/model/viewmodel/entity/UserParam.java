package model.viewmodel.entity;

import com.custom.annotation.FieldSqlWhereAnnotation;
import com.iflytek.mybatis.page.dialect.PageBase;

public class UserParam extends PageBase{
	@FieldSqlWhereAnnotation(DbField="NAME")
	private String RealName;

	public String getRealName() {
		return RealName;
	}
	public void setRealName(String realName) {
		RealName = realName;
	}
}
