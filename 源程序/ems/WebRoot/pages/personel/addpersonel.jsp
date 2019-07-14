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

		<title>�����Ա</title>
<link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript">
	  var pcode = [<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>'${item.pcode}'</c:forEach>];
     var flag=0;
      function checkForm(){
         var form = document.forms[0];
         if(form.pcode.value=="" || form.pcode.value.length<=0){
			alert("��������Ա��ţ�");
			form.pcode.focus();
			return false;
		}
		checkcode();
		if(flag==1)
			{
				alert("��Ա����Ѵ��ڣ�");
				form.pcode.focus();
				return false;
			}
	     if(form.pname.value=="" || form.pname.value.length<=0){
			alert("��������Ա������");
			form.pname.focus();
			return false;
		}
		if(form.psex.value=="" || form.psex.value.length<=0){
			alert("��ѡ���Ա�");
			form.psex.focus();
			return false;
		}
		if(form.page.value=="" || form.page.value.length<=0){
			alert("���������䣡");
			form.page.focus();
			return false;
		}
		var re =/^[0-9]{1,}(|.[0-9]{1,2})$/;  
		if(!re.test(form.page.value)){
			alert("������������֣�");
			form.page.focus();
			return false;
		} 
		if(form.sid.value=="" || form.sid.value.length<=0){
			alert("��ѡ���ţ�");
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
				alert("��Ա����Ѵ��ڣ�");
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
				�����Ա
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/PersonelServlet?method=savePersonel" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							��ţ�
						</td>
						<td>
							<input type="text" id="pcode" name="pcode" onblur="checkcode()">	
							<span style="color: red;">*</span>					
						</td>
						<td>
							������
						</td>
						<td>
							<input type="text" id="pname" name="pname" >	
							<span style="color: red;">*</span>					
						</td>
					</tr>
					
					<tr>
						<td> 
							�Ա� 
						</td>
						<td>
							<select id="psex" name="psex">
								<option value="">--��ѡ��--</option>
								<option value="��">��</option>
								<option value="Ů">Ů</option>
							</select>		
							<span style="color: red;">*</span>	
						</td>
						<td>
							���䣺
						</td>
						<td>
							<input type="text" id="page" name="page" >	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							��ͥסַ��
						</td>
						<td>
							<input type="text" id="padd" name="padd" >	
						</td>
						<td>
							��ϵ�绰��
						</td>
						<td>
							<input type="text" id="ptel" name="ptel" >	
						</td>
					</tr>
					<tr>
						<td>
							�������ţ�
						</td>
						<td>
							<select id="sid" name="sid">
								<option value="">--��ѡ��--</option>
								<c:forEach var="item" items="${section}">
								<option value="${item.sid }">${item.scode }:${item.sname }</option>
								</c:forEach>
							</select>
							<span style="color: red;">*</span>	
						</td>
						<td>
							���䣺
						</td>
						<td>
							<input type="text" id="pmail" name="pmail" >	
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="�ύ" />
							<input type="reset" value="����" />
							<input class="box" type="button" value="��  ��"
							onClick="javascript:history.go(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
