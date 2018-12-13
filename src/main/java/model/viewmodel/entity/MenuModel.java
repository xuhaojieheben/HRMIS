package model.viewmodel.entity;

public class MenuModel {
	private String MenuID;
	private String MenuTITLE;
	private String MenuPARENT;
	private String MenuURL;
	private int MenuLEVEL;
	private int MenuSORT;
	private Object MenuChild;
	public String getMenuID() {
		return MenuID;
	}
	public void setMenuID(String menuID) {
		MenuID = menuID;
	}
	public String getMenuTITLE() {
		return MenuTITLE;
	}
	public void setMenuTITLE(String menuTITLE) {
		MenuTITLE = menuTITLE;
	}
	public String getMenuPARENT() {
		return MenuPARENT;
	}
	public void setMenuPARENT(String menuPARENT) {
		MenuPARENT = menuPARENT;
	}
	public String getMenuURL() {
		return MenuURL;
	}
	public void setMenuURL(String menuURL) {
		MenuURL = menuURL;
	}
	public int getMenuLEVEL() {
		return MenuLEVEL;
	}
	public void setMenuLEVEL(int menuLEVEL) {
		MenuLEVEL = menuLEVEL;
	}
	public int getMenuSORT() {
		return MenuSORT;
	}
	public void setMenuSORT(int menuSORT) {
		MenuSORT = menuSORT;
	}
	public Object getMenuChild() {
		return MenuChild;
	}
	public void setMenuChild(Object menuChild) {
		MenuChild = menuChild;
	}
}
