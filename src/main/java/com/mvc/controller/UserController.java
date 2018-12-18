package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.custom.annotation.AllowAnonymous;
import com.custom.annotation.AllowAnonymousType;
import com.data.iservice.ISysUserService;

import model.sysmodel.entity.Sys_User;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
@AllowAnonymous(value=AllowAnonymousType.Authorize)
public class UserController {
	@Autowired
	private ISysUserService _iSysUserService;
	@RequestMapping("/userList.do")
	@ResponseBody
	public JSONObject UserList(@RequestBody Sys_User user, HttpSession session) {
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("currPage", user.getCurrPage());
		map.put("pageSize", user.getPageSize());
		
		List<Sys_User> list = _iSysUserService.QuerySysUserByPage(user);
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> _map = new HashMap<String, Object>();
		jsonObject.put("rows", list);
		jsonObject.put("total", 23);*/
		
		return null;
	}
}
