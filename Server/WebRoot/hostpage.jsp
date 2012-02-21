<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>个人主页</title>
    <!--   -->
    <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.12.custom.css" rel="stylesheet" />	
   
    <script src="js/jquery-1.6.1.min.js"></script>
	<script src="js/ui/jquery.ui.core.js"></script>
	<script src="js/ui/jquery.ui.widget.js"></script>
	<script src="js/ui/jquery.ui.tabs.js"></script>
	

	<link rel="stylesheet" href="css/demos.css">
    <SCRIPT type="text/javascript">
  
  
    	$(function() {
    	  $( "#tabs" ).hide();
    	  /**
    	$( "#tabs" ).show();
			$( "#tabs" ).tabs({
				ajaxOptions: {
					error: function( xhr, status, index, anchor ) {
						$( anchor.hash ).html(
							"暂无信息" );
					},
					cache: false
				}
			});
		});
	
    </SCRIPT>
    
   	<style type="text/css">
		#tabs{
			width:680px; 
		}
	</style>
    
  </head>
  <body>
  <h1>个人主页:<s:property value='#session.user.nickName'/></h1>
	<div id="tabs">
		<ul>
			<li><a href="UserAction_toFreshNewsView.action?id=<s:property value='#session.user.id'/>">查看新鲜事</a></li>
			<li><a href="UserAction_toEvaluateMovies.action?id=<s:property value='#session.user.id'/>">参与电影评分</a></li>
			<li><a href="UserAction_toRecommendMoviesPage.action?id=<s:property value='#session.user.id'/>">给您推荐的电影</a></li>
			<li><a href="UserAction_toRecommendUsersPage.action?id=<s:property value='#session.user.id'/>">给您推荐的用户</a></li>
		</ul>
	</div>
  </body>
</html>
