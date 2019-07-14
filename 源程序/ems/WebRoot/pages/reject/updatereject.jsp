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
    
    <title>�޸��ʲ�������Ϣ</title>
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
		if(form.rdate.value=="" || form.rdate.value.length<=0){
			alert("�����뱨��ʱ�䣡");
			form.rdate.focus();
			return false;
		}
		var re =/^[0-9]{1,}(|.[0-9]{1,2})$/;  
		
		if(form.rdepreciation.value=="" || form.rdepreciation.value.length<=0){
			alert("�������ۼ��۾ɣ�");
			form.rdepreciation.focus();
			return false;
		}
		if(!re.test(form.rdepreciation.value)){
			alert("�ۼ��۾ɱ��������֣�");
			form.rdepreciation.focus();
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
				�޸��ʲ�������Ϣ
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/RejectServlet?method=updateReject" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							�ʲ���ţ�
						</td>
						<td>
							<select id="reid" name="reid" onchange="changecode()" disabled="disabled" >
								<option value="">--��ѡ��--</option>
								<c:forEach var="item" items="${equipment}">
								<option value="${item.eid }">${item.ecode }</option>
								</c:forEach>
							</select>		
							<input id="rid" type="hidden" name="rid" value="${reject.rid }">
							<input id="eid" type="hidden" name="eid" value="${reject.eid }">
							<span style="color: red;">*</span>	
						</td>
						
					</tr>
					<tr>
						<td>
							�ʲ����ƣ�
						</td>
						<td>
							<input type="text" id="ename" name="ename" readonly="readonly"  value="${reject.ename }">	
							<script type="text/javascript">document.getElementById("reid").value='${reject.eid}';changecode();</script>		
							<span style="color: red;">*</span>					
						</td>
					</tr>
					<tr>
						<td>
							����ʱ��
						</td>
						<td>
							<input type="text" id="rdate" name="rdate" onfocus="setday(this)" value="${reject.rdate }">		
							<span style="color: red;">*</span>				
						</td>
					</tr>
					<tr>
						<td>
							�ۼ��۾ɣ�
						</td>
						<td>
							<input type="text" id="rdepreciation" name="rdepreciation" value="${reject.rdepreciation }">	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					
					<tr>
						<td>
							��ע��
						</td>
						<td>
							<textarea rows="3" cols="50" id="rremarks" name="rremarks">${reject.rremarks }</textarea>	
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
