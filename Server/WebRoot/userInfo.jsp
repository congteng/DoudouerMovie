<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<img style="border-color: rgb(149, 195, 234);border-style: solid;border-width: thin;" src="<s:property value='infoUser.avatarLink'/>">
<table class="userInfoTable" cellpadding="4">
	<tr>
		<td class="firstTd">id:</td>
		<td><s:property value="infoUser.id"/></td>
	</tr>
	<tr>
		<td class="firstTd">email:</td>
		<td><s:property value="infoUser.email"/></td>
	</tr>
	<tr>
		<td class="firstTd">昵称：</td>
		<td><s:property value="infoUser.nickName"/></td>
	</tr>
	<tr>
		<td class="firstTd">密码：</td>
		<td><s:property value="infoUser.password"/></td>
	</tr>
	<tr>
		<td class="firstTd">新鲜事类型:</td>
		<td>
			<table class="userInfoTable">
				<s:iterator value="infoUser.newsTypeArray">
					<tr>
					<td><s:property/></td>
					</tr>
				</s:iterator>
			</table>
		</td>
	</tr>
</table>
