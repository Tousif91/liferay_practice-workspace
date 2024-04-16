<%@page import="com.common.db.service.NewsLocalServiceUtil"%>
<%@page import="com.common.db.model.News"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%
	long newsId = ParamUtil.getLong(request, "id");
	News news = NewsLocalServiceUtil.getNews(newsId);

%>

<portlet:renderURL var="renderURL">
 	<portlet:param name="mvcPath" value="/newsNotification.jsp"/>
</portlet:renderURL>

<portlet:actionURL name="#" var="editNewsURL"/>

<aui:form action="<%=editNewsURL %>" method="post" >
	<aui:input name="id" type="hidden" value="<%=news.getId() %>" label="News ID"/>
	<aui:input name="title" type="text" value="<%=news.getTitle() %>" label="Title" disabled="true"/>
	<aui:input name="subtitle" type="text" value="<%=news.getSubTitle() %>" label="Sub-Title" disabled="true"/>
	<aui:input name="description" type="text" value="<%=news.getDescription() %>" label="Description" disabled="true"/>
	
</aui:form>

<aui:button href="<%=renderURL %>" value="Back"/>