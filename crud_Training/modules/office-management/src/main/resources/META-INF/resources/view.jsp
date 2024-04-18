<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.common.db.service.NewsLocalServiceUtil"%>
<%@page import="com.common.db.model.News"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<portlet:renderURL var="notificationURL">
	<portlet:param name="mvcPath" value="/newsNotification.jsp"/>
</portlet:renderURL>

<%
	List<News> newsList = (List<News>) renderRequest.getAttribute("newsList");	
%>
<div style="float: right;">  
	<%-- <aui:button href="<%=notificationURL %>" value="Notification"/>--%>
	<a href="<%=notificationURL %>" class="btn btn-primary">
		Notifications <span class="badge badge-danger"><%= newsList.size() %></span>
	</a>
</div>
<br>

<portlet:actionURL name="addNews" var="addNewsURL"/>

<aui:form action="${addNewsURL}" method="post">
 
	<aui:input name="title" type="text" placeholder="Enter news title" label="TITLE" required="true"/>
	<aui:input name="subTitle" type="text" placeholder="Enter news subTitle" label="SUB-TITLE" required="true"/>
	
	<aui:input name="description" type="textarea" placeholder="Enter news content" label="DESCRIPTION" required="true"/>
	
	<aui:button type="submit" value="SAVE"/>

</aui:form>


