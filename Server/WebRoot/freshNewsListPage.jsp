<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新鲜事</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="-1">
   	
   	<!-- 弹框效果 -->
   	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
	<!-- 
	<link rel="stylesheet" href="css/common.css" type="text/css" />
	 -->
	<link rel="stylesheet" href="css/freshNews.css" type="text/css" />
	
	
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
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent;}
	</style> 
	
   
   	<SCRIPT type="text/javascript">
		// 评论的新鲜事的类型
  		var type = "UserGibberish";
  		// 评论新鲜事是 该实体表中第几条记录
  		var recordId = 0;
  		// 第几页
  		var pageNum = 0;
		// 当前被点击的节点
		var currNode;  	
		// 新闻中的第几条记录	
  		var indexOfFreshNews;
  		// 当前用户是谁
  		var currUserName;
  		// 我的头像地址
  		var myAvatarPath;

		var id;
   	
		$(function(){
		
			// 更多新鲜事加载 效果
			id = $("input:hidden").first().val();
			currUserName = $("#userName").val();
			myAvatarPath = $("#myAvatarPath").val();
			
			$("#showMore").click(function(){
				$("#showMore").text("等待...")
				pageNum++;
				var url = "UserAction_getMoreFreshNews.action";
				var data = "id=" + id + "&pageNum=" + pageNum;
				$.post(url, data, function(ret){
					if(ret == "nothing"){
						$("#showMore").text("没有更多新鲜事");
						$("#showMore").fadeOut(3000);;
					}else{
						$("#showMore").text("更多新鲜事")
					}
					$(ret).insertBefore("#sig");
				});
			});
			
		});
		
		
		var box;
		function toDissUserGibberish(node){
			// 设置全局变量
			type = "UserGibberish";
			var happenToName = $(node).next("input:hidden").get(0).value;
			recordId = $(node).nextAll("input:hidden").get(1).value;
			indexOfFreshNews = $(node).nextAll("input:hidden").get(2).value;
			
			currNode = node;
			
			openDialog();
			
		}
		
		function toDissFilmReview(node){
			// 设置全局变量
			type = "FilmReview";
			var happenToName = $(node).next("input:hidden").get(0).value;
			recordId = $(node).nextAll("input:hidden").get(1).value;
			indexOfFreshNews = $(node).nextAll("input:hidden").get(2).value;
			
			currNode = node;
			
			openDialog();
		}
		
		
		tips = $( ".validateTips" );
		
		function openDialog(){
		
				var content = $("#content");
				allFields = $( [] ).add( content );
		
			$( "#Review" ).dialog({
				autoOpen: false,
				height: 200,
				width: 500,
				modal: true,
				buttons: {
						"确认": function() {
							var bValid = true;
							allFields.removeClass( "ui-state-error" );
							
							
		 					content = $( "#content" );
		 					// 验证 是否为空
		 					bValid = bValid && checkLength( content, "评论内容", 1, 50 );
		 
							if ( bValid ) {
								sendReview();
							
							
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
				
				
				$( "#Review" ).dialog( "open" );
			}
	
	
			function checkLength( o, n, min, max ) {
				if ( o.val().length > max || o.val().length < min ) {
					o.addClass( "ui-state-error" );
					updateTips(  n + " 必须在" +
						min + "和 " + max + "之间." );
					return false;
				} else {
					return true;
				}
			}	
			
			function updateTips( t ) {
				tips.text( t )
					.addClass( "ui-state-highlight" );
				setTimeout(function() {
					tips.removeClass( "ui-state-highlight", 1500 );
				}, 500 );
			}
		   		
	   		function sendReview(){
				var url = "UserAction_discussToFreshNews.action";
				var content = $("#content").val();
				var data = "&dissContent=" + content + "&recordID=" + recordId + 
						"&type=" + type + "&indexOfFreshNews=" + indexOfFreshNews;				
				
				$.post(url, data, function(ret){
					var $tr = $(currNode).parent().siblings().has("a").children("table").children("tbody").children("tr:last");
					var $td = $tr.children("td");
					
					// 改变原来最后一条的样式
					var $last2thTr = $tr.siblings("tr:last").children("td:last").removeClass("lasttd").addClass("innertext");;
					
					if($td.get(0)){
						$tr.before($("<tr class='tbcolor'><td class='innerTd'><img src='" + myAvatarPath + "'/></td><td class='innername'>" + currUserName + ":</td><td class='lasttd'>"
								+ content + "</td></tr>"));
					}else{
						$tr.after($("<tr><td class='shape' colspan='3'><img src='img/top.png'></td></tr><tr class='tbcolor'><td class='innerTd'><img src='" 
								+ myAvatarPath + "'/></td><td class='innername'>" + currUserName + ":</td><td class='lasttd'>"+ content 
								+ "</td></tr><tr><td style='vertical-align:top' colspan='3'><img src='img/bottom.png'></td></tr>"));
					}
					
					// 清理数据		
					$("#content").val("");
					
				});
				
			}
		   		
   	</SCRIPT>
   
  </head>
  <body>
  <input type="hidden" name="id" value="<s:property value='#session.user.id'/>">
  <input type="hidden" id="userName" value="<s:property value='getUserNameFromId(#session.user.id)'/>">
  <input type="hidden" id="myAvatarPath" value="<s:property value='getAvatarPath(#session.user.id)'/>">
	<table class="outTable">
	<s:if test="newsList.size > 0">
		<s:iterator value="newsList" status="st">
			<tr>
				<td class="outTd" style="vertical-align: top"><img src="<s:property value='getAvatarPath(happenTo)'/>"></td>
				<td class="text">
					<a href="#"><s:property value='getUserNameFromId(happenTo)'/></a>: 
					
					<s:if test="newsOfEntity == 'User'">
						加了好友<a href="#"><s:property value="getUserNameFromId(newsID)"/></a>
					</s:if>
					
					<s:elseif test="newsOfEntity == 'UserGibberish'">
						<s:property value="content"/>
						
							<table class="innerTable" cellspacing="0" cellpadding="0">
							<tr></tr>
							<!-- 评论头图 -->
							<s:if test="discussList.size() > 0">
								<tr>	
									<td class="shape" colspan="3"><img src="img/top.png"></td>					
								</tr>
							</s:if>
							
							<s:iterator value="discussList" status="st">
								<tr class="tbcolor">
									<td class="innerTd"><img src="<s:property value='getAvatarPath(whoDiss)'/>"></td>
									<td class="innername"><s:property value="getUserNameFromId(whoDiss)"/>:</td>
									
									<s:if test="#st.index == (discussList.size()-1)">
										<td class="lasttd"><s:property value="content"/></td>
									</s:if>
									<s:else>
										<td class="innertext"><s:property value="content"/></td>
									</s:else>
								</tr>
							</s:iterator>
							
							<!-- 评论尾图 -->
							<s:if test="discussList.size() > 0">
								<tr>	
									<td style="vertical-align:top" colspan="3"><img src="img/bottom.png"></td>					
								</tr>
							</s:if>
						</table>
					</s:elseif>
					
					<s:elseif test="newsOfEntity == 'FilmReview'">
						<s:property value="content"/>
						<table class="innerTable" cellspacing="0" cellpadding="0">
							<tr></tr>
							
							<s:if test="discussList.size() > 0">
								<tr>	
									<td class="shape" colspan="3"><img src="img/top.png"></td>					
								</tr>
							</s:if>
							
							<s:iterator value="discussList" status="st">
								<tr class="tbcolor">
									<td class="innerTd"><img src="<s:property value='getAvatarPath(whoDiss)'/>"></td>
									<td class="innername"><s:property value="getUserNameFromId(whoDiss)"/>:</td>
									
									<s:if test="#st.index == (discussList.size()-1)">
										<td class="lasttd"><s:property value="content"/></td>
									</s:if>
									<s:else>
										<td class="innertext"><s:property value="content"/></td>
									</s:else>
									
								</tr>
							</s:iterator>
							
							<s:if test="discussList.size() > 0">
								<tr>	
									<td style="vertical-align:top" colspan="3"><img src="img/bottom.png"></td>					
								</tr>
							</s:if>
						</table>
					</s:elseif>
					
					<s:elseif test="newsOfEntity == 'Movie'">
						收藏了电影:<a href=""><s:property value="getMovieNameFromId(newsID)"/></a>
					</s:elseif>
				</td>
				<td class="discuss">
					<s:if test="newsOfEntity == 'UserGibberish'">
						<a href="#" id="<s:property value='"dissUserGibberish_" + #st.index'/>" onclick="toDissUserGibberish(this)">评论</a>
						<input type="hidden" name="happenToName" value="<s:property value='getUserNameFromId(happenTo)'/>">
						<input type="hidden" name="recordId" value="<s:property value='newsID'/>">
						<input type="hidden" name="indexOfFreshNews" value="<s:property value='id'/>">
					</s:if>
					
					<s:elseif test="newsOfEntity == 'FilmReview'">
						<a href="#" id="<s:property value='"dissUserGibberish_" + #st.index'/>" onclick="toDissFilmReview(this)">评论</a>
						<input type="hidden" name="happenToName" value="<s:property value='getUserNameFromId(happenTo)'/>">
						<input type="hidden" name="recordId" value="<s:property value='newsID'/>">
						<input type="hidden" name="indexOfFreshNews" value="<s:property value='id'/>">
					</s:elseif>
				</td>
			</tr>
		</s:iterator>
		<tr id="sig">
			<td colspan="3">
				<div id="showMore" class="moredata">更多新鲜事</div>  
			</td>
		</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="3">
					<div class="moredata">您暂无新鲜事</div> 
				</td>
			</tr>
		</s:else>
  	</table>
  
		
		<!-- 对话框 -->
		<div id="Review" title="评论" style="display:none;"> 
			<p class="validateTips"></p> 
			<fieldset> 
				<input type="text" style="height :40px" name="content" id="content" class="text ui-widget-content ui-corner-all" /> 
			</fieldset> 
		</div> 
		
		
  </body>
</html>
