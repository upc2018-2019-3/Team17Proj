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

		<title>资产统计管理</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/EquipmentServlet?method=addEquipment" method="post">
			<div align="center">
				<h1>
					资产统计列表
				</h1>


			</div>

			
			
			<div align="center" id="studentlist">
				<table border="1" style="width: 500px;">
					<tr>
						<th align="center">
							序号
						</th>
						<th>
							部门编号
						</th>
						<th>
							部门名称
						</th>

						<th>
							资产数量
						</th>
						<th>
							资产总价值
						</th>
						

					</tr>
					<c:set var="totalcount" value="0"></c:set>
					<c:set var="totalworth" value="0"></c:set>
					<c:forEach var="item" varStatus="i" items="${list}">

						<tr>
							<td>
								${(pagenum-1)*currentp+i.index+1}
							</td>
							<td >
								${item.scode}
							</td>
							<td >
								${item.sname}
							</td>
							
							<td >
								${item.ecount}
							</td>
							<td >
								${item.eworth}
							<c:set var="totalcount" value="${totalcount+item.ecount }"></c:set>
							<c:set var="totalworth" value="${ totalworth+item.eworth}"></c:set>
							</td>

						</tr>
					</c:forEach>
					<tr>
							<td>
								
							</td>
							<td >
								
							</td>
							<td >
								总计
							</td>
							
							<td >
								${totalcount}
							</td>
							<td >
								${totalworth}
							
							</td>

						</tr>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
