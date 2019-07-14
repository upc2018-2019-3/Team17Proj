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
		<title>首页</title>
		<link href="<%=basePath%>/css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function gO(url)
			{
				document.forms[0].action=url;
				document.forms[0].submit();
			}
		</script>
	</head>

	<body>
		<form action="" target="content">
		<table width="100%" height="10%">
			<tr>
				<td align="center" width="6%" background="/images/top.gif">
					<div align="right" style="font-weight: bold;"> 
						资产管理系统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 欢迎 
						 ${username }登录!&nbsp;&nbsp;&nbsp;&nbsp;<a href="pages/login.jsp" style="color: red;">退出</a>
					</div>
				</td>
			</tr>
		</table>

		<table width="100%" height="90%" border="1" cellspacing="0"
			cellpadding="1" >
			<tr>
				<td width="18%">
					<table width="100%" border="1">
						<tr>
							<td background="/images/left.gif" style="color: white;">
								<c:if test="${type==1 }">
									<div
										style="font-weight: bold;height: 500px;width: 200px;color: black;">
										<a href="servlet/UsersServlet?method=findAllUsers" target="content">用户管理</a>
										<br>
										<a href="servlet/SectionServlet?method=findAllSection" target="content">部门管理</a>
										<br>
										<a href="servlet/PersonelServlet?method=findAllPersonel" target="content">员工管理</a>
										<br>
										<a href="servlet/TypeServlet?method=findAllType" target="content">资产类别管理</a>
										<br>
										<a href="servlet/EquipmentServlet?method=findAllEquipment" target="content">资产信息管理</a>
										<br>
										<a href="servlet/MaintainServlet?method=findAllMaintain" target="content">资产维修管理</a>
										<br>
										<a href="servlet/RejectServlet?method=findAllReject" target="content">资产报废管理</a>
										<br>
										<a href="servlet/EquipmentServlet?method=searchEquipment" target="content">资产统计</a>
										<br>
									</div>
								</c:if>
								<c:if test="${type==2 }">
									<div
										style="font-weight: bold;height: 500px;width: 200px;">
										
										<a href="servlet/TypeServlet?method=findAllType" target="content">资产类别管理</a>
										<br>
										<a href="servlet/EquipmentServlet?method=findAllEquipment" target="content">资产信息管理</a>
										<br>
										<a href="servlet/MaintainServlet?method=findAllMaintain" target="content">资产维修管理</a>
										<br>
										<a href="servlet/RejectServlet?method=findAllReject" target="content">资产报废管理</a>
										<br>
										<a href="servlet/EquipmentServlet?method=searchEquipment" target="content">资产统计</a>
									</div>
								</c:if>
								
							</td>

						</tr>
					</table>

				</td>
				<td width="82%" >
					<iframe id="content" style="overflow-x: auto; overflow-y: auto;background-color: #33FFFF;"
						name="content" src="pages/message.jsp" width="100%" height="100%" ></iframe>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>


