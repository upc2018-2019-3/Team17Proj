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

		<title>�ʲ����͹���</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/TypeServlet?method=findAllType" method="post">
			<div align="center">
				<h1>
					�ʲ������б�
				</h1>


			</div>
			
				<div align="center">
				<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							�ʲ���������
							<input name="tname" id="tname" value="${ type.tname}">
						
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th style="background-color: gray;">
					<input type="button" onclick="location.href='TypeServlet?method=addType'" value="��  ��" />
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
							���ͱ��
						</th>
						<th>
							�ʲ�������
						</th>
						
						<th>
							��ע
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
										href="servlet/TypeServlet?method=updatePage&tid=${item.tid}">�޸�</a>|
									<a
										href="servlet/TypeServlet?method=deleteType&tid=${item.tid}">ɾ��</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
