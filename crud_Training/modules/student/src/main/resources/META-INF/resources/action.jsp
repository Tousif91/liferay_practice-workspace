<%@page import="javax.portlet.ActionRequest"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.student.db.model.Student"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ include file="init.jsp" %>

<%
	ResultRow row =(ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	Student student =(Student)row.getObject();
	
	PortletURL editStudentURL = renderResponse.createRenderURL();
	editStudentURL.setParameter("studentId", String.valueOf(student.getStudentId()));
	editStudentURL.setParameter("mvcPath", "/editStudent.jsp");
	
	PortletURL deleteStudentURL = renderResponse.createActionURL();
	deleteStudentURL.setParameter("studentId", String.valueOf(student.getStudentId()));
	deleteStudentURL.setParameter(ActionRequest.ACTION_NAME, "deleteStudent");

%>

<liferay-ui:icon-menu>
 	<liferay-ui:icon image="edit" message="Edit Student" url="<%=editStudentURL.toString() %>"/>
	<liferay-ui:icon image="delete" message="Delete Student" url="<%=deleteStudentURL.toString() %>"/>

</liferay-ui:icon-menu>