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

		<title>�ʲ�ά����Ϣ����</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/MaintainServlet?method=findAllMaintain" method="post">
			<div align="center">
				<h1>
					�ʲ�ά����Ϣ�б�
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
							<script type="text/javascript">document.getElementById("eid").value='${maintain.eid}'</script>	
							<input type="submit" value="��  ��" />
						</th>
						
						<th style="background-color: gray;">
							<input type="button" onclick="location.href='MaintainServlet?method=addMaintain'" value="��  ��" />
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
							ά�޹�ʱ
						</th>
						<th>
							ά�޷���
						</th>
						<th>
							����ԭ��
						</th>
						<th>
							ά������
						</th>
						<th>
							��ע
						</th>
						<th>
							ά����
						</th>
						<th>
							ά���˵绰
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
								${item.mhour}
							</td>
							<td >
								${item.mcharge}
							</td>
							<td >
								${item.mreson}
							</td>
							<td>
								${item.mdate}
							</td>
							<td >
								${item.mremarks}
							</td>
							<td >
								${item.mperson}
							</td>
							<td>
								${item.mtel}
							</td>
							
							
							
								<td align="center" >
									<a
										href="servlet/MaintainServlet?method=updatePage&mid=${item.mid}">�޸�</a>|
									<a
										href="servlet/MaintainServlet?method=deleteMaintain&mid=${item.mid}">ɾ��</a>
								</td>
							
						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
