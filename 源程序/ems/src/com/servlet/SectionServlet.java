package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Section;
import com.service.SectionService;
import com.tool.Tools;

public class SectionServlet extends HttpServlet {

	SectionService sectionService = new SectionService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllSection")) {
				findAllSection(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteSection")) {
				deleteSection(request, response);
			} else if (methodName.equals("saveSection")) {
				try {
					saveSection(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addSection")) {
				int allRecord = sectionService.findAllCounts(null);
				List<Section> list = sectionService.findAllSection(null,1, allRecord);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/pages/section/addsection.jsp")
						.forward(request, response);
			} else if (methodName.equals("updateSection")) {
				try {
					updateSection(request, response);
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
	
	public void findAllSection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Section sec = new Section();
		sec.setSname(Tools.encode(request.getParameter("sname")));
		int allRecord = sectionService.findAllCounts(sec);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		List<Section> list = sectionService.findAllSection(sec,pageIndex, pageSize);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		pagingModel.setPageLink("servlet/SectionServlet?method=findAllSection&sname="+Tools.encode(request.getParameter("sname")));
		request.setAttribute("list", list);
		request.setAttribute("sec", sec);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/section/sectionlist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("sid");
		if (id != "" && id != null) {
			int sid= Integer.parseInt(id);
			Section section = sectionService.findSectionById(sid);
			int allRecord = sectionService.findAllCounts(null);
			List<Section> list = sectionService.findAllSection(null,1, allRecord);
			request.setAttribute("list", list);
			request.setAttribute("section", section);
			request.getRequestDispatcher("/pages/section/updatesection.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteSection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("sid");
		if (id != "" && id != null) {
			int sid = Integer.parseInt(id);
			sectionService.deleteSectionById(sid);
			request.getRequestDispatcher(
					"SectionServlet?method=findAllSection").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveSection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		String scode= request.getParameter("scode");
		String sname= request.getParameter("sname");
		String sremarks= request.getParameter("sremarks");		
		Section section = new Section();
		section.setScode(Tools.encode(scode));
		section.setSname(Tools.encode(sname));
		section.setSremarks(Tools.encode(sremarks));
		int i = sectionService.insertSection(section);
		if (i > 0) {
			request.setAttribute("url", "servlet/SectionServlet?method=findAllSection");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updateSection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		int sid= Integer.parseInt(request.getParameter("sid"));
		String scode= request.getParameter("scode");
		String sname= request.getParameter("sname");
		String sremarks= request.getParameter("sremarks");		
		Section section = new Section();
		section.setSid(sid);
		section.setScode(Tools.encode(scode));
		section.setSname(Tools.encode(sname));
		section.setSremarks(Tools.encode(sremarks));
		int i = sectionService.updateSection(section);
		if (i > 0) {
			request.setAttribute("url", "servlet/SectionServlet?method=findAllSection");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
