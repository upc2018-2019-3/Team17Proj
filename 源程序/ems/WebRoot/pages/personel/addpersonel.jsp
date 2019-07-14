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

		<title>添加人员</title>
<link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript">
	  var pcode = [<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>'${item.pcode}'</c:forEach>];
     var flag=0;
      function checkForm(){
         var form = document.forms[0];
         if(form.pcode.value=="" || form.pcode.value.length<=0){
			alert("请输入人员编号！");
			form.pcode.focus();
			return false;
		}
		checkcode();
		if(flag==1)
			{
				alert("人员编号已存在！");
				form.pcode.focus();
				return false;
			}
	     if(form.pname.value=="" || form.pname.value.length<=0){
			alert("请输入人员姓名！");
			form.pname.focus();
			return false;
		}
		if(form.psex.value=="" || form.psex.value.length<=0){
			alert("请选择性别！");
			form.psex.focus();
			return false;
		}
		if(form.page.value=="" || form.page.value.length<=0){
			alert("请输入年龄！");
			form.page.focus();
			return false;
		}
		var re =/^[0-9]{1,}(|.[0-9]{1,2})$/;  
		if(!re.test(form.page.value)){
			alert("年龄必须是数字！");
			form.page.focus();
			return false;
		} 
		if(form.sid.value=="" || form.sid.value.length<=0){
			alert("请选择部门！");
			form.sid.focus();
			return false;
		}
		return true;
	}
	function checkcode()
	{
		 var form = document.forms[0];
		var i ;
		for(i=0;i<pcode.length;i++)
		{
			if(pcode[i]==form.pcode.value)
			{
				alert("人员编号已存在！");
				form.pcode.focus();
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
				添加人员
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/PersonelServlet?method=savePersonel" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							编号：
						</td>
						<td>
							<input type="text" id="pcode" name="pcode" onblur="checkcode()">	
							<span style="color: red;">*</span>					
						</td>
						<td>
							姓名：
						</td>
						<td>
							<input type="text" id="pname" name="pname" >	
							<span style="color: red;">*</span>					
						</td>
					</tr>
					
					<tr>
						<td> 
							性别： 
						</td>
						<td>
							<select id="psex" name="psex">
								<option value="">--请选择--</option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>		
							<span style="color: red;">*</span>	
						</td>
						<td>
							年龄：
						</td>
						<td>
							<input type="text" id="page" name="page" >	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							家庭住址：
						</td>
						<td>
							<input type="text" id="padd" name="padd" >	
						</td>
						<td>
							联系电话：
						</td>
						<td>
							<input type="text" id="ptel" name="ptel" >	
						</td>
					</tr>
					<tr>
						<td>
							所属部门：
						</td>
						<td>
							<select id="sid" name="sid">
								<option value="">--请选择--</option>
								<c:forEach var="item" items="${section}">
								<option value="${item.sid }">${item.scode }:${item.sname }</option>
								</c:forEach>
							</select>
							<span style="color: red;">*</span>	
						</td>
						<td>
							邮箱：
						</td>
						<td>
							<input type="text" id="pmail" name="pmail" >	
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
