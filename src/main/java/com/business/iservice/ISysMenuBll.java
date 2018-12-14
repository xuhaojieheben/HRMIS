package com.business.iservice;

import java.util.List;
import model.sysmodel.entity.Sys_Menu;
import model.viewmodel.entity.MenuModel;

/*
 *       菜单操作
 */
public interface ISysMenuBll {
	/*
	 *      获取父节点
	 */
	List<MenuModel> CreateMenu();
	
	/*
	 * 递归父节点获取子节点
	 */
	List<MenuModel> CreateMenuChild(List<Sys_Menu> menuList, MenuModel menuModel);
}
