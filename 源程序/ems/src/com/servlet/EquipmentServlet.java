package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Equipment;
import com.bean.Personel;
import com.bean.SearchEquipment;
import com.bean.Section;
import com.bean.Type;
import com.service.EquipmentService;
import com.service.PersonelService;
import com.service.SectionService;
import com.service.TypeService;
import com.tool.Tools;

public class EquipmentServlet extends HttpServlet {

	EquipmentService equipmentService = new EquipmentService();
	TypeService typeService = new TypeService();
	PersonelService personelService = new PersonelService();
	SectionService sectionService = new SectionService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllEquipment")) {
				findAllEquipment(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteEquipment")) {
				deleteEquipment(request, response);
			}else if (methodName.equals("searchEquipment")) {
				searchEquipment(request, response);
			} else if (methodName.equals("saveEquipment")) {
				try {
					saveEquipment(request, response);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			} else if (methodName.equals("addEquipment")) {
				int allRecord = typeService.findAllCounts(null);
				List<Type> type = typeService.findAllType(null,1, allRecord);
				request.setAttribute("type", type);
				allRecord = sectionService.findAllCounts(null);
				List<Section> section = sectionService.findAllSection(null,1, allRecord);
				request.setAttribute("section", section);
				allRecord = personelService.findAllCounts(null);
				List<Personel> personel = personelService.findAllPersonel(null,1, allRecord);
				request.setAttribute("personel",personel);
				allRecord = equipmentService.findAllCounts(null);
				List<Equipment> list = equipmentService.findAllEquipment(null,1, allRecord);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/pages/equipment/addequipment.jsp")
						.forward(request, response);
			} else if (methodName.equals("updateEquipment")) {
				try {
					updateEquipment(request, response);
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
	
	public void findAllEquipment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		int allRecord = typeService.findAllCounts(null);
		List<Type> type = typeService.findAllType(null,1, allRecord);
		request.setAttribute("type", type);
		Equipment equipment = new Equipment();
		equipment.setEname(Tools.encode(request.getParameter("ename")));
		equipment.setTid(Integer.parseInt((request.getParameter("tid")==null?"0":request.getParameter("tid"))));
		 allRecord = equipmentService.findAllCounts(equipment);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		List<Equipment> list = equipmentService.findAllEquipment(equipment,pageIndex, pageSize);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		pagingModel.setPageLink("servlet/EquipmentServlet?method=findAllEquipment&ename="+Tools.encode(request.getParameter("ename")));
		request.setAttribute("equipment", equipment);
		request.setAttribute("list", list);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/equipment/equipmentlist.jsp").forward(
				request, response);
	}
	public void searchEquipment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		List<SearchEquipment> list = equipmentService.searchEquipment();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/equipment/searchequipment.jsp").forward(
				request, response);
	}
	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("eid");
		if (id != "" && id != null) {
			int eid= Integer.parseInt(id);
			Equipment equipment = equipmentService.findEquipmentById(eid);
			request.setAttribute("equipment", equipment);
			int allRecord = typeService.findAllCounts(null);
			List<Type> type = typeService.findAllType(null,1, allRecord);
			request.setAttribute("type", type);
			allRecord = sectionService.findAllCounts(null);
			List<Section> section = sectionService.findAllSection(null,1, allRecord);
			request.setAttribute("section", section);
			allRecord = personelService.findAllCounts(null);
			List<Personel> personel = personelService.findAllPersonel(null,1, allRecord);
			allRecord = equipmentService.findAllCounts(null);
			List<Equipment> list = equipmentService.findAllEquipment(null,1, allRecord);
			request.setAttribute("list", list);
			request.setAttribute("personel",personel);
			request.getRequestDispatcher("/pages/equipment/updateequipment.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteEquipment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("eid");
		if (id != "" && id != null) {
			int eid = Integer.parseInt(id);
			equipmentService.deleteEquipmentById(eid);
			request.getRequestDispatcher(
					"EquipmentServlet?method=findAllEquipment").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveEquipment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {	
		String ecode= request.getParameter("ecode");
		String ename= request.getParameter("ename");		
		int tid= Integer.parseInt(request.getParameter("tid"));		
		float eworth= Float.parseFloat(request.getParameter("eworth"));;
		String eproducer= request.getParameter("eproducer");		
		String eoutdate= request.getParameter("eoutdate");		
		String ebuydate= request.getParameter("ebuydate");		
		String eremarks= request.getParameter("eremarks");	
		int esid= Integer.parseInt(request.getParameter("esid"));	
		int pid= Integer.parseInt(request.getParameter("pid"));	
		String estatus= request.getParameter("estatus");
		Equipment equipment = new Equipment();
		equipment.setEcode(Tools.encode(ecode));
		equipment.setEname(Tools.encode(ename));
		equipment.setTid(tid);
		equipment.setPid(pid);
		equipment.setEworth(eworth);
		equipment.setEproducer(Tools.encode(eproducer));
		equipment.setEoutdate(Tools.encode(eoutdate));
		equipment.setEbuydate(Tools.encode(ebuydate));
		equipment.setEsid(esid);
		equipment.setEremarks(Tools.encode(eremarks));
		equipment.setEstatus(Tools.encode(estatus));
		int i = equipmentService.insertEquipment(equipment);
		if (i > 0) {
			request.setAttribute("url", "servlet/EquipmentServlet?method=findAllEquipment");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updateEquipment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		int eid= Integer.parseInt(request.getParameter("eid"));		
		String ecode= request.getParameter("ecode");
		String ename= request.getParameter("ename");		
		int tid= Integer.parseInt(request.getParameter("tid"));		
		float eworth= Float.parseFloat(request.getParameter("eworth"));;
		String eproducer= request.getParameter("eproducer");		
		String eoutdate= request.getParameter("eoutdate");		
		String ebuydate= request.getParameter("ebuydate");		
		String eremarks= request.getParameter("eremarks");		
		int esid= Integer.parseInt(request.getParameter("esid"));		
		int pid= Integer.parseInt(request.getParameter("pid"));	
		String estatus= request.getParameter("estatus");
		Equipment equipment = new Equipment();
		equipment.setEid(eid);
		equipment.setEcode(Tools.encode(ecode));
		equipment.setEname(Tools.encode(ename));
		equipment.setTid(tid);
		equipment.setEworth(eworth);
		equipment.setPid(pid);
		equipment.setEproducer(Tools.encode(eproducer));
		equipment.setEoutdate(Tools.encode(eoutdate));
		equipment.setEbuydate(Tools.encode(ebuydate));
		equipment.setEsid(esid);
		equipment.setEremarks(Tools.encode(eremarks));
		equipment.setEstatus(Tools.encode(estatus));
		int i = equipmentService.updateEquipment(equipment);
		if (i > 0) {
			request.setAttribute("url", "servlet/EquipmentServlet?method=findAllEquipment");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
