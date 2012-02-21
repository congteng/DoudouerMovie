<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加电影</title>
  </head>
  <body>
  <h1>为电影库添加电影</h1>
  <hr>
  	<s:form action="MovieAction_addMovie" method="post" enctype="multipart/form-data">
  		<table>
  			<tr>
  				<td>上传电影海报:</td>
  				<td><s:file name="moviePoster"/></td>
  			</tr>
  			<tr>
  				<td>电影名:</td>
  				<td><s:textfield name="movieName"/></td>
  			</tr>
  			<tr>
  				<td>类型:</td>
  				<td><s:textfield name="type"/></td>
  			</tr>
  			<tr>
  				<td>导演:</td>
  				<td><s:textfield name="director"/></td>
  			</tr>
  			<tr>
  				<td>演员:</td>
  				<td><s:textfield name="actor"/></td>
  			</tr>
  			<tr>
  				<td>国家:</td>
  				<td><s:textfield name="country"/></td>
  			</tr>
  			<tr>
  				<td>语言:</td>
  				<td><s:textfield name="language"/></td>
  			</tr>
			<tr>
  				<td>上映时间:</td>
  				<td><s:textfield name="releaseYear"/></td>
  			</tr>
  			<tr>
  				<td>电影简介:</td>
  				<td><s:textarea name="description"/> </td>
  			</tr>
  			<tr>
  				<td></td>
  				<td colspan="1"><s:submit value="提交"/></td>
  			</tr>
  		</table>
  	</s:form>
  </body>
</html>
