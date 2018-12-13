package com.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.custom.annotation.AllowAnonymous;
import com.custom.annotation.AllowAnonymousType;

@Controller
@RequestMapping("/index")
@AllowAnonymous(value=AllowAnonymousType.Authorize)
public class IndexController {
	/*@Autowired
	private IUserBusiness _iUserBusiness;*/
	
	@RequestMapping("/index.do")
	public void Index(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		//return "index";
	}
	
	@RequestMapping("/main.do")
	public String Main() {
		return "main";
	}
	
	@RequestMapping("/menu.do")
	public String Menu() {
		return "menu";
	}
}
