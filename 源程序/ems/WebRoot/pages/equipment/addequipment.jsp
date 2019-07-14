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

		<title>添加资产信息</title>
<link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>/scripts/meizzDate.js" ></script>
		<script type="text/javascript" language="javascript">
	  var ecode = [<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>'${item.ecode}'</c:forEach>];
     var flag=0;
      function checkForm(){
         var form = document.forms[0];
         if(form.ecode.value=="" || form.ecode.value.length<=0){
			alert("请输入资产信息编号！");
			form.ecode.focus();
			return false;
		}
		checkcode();
		if(flag==1)
			{
				alert("资产信息编号已存在！");
				form.ecode.focus();
				return false;
			}
	     if(form.ename.value=="" || form.ename.value.length<=0){
			alert("请输入资产信息名称！");
			form.ename.focus();
			return false;
		}
		if(form.tid.value=="" || form.tid.value.length<=0){
			alert("请选择所属类别！");
			form.tid.focus();
			return false;
		}
		if(form.eworth.value=="" || form.eworth.value.length<=0){
			alert("请输入价值！");
			form.eworth.focus();
			return false;
		}
		var re =/^[0-9]{1,}(|.[0-9]{1,2})$/;  
		if(!re.test(form.eworth.value)){
			alert("价值必须是数字！");
			form.eworth.focus();
			return false;
		} 
		if(form.eproducer.value=="" || form.eproducer.value.length<=0){
			alert("请输入生产厂家！");
			form.eproducer.focus();
			return false;
		}
		if(form.eoutdate.value=="" || form.eoutdate.value.length<=0){
			alert("请输入出厂日期！");
			form.eoutdate.focus();
			return false;
		}
		if(form.ebuydate.value=="" || form.ebuydate.value.length<=0){
			alert("请输入采购时间！");
			form.ebuydate.focus();
			return false;
		}
		if(form.pid.value=="" || form.pid.value.length<=0){
			alert("请选择使用人！");
			form.pid.focus();
			return false;
		}
		if(form.esid.value=="" || form.esid.value.length<=0){
			alert("请选择使用部门！");
			form.esid.focus();
			return false;
		}
		if(form.estatus.value=="" || form.estatus.value.length<=0){
			alert("请选择使用状态！");
			form.estatus.focus();
			return false;
		}
		return true;
	}
	function checkcode()
	{
		 var form = document.forms[0];
		var i ;
		for(i=0;i<ecode.length;i++)
		{
			if(ecode[i]==form.ecode.value)
			{
				alert("资产信息编号已存在！");
				form.ecode.focus();
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
				添加资产信息
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/EquipmentServlet?method=saveEquipment" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							资产编号：
						</td>
						<td>
							<input type="text" id="ecode" name="ecode" onblur="checkcode()">	
							<span style="color: red;">*</span>					
						</td>
						<td>
							资产名称：
						</td>
						<td>
							<input type="text" id="ename" name="ename" >
							<span style="color: red;">*</span>						
						</td>
					</tr>
					
					<tr>
						<td>
							所属类别：
						</td>
						<td>
							<select id="tid" name="tid">
								<option value="">--请选择--</option>
								<c:forEach var="item" items="${type}">
								<option value="${item.tid }">${item.tcode }:${item.tname }</option>
								</c:forEach>
							</select>	
							<span style="color: red;">*</span>	
						</td>
						<td>
							价值：
						</td>
						<td>
							<input type="text" id="eworth" name="eworth" >	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							生产厂家：
						</td>
						<td>
							<input type="text" id="eproducer" name="eproducer" >	
							<span style="color: red;">*</span>	
						</td>
						<td>
							出厂日期：
						</td>
						<td>
							<input type="text" id="eoutdate" name="eoutdate" onfocus="setday(this)" >	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							采购时间：
						</td>
						<td>
							<input type="text" id="ebuydate" name="ebuydate" onfocus="setday(this)" >	
							<span style="color: red;">*</span>	
						</td>
						<td>
							使用人：
						</td>
						<td>
							<select id="pid" name="pid">
								<option value="">--请选择--</option>
								<c:forEach var="item" items="${personel}">
								<option value="${item.pid }">${item.pcode }:${item.pname }</option>
								</c:forEach>
							</select>
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							使用部门：
						</td>
						<td>
							<select id="esid" name="esid">
								<option value="">--请选择--</option>
								<c:forEach var="item" items="${section}">
								<option value="${item.sid }">${item.scode }:${item.sname }</option>
								</c:forEach>
							</select>
							<span style="color: red;">*</span>	
						</td>
						<td>
							资产状态：
						</td>
						<td>
							<select id="estatus" name="estatus">
								<option value="">--请选择--</option>
								<option value="正常">正常</option>
								<option value="报废">报废</option>
							</select>	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							备注：
						</td>
						<td colspan="3">
							<textarea rows="2" cols="50" id="eremarks" name="eremarks"></textarea>
						</td>
						
					</tr>
					<tr>
						<td colspan="4">
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
