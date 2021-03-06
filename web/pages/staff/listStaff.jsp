<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>

    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>
    <script src="../../js/jquery-3.2.1.js"></script>
</head>

<body>
<table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
        <td class="topg"></td>
    </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="wukuang" width="100%">
    <tr>
        <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
        <td width="39%" align="left">[员工管理]</td>

        <td width="57%" align="right">
            <%--高级查询 --%>
            <a href="javascript:void(0)" onclick="document.forms[0].submit()"><img
                    src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif"/></a>
            <%--员工注入 --%>
            <c:if test="${applicationScope.staff.post.department.depId == '2c9090cb5f585a97015f585af7990000'}">
                <a href="${pageContext.request.contextPath}/pages/staff/addStaff.jsp">
                    <img src="${pageContext.request.contextPath}/images/button/tianjia.gif"/>
                </a>
            </c:if>

        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>

<!-- 查询条件：马上查询 -->
<form id="conditionFormId" action="${pageContext.request.contextPath}/staff/findAllStaff.action" method="post">
    <table width="88%" border="0" style="margin: 20px;">
        <tr>
            <td width="80px">部门：</td>
            <td width="200px">

                <select name="depId" id="department">
                    <option value="">--请选择部门--</option>
                </select>

            </td>
            <td width="80px">职务：</td>
            <td width="200px">

                <select name="postId" id="post">
                    <option value="">--请选择职务--</option>
                </select>

            </td>
            <td width="80px">姓名：</td>
            <td width="200px"><input type="text" name="staffName" size="12"/></td>
            <td></td>
        </tr>
    </table>
</form>


<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
    <tr>
        <td><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
    </tr>
</table>

<table width="100%" border="1">
    <tr class="henglan" style="font-weight:bold;">
        <td width="10%" align="center">员工姓名</td>
        <td width="6%" align="center">性别</td>
        <td width="12%" align="center">入职时间</td>
        <td width="15%" align="center">所属部门</td>
        <td width="10%" align="center">职务</td>
        <td width="10%" align="center">编辑</td>
    </tr>

    <s:iterator var="s" value="pageBean.data">
    <tr class="tabtd1">
        <td align="center">${s.staffName}</td>
        <td align="center">${s.gender}</td>
        <td align="center">${s.onDutyDate}</td>
        <td align="center">${s.post.department.depName}</td>
        <td align="center">${s.post.postName}</td>
        <td width="7%" align="center">

            <c:choose>
                <c:when test="${applicationScope.staff.post.department.depId == '2c9090cb5f585a97015f585af7990000' || applicationScope.staff.staffId == s.staffId}">
                    <a href="${pageContext.request.contextPath}/staff/prepareEdit.action?staffId=${s.staffId}&loginName=${s.loginName}&loginPwd=${s.loginPwd}&staffName=${s.staffName}&gender=${s.gender}
			&onDutyDate=${s.onDutyDate}&depId=${s.post.department.depId}&
			postId=${s.post.postId}&postName=${s.post.postName}&depName=${s.post.department.depName}">
                        <img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/>
                </c:otherwise>
            </c:choose>
        </td>
        </s:iterator>

</table>


<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td align="right">
            <span>第${pageBean.pageNum}/${pageBean.totalPage}页</span>
            <span>
        	<a href="${pageContext.request.contextPath}/staff/findAllStaff.action?pageNum=0&depId=${depId}&postId=${postId}&staffName=${staffName}">[首页]</a>&nbsp;&nbsp;

            <a href="${pageContext.request.contextPath}
                /staff/findAllStaff.action?pageNum=${pageBean.pageNum - 1}&depId=${depId}&postId=${postId}&staffName=${staffName}">[上一页]</a>&nbsp;&nbsp;

            <a
                    <c:choose>
                        <c:when test="${pageBean.pageNum >= pageBean.totalPage}">href="#"</c:when>
                        <c:otherwise>href="${pageContext.request.contextPath}
                            /staff/findAllStaff.action?pageNum=${pageBean.pageNum + 1}&depId=${depId}&postId=${postId}&staffName=${staffName}"</c:otherwise>
                    </c:choose>
            >[下一页]</a>&nbsp;&nbsp;

            <a href="${pageContext.request.contextPath}
                /staff/findAllStaff.action?pageNum=${pageBean.totalPage}&depId=${depId}&postId=${postId}&staffName=${staffName}">[尾页]</a>
        </span>
        </td>
    </tr>
</table>


<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/post/showDepart.action",
                null,
                function (data) {
                    var _html = "<option value=''>----请--选--择----</option>";
                    $.each(data, function (index, per) {
                        _html += '<option value="' + per.depId + '">' + per.depName + '</option>';
                    });
                    $("#department").html(_html);
                }, "json"
        );

        $("#department").change(function () {
                    $.post("${pageContext.request.contextPath}/post/showPost.action",
                            {
                                departId: $("#department").val()
                            },
                            function (data) {
                                var _html = "<option value=''>----请--选--择----</option>";
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
