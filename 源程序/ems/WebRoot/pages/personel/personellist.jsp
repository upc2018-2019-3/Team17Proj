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

		<title>��Ա����</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/PersonelServlet?method=findAllPersonel" method="post">
			<div align="center">
				<h1>
					��Ա�б�
				</h1>


			</div>

			<div align="center">
				<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							�û�����
							<input name="pname" id="pname" value="${ personel.pname}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th style="background-color: gray;">
							<input type="button" onclick="location.href='PersonelServlet?method=addPersonel'" value="��  ��" />
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
							���
						</th>
						<th>
							����
						</th>

						<th>
							�ձ�
						</th>

						<th>
							����
						</th>

						<th>
							��ͥסַ
						</th>

						<th>
							��ϵ�绰
						</th>
						
						<th>
							��������
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
								<a href="servlet/PersonelServlet?method=updatePage&pid=${item.pid}">�޸�</a>|
								<a
									href="servlet/PersonelServlet?method=deletePersonel&pid=${item.pid}">ɾ��</a>
							</td>

						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
