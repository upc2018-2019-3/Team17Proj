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

		<title>�ʲ�������Ϣ����</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/RejectServlet?method=findAllReject" method="post">
			<div align="center">
				<h1>
					�ʲ�������Ϣ�б�
				</h1>


			</div>
			
				<div align="center">
					<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							�ʲ�����
							<select id="eid" name="eid" onchange="changecode()">
								<option value="">--��ѡ��--</option>
								<c:forEach var="item" items="${equipment}">
								<option value="${item.eid }">${item.ecode }:${item.ename }</option>
								</c:forEach>
							</select>
							<script type="text/javascript">document.getElementById("eid").value='${rej.eid}'</script>	
							<input type="submit" value="��  ��" />
						</th>
						
						<th style="background-color: gray;">
							<input type="button" onclick="location.href='RejectServlet?method=addReject'" value="��  ��" />
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
							�ʲ����
						</th>
						<th>
							�ʲ�����
						</th>
						<th>
							����ʱ��
						</th>
						<th>
							�ۼ��۾�
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
										href="servlet/RejectServlet?method=updatePage&rid=${item.rid}">�޸�</a>|
									<a
										href="servlet/RejectServlet?method=deleteReject&rid=${item.rid}">ɾ��</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
