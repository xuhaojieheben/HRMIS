package com.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.business.iservice.ISysMenuBll;
import com.data.iservice.ISysMenuService;
import model.sysmodel.entity.Sys_Menu;
import model.viewmodel.entity.MenuModel;

@Repository("sysMenuBll")
public class SysMenuBll implements ISysMenuBll{
	@Autowired
	private ISysMenuService _iSysMenuService;
	@Override
	public List<MenuModel> CreateMenu() {
		// TODO 自动生成的方法存根
		List<Sys_Menu> menus = _iSysMenuService.SelectAll();
		
		
		
		List<MenuModel> rootMenus = new ArrayList<MenuModel>();
		List<Sys_Menu> result = menus.stream()
				.filter((Sys_Menu menu) -> menu.getMENU_PARENT().contains("1000"))
				.collect(Collectors.toList());
		if(result != null) {
			result.forEach(m -> {
				MenuModel _model = new MenuModel();
				_model.setMenuID(m.getMENU_ID());
				_model.setMenuLEVEL(m.getMEUN_LEVEL());
				_model.setMenuPARENT(m.getMENU_PARENT());
				_model.setMenuSORT(m.getMENU_SORT());
				_model.setMenuTITLE(m.getMENU_TITLE());
				_model.setMenuURL(m.getMENU_URL());
				_model.setMenuChild(CreateMenuChild(menus ,_model));
				rootMenus.add(_model);
			});
		}
		/*Map<String, Object> menuParam = new HashMap<String, Object>();
		menuParam.put("rootMenus", rootMenus);*/
		return rootMenus;
	}

	@Override
	public List<MenuModel> CreateMenuChild(List<Sys_Menu> menuList, MenuModel menuModel) {
		// TODO 自动生成的方法存根
		String parentId = menuModel.getMenuID();
        List<MenuModel> _list = new ArrayList<MenuModel>();
        List<Sys_Menu> childrenMenu = menuList.stream().filter((Sys_Menu menu) -> menu.getMENU_PARENT().equals(parentId)).collect(Collectors.toList());//(t => t.ParentGuid == parentGuid);
        childrenMenu.forEach(m -> {
        	MenuModel _tmp = new MenuModel();
            _tmp.setMenuID(m.getMENU_ID());
            _tmp.setMenuLEVEL(m.getMEUN_LEVEL());
            _tmp.setMenuPARENT(m.getMENU_PARENT());
            _tmp.setMenuSORT(m.getMENU_SORT());
            _tmp.setMenuTITLE(m.getMENU_TITLE());
            _tmp.setMenuURL(m.getMENU_URL());
            _tmp.setMenuChild(CreateMenuChild(menuList ,_tmp));
            _list.add(_tmp);
        });
        return _list;
	}
}
