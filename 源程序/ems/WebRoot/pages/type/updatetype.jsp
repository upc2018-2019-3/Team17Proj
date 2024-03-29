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
    
    <title>修改资产类型</title>
    <link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" language="javascript">
	  var tcode =[<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>{'tid':'${item.tid}','tcode':'${item.tcode}'}</c:forEach>];
       var flag=0;
       function checkForm(){
         var form = document.forms[0];
	     if(form.tcode.value=="" || form.tcode.value.length<=0){
			alert("请输入编号！");
			form.tcode.focus();
			return false;
		}
		checkcode();
		if(flag==1)
			{
				alert("编号已存在！");
				form.tcode.focus();
				return false;
			}
		
		if(form.tname.value=="" || form.tname.value.length<=0){
			alert("请输入名称！");
			form.tname.focus();
			return false;
		}
		
		return true;
	}
	function checkcode()
	{
		 var form = document.forms[0];
		var i ;
		for(i=0;i<tcode.length;i++)
		{
			if(tcode[i].tcode==form.tcode.value&&form.tid.value!=tcode[i].tid)
			{
				alert("资产类型编号已存在！");
				form.tcode.focus();
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
				修改资产类型
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/TypeServlet?method=updateType" method="post"
				onsubmit="return checkForm()">
				<table border="1">
				<tr>
						<td>
							类型编号：
						</td>
						<td>
							<input type="text" id="tcode" name="tcode" value="${type.tcode }" onblur="checkcode()">	
							<input id="tid" name="tid" value="${type.tid }" type="hidden">
							<span style="color: red;">*</span>	
											
						</td>
					</tr>
					<tr>
						<td>
							类型名：
						</td>
						<td>
							<input type="text" id="tname" name="tname" value="${type.tname }">
							<span style="color: red;">*</span>		
							
						</td>
					</tr>
					<tr>
						<td>
							备注：
						</td>
						<td>
							<textarea rows="3" cols="50" id="tremarks" name="tremarks">${type.tremarks  }</textarea>	
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
