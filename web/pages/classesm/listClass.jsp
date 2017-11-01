<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sys.css" type="text/css"/>
    <title>班级管理</title>
</head>
<body>
<!--距离页面顶端5px-->
<table border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="topg"></td>
    </tr>
</table>
<form name="createForm" action="" method="post">
    <table border="0" cellspacing="0" cellpadding="0" class="wukuang">
        <tr>
            <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
            <td width="20%" align="left">[班级管理]</td>
            <td width="42%" align="center">&nbsp;</td>
            <td width="36%" align="right">
                <%--添加班级  /html/classesm/addClass.jsp--%>
                <c:if test="${applicationScope.staff.post.department.depId == '2c9090cb5f585a97015f585b1cdd0001'}">
                    <a href="${pageContext.request.contextPath}/classes/showCourse.action">
                        <img src="${pageContext.request.contextPath}/images/button/tianjia.gif" class="img"/>
                    </a>
                </c:if>

                <%--高级查询
                <a href="queryClass.html"><img src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif" class="img"/></a>
                --%>
            </td>
            <td width="1%"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
        </tr>
    </table>
</form>
<table border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td class="topg"></td>
    </tr>
</table>
<table border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
    </tr>
</table>

<table width="97%" border="1">
    <thead>

    <tr class="henglan" style="font-weight:bold;">
        <th width="150px" align="center">班级名称</th>
        <th width="200px" align="center">所属课程</th>
        <th width="80px" align="center">开班时间</th>
        <th width="80px" align="center">毕业时间</th>

        <th width="80px" align="center">状态</th>
        <th width="70px" align="center">学生总数</th>
        <th width="70px" align="center">升学数</th>
        <th width="70px" align="center">转班数</th>
        <th width="50px" align="center">编辑</th>
        <th width="50px" align="center">查看</th>
        <th align="center">课程表</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="pageBean.data" var="clazz">
        <tr class="tabtd2">
            <td align="center">${clazz.className}</td>
            <td align="center">${clazz.courseType.courseName}</td>
            <td align="center">${clazz.beginTime}</td>
            <td align="center">${clazz.endTime}</td>
            <td align="center">${clazz.status}</td>
            <td align="center">${clazz.totalCount}</td>
            <td align="center">${clazz.upgradeCount}</td>
            <td align="center">${clazz.changeCount}</td>
            <td align="center">
                <c:choose>
                    <c:when test="${applicationScope.staff.post.department.depId == '2c9090cb5f585a97015f585b1cdd0001'}">
                        <a href="${pageContext.request.contextPath}/classes/showCourse.action?classId=${clazz.classId}&className=${clazz.className}&courseName=${clazz.courseType.courseName}&beginTime=${clazz.beginTime}&endTime=${clazz.endTime}&status=${clazz.status}&totalCount=${clazz.totalCount}&courseId=${clazz.courseType.courseId}&upgradeCount=${clazz.upgradeCount}&changCount=${clazz.changeCount}">
                            <img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td align="center">
                <a href="${pageContext.request.contextPath}/classes/showClasses.action?classId=${clazz.classId}"><img
                        src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
            </td>
            <td align="center" title="上次上传时间：${clazz.uploadTime}">
                <c:choose>
                    <c:when test="${applicationScope.staff.post.department.depId == '2c9090cb5f585a97015f585b1cdd0001'}">
                        <a href="${pageContext.request.contextPath}/classes/preparedUpload.action?classId=${clazz.classId}">上传</a>
                    </c:when>
                    <c:otherwise>
                        <span>上传</span>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${clazz.uploadPath != null && clazz.uploadFilename != null}">
                        <a href="${pageContext.request.contextPath}/classes/download.action?classId=${clazz.classId}">下载</a>
                    </c:when>
                    <c:otherwise>
                        <span>未上传</span>
                    </c:otherwise>
                </c:choose>
                <br/>
            </td>
        </tr>
    </s:iterator>

    </tbody>
</table>


<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td align="right">
            <span>第${pageBean.pageNum}/${pageBean.totalPage}页</span>
            <span>
        	<a href="${pageContext.request.contextPath}/classes/findAll.action?">[首页]</a>&nbsp;&nbsp;

            <a href="${pageContext.request.contextPath}
                /classes/findAll.action?pageNum=${pageBean.pageNum - 1}">[上一页]</a>&nbsp;&nbsp;

            <a
                    <c:choose>
                        <c:when test="${pageBean.pageNum >= pageBean.totalPage}">href="#"</c:when>
                        <c:otherwise>href="${pageContext.request.contextPath}
                            /classes/findAll.action?pageNum=${pageBean.pageNum + 1}"</c:otherwise>
                    </c:choose>
            >[下一页]</a>&nbsp;&nbsp;

            <a href="${pageContext.request.contextPath}
                /classes/findAll.action?pageNum=${pageBean.totalPage}">[尾页]</a>
        </span>
        </td>
    </tr>
</table>
</body>
</html>
