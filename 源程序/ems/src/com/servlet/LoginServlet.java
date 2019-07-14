package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Users;
import com.service.LoginService;

public class LoginServlet extends HttpServlet {

	LoginService loginService = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			loginService = new LoginService();
			String name = request.getParameter("userName");
			String password = request.getParameter("password");
			Users login = new Users();
			if(!name.equals("")&&name!=null&&!password.equals("")&&password != ""){
				try {
					login = loginService.findUserByNameAndPassword(name, password);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(login!=null&&login.getUid()!=0){
					request.setAttribute("username", name);			
					request.setAttribute("userid", login.getUid());
					request.setAttribute("type", login.getUtype());
					request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
				}else{
					request.setAttribute("message", "用户名或密码错误");
					request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
	
