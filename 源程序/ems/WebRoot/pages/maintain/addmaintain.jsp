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

		<title>����ʲ�ά����Ϣ</title>
		<script src="<%=basePath%>/scripts/meizzDate.js" ></script>
		<link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript">
	   var equ =[<c:forEach items="${equipment}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>{'eid':'${item.eid}','ecode':'${item.ecode}','ename':'${item.ename}'}</c:forEach>];

      function checkForm(){
         var form = document.forms[0];
	     if(form.eid.value=="" || form.eid.value.length<=0){
			alert("��ѡ���ʲ���ţ�");
			form.tcode.focus();
			return false;
		}
		if(form.mhour.value=="" || form.mhour.value.length<=0){
			alert("������ά�޹�ʱ��");
			form.mhour.focus();
			return false;
		}
		var re =/^[0-9]{1,}(|.[0-9]{1,2})$/;  
		if(!re.test(form.mhour.value)){
			alert("ά�޹�ʱ���������֣�");
			form.mhour.focus();
			return false;
		} 
		if(form.mcharge.value=="" || form.mcharge.value.length<=0){
			alert("������ά�޷��ã�");
			form.mcharge.focus();
			return false;
		}
		if(!re.test(form.mcharge.value)){
			alert("ά�޷��ñ��������֣�");
			form.mcharge.focus();
			return false;
		} 
		if(form.mreson.value=="" || form.mreson.value.length<=0){
			alert("���������ԭ��");
			form.mreson.focus();
			return false;
		}
		if(form.mdate.value=="" || form.mdate.value.length<=0){
			alert("������ά�����ڣ�");
			form.mdate.focus();
			return false;
		}
		if(form.mperson.value=="" || form.mperson.value.length<=0){
			alert("������ά���ˣ�");
			form.mperson.focus();
			return false;
		}
		if(form.mtel.value=="" || form.mtel.value.length<=0){
			alert("������ά���˵绰��");
			form.mtel.focus();
			return false;
		}
		return true;
	}
	function changecode()
	{
		 var form = document.forms[0];
		var i ;
		for(i=0;i<equ.length;i++)
		{
			if(equ[i].eid==form.eid.value)
			{
				form.ename.value=equ[i].ename;
			}
		}
	}
</script>
	</head>

	<body >
		<div align="center">
			<h1>
				����ʲ�ά����Ϣ
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/MaintainServlet?method=saveMaintain" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							�ʲ���ţ�
						</td>
						<td>
							<select id="eid" name="eid" onchange="changecode()">
								<option value="">--��ѡ��--</option>
								<c:forEach var="item" items="${equipment}">
								<option value="${item.eid }">${item.ecode }</option>
								</c:forEach>
							</select>	
							<span style="color: red;">*</span>					
						</td>
					</tr>
					<tr>
						<td>
							�ʲ����ƣ�
						</td>
						<td>
							<input type="text" id="ename" name="ename" readonly="readonly">	
							<span style="color: red;">*</span>					
						</td>
					</tr>
					<tr>
						<td>
							ά�޹�ʱ
						</td>
						<td>
							<input type="text" id="mhour" name="mhour" >		
							<span style="color: red;">*</span>				
						</td>
					</tr>
					<tr>
						<td>
							ά�޷���
						</td>
						<td>
							<input type="text" id="mcharge" name="mcharge" >
							<span style="color: red;">*</span>						
						</td>
					</tr>
					<tr>
						<td>
							����ԭ��
						</td>
						<td>
							<input type="text" id="mreson" name="mreson"  >			
							<span style="color: red;">*</span>			
						</td>
					</tr>
					<tr>
						<td>
							ά������
						</td>
						<td>
							<input type="text" id="mdate" name="mdate" onfocus="setday(this)">		
							<span style="color: red;">*</span>				
						</td>
					</tr>
					
					<tr>
						<td>
							ά����
						</td>
						<td>
							<input type="text" id="mperson" name="mperson" >	
							<span style="color: red;">*</span>					
						</td>
					</tr>
					<tr>
						<td>
							ά���˵绰
						</td>
						<td>
							<input type="text" id="mtel" name="mtel" >	
							<span style="color: red;">*</span>					
						</td>
					</tr>
					<tr>
						<td>
							��ע��
						</td>
						<td>
							<textarea rows="3" cols="50" id="mremarks" name="mremarks"></textarea>	
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
