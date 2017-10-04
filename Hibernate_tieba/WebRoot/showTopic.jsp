<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>凤姐专区</title>
	<link rel="stylesheet" type="text/css" href="css/main.css"> 
</head>
<body>
<s:debug />
	<!-- 简单搜索表单 -->
	<div style="margin: 15px auto; " >
		<!-- 搜索表单 -->
		<s:form action="TopicAction_list" namespace="/" theme="simple" cssClass="simpleSearchForm" >
			<font class="logoLabel">谢忱贴吧</font>
			<s:textfield name="key" cssClass="queryString" ></s:textfield>
			<input type="submit" value="搜 索" />
		</s:form>
	</div>
	<!-- 菜单 -->
	<div class="menubar">
		<a href="listTopics.html">主题列表</a>
	</div>
	
	<!-- 当前主题贴数 -->
	<div style="padding: 10px 30px; font-size: 12px; font-family:'宋体'">
		共有<font color="red">55<s:property value="#list.size" /></font>篇帖子
	</div>
	
	<!-- 显示主题 -->
	
	<table class="postList" cellspacing="0">
	    <tr class="title">
	        <td width="25" class="num">楼主</td>
	        <td>标题:<s:property value="#find.title"/></td>
	    </tr>
	    <tr class="content">
	        <td width="25" class="num">内容</td>
	        <td><pre><s:property value="#find.topicContent" /></pre></td>
	    </tr>
	    <tr>
	    	<td width="25" class="num">图片</td>
	    	<td><img  alt="图片大小为100*100" src="${pageContext.request.contextPath }/files/<s:property value="#find.url" />" width="100" height="100"/></td>
	    </tr>
	    <tr class="info">
	        <td></td>
	        <td>
				作者：<font color="blue"><s:property value="#find.ipAddr"/></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <font color="#999999">发帖时间：<s:property value="#find.createDate"/></font>
	        </td>
	    </tr>
	</table>
<c:set var="c" value="2"></c:set>
<s:iterator value="#find.replySet" var="reply" >
	<!-- 显示回复列表 -->
	<table class="postList" cellspacing="0">
        <tr class="title">
            <td width="25" class="num"><c:out value="${c }"></c:out>楼</td>
            <td></td>
        </tr>
        <tr class="content">
            <td></td>
            <td><pre><s:property value="replyContent"/></pre></td>
        </tr>
        <tr class="info">
            <td></td>
            <td>
				作者：<font color="blue"><s:property  value="ipAddr" /></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <font color="#999999">回帖时间：<s:date name="createDate" format="yyyy-MM-dd hh:mm:ss" /></font>
            </td>
        </tr>
    </table>
    <c:set var="c" value="${c+1 }"></c:set>
</s:iterator>
    
    <div style="margin-bottom: 20px"></div>
	<!-- 发表回复表单 -->
	<s:form action="addh" class="addNewTopicForm">
		<table class="publishArticleForm">
	        <tr>
	            <td class="label">内　容:</td>
	            <td>
	            	<s:textarea name="reply.replyContent" class="content"></s:textarea>
	        </tr>
	        <tr>
	           <!--  <td><input type="text" name="tid" /></td> -->
	            <s:param name="tid" value="#find.tid"></s:param>
	            <td><input type="submit" value="回　贴"/></td>
	        </tr>
	    </table>
	</s:form>
</body>
</html>