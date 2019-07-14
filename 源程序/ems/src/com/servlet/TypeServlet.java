package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Type;
import com.service.TypeService;
import com.tool.Tools;

public class TypeServlet extends HttpServlet {

	TypeService typeService = new TypeService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllType")) {
				findAllType(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteType")) {
				deleteType(request, response);
			} else if (methodName.equals("saveType")) {
				try {
					saveType(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addType")) {
				int allRecord = typeService.findAllCounts(null);
				List<Type> list = typeService.findAllType(null,1, allRecord);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/pages/type/addtype.jsp")
						.forward(request, response);
			} else if (methodName.equals("updateType")) {
				try {
					updateType(request, response);
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
	
	public void findAllType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Type type = new Type();
		type.setTname(Tools.encode(request.getParameter("tname")));
		int allRecord = typeService.findAllCounts(type);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		List<Type> list = typeService.findAllType(type,pageIndex, pageSize);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		pagingModel.setPageLink("servlet/TypeServlet?method=findAllType&tname="+Tools.encode(request.getParameter("tname")));
		request.setAttribute("list", list);
		request.setAttribute("type", type);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/type/typelist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("tid");
		if (id != "" && id != null) {
			int tid= Integer.parseInt(id);
			Type type = typeService.findTypeById(tid);
			request.setAttribute("type", type);
			int allRecord = typeService.findAllCounts(null);
			List<Type> list = typeService.findAllType(null,1, allRecord);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/type/updatetype.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("tid");
		if (id != "" && id != null) {
			int tid = Integer.parseInt(id);
			typeService.deleteTypeById(tid);
			request.getRequestDispatcher(
					"TypeServlet?method=findAllType").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		String tcode= request.getParameter("tcode");
		String tname= request.getParameter("tname");
		String tremarks= request.getParameter("tremarks");			
		Type type = new Type();
		type.setTcode(Tools.encode(tcode));
		type.setTname(Tools.encode(tname));
		type.setTremarks(Tools.encode(tremarks));
		int i = typeService.insertType(type);
		if (i > 0) {
			request.setAttribute("url", "servlet/TypeServlet?method=findAllType");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updateType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		int tid= Integer.parseInt(request.getParameter("tid"));
		String tcode= request.getParameter("tcode");
		String tname= request.getParameter("tname");
		String tremarks= request.getParameter("tremarks");			
		Type type = new Type();
		type.setTid(tid);
		type.setTcode(Tools.encode(tcode));
		type.setTname(Tools.encode(tname));
		type.setTremarks(Tools.encode(tremarks));
		int i = typeService.updateType(type);
		if (i > 0) {
			request.setAttribute("url", "servlet/TypeServlet?method=findAllType");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
