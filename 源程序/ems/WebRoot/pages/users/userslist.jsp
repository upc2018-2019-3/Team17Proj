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

		<title>�û�����</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/UsersServlet?method=findAllUsers" method="post">
			<div align="center">
				<h1>
					�û��б�
				</h1>


			</div>
			
			
				<div align="center" >
					<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							�û�����
							<input name="uname" id="uname" value="${ stu.uname}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th style="background-color: gray;">
							<input type="button" onclick="location.href='UsersServlet?method=addUsers'" value="��  ��" />
					<input class="box" type="button" value="��  ��"
						onClick="javascript:history.go(-1);" />
						</th>

					</tr>
					</table>
					
				</div>
			<div align="center" id="studentlist">
				<table border="1">
					<tr>
						<th align="center">
							���
						</th>
						<th>
							�û���
						</th>
						<th>
							����
						</th>
						<th>
							����
						</th>
						<th>
							����
						</th>

					</tr>
					<c:forEach var="item" varStatus="i" items="${list}">

						<tr>
							<td>
								${(pagenum-1)*currentp+i.index+1}
							</td>
							<td width="100px;">
								${item.uname}
							</td>
							<td width="100px;">
								<c:if test="${item.utype==1}">����Ա</c:if>
								<c:if test="${item.utype==2}">��ͨ</c:if>
							</td>
							<td>
								${item.upassword}
							</td>
							
							
								<td align="center" width="100px;">
									<a
										href="servlet/UsersServlet?method=updatePage&uid=${item.uid}">�޸�</a>|
									<a
										href="servlet/UsersServlet?method=deleteUsers&uid=${item.uid}">ɾ��</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
