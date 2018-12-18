package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.business.iservice.ISysMenuBll;
import com.core.library.BaseController;
import com.custom.annotation.AllowAnonymous;
import com.custom.annotation.AllowAnonymousType;
import com.data.iservice.ISysMenuService;

import model.sysmodel.entity.Sys_Menu;
import model.viewmodel.entity.MenuModel;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/menu")
@AllowAnonymous(value=AllowAnonymousType.Authorize)
public class MenuController extends BaseController{
	@Autowired
	private ISysMenuBll _iSysMenuBll;
	@Autowired
	private ISysMenuService _iSysMenuService;
	@RequestMapping("/userManage.do")
	public String UserManage() {
		return "usermanage";
	}

	@RequestMapping(value="/powerManage.do",method= {RequestMethod.POST})
	@ResponseBody
	public List<MenuModel> Power() {
		List<MenuModel> menus = _iSysMenuBll.CreateMenu();
		return menus;
	}
	
	@RequestMapping("/menuManage.do")
	public String MenuManage() {
		return "menumanage";
	}

	@RequestMapping("/menuList.do")
	@ResponseBody
	public JSONObject MenuList(HttpSession session) {
		
		/*List<Sys_Menu> list = _iSysMenuService.QuerySysUserByPage(menu);
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> _map = new HashMap<String, Object>();
		jsonObject.put("rows", list);
		jsonObject.put("total", list.size());*/
		return null;
	}
}
