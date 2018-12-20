package model.viewmodel.entity;



import java.util.Date;

import com.custom.annotation.FieldSqlWhereAnnotation;
import com.iflytek.mybatis.page.dialect.PageBase;

public class UserParam extends PageBase{
	@FieldSqlWhereAnnotation(DbField="NAME")
	private String RealName;
	@FieldSqlWhereAnnotation(DbField="CREATEDATE")
	private Date BeginTime;
	@FieldSqlWhereAnnotation(DbField="CREATEDATE")
	private Date EndTime;

	public String getRealName() {
		return RealName;
	}
	public void setRealName(String realName) {
		RealName = realName;
	}
	public Date getBeginTime() {
		return BeginTime;
	}
	public void setBeginTime(Date beginTime) {
		BeginTime = beginTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}
}
