<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
<h2>this is pages/index.jsp</h2>
<a href="./i18n?lang=zh">中文版</a> | <!-- 对应 messages_zh.properties文件-->
<a href="./i18n?lang=en">英文版</a> |    <!-- 对应 messages_ja.properties文件-->

<br>

<spring:message code="header.message" /> ,
<spring:message code="header.language"/>

</body>
</html>
