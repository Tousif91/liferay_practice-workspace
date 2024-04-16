<%@page import="com.common.db.model.News"%>
<%@ include file="./init.jsp" %>

<%
	List<News> newsList = (List<News>) renderRequest.getAttribute("newsList");
	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("mvcPath", "/newsNotification.jsp");
	
	
	
%>

<liferay-ui:search-container delta="5" emptyResultsMessage="No News Available" total="<%=newsList.size()%>" iteratorURL="<%=iteratorURL %>">
	<liferay-ui:search-container-results results="<%=ListUtil.subList(newsList, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
	<liferay-ui:search-container-row className="com.common.db.model.News" modelVar="news">
		<%-- <liferay-ui:search-container-column-text name="News ID" property="id"/> --%>
		<liferay-ui:search-container-column-text name="News Title" property="title"/>
		<%-- <liferay-ui:search-container-column-text name="News Sub-Title" property="subTitle"/>
		<liferay-ui:search-container-column-text name="Description" property="description"/>
		<liferay-ui:search-container-column-text name="Status" property="status"/> --%>
		<liferay-ui:search-container-column-jsp path="/action.jsp" name="View"/> 
		
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator paginate="true"/>
</liferay-ui:search-container>