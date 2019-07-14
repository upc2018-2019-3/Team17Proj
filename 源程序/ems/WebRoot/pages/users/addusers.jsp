<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

		<title>添加用户</title>
<link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript">
	  var uname = [<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>'${item.uname}'</c:forEach>];
     var flag=0;
      function checkForm(){
         var form = document.forms[0];
	     if(form.uname.value=="" || form.uname.value.length<=0){
			alert("请输入用户名！");
			form.uname.focus();
			return false;
		}
		checkname();
		if(flag==1)
			{
				alert("用户名已存在！");
				form.uname.focus();
				return false;
			}
		
		if(form.upassword.value=="" || form.upassword.value.length<=0){
			alert("请输入密码！");
			form.upassword.focus();
			return false;
		}
		if(form.utype.value=="" || form.utype.value.length<=0){
			alert("请选择类型！");
			form.utype.focus();
			return false;
		}
		return true;
	}
	function checkname()
	{
		 var form = document.forms[0];
		var i ;
		for(i=0;i<uname.length;i++)
		{
			if(uname[i]==form.uname.value)
			{
				alert("用户名已存在！");
				form.uname.focus();
				flag=1;
				return false;
			}
			else
			{
				flag=0;
			}
		}
	}
</script>
	</head>

	<body >
		<div align="center">
			<h1>
				添加用户
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/UsersServlet?method=saveUsers" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							用户名：
						</td>
						<td>
							<input type="text" id="uname" name="uname" onblur="checkname()">
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							密码：
						</td>
						<td>
							<input type="password" id="upassword" name="upassword" >	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							类型：
						</td>
						<td>
							<select id="utype" name="utype">
								<option value="">--请选择--</option>
								<option value="1">管理员</option>
								<option value="2">普通</option>
							</select>	
							<span style="color: red;">*</span>		
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="提交" />
							<input type="reset" value="重置" />
							<input class="box" type="button" value="返  回"
							onClick="javascript:history.go(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
