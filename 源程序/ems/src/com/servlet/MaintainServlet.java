package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Equipment;
import com.bean.PagingModel;
import com.bean.Maintain;
import com.service.EquipmentService;
import com.service.MaintainService;
import com.tool.Tools;

public class MaintainServlet extends HttpServlet {

	MaintainService maintainService = new MaintainService();
	EquipmentService equipmentService = new EquipmentService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllMaintain")) {
				findAllMaintain(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteMaintain")) {
				deleteMaintain(request, response);
			} else if (methodName.equals("saveMaintain")) {
				try {
					saveMaintain(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addMaintain")) {
				List<Equipment> equipment = equipmentService.findAllExpEquipment();
				request.setAttribute("equipment", equipment);
				request.getRequestDispatcher("/pages/maintain/addmaintain.jsp")
						.forward(request, response);
			} else if (methodName.equals("updateMaintain")) {
				try {
					updateMaintain(request, response);
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
	
	public void findAllMaintain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Maintain maintain = new Maintain();
		if(request.getParameter("eid")!=null)	
			maintain.setEid(Integer.parseInt(request.getParameter("eid")));
		int allRecord = maintainService.findAllCounts(maintain);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		List<Maintain> list = maintainService.findAllMaintain(maintain,pageIndex, pageSize);
		List<Equipment> equipment = equipmentService.findAllExpEquipment();
		request.setAttribute("equipment", equipment);
		request.setAttribute("maintain", maintain);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		pagingModel.setPageLink("servlet/MaintainServlet?method=findAllMaintain&ename="+Tools.encode(request.getParameter("eid")));
		request.setAttribute("list", list);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/maintain/maintainlist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("mid");
		if (id != "" && id != null) {
			int mid= Integer.parseInt(id);
			Maintain maintain = maintainService.findMaintainById(mid);
			request.setAttribute("maintain", maintain);
			List<Equipment> equipment = equipmentService.findAllExpEquipment();
			request.setAttribute("equipment", equipment);
			request.getRequestDispatcher("/pages/maintain/updatemaintain.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteMaintain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("mid");
		if (id != "" && id != null) {
			int mid = Integer.parseInt(id);
			maintainService.deleteMaintainById(mid);
			request.getRequestDispatcher(
					"MaintainServlet?method=findAllMaintain").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveMaintain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		int eid=Integer.parseInt(request.getParameter("eid"));			
		float mhour=Float.parseFloat(request.getParameter("mhour"));
		float mcharge=Float.parseFloat(request.getParameter("mcharge"));
		String mdate=request.getParameter("mdate");		
		String mremarks=request.getParameter("mremarks");		
		String mperson=request.getParameter("mperson");		
		String mtel=request.getParameter("mtel");
		String mreson=request.getParameter("mreson");
		Maintain maintain = new Maintain();
		maintain.setEid(eid);
		maintain.setMhour(mhour);
		maintain.setMcharge(mcharge);
		maintain.setMdate(Tools.encode(mdate));
		maintain.setMremarks(Tools.encode(mremarks));
		maintain.setMperson(Tools.encode(mperson));
		maintain.setMtel(Tools.encode(mtel));
		maintain.setMreson(Tools.encode(mreson));
		int i = maintainService.insertMaintain(maintain);
		if (i > 0) {
			request.setAttribute("url", "servlet/MaintainServlet?method=findAllMaintain");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updateMaintain(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		int eid=Integer.parseInt(request.getParameter("eid"));		
		int mid=Integer.parseInt(request.getParameter("mid"));		
		float mhour=Float.parseFloat(request.getParameter("mhour"));
		float mcharge=Float.parseFloat(request.getParameter("mcharge"));
		String mdate=request.getParameter("mdate");		
		String mremarks=request.getParameter("mremarks");		
		String mperson=request.getParameter("mperson");		
		String mtel=request.getParameter("mtel");
		String mreson=request.getParameter("mreson");	
		Maintain maintain = new Maintain();
		maintain.setEid(eid);
		maintain.setMid(mid);
		maintain.setMhour(mhour);
		maintain.setMcharge(mcharge);
		maintain.setMdate(Tools.encode(mdate));
		maintain.setMremarks(Tools.encode(mremarks));
		maintain.setMperson(Tools.encode(mperson));
		maintain.setMtel(Tools.encode(mtel));
		maintain.setMreson(Tools.encode(mreson));
		int i = maintainService.updateMaintain(maintain);
		if (i > 0) {
			request.setAttribute("url", "servlet/MaintainServlet?method=findAllMaintain");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
