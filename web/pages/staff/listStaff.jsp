<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />
</head>

<body >
 <table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="39%" align="left">[员工管理]</td>
   
    <td width="57%"align="right">
    	<%--高级查询 --%>
		<a href="javascript:void(0)" onclick="condition()"><img src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif" /></a>
    	<%--员工注入 --%>
	  	<a href="${pageContext.request.contextPath}/pages/staff/addStaff.jsp">
	  		<img src="${pageContext.request.contextPath}/images/button/tianjia.gif" />
	  	</a>
      
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>

<!-- 查询条件：马上查询 -->
<form id="conditionFormId" action="${pageContext.request.contextPath}/staff/staffAction_findAll" method="post">
	<table width="88%" border="0" style="margin: 20px;" >
	  <tr>
	    <td width="80px">部门：</td>
	    <td width="200px">
	    	
	    	<select name="crmPost.crmDepartment.depId" onchange="changePost(this)">
			    <option value="">--请选择部门--</option>
			</select>

	    </td>
	    <td width="80px" >职务：</td>
	    <td width="200px" >
	    	
	    	<select name="crmPost.postId" id="postSelectId">
			    <option value="">--请选择职务--</option>
			</select>

	    </td>
	    <td width="80px">姓名：</td>
	    <td width="200px" ><input type="text" name="staffName" size="12" /></td>
	    <td ></td>
	  </tr>
	</table>
</form>


<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
  <tr>
    <td ><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
  </tr>
</table>

<table width="100%" border="1" >
  <tr class="henglan" style="font-weight:bold;">
    <td width="10%" align="center">员工姓名</td>
    <td width="6%" align="center">性别</td>
    <td width="12%" align="center">入职时间</td>
    <td width="15%" align="center">所属部门</td>
    <td width="10%" align="center">职务</td>
    <td width="10%" align="center">编辑</td>
  </tr>

	<s:iterator var="s" value="staffs">
	<tr class="tabtd1">
		<td align="center">${s.staffName}</td>
		<td align="center">${s.gender}</td>
		<td align="center">${s.onDutyDate}</td>
		<td align="center">${s.department.depName}</td>
		<td align="center">${s.post.postName}</td>
		<td width="7%" align="center">

			<a href="${pageContext.request.contextPath}/staff/edit.action?&staffId=${s.staffId}"><img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img" /></a>
			<%--<a href="${pageContext.request.contextPath}/pages/staff/editStaff.jsp?--%>
			<%--loginName=${s.loginName}&loginPwd=${s.loginPwd}&staffName=${s.staffName}&--%>
			<%--gender=${s.gender}&onDutyDate=${s.onDutyDate}&depId=${s.post.department.depId}&--%>
			<%--postId=${s.post.postId}&postName=${s.post.postName}&depName=${s.post.department.depName}"><img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img" /></a>--%>
		</td>
	</s:iterator>

</table>


<%-- 
<table border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td align="right">
    	<span>第1/3页</span>
        <span>
        	<a href="#">[首页]</a>&nbsp;&nbsp;
            <a href="#">[上一页]</a>&nbsp;&nbsp;
            <a href="#">[下一页]</a>&nbsp;&nbsp;
            <a href="#">[尾页]</a>
        </span>
    </td>
  </tr>
</table>
--%>
</body>
</html>
