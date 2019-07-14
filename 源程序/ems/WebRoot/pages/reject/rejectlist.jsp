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

		<title>资产报废信息管理</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/RejectServlet?method=findAllReject" method="post">
			<div align="center">
				<h1>
					资产报废信息列表
				</h1>


			</div>
			
				<div align="center">
					<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							资产名：
							<select id="eid" name="eid" onchange="changecode()">
								<option value="">--请选择--</option>
								<c:forEach var="item" items="${equipment}">
								<option value="${item.eid }">${item.ecode }:${item.ename }</option>
								</c:forEach>
							</select>
							<script type="text/javascript">document.getElementById("eid").value='${rej.eid}'</script>	
							<input type="submit" value="搜  索" />
						</th>
						
						<th style="background-color: gray;">
							<input type="button" onclick="location.href='RejectServlet?method=addReject'" value="添  加" />
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
							资产编号
						</th>
						<th>
							资产名称
						</th>
						<th>
							报废时间
						</th>
						<th>
							累计折旧
						</th>
						
						<th>
							备注
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
							<td>
								${item.rdate}
							</td>
							<td >
								${item.rdepreciation}
							</td>
							<td >
								${item.rremarks}
							</td>
							
							
							
							
								<td align="center" >
									<a
										href="servlet/RejectServlet?method=updatePage&rid=${item.rid}">修改</a>|
									<a
										href="servlet/RejectServlet?method=deleteReject&rid=${item.rid}">删除</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
