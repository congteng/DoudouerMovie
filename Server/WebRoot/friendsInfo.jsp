<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="userCollection.size > 0">
	<s:iterator value="userCollection">
		<img src="<s:property value='avatarLink'/>"/>
	</s:iterator>
</s:if>
<s:else>
	您暂无好友
</s:else>