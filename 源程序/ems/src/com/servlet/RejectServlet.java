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
import com.bean.Reject;
import com.service.EquipmentService;
import com.service.RejectService;
import com.tool.Tools;

public class RejectServlet extends HttpServlet {

	RejectService rejectService = new RejectService();
	EquipmentService equipmentService = new EquipmentService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllReject")) {
				findAllReject(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteReject")) {
				deleteReject(request, response);
			} else if (methodName.equals("saveReject")) {
				try {
					saveReject(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addReject")) {
				List<Equipment> equipment = equipmentService.findAllExpEquipment();
				request.setAttribute("equipment", equipment);
				request.getRequestDispatcher("/pages/reject/addreject.jsp")
						.forward(request, response);
			} else if (methodName.equals("updateReject")) {
				try {
					updateReject(request, response);
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
	
	public void findAllReject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Reject rej = new Reject();
		rej.setEid(Integer.parseInt((request.getParameter("eid")==null?"0":request.getParameter("eid"))));
		int allRecord = rejectService.findAllCounts(rej);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		List<Reject> list = rejectService.findAllReject(rej,pageIndex, pageSize);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		pagingModel.setPageLink("servlet/RejectServlet?method=findAllReject&eid="+request.getParameter("eid"));
		List<Equipment> equipment = equipmentService.findAllExpEquipment();
		request.setAttribute("rej", rej);
		request.setAttribute("equipment", equipment);
		request.setAttribute("list", list);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/reject/rejectlist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("rid");
		if (id != "" && id != null) {
			int rid= Integer.parseInt(id);
			Reject reject = rejectService.findRejectById(rid);
			request.setAttribute("reject", reject);
			int allRecord = equipmentService.findAllCounts(null);
			List<Equipment> equipment = equipmentService.findAllEquipment(null,1, allRecord);
			request.setAttribute("equipment", equipment);
			request.getRequestDispatcher("/pages/reject/updatereject.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteReject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("rid");
		if (id != "" && id != null) {
			int rid = Integer.parseInt(id);
			Reject reject = rejectService.findRejectById(rid);
			rejectService.deleteRejectById(rid,reject.getEid());
			request.getRequestDispatcher(
					"RejectServlet?method=findAllReject").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveReject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		int eid=Integer.parseInt(request.getParameter("eid"));;		
		String rdate=request.getParameter("rdate");		
		float rdepreciation=Float.parseFloat(request.getParameter("rdepreciation").equals("")?"0":request.getParameter("rdepreciation"));
		String rremarks=request.getParameter("rremarks");		
		Reject reject = new Reject();
		reject.setEid(eid);
		reject.setRdate(Tools.encode(rdate));
		reject.setRdepreciation(rdepreciation);
		reject.setRremarks(Tools.encode(rremarks));
		int i = rejectService.insertReject(reject);
		if (i > 0) {
			request.setAttribute("url", "servlet/RejectServlet?method=findAllReject");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updateReject(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		int rid=Integer.parseInt(request.getParameter("rid"));
		int eid=Integer.parseInt(request.getParameter("eid"));	
		String rdate=request.getParameter("rdate");		
		float rdepreciation=Float.parseFloat(request.getParameter("rdepreciation").equals("")?"0":request.getParameter("rdepreciation"));
		String rremarks=request.getParameter("rremarks");		
		Reject reject = new Reject();
		reject.setRid(rid);
		reject.setEid(eid);
		reject.setRdate(Tools.encode(rdate));
		reject.setRdepreciation(rdepreciation);
		reject.setRremarks(Tools.encode(rremarks));
		int i = rejectService.updateReject(reject);
		if (i > 0) {
			request.setAttribute("url", "servlet/RejectServlet?method=findAllReject");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
