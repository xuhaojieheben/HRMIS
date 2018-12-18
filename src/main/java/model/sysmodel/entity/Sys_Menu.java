package model.sysmodel.entity;

public class Sys_Menu extends BaseModel{
	private String MENU_ID;
	private String MENU_TITLE;
	private String MENU_DESCRIBE;
	private String MENU_PARENT;
	private String MENU_URL;
	private int MEUN_LEVEL;
	private int MENU_SORT;
	private String MENU_REMARK;
	private String MENU_TEMP;
	private int ISDEL;
	public String getMENU_ID() {
		return MENU_ID;
	}
	public void setMENU_ID(String mENU_ID) {
		MENU_ID = mENU_ID;
	}
	public String getMENU_TITLE() {
		return MENU_TITLE;
	}
	public void setMENU_TITLE(String mENU_TITLE) {
		MENU_TITLE = mENU_TITLE;
	}
	public String getMENU_DESCRIBE() {
		return MENU_DESCRIBE;
	}
	public void setMENU_DESCRIBE(String mENU_DESCRIBE) {
		MENU_DESCRIBE = mENU_DESCRIBE;
	}
	public String getMENU_PARENT() {
		return MENU_PARENT;
	}
	public void setMENU_PARENT(String mENU_PARENT) {
		MENU_PARENT = mENU_PARENT;
	}
	public String getMENU_URL() {
		return MENU_URL;
	}
	public void setMENU_URL(String mENU_URL) {
		MENU_URL = mENU_URL;
	}
	public int getMEUN_LEVEL() {
		return MEUN_LEVEL;
	}
	public void setMEUN_LEVEL(int mEUN_LEVEL) {
		MEUN_LEVEL = mEUN_LEVEL;
	}
	public int getMENU_SORT() {
		return MENU_SORT;
	}
	public void setMENU_SORT(int mENU_SORT) {
		MENU_SORT = mENU_SORT;
	}
	public String getMENU_REMARK() {
		return MENU_REMARK;
	}
	public void setMENU_REMARK(String mENU_REMARK) {
		MENU_REMARK = mENU_REMARK;
	}
	public String getMENU_TEMP() {
		return MENU_TEMP;
	}
	public void setMENU_TEMP(String mENU_TEMP) {
		MENU_TEMP = mENU_TEMP;
	}
	public int getISDEL() {
		return ISDEL;
	}
	public void setISDEL(int iSDEL) {
		ISDEL = iSDEL;
	}
}
