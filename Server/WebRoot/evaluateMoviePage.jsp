<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>电影打分</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="-1">
   
    <script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
    <!-- 星级评分 -->
	<script type="text/javascript" src="js/jquery.raty.js"></script>
    
    <link type="text/css" href="css/evaluateMovie.css" rel="stylesheet" />	
    
    <script type="text/javascript">
		var id;
		$(function(){
			id = $("input:hidden").first().val();
    		addStars();
		});
		
		function collectThis(node){
			var mId = $(node).next("input:hidden").val();
			var url = "UserAction_collectMovie.action";
			var data= "id=" + id + "&movieID=" + mId;
			$.post(url, data, function(){
				alert("收藏成功！ 您可以继续给该电影评分");
			});
		}
		
		function addStars(){
    		// 定义movieId
    		var movieId;
    		// 获取数据大小
    		var size = $("table input:hidden[name='size']").val();
    		// 给每个电影增加星级评分
    		for(var i=0; i<size;i++){
    			
    			movieId = "movieId_" + i;
    		
    			$("#" + movieId).raty({
				  half:  true,
				  start: 3.3,
				  width: 200,
				  click: function(score){
				  	var url = "UserAction_evaluateMovie.action";
				  	//alert(url);
				  	var data = "id=" + id + "&movieID=" + $(this).next("input:hidden").val() + "&score=" + score; 
				  	$.post(url, data, function(msg){
					  	
					  	alert("评分成功！");
					  	$("table[class='movieTable']").empty().append(msg);
					  	
					  	// 渲染
					  	addStars();
					  	
				  	});
				  }
				});
    		}
		}
	</script>
    
  </head>
  <body>
  	<input type="hidden" name="id" value="<s:property value='id'/>">
  	
	<table class="movieTable">
	<s:if test="evaluateMovieList != null && evaluateMovieList.size() > 0">
		<s:iterator value="evaluateMovieList" status="st">
			<tr>
				<td class="firstTd"><img src="<s:property value='avatarLink'/>"/></td>
				<td>
					<a href="#" onclick="collectThis(this)">收藏</a>
					<input type="hidden" value="<s:property value='id'/>">
				</td>
			</tr>
			<tr>
				<td class="firstTd">电影名称</td>
				<td><s:property value="movieName"/></td>
			</tr>
			<tr>
				<td class="firstTd">类型:</td>
				<td><s:property value="type"/></td>
			</tr>
			<tr>
				<td class="firstTd">导演:</td>
				<td><s:property value="director"/></td>
			</tr>
			<tr>
				<td class="firstTd">主要演员:</td>
				<td><s:property value="actor"/></td>
			</tr>
			<tr>
				<td class="firstTd">国家:</td>
				<td><s:property value="country"/></td>
			</tr>
			<tr>
				<td class="firstTd">语言:</td>
				<td><s:property value="language"/></td>
			</tr>
			<tr>
				<td class="firstTd">发行日期:</td>
				<td><s:property value="releaseYear"/></td>
			</tr>
			<tr>
				<td class="firstTd">剧情简介:</td>
				<td><s:property value="description"/></td>
			</tr>
			
			<tr>
				<td class="firstTd">打分:</td>
				<td>
					<div id="<s:property value='"movieId_" + #st.index'/>"></div>
					<input type="hidden" value="<s:property value='id'/>"/>
					<input name="size" type="hidden" value="<s:property value='evaluateMovieList.size()'/>" />
				</td>
			</tr>
			
		</s:iterator> 
	</s:if>
	<s:else>
		暂无电影
	</s:else>
  </table>
  </body>
</html>
