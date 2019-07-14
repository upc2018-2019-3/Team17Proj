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
    
    <title>�޸���Ա</title>
    <link href="<%=basePath%>/css/page.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath%>/scripts/meizzDate.js" ></script>
	<script type="text/javascript" language="javascript">
	  var ecode =[<c:forEach items="${list}" var="item" varStatus="i"><c:if test="${i.index!=0}">,</c:if>{'eid':'${item.eid}','ecode':'${item.ecode}'}</c:forEach>];
        var flag=0;
      function checkForm(){
         var form = document.forms[0];
         if(form.ecode.value=="" || form.ecode.value.length<=0){
			alert("�������ʲ���Ϣ��ţ�");
			form.ecode.focus();
			return false;
		}
		checkcode();
		if(flag==1)
			{
				alert("�ʲ���Ϣ����Ѵ��ڣ�");
				form.ecode.focus();
				return false;
			}
	     if(form.ename.value=="" || form.ename.value.length<=0){
			alert("�������ʲ���Ϣ���ƣ�");
			form.ename.focus();
			return false;
		}
		if(form.tid.value=="" || form.tid.value.length<=0){
			alert("��ѡ���������");
			form.tid.focus();
			return false;
		}
		if(form.eworth.value=="" || form.eworth.value.length<=0){
			alert("�������ֵ��");
			form.eworth.focus();
			return false;
		}
		var re =/^[0-9]{1,}(|.[0-9]{1,2})$/;  
		if(!re.test(form.eworth.value)){
			alert("��ֵ���������֣�");
			form.eworth.focus();
			return false;
		} 
		if(form.eproducer.value=="" || form.eproducer.value.length<=0){
			alert("�������������ң�");
			form.eproducer.focus();
			return false;
		}
		if(form.eoutdate.value=="" || form.eoutdate.value.length<=0){
			alert("������������ڣ�");
			form.eoutdate.focus();
			return false;
		}
		if(form.ebuydate.value=="" || form.ebuydate.value.length<=0){
			alert("������ɹ�ʱ�䣡");
			form.ebuydate.focus();
			return false;
		}
		if(form.pid.value=="" || form.pid.value.length<=0){
			alert("��ѡ��ʹ���ˣ�");
			form.pid.focus();
			return false;
		}
		if(form.esid.value=="" || form.esid.value.length<=0){
			alert("��ѡ��ʹ�ò��ţ�");
			form.esid.focus();
			return false;
		}
		if(form.estatus.value=="" || form.estatus.value.length<=0){
			alert("��ѡ��ʹ��״̬��");
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
			if(ecode[i].ecode==form.ecode.value&&form.eid.value!=ecode[i].eid)
			{
				alert("��Ա����Ѵ��ڣ�");
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
				�޸���Ա
			</h1>
		</div>
		<div id="user" align="center">
			<form action="servlet/EquipmentServlet?method=updateEquipment" method="post"
				onsubmit="return checkForm()">
				<table border="1">
					<tr>
						<td>
							�ʲ���ţ�
						</td>
						<td>
							<input type="text" id="ecode" name="ecode" onblur="checkcode()" value="${equipment.ecode }">	
							<input type="hidden" value="${equipment.eid }" name="eid" id="eid">				
							<span style="color: red;">*</span>	
						</td>
						
						<td>
							�ʲ����ƣ�
						</td>
						<td>
							<input type="text" id="ename" name="ename" value="${equipment.ename }" >		
							<span style="color: red;">*</span>				
						</td>
					</tr>
					
					<tr>
						<td>
							�������
						</td>
						<td>
							<select id="tid" name="tid">
								<option value="">--��ѡ��--</option>
								<c:forEach var="item" items="${type}">
								<option value="${item.tid }">${item.tcode }:${item.tname }</option>
								</c:forEach>
							</select>	
							<script type="text/javascript">document.getElementById('tid').value = '${equipment.tid }'</script>	
							<span style="color: red;">*</span>	
						</td>
						<td>
							��ֵ��
						</td>
						<td>
							<input type="text" id="eworth" name="eworth" value="${ equipment.eworth}" >	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							�������ң�
						</td>
						<td>
							<input type="text" id="eproducer" name="eproducer" value="${ equipment.eproducer}">	
							<span style="color: red;">*</span>	
						</td>
						<td>
							�������ڣ�
						</td>
						<td>
							<input type="text" id="eoutdate" name="eoutdate" onfocus="setday(this)"  value="${ equipment.eoutdate}">
							<span style="color: red;">*</span>		
						</td>
					</tr>
					<tr>
						<td>
							�ɹ�ʱ�䣺
						</td>
						<td>
							<input type="text" id="ebuydate" name="ebuydate" onfocus="setday(this)" value="${ equipment.ebuydate}">
							<span style="color: red;">*</span>		
						</td>
						<td>
							ʹ���ˣ�
						</td>
						<td>
							<select id="pid" name="pid">
								<option value="">--��ѡ��--</option>
								<c:forEach var="item" items="${personel}">
								<option value="${item.pid }">${item.pcode }:${item.pname }</option>
								</c:forEach>
							</select>
							<script type="text/javascript">document.getElementById('pid').value = '${equipment.pid }'</script>	
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							ʹ�ò��ţ�
						</td>
						<td>
							<select id="esid" name="esid">
								<option value="">--��ѡ��--</option>
								<c:forEach var="item" items="${section}">
								<option value="${item.sid }">${item.scode }:${item.sname }</option>
								</c:forEach>
							</select>
							<script type="text/javascript">document.getElementById('esid').value = '${equipment.esid }'</script>	
							<span style="color: red;">*</span>	
						</td>
						<td>
							�ʲ�״̬��
						</td>
						<td>
							<select id="estatus" name="estatus">
								<option value="">--��ѡ��--</option>
								<option value="����">����</option>
								<option value="����">����</option>
							</select>
							<script type="text/javascript">document.getElementById('estatus').value = '${equipment.estatus }'</script>		
							<span style="color: red;">*</span>	
						</td>
					</tr>
					<tr>
						<td>
							��ע��
						</td>
						<td colspan="3">
							<textarea rows="2" cols="50" id="eremarks" name="eremarks">${ equipment.eremarks}</textarea>
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
