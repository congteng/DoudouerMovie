<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib prefix="s" uri="/struts-tags" %><s:if test="newsList.size() > 0"><s:iterator value="newsList" status="st">
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
</s:iterator></s:if><s:else>nothing</s:else>