package com.business.iservice;

import java.util.List;
import model.sysmodel.entity.Sys_Menu;
import model.viewmodel.entity.MenuModel;

/*
 *       �˵�����
 */
public interface ISysMenuBll {
	/*
	 *      ��ȡ���ڵ�
	 */
	List<MenuModel> CreateMenu();
	
	/*
	 * �ݹ鸸�ڵ��ȡ�ӽڵ�
	 */
	List<MenuModel> CreateMenuChild(List<Sys_Menu> menuList, MenuModel menuModel);
}
