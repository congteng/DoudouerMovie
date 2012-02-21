<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" type="text/css" href="css/recommendUser.css">
<input type="hidden" name="id" value="<s:property value='id'/>"><s:if test="recommendUserList.size() > 0">
<s:iterator value="recommendUserList">
	<table class="rUserTable">
		<tr>
			<td class="firstTd"><img src="<s:property value='avatarLink'/>"/></td>
		</tr>
		<tr>
			<td>昵称:</td>
			<td><s:property value="nickName"/></td>
		</tr>
		<tr>
			<td class="firstTd">收藏电影:</td>
			<td>
				<s:if test="movieCollection.size() > 0">
					<table>
						<tr>
							<s:iterator value="movieCollection">
								<td><img src="<s:property value='avatarLink'/>"/></td>
							</s:iterator>				
						<tr>
						<tr>
							<s:iterator value="movieCollection">
								<td><s:property value='movieName'/></td>
							</s:iterator>				
						<tr>
					</table>
				</s:if>
				<s:else>
					暂无电影收藏
				</s:else>
			</td>
		</tr>
		<tr>
			<td class="firstTd"></td>
			<td class="addFriendMark"><a href="#" onclick="addFriend(this)">加为好友</a>
				<input type="hidden" id="rId" value="<s:property value='id'/>">
			</td>
		</tr>
	 </table>
</s:iterator></s:if><s:else>暂无推荐用户</s:else>
<script type="text/javascript">
	var mId;
	$(function(){
		mId = $("input:hidden").first().val();
	});
		
	function addFriend(node){
		// 推荐好友的id
		var rId = $(node).next("input:hidden").val();
		var url = "UserAction_addNewFriend.action";
		// 这里用recordID代替fId
		data = "id=" + mId + "&userID=" + rId;
		$.post(url, data, function(){
			$(node).parentsUntil("table").empty();	
		});
	}
</script>
