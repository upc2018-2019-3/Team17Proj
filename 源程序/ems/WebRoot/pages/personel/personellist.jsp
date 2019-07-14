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

		<title>人员管理</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/PersonelServlet?method=findAllPersonel" method="post">
			<div align="center">
				<h1>
					人员列表
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							用户名：
							<input name="pname" id="pname" value="${ personel.pname}">
						
						
							<input type="submit" value="搜  索" />
						</th>
						
						<th style="background-color: gray;">
							<input type="button" onclick="location.href='PersonelServlet?method=addPersonel'" value="添  加" />
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
							姓名
						</th>

						<th>
							姓别
						</th>

						<th>
							年龄
						</th>

						<th>
							家庭住址
						</th>

						<th>
							联系电话
						</th>
						
						<th>
							部门名称
						</th>

						<th>
							邮箱
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
								${item.pcode}
							</td>
							<td >
								${item.pname}
							</td>
							<td >
								${item.psex}
							</td>
							<td >
								${item.page}
							</td>
							<td>
								${item.padd}
							</td>
							<td>
								${item.ptel}
							</td>
							
							<td>
								${item.sname}
							</td>
							<td>
								${item.pmail}
							</td>
							<td align="center" >
								<a href="servlet/PersonelServlet?method=updatePage&pid=${item.pid}">修改</a>|
								<a
									href="servlet/PersonelServlet?method=deletePersonel&pid=${item.pid}">删除</a>
							</td>

						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
