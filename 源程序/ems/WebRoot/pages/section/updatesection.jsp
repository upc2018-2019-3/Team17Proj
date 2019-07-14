<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改学生</title>
    <link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript">
	  var scode =[<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>{'sid':'${item.sid}','scode':'${item.scode}'}</c:forEach>];
      var flag=0;
      function checkForm(){
         var form = document.forms[0];
	     if(form.sname.value=="" || form.sname.value.length<=0){
			alert("请输入部门名！");
			form.sname.focus();
			return false;
		}
		checkcode();
		if(flag==1)
			{
				alert("部门名已存在！");
				form.sname.focus();
				return false;
			}
		
		if(form.scode.value=="" || form.scode.value.length<=0){
			alert("请填写部门编号！");
			form.scode.focus();
			return false;
		}
		
		return true;
	}
	function checkcode()
	{
		 var form = document.forms[0];
		var i ;
		for(i=0;i<scode.length;i++)
		{
			
			if(scode[i].scode==form.scode.value&&form.sid.value!=scode[i].sid)
			{
				alert("部门编号已存在！");
				flag=1;
				form.scode.focus();
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
				修改学生
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/SectionServlet?method=updateSection" method="post"
				onsubmit="return checkForm()">
				<table border="1">
				<tr>
						<td>
							部门名：
						</td>
						<td>
							<input type="text" id="sname" name="sname" value="${section.sname }">	
							<input type="hidden" value="${section.sid }" name="sid" id="sid">	
							<span style="color: red;">*</span>				
						</td>
					</tr>
					<tr>
						<td>
							部门编号：
						</td>
						<td>
							<input type="text" id="scode" name="scode" onblur="checkcode()" value="${section.scode }">
							<span style="color: red;">*</span>		
							
						</td>
					</tr>
					<tr>
						<td>
							备注：
						</td>
						<td>
							<textarea rows="3" cols="50" id="sremarks" name="sremarks">${section.sremarks }</textarea>	
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
