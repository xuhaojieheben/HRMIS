package com.componentscan.unity;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.core.library.LoginUserModel;
import com.core.library.SessionEnum;
import com.custom.annotation.AllowAnonymous;
import com.custom.annotation.AllowAnonymousType;

public class Interceptor implements HandlerInterceptor  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO 自动生成的方法存根
		//HandlerMethod handlerMethod = (HandlerMethod) handler;
		if(handler instanceof HandlerMethod) {
	        HandlerMethod h = (HandlerMethod)handler;
	        System.out.println(h.getMethod().getName());
	        //System.out.println("用户想执行的操作是:"+h.getMethodAnnotation(AllowAnonymous.class).value());
	        //AllowAnonymous validate = ((HandlerMethod)handler).getMethodAnnotation(AllowAnonymous.class);
			AllowAnonymous validate = h.getMethod().getDeclaringClass().getAnnotation(AllowAnonymous.class);
	        if(validate == null){
	            throw new Exception("未配置自定义注解");
	        }
	        AllowAnonymousType aaCode = validate.value();
	        if(aaCode == aaCode.Authorize){ //需要验证
	        	//验证用户
	        	LoginUserModel user_model = (LoginUserModel)request.getSession().getAttribute(SessionEnum.USER_SESSION.name());
	        	if (user_model == null) {
	        		//request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);  
	        		//request.getRequestDispatcher("/login.jsp").forward(request, response);  
	        		//跳转到服务器内部的一个功能处理方法
	        	      //request.getRequestDispatcher("/dispather/b").forward(request,response);

	        	      //重定向一个功能方法
	        	      //response.sendRedirect(request.getContextPath()+"/login.jsp");
	        	      //request.getRequestDispatcher("/login/login.do").forward(request,response);
	    			//response.sendRedirect("/johnny.spring/login.jsp");
	    			//HttpSession session = request.getSession(true); 
	    	    	//String uri = request.getRequestURI();//拿到上一个页面地址
	    	    	//String path = uri.substring(request.getContextPath().length());//去掉项目地址长度的字符（因为我的默认项目地址是给出的）
	    	    	//String query = request.getQueryString();//得到参数
	    	    	//if(query == null) {
	    	    		//query = "";
	    	    	//}
	    	    	//String realPath = path+"?"+query;
	    	    	//System.out.println(uri+"?"+query);//测试用
	    	    	//System.out.println(realPath);//测试用
	    	    	//session.setAttribute("realPath", realPath);
	        		String loginUrl = request.getContextPath() + "/login.jsp";
	        		
	        		String url = request.getRequestURI();
	        		String str = "<script language='javascript'>alert('会话过期,请重新登录');"
	        					+ "window.top.location.href='"
	        					+ loginUrl
	        					+ "';</script>";
	        		response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
	        		PrintWriter writer = response.getWriter();
	        		writer.write(str);
	        		writer.flush();
	        		return false;
	    		}
	        }
	    }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自动生成的方法存根
	}
}
