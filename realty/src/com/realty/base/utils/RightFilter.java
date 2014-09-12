package com.realty.base.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RightFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException { }
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		String path = req.getRequestURI();
		//��session��ȡ���û�����Ϣ
		String uid=(String)session.getAttribute("uid"); 

		 // ��½ҳ���������
		 if(path.indexOf("/login.jsp") > -1) {
		      chain.doFilter(req, res);
		       return;
		   }
		/*for (int i = 0; i < Constants.NoFilter_Pages.length; i++) { 
		if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
		  chain.doFilter(req, res);
		  return;
		}
		} */
		if (session.isNew()||uid!=",dbpg.") {
		//��ת����½ҳ��
		  res.sendRedirect("http://"+req.getHeader("Host")+"/realty/manage/login.jsp");
		//res.sendRedirect("http://localhost:8080/realty/manage/login.jsp");
		} else {
		//�Ѿ���½,�����˴�����
		chain.doFilter(request, response);
		}
		}
		public void destroy() { }
		}
	
	/*public class Constants { 
		public Constants() {
		}
		public static String[] NoFilter_Pages = {
		   "/index.jsp",
		   "/login.jsp",
	   
	}; 
	}*/

