package model.sysmodel.entity;

import java.util.Date;

public class BaseModel {
	private int ID;
	private Date CREATEDATE;
	private int ISDEL;
	private int CurrPage;
	private int PageSize;
	public int getCurrPage() {
		return CurrPage;
	}
	public void setCurrPage(int currPage) {
		this.CurrPage = currPage;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		this.PageSize = pageSize;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	public Date getCREATEDATE() {
		return CREATEDATE;
	}

	public void setCREATEDATE(Date cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	
	public int getISDEL() {
		return ISDEL;
	}

	public void setISDEL(int iSDEL) {
		ISDEL = iSDEL;
	}
/*	public Date getBeginCreateDate() {
		return BeginCreateDate;
	}
	public void setBeginCreateDate(Date beginCreateDate) {
		BeginCreateDate = beginCreateDate;
	}
	public Date getEndCreateDate() {
		return EndCreateDate;
	}
	public void setEndCreateDate(Date endCreateDate) {
		EndCreateDate = endCreateDate;
	}
	private Date BeginCreateDate;
	private Date EndCreateDate;*/
}
