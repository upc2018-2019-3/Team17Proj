package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Personel;
import com.bean.Section;
import com.service.PersonelService;
import com.service.SectionService;
import com.tool.Tools;

public class PersonelServlet extends HttpServlet {

	PersonelService personelService = new PersonelService();
	SectionService sectionService = new SectionService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllPersonel")) {
				findAllPersonel(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deletePersonel")) {
				deletePersonel(request, response);
			} else if (methodName.equals("savePersonel")) {
				try {
					savePersonel(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addPersonel")) {
				int allRecord = personelService.findAllCounts(null);
				List<Personel> list = personelService.findAllPersonel(null,1, allRecord);
				request.setAttribute("list",list);
				allRecord = sectionService.findAllCounts(null);
				List<Section> section = sectionService.findAllSection(null,1, allRecord);
				request.setAttribute("section", section);
				request.getRequestDispatcher("/pages/personel/addpersonel.jsp")
						.forward(request, response);
			} else if (methodName.equals("updatePersonel")) {
				try {
					updatePersonel(request, response);
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
	
	public void findAllPersonel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Personel personel = new Personel();
		personel.setPname(Tools.encode(request.getParameter("pname")));
		int allRecord = personelService.findAllCounts(personel);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		List<Personel> list = personelService.findAllPersonel(personel,pageIndex, pageSize);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		pagingModel.setPageLink("servlet/PersonelServlet?method=findAllPersonel&pname="+Tools.encode(request.getParameter("pname")));
		request.setAttribute("list", list);
		request.setAttribute("personel", personel);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/personel/personellist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("pid");
		if (id != "" && id != null) {
			int pid= Integer.parseInt(id);
			Personel personel = personelService.findPersonelById(pid);
			int allRecord = personelService.findAllCounts(null);
			List<Personel> list = personelService.findAllPersonel(null,1, allRecord);
			request.setAttribute("list",list);
			request.setAttribute("personel", personel);
			allRecord = sectionService.findAllCounts(null);
			List<Section> section = sectionService.findAllSection(null,1, allRecord);
			request.setAttribute("section", section);
			request.getRequestDispatcher("/pages/personel/updatepersonel.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deletePersonel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("pid");
		if (id != "" && id != null) {
			int pid = Integer.parseInt(id);
			personelService.deletePersonelById(pid);
			request.getRequestDispatcher(
					"PersonelServlet?method=findAllPersonel").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void savePersonel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		String pcode= request.getParameter("pcode");
		String pname= request.getParameter("pname");
		String psex= request.getParameter("psex");		
		int page= Integer.parseInt(request.getParameter("page"));		
		String padd= request.getParameter("padd");		
		String ptel= request.getParameter("ptel");	
		String pmail= request.getParameter("pmail");
		int sid = Integer.parseInt(request.getParameter("sid"));	
		Personel personel = new Personel();
		personel.setPcode(Tools.encode(pcode));
		personel.setPname(Tools.encode(pname));
		personel.setPsex(Tools.encode(psex));
		personel.setPage(page);
		personel.setSid(sid);
		personel.setPadd(Tools.encode(padd));
		personel.setPtel(Tools.encode(ptel));
		personel.setPmail(Tools.encode(pmail));
		int i = personelService.insertPersonel(personel);
		if (i > 0) {
			request.setAttribute("url", "servlet/PersonelServlet?method=findAllPersonel");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updatePersonel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		int pid= Integer.parseInt(request.getParameter("pid"));
		String pcode= request.getParameter("pcode");
		String pname= request.getParameter("pname");
		String psex= request.getParameter("psex");		
		int page= Integer.parseInt(request.getParameter("page"));		
		String padd= request.getParameter("padd");		
		String ptel= request.getParameter("ptel");	
		String pmail= request.getParameter("pmail");	
		int sid = Integer.parseInt(request.getParameter("sid"));	
		Personel personel = new Personel();
		personel.setPid(pid);
		personel.setPcode(Tools.encode(pcode));
		personel.setPname(Tools.encode(pname));
		personel.setPsex(Tools.encode(psex));
		personel.setPage(page);
		personel.setSid(sid);
		personel.setPadd(Tools.encode(padd));
		personel.setPtel(Tools.encode(ptel));
		personel.setPmail(Tools.encode(pmail));
		int i = personelService.updatePersonel(personel);
		if (i > 0) {
			request.setAttribute("url", "servlet/PersonelServlet?method=findAllPersonel");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
