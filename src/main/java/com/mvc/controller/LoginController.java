package com.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.core.library.AmericaEnum;
import com.core.library.BaseController;
import com.core.library.CreateLoginUserModel;
import com.core.library.LoginUserModel;
import com.core.library.SessionEnum;
import com.custom.annotation.AllowAnonymous;
import com.custom.annotation.AllowAnonymousType;
import com.data.iservice.ISysRoleService;
import com.data.iservice.ISysUserService;
import hrmis.context.unity.MD5Util;
import model.viewmodel.entity.LoginParam;
import model.sysmodel.entity.Sys_Role;
import model.sysmodel.entity.Sys_User;

@Controller
@RequestMapping("/login")
@AllowAnonymous(value=AllowAnonymousType.Allow)
public class LoginController extends BaseController {
	@Autowired
	private ISysUserService _iSysUserService;
	
	@Autowired
	private ISysRoleService _iSysRoleService;
	
	@RequestMapping("/login.do")
	public void Login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
	@RequestMapping(value="/doLogin.do",method= {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> UserLogin(@RequestBody LoginParam user, HttpSession session) throws Exception {
		/*Sys_Role role = new Sys_Role();
		role.setROLE_ID("002");
		role.setROLE_NAME("∆’Õ®”√ªß");
		role.setCREATEDATE(new Date());
		_iSysRoleService.Add(role);*/
		List<Sys_Role> roles = _iSysRoleService.SelectAll();
		System.out.println(roles);
		String name = user.getAccount();
		String pd =  user.getPassword();
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("ACCOUNT", name);
		mapParam.put("PASSWORD", MD5Util.String2MD5(pd));
		List<Sys_User> _list = _iSysUserService.ValidateUser(mapParam);
		
		if(_list.size() > 0) {
			LoginUserModel _lUserModel = CreateLoginUserModel.Bind(_list.get(0));
			session.setAttribute(SessionEnum.USER_SESSION.name(), _lUserModel);
			return JsonData(_lUserModel);
		}else {
			return Failure(AmericaEnum.Error_NameOrPwError);
		}
/*		Sys_User t = new Sys_User();
		t.setACCOUNT("admin");
		t.setBIRTHDAY(new Date());
		t.setCREATEDATE(new Date());
		t.setNAME("admin");
		t.setPASSWORD(MD5Util.String2MD5("000000"));
		
		_iSysUserService.Add(t);*/
		//return mapParam;
	}
}

