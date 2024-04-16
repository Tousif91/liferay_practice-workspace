<%@page import="com.common.db.service.NewsLocalServiceUtil"%>
<%@page import="com.common.db.model.News"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%
	long newsId = ParamUtil.getLong(request, "id");
	News news = NewsLocalServiceUtil.getNews(newsId);

%>

<portlet:actionURL name="editNews" var="editNewsURL"/>

<aui:form action="<%=editNewsURL %>" method="post" >
	<aui:input name="id" type="hidden" value="<%=news.getId() %>" label="News ID"/>
	<aui:input name="title" type="text" value="<%=news.getTitle() %>" label="Title"/>
	<aui:input name="subtitle" type="text" value="<%=news.getSubTitle() %>" label="Sub-Title"/>
	<aui:input name="description" type="text" value="<%=news.getDescription() %>" label="Description"/>
	<%-- <aui:input name="status" type="" value="" label=""/> --%>
	<aui:button type="submit" value="Update"/>
</aui:form>