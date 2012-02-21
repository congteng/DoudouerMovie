<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="s" uri="/struts-tags"%><s:if
	test="evaluateMovieList != null && evaluateMovieList.size() > 0">
	<s:iterator value="evaluateMovieList" status="st">
		<tr>
			<td class="firstTd">
				<img src="<s:property value='avatarLink'/>" />
			</td>
			<td>
				<a href="#" onclick="collectThis(this)">收藏</a>
				<input type="hidden" value="<s:property value='id'/>">
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				电影名称
			</td>
			<td>
				<s:property value="movieName" />
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				类型:
			</td>
			<td>
				<s:property value="type" />
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				导演:
			</td>
			<td>
				<s:property value="director" />
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				主要演员:
			</td>
			<td>
				<s:property value="actor" />
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				国家:
			</td>
			<td>
				<s:property value="country" />
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				语言:
			</td>
			<td>
				<s:property value="language" />
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				发行日期:
			</td>
			<td>
				<s:property value="releaseYear" />
			</td>
		</tr>
		<tr>
			<td class="firstTd">
				剧情简介:
			</td>
			<td>
				<s:property value="description" />
			</td>
		</tr>

		<tr>
			<td class="firstTd">
				打分:
			</td>
			<td>
				<div id="<s:property value='"movieId_" + #st.index'/>"></div>
				<input type="hidden" value="<s:property value='id'/>" />
				<input name="size" type="hidden"
					value="<s:property value='evaluateMovieList.size()'/>" />
			</td>
		</tr>
	</s:iterator>
</s:if><s:else>暂无电影</s:else>
