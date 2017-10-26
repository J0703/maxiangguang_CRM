<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
	<script src="../../jquery-3.2.1.js"></script>
</head>

<body class="emp_body">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="44%" align="left">[员工管理]</td>
   
    <td width="52%"align="right">
    	<!-- 提交表单 -->
       <a href="javascript:void(0)" onclick="document.forms[0].submit()">
       	<img src="${pageContext.request.contextPath}/images/button/save.gif" />
       </a>
       <!-- 执行js，进行返回 -->
       <a href="javascript:void(0)" onclick="window.history.go(-1)"><img src="${pageContext.request.contextPath}/images/button/tuihui.gif" /></a>
      
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>

<form action="/crm2/staff/staffAction_edit.action" method="post">
	
	<input type="hidden" name="staffId" value="2c9091c14c78e58b014c78e7ecd90007"/>
	
	<table width="88%" border="0" class="emp_table" style="width:80%;">
	 <tr>
	    <td>登录名：</td>
	    <td><input type="text" name="loginName" value="${staff.loginName}" /> </td>
	    <td>密码：</td>
	    <td><input type="password" name="loginPwd" value="${param.loginPwd}" /> </td>
	  </tr>
	 <tr>
	    <td>姓名：</td>
	    <td><input type="text" name="staffName" value="${param.staffName}" /> </td>
	    <td>性别：</td>
		 <td>
	    	<input type="radio" name="gender" value="男" <c:if test="${param.gender eq '男'}">checked</c:if>/>男
	    	<input type="radio" name="gender" value="女" <c:if test="${param.gender eq '女'}">checked</c:if>/>女
	    </td>
	  </tr>
	 <tr>
	    <td width="10%">所属部门：</td>
	    <td width="20%">
	    	<select name="depId"  onchange="changePost(this)" id="department">
			    <option value="${param.depId}">${param.depName}</option>
			</select>

	    </td>
	    <td width="8%">职务：</td>
	    <td width="62%">
	    	<select name="postId" id="post">
				<option value="${param.postId}">${param.postName}</option>
			</select>
	    </td>
	  </tr>
	  <tr>
	    <td width="10%">入职时间：</td>
	    <td width="20%">
	    	<input type="text" name="onDutyDate" value="${param.onDutyDate}" readonly="readonly" onfocus="c.showMoreDay=true; c.show(this);"/>
	    </td>
	    <td width="8%"></td>
	    <td width="62%"></td>
	  </tr>
	</table>
</form>

<script>
	$(function () {
		$.post("${pageContext.request.contextPath}/post/showDepart.action",
				null,
				function (data) {
					var _html = "<option>----请--选--择----</option>";
					$.each(data, function (index, per) {
						_html += '<option value="' + per.depId + '">' + per.depName + '</option>';
					});
					$("#department").html(_html);
				}, "json"
		);

		$("#post").click(function () {
			$.post("${pageContext.request.contextPath}/post/showPost.action",
					{departId:${param.depId}},
					function (data) {
						var _html = "";
						$.each(data, function (index, per) {
							_html += '<option value="' + per.depId + '">' + per.depName + '</option>';
						});
						$("#department").html(_html);
					}, "json"
			);
		});

		$("#department").change(function () {
					$.post("${pageContext.request.contextPath}/post/showPost.action",
							{
								departId: $("#department").val()
							},
							function (data) {
								var _html = "<option>----请--选--择----</option>";
								$.each(data, function (index, per) {
									_html += "<option value='" + per.postId + "'>" + per.postName + "</option>";
								});
								$("#post").html(_html);
							}, "json"
					)
				}
		)

	})
</script>
</body>
</html>
