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

		<title>���Ź���</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/SectionServlet?method=findAllSection" method="post">
			<div align="center">
				<h1>
					�����б�
				</h1>


			</div>
			
				<div align="center">
				<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							��������
							<input name="sname" id="sname" value="${ sec.sname}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th style="background-color: gray;">
						<input type="button" onclick="location.href='SectionServlet?method=addSection'" value="��  ��" />
					<input class="box" type="button" value="��  ��"
						onClick="javascript:history.go(-1);" />
						</th>
						</tr>
						</table>
				</div>
			<div align="center" id="studentlist">
				<table border="1" >
					<tr>
						<th align="center">
							���
						</th>
						<th  align="center" width="200px;">
							���
						</th>
						<th  align="center" width="200px;">
							������
						</th>
						
						<th  align="center" width="400px;">
							��ע
						</th>
						<th  align="center" width="200px;">
							����
						</th>

					</tr>
					<c:forEach var="item" varStatus="i" items="${list}">

						<tr>
							<td  align="center">
								${(pagenum-1)*currentp+i.index+1}
							</td>
							<td width="200px;"  align="center">
								${item.scode}
							</td>
							<td width="200px;"  align="center">
								${item.sname}
							</td>
							
							<td width="400px;"  align="center">
								${item.sremarks}
							</td>
							
							
								<td align="center" width="200px;">
									<a
										href="servlet/SectionServlet?method=updatePage&sid=${item.sid}">�޸�</a>|
									<a
										href="servlet/SectionServlet?method=deleteSection&sid=${item.sid}">ɾ��</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
