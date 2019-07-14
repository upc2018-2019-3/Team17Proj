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

		<title>资产类型管理</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/TypeServlet?method=findAllType" method="post">
			<div align="center">
				<h1>
					资产类型列表
				</h1>


			</div>
			
				<div align="center">
				<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							资产类型名：
							<input name="tname" id="tname" value="${ type.tname}">
						
						
							<input type="submit" value="搜  索" />
						</th>
						
						<th style="background-color: gray;">
					<input type="button" onclick="location.href='TypeServlet?method=addType'" value="添  加" />
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
							类型编号
						</th>
						<th>
							资产类型名
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
							<td width="100px;">
								${item.tcode}
							</td>
							<td width="100px;">
								${item.tname}
							</td>
							<td>
								${item.tremarks}
							</td>
							
							
								<td align="center" width="100px;">
									<a
										href="servlet/TypeServlet?method=updatePage&tid=${item.tid}">修改</a>|
									<a
										href="servlet/TypeServlet?method=deleteType&tid=${item.tid}">删除</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
