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

		<title>�ʲ���Ϣ����</title>
		<link href="<%=basePath%>/css/list.css" rel="stylesheet"
			type="text/css" />
	</head>

	<body>
		<form action="servlet/EquipmentServlet?method=findAllEquipment" method="post">
			<div align="center">
				<h1>
					�ʲ���Ϣ�б�
				</h1>


			</div>

			<div align="center">
			<table border="1" >
					<tr >
						<th align="left" style="background-color: gray;">
							�ʲ�����
							<input name="ename" id="ename" value="${ equipment.ename}">
							�������
							<select id="tid" name="tid">
								<option value="0">--��ѡ��--</option>
								<c:forEach var="item" items="${type}">
								<option value="${item.tid }">${item.tcode }:${item.tname }</option>
								</c:forEach>
							</select>
							<script type="text/javascript">document.getElementById('tid').value = '${equipment.tid }'</script>	
							
						
							<input type="submit" value="��  ��" />
						</th>
						
						<th style="background-color: gray;">
				<input type="button" onclick="location.href='EquipmentServlet?method=addEquipment'" value="��  ��" />
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
							�������
						</th>
						<th>
							��ֵ
						</th>

						<th>
							��������
						</th>

						<th>
							��������
						</th>
						<th>
							�ɹ�ʱ��
						</th>

						<th>
							��ע
						</th>

						
						<th>
							ʹ�ò�������
						</th>
						<th>
							ʹ����<br>���
						</th>
						<th>
							ʹ����
						</th>
						<th>
							�ʲ�<br>״̬
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
							
							<td >
								${item.tname}
							</td>
							<td>
								${item.eworth}
							</td>
							<td>
								${item.eproducer}
							</td>
							<td>
								${item.eoutdate}
							</td>
							<td>
								${item.ebuydate}
							</td>
							<td>
								${item.eremarks}
							</td>
							
							<td>
								${item.sname}
							</td>
							<td>
								${item.pcode}
							</td>
							<td>
								${item.pname}
							</td>
							<td>
								${item.estatus}
							</td>
							<td align="center" width="200px">
								<a href="servlet/EquipmentServlet?method=updatePage&eid=${item.eid}">�޸�</a>
								<a
									href="servlet/EquipmentServlet?method=deleteEquipment&eid=${item.eid}">ɾ��</a>
							</td>

						</tr>
					</c:forEach>
				</table>
				${pagingModel.pageLink }${pagingModel.pageInfo }
			</div>
		</form>
	</body>
</html>
