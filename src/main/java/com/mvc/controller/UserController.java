package com.mvc.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.core.library.Page;
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
	public JSONObject UserList(@ModelAttribute Page<Sys_User> user, HttpSession session) {
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("currPage", user.getCurrPage());
		map.put("pageSize", user.getPageSize());
		*/
		System.out.println(user.getCurrPage());
		Page<Sys_User> page = new Page<Sys_User>();  
		page.setCurrPage(user.getCurrPage());
	    List<Sys_User> users = _iSysUserService.QuerySysUserByPage(page);
	    page.setResults(users);
	    JSONObject jsonObject = new JSONObject();
		jsonObject.put("rows", page.getResults());
		jsonObject.put("total", page.getTotalRecord());
		return jsonObject;
	}
}
