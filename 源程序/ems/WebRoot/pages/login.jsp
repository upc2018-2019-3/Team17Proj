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
		<title>��½����</title>
		<script type="text/javascript" language="javascript">
	function checkForm(){
		var form = document.forms[0];
		if(form.userName.value==""){
			alert("�������û���");
			return false;
		}
		if(form.password.value==""){
			alert("����������");
			return false;
		}
		return true;
	}
	
</script>
	</head>
	<body>
		<c:if test="${message!=null&&message!=''}">
		<script type="text/javascript">
			alert('${message}');
		</script>
		</c:if>
		<div id="userPart" align="center" style="">
			<form action="servlet/LoginServlet?method=login" method="post"
				name="frm" onsubmit="return checkForm()">
				<table border="0" width="570" height="370" align="center"
					style="margin-top: 155px; margin-left: 30px;"
					background="<%=basePath%>/images/login.jpg">
					<tr>
						<td align="center" width="100%">
							<table width="300px" height="150px" border="0">

								<tr>
									<td>
										��&nbsp;��&nbsp;����
									</td>
									<td>
										<input name="userName" type="text" size="20" width="20px" value="" />
									</td>
								</tr>
								<tr>
									<td>
										��&nbsp;&nbsp;&nbsp;&nbsp;�룺
									</td>
									<td>
										<input name="password" value="" type="password" size="21" width="20px" />
									</td>
								</tr>
								
								<tr>
									<td align="center" colspan="2">
										<input type="submit" value="��  ¼" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="reset" value="��  ��" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
