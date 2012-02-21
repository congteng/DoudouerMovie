<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Doudouer</title>
		<link rel="stylesheet" type="text/css" href="css/index.css">


		<link type="text/css"
			href="css/ui-lightness/jquery-ui-1.8.12.custom.css" rel="stylesheet" />

		<script src="js/jquery-1.6.1.min.js"></script>
		<script src="js/ui/jquery.ui.core.js"></script>
		<script src="js/ui/jquery.ui.widget.js"></script>
		<script src="js/ui/jquery.ui.tabs.js"></script>

		<link rel="stylesheet" href="css/demos.css">

		<style type="text/css">
#tabs {
	width: 650px;
}
</style>



		<!-- Jquery UI 使用评论插件 -->

		<script src="js/external/jquery.bgiframe-2.1.2.js"></script>
		<script src="js/ui/jquery.ui.core.js"></script>
		<script src="js/ui/jquery.ui.widget.js"></script>
		<script src="js/ui/jquery.ui.mouse.js"></script>
		<script src="js/ui/jquery.ui.button.js"></script>
		<script src="js/ui/jquery.ui.draggable.js"></script>
		<script src="js/ui/jquery.ui.position.js"></script>
		<script src="js/ui/jquery.ui.resizable.js"></script>
		<script src="js/ui/jquery.ui.dialog.js"></script>
		<script src="js/ui/jquery.effects.core.js"></script>
		<link rel="stylesheet" href="css/demos.css">

		<style>
label,input {
	display: block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
}
</style>


		<script language="javascript" type="text/javascript">
			function hidden_mood(){
				if(document.getElementById("mood").style.display=="none"){
				document.getElementById("mood").style.display="block";}else{
					document.getElementById("mood").style.display="none";
				}
				
			}
			
			function change(){
				document.getElementById("email").className="text_onfocus";
				
			}
			
			function changePwd(){
				document.getElementById("pwd").className="text_onfocus";
				
			}
			
			function change_li(){
				document.getElementById("email").className="text_error";
				
			}
			
			function change_pwd(){
				document.getElementById("pwd").className="text_error";
				
			}
			
			
			/** 一下是 Jquery 代码 **/
			
				$(function(){
			
					$(".login-submit").click(function(){
					
						var email = $("#email").val();
						var pass = $("#pwd").val();
						
						
						var url = "LoginAction_doLogin.action";
						var params = "email=" + email + "&password=" + pass;
						
						$.post(url, params, function(){
							showHead();
							showTabs();
							hideLogin();
							showUserInfo();
							
							
							$("#teamLogo").fadeOut("slow");
						});
					});
					
					// 
					$("#sendGibberish").click(function(){
						
						openDialog_();
						
						
					});
					
					$("#showCollection").click(function(){
						var url = "UserAction_getCollection.action";
						$.post(url, function(data){
							$( "#showInfos" ).html(data);
						});
						
						showWindow(600);
					});
					
					$("#showFriends").click(function(){
					
						var url = "UserAction_getFriends.action";
						$.post(url, function(data){
							$( "#showInfos" ).html(data);
						});
						showWindow(400);
					});
					
					$("#showTeam").click(function(){
						$( "#team_intro" ).dialog({
							width: 410,
							modal: true,
							buttons: {
								"确定": function() {
									$( this ).dialog( "close" );
									$("#showInfos").empty();
								}
							}
						});
					});
				
				});
				
				
				
				
				function showWindow(width_){
					$( "#showInfos" ).dialog({
						width: width_,
						modal: true,
						buttons: {
							"确定": function() {
								$( this ).dialog( "close" );
								$("#showInfos").empty();
							}
						}
					});
				}
				
				//////functions
				
				function showTabs(){
					$( "#tabs" ).hide();
		    		$( "#tabs" ).fadeIn("slow");
					$( "#tabs" ).tabs({
						ajaxOptions: {
							error: function( xhr, status, index, anchor ) {
								$( anchor.hash ).html(
									"暂无信息" );
							},
							cache: false
						}
					});
				}
				
				function showHead(){
					$("#head_").css("visibility", "visible");
					$("#main_left_top").css("background-image", "url(img/main_head.jpg)");
					
					$("#head_big").css("visibility", "hidden");
					$("#head_small").fadeIn("slow");
				}
				
				function hideLogin(){
					$("#login").fadeOut();
				}
				
				function showUserInfo(){
				
					var url = "UserAction_getUserInfo.action";
					$.post(url, function(data){
						$("#userInfo").html(data);
						$("#userInfo").fadeIn();
					});
				}
				
				
				tips_ = $( ".validateTips" );
				
				////////////
				function openDialog_(){
		
					var content = $("#gibberish_content");
						allFields = $( [] ).add( content );
				
						$( "#gibberish" ).dialog({
							autoOpen: false,
							height: 200,
							width: 500,
							modal: true,
							buttons: {
								"确认": function() {
									var bValid = true;
									allFields.removeClass( "ui-state-error" );
									
				 					// 验证 是否为空
				 					bValid = bValid && checkLength_( content, "心情", 1, 50 );
				 
									if ( bValid ) {
									
										sendGibberish_();
									// 添加
										$( this ).dialog( "close" );
									}
								},
								"取消": function() {
									$( this ).dialog( "close" );
								}
							},
							close: function() {
								allFields.val( "" ).removeClass( "ui-state-error" );
							}
						});
						
						$( "#gibberish" ).dialog( "open" );
					}
		
		
				function checkLength_( o, n, min, max ) {
					if ( o.val().length > max || o.val().length < min ) {
						o.addClass( "ui-state-error" );
						updateTips_(  n + " 必须在" +
							min + "和 " + max + "之间." );
						return false;
					} else {
						return true;
					}
				}	
				
				function updateTips_( t ) {
					tips.text( t )
						.addClass( "ui-state-highlight" );
					setTimeout(function() {
						tips.removeClass( "ui-state-highlight", 1500 );
					}, 500 );
				}
				
				function sendGibberish_(){
					var url = "UserAction_addANewGibberish.action";
					var content = $("#gibberish_content").val();
					var data = "&dissContent=" + content;				
					
					$.post(url, data);
					$("#gibberish_content").val("");
				}
			
</script>

	</head>



	<body>
	
		<div id="head_big">
			<img src="img/head_img1.png">
		</div>
		
		<div id="head_small" style="display: none">
			<img src="img/head_img2.png">
		</div>
		
		
		<div id="body_main">
		
			<div id="head_" style="visibility: hidden">
				<div id="head_item">
					<div class="item">
						<a href="#" id="sendGibberish">发表心情</a>
					</div>
					<div class="item">
						<a href="#" id="showCollection">我的收藏</a>
					</div>
					<div class="item">
						<a href="#" id="showFriends">我的好友</a>
					</div>
					<div class="item">
						<a href="#" id="showTeam">团队简介</a>
					</div>
				</div>
			</div>


			<div id="main">
				<div id="main_left">
					<div id="main_left_top"></div>
					
					
					<div class="info">
					
					<div id="teamLogo">
						<img src="img/!team.png">
					</div>
					
						<div id="tabs" style="display: none">
							<ul>
								<li>
									<a href="UserAction_toFreshNewsView.action">查看新鲜事</a>
								</li>
								<li>
									<a href="UserAction_toEvaluateMovies.action">参与电影评分</a>
								</li>
								<li>
									<a href="UserAction_toRecommendMoviesPage.action">给您推荐的电影</a>
								</li>
								<li>
									<a href="UserAction_toRecommendUsersPage.action">给您推荐的用户</a>
								</li>
							</ul>
						</div>
					</div>

					<div id="main_left_bot"></div>
				</div>

				<div id="main_right">

					<div id="userInfo"></div>

					<div id="login">
						<table>
							<tr>
								<td colspan="2" class="td_text">
									Email:
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="text" id="email" class="text_normal"
										onFocus="change()" onBlur="change_li()" name="userName"
										size="8"
										style="width: 149px; height: 27px; font-family: '΢���ź�' font-size : 18px; background-repeat: repeat-x;"
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td colspan="2" class="td_text">
									密码:
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="password" name="password" id="pwd"
										class="text_normal" onFocus="changePwd()"
										onBlur="change_pwd()" size="8"
										style="width: 149px; height: 27px; font-size: 18px; margin-top: 6px;"
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<br>
							<tr>
								<td>
									<input type="button" value="" class="login-submit"
										style="width: 130px; height: 40px; cursor: pointer; border: 0px; background-image: url(img/btn.png);">
								</td>

							</tr>
							<tr>
								<td>
									<input class="reg" type="button" value=" "
										style="width: 130px; height: 40px; cursor: pointer; border: 0px; background-image: url(img/btn2.png);">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>

		
		
	

		<!-- 信息展示 -->
		<div id="showInfos" style="display: none;" title="相关信息">
		</div>
		
		<div id="gibberish" title="心情" style="display:none;"> 
			<p class="validateTips"></p> 
			<fieldset> 
				<input type="text" style="height :40px" id="gibberish_content" class="text ui-widget-content ui-corner-all" /> 
			</fieldset> 
		</div> 


		<div id="team_intro" style="display: none">
			<img src="img/intro.jpg">
		</div>



	</body>
</html>
