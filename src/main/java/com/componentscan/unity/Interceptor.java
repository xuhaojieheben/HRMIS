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
		// TODO �Զ����ɵķ������
		//HandlerMethod handlerMethod = (HandlerMethod) handler;
		if(handler instanceof HandlerMethod) {
	        HandlerMethod h = (HandlerMethod)handler;
	        System.out.println(h.getMethod().getName());
	        //System.out.println("�û���ִ�еĲ�����:"+h.getMethodAnnotation(AllowAnonymous.class).value());
	        //AllowAnonymous validate = ((HandlerMethod)handler).getMethodAnnotation(AllowAnonymous.class);
			AllowAnonymous validate = h.getMethod().getDeclaringClass().getAnnotation(AllowAnonymous.class);
	        if(validate == null){
	            throw new Exception("δ�����Զ���ע��");
	        }
	        AllowAnonymousType aaCode = validate.value();
	        if(aaCode == aaCode.Authorize){ //��Ҫ��֤
	        	//��֤�û�
	        	LoginUserModel user_model = (LoginUserModel)request.getSession().getAttribute(SessionEnum.USER_SESSION.name());
	        	if (user_model == null) {
	        		//request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);  
	        		//request.getRequestDispatcher("/login.jsp").forward(request, response);  
	        		//��ת���������ڲ���һ�����ܴ�����
	        	      //request.getRequestDispatcher("/dispather/b").forward(request,response);

	        	      //�ض���һ�����ܷ���
	        	      //response.sendRedirect(request.getContextPath()+"/login.jsp");
	        	      //request.getRequestDispatcher("/login/login.do").forward(request,response);
	    			//response.sendRedirect("/johnny.spring/login.jsp");
	    			//HttpSession session = request.getSession(true); 
	    	    	//String uri = request.getRequestURI();//�õ���һ��ҳ���ַ
	    	    	//String path = uri.substring(request.getContextPath().length());//ȥ����Ŀ��ַ���ȵ��ַ�����Ϊ�ҵ�Ĭ����Ŀ��ַ�Ǹ����ģ�
	    	    	//String query = request.getQueryString();//�õ�����
	    	    	//if(query == null) {
	    	    		//query = "";
	    	    	//}
	    	    	//String realPath = path+"?"+query;
	    	    	//System.out.println(uri+"?"+query);//������
	    	    	//System.out.println(realPath);//������
	    	    	//session.setAttribute("realPath", realPath);
	        		String loginUrl = request.getContextPath() + "/login.jsp";
	        		
	        		String url = request.getRequestURI();
	        		String str = "<script language='javascript'>alert('�Ự����,�����µ�¼');"
	        					+ "window.top.location.href='"
	        					+ loginUrl
	        					+ "';</script>";
	        		response.setContentType("text/html;charset=UTF-8");// �����������
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
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO �Զ����ɵķ������
	}
}
