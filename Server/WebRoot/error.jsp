<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>豆兜儿Movie</title>
	</head>
	<body>
		出错了!!
		<table border="1">
			<tr>
				<td><s:fielderror fieldName="movieName"/></td>
			</tr>
			<tr>
				<td><s:fielderror fieldName="type"/></td>
			</tr>
		</table>
	</body>
</html>
