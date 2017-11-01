<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
    <script src="../../js/jquery-3.2.1.js"></script>
</head>

<body class="emp_body">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
        <td class="topg"></td>
    </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="wukuang" width="100%">
    <tr>
        <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
        <td width="44%" align="left">[员工管理]</td>

        <td width="52%" align="right">
            <!-- 提交表单 -->
            <a href="javascript:void(0)" onclick="document.forms[0].submit()">
                <img src="${pageContext.request.contextPath}/images/button/save.gif"/>
            </a>
            <!-- 执行js，进行返回 -->
            <a href="javascript:void(0)" onclick="window.history.go(-1)"><img
                    src="${pageContext.request.contextPath}/images/button/tuihui.gif"/></a>

        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>
<span style="color: red">${sessionScope.msg}</span>

<form action="${pageContext.request.contextPath}/staff/edit.action" method="post">

    <input type="hidden" name="staffId" value="${param.staffId}"/>

    <table width="88%" border="0" class="emp_table" style="width:80%;">
        <tr>
            <td>登录名：</td>
            <td><input type="text" name="loginName" value="${param.loginName}"/></td>
            <td>密码：</td>
            <td><input type="password" name="loginPwd" value="" placeholder="请重新输入密码" autocomplete="off"/></td>
        </tr>
        <tr>
            <td>姓名：</td>
            <td><input type="text" name="staffName" value="${param.staffName}"/></td>
            <td>性别：</td>
            <td>
                <input type="radio" name="gender" value="男" <c:if test="${param.gender eq '男'}">checked</c:if>/>男
                <input type="radio" name="gender" value="女" <c:if test="${param.gender eq '女'}">checked</c:if>/>女
            </td>
        </tr>
        <tr>
            <td width="10%">所属部门：</td>
            <td width="20%">
                <select name="depId"  id="department">
                    <s:iterator var="dep" value="departments">
                        <option value="${dep.depId}" <c:if test="${dep.depId eq param.depId}">selected</c:if>>${dep.depName}</option>
                    </s:iterator>
                </select>

            </td>
            <td width="8%">职务：</td>
            <td width="62%">
                <select name="postId" id="post">
                    <s:iterator value="posts" var="po">
                        <option value="${po.postId}" <c:if test="${po.postId eq param.postId}">selected</c:if>>${po.postName}</option>
                    </s:iterator>
                </select>
            </td>
        </tr>
        <tr>
            <td width="10%">入职时间：</td>
            <td width="20%">
                <input type="text" name="onDutyDate" value="${param.onDutyDate}" readonly="readonly"
                       onfocus="c.showMoreDay=true; c.show(this);"/>
            </td>
            <td width="8%"></td>
            <td width="62%"></td>
        </tr>
    </table>
</form>

<script>
    $(function () {

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
