<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ include file="/pages/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>资产信息管理</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/EquipmentServlet?method=findAllEquipment" method="post">
			<div align="center">
				<h1>
					资产信息列表
				</h1>


			</div>

			<div align="center">
			<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							资产名：
							<input name="ename" id="ename" value="${ equipment.ename}">
							所属类别：
							<select id="tid" name="tid">
								<option value="0">--请选择--</option>
								<c:forEach var="item" items="${type}">
								<option value="${item.tid }">${item.tcode }:${item.tname }</option>
								</c:forEach>
							</select>
							<script type="text/javascript">document.getElementById('tid').value = '${equipment.tid }'</script>	
							
						
							<input type="submit" value="搜  索" />
						</th>
						
						<th style="background-color: gray;">
				<input type="button" onclick="location.href='EquipmentServlet?method=addEquipment'" value="添  加" />
				<input class="box" type="button" value="返  回"
					onClick="javascript:history.go(-1);" />
					</th>

					</tr>
					</table>
			</div>
			
			<div align="center" id="studentlist">
				<table border="1">
					<tr>
						<th align="center">
							序号
						</th>
						<th>
							编号
						</th>
						<th>
							名称
						</th>

						
						<th>
							类别名称
						</th>
						<th>
							价值
						</th>

						<th>
							生产厂家
						</th>

						<th>
							出厂日期
						</th>
						<th>
							采购时间
						</th>

						<th>
							备注
						</th>

						
						<th>
							使用部门名称
						</th>
						<th>
							使用人<br>编号
						</th>
						<th>
							使用人
						</th>
						<th>
							资产<br>状态
						</th>
						<th>

							操作
						</th>

					</tr>
					<c:forEach var="item" varStatus="i" items="${list}">

						<tr>
							<td>
								${(pagenum-1)*currentp+i.index+1}
							</td>
							<td >
								${item.ecode}
							</td>
							<td >
								${item.ename}
							</td>
							
							<td >
								${item.tname}
							</td>
							<td>
								${item.eworth}
							</td>
							<td>
								${item.eproducer}
							</td>
							<td>
								${item.eoutdate}
							</td>
							<td>
								${item.ebuydate}
							</td>
							<td>
								${item.eremarks}
							</td>
							
							<td>
								${item.sname}
							</td>
							<td>
								${item.pcode}
							</td>
							<td>
								${item.pname}
							</td>
							<td>
								${item.estatus}
							</td>
							<td align="center" width="200px">
								<a href="servlet/EquipmentServlet?method=updatePage&eid=${item.eid}">修改</a>
								<a
									href="servlet/EquipmentServlet?method=deleteEquipment&eid=${item.eid}">删除</a>
							</td>

						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
