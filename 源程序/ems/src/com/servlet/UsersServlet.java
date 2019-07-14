package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Users;
import com.service.UsersService;
import com.tool.Tools;

public class UsersServlet extends HttpServlet {

	UsersService usersService = new UsersService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllUsers")) {
				findAllUsers(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteUsers")) {
				deleteUsers(request, response);
			} else if (methodName.equals("saveUsers")) {
				try {
					saveUsers(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addUsers")) {
				int allRecord = usersService.findAllCounts(null);
				List<Users> list = usersService.findAllUsers(null,1, allRecord);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/pages/users/addusers.jsp")
						.forward(request, response);
			} else if (methodName.equals("updateUsers")) {
				try {
					updateUsers(request, response);
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				}
			} else {
				request.setAttribute("message", "口令出错");
				request.getRequestDispatcher("/pages/message.jsp").forward(
						request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void findAllUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Users stu = new Users();
		stu.setUname(Tools.encode(request.getParameter("uname")));
		int allRecord = usersService.findAllCounts(stu);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		List<Users> list = usersService.findAllUsers(stu,pageIndex, pageSize);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		pagingModel.setPageLink("servlet/UsersServlet?method=findAllUsers&uname="+Tools.encode(request.getParameter("uname")));
		request.setAttribute("list", list);
		request.setAttribute("stu", stu);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/users/userslist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("uid");
		if (id != "" && id != null) {
			int uid= Integer.parseInt(id);
			Users users = usersService.findUsersById(uid);
			request.setAttribute("users", users);
			int allRecord = usersService.findAllCounts(null);
			List<Users> list = usersService.findAllUsers(null,1, allRecord);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/users/updateusers.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("uid");
		if (id != "" && id != null) {
			int uid = Integer.parseInt(id);
			usersService.deleteUsersById(uid);
			request.getRequestDispatcher(
					"UsersServlet?method=findAllUsers").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		// sid,sname,spassword,sclass,code,srealname
		String uname = request.getParameter("uname");
		String upassword = request.getParameter("upassword");
		String utype = request.getParameter("utype");
		Users users = new Users();
		users.setUname(Tools.encode(uname));
		users.setUpassword(Tools.encode(upassword));
		users.setUtype(Tools.encode(utype));		
		int i = usersService.insertUsers(users);
		if (i > 0) {
			request.setAttribute("url", "servlet/UsersServlet?method=findAllUsers");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updateUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		String upassword = request.getParameter("upassword");
		String utype = request.getParameter("utype");
		Users users = new Users();
		users.setUid(Integer.parseInt(uid));
		users.setUname(Tools.encode(uname));
		users.setUpassword(Tools.encode(upassword));
		users.setUtype(Tools.encode(utype));
		int i = usersService.updateUsers(users);
		if (i > 0) {
			request.setAttribute("url", "servlet/UsersServlet?method=findAllUsers");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
