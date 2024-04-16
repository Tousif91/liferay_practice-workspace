<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.common.db.model.News"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="init.jsp" %>

<%
	ResultRow rows = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	News news = (News)rows.getObject();
	
	PortletURL editNewsURL = renderResponse.createRenderURL();
	editNewsURL.setParameter("id", String.valueOf(news.getId()));
	editNewsURL.setParameter("mvcPath", "/editNews.jsp");
	
	PortletURL viewNewsURL = renderResponse.createRenderURL();
	viewNewsURL.setParameter("id", String.valueOf(news.getId()));
	viewNewsURL.setParameter("mvcPath", "/viewNews.jsp");
	
	
%>
<%-- <liferay-ui:icon-menu> --%>

<%-- 	<liferay-ui:icon image="edit" message="Edit News" url="<%=editNewsURL.toString() %>"/> --%>
	<liferay-ui:icon image="view" message="View News" url="<%=viewNewsURL.toString() %>"/>

<%-- </liferay-ui:icon-menu> --%>
