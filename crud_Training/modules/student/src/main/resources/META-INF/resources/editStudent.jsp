<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.student.db.model.Student"%>
<%@page import="com.student.db.service.StudentLocalServiceUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%
	long studentId = ParamUtil.getLong(request, "studentId");
	Student student = StudentLocalServiceUtil.getStudent(studentId);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

%>

<portlet:actionURL name="editStudent" var="studentURL"/>

<aui:form action="<%=studentURL %>" method="post">

<aui:input name="studentId" type="hidden" value="<%=student.getStudentId() %>"/> 
 
<aui:input name="name" type="text" value="<%=student.getName() %>"/>

<aui:input name="dob" type="Date" value="<%=sdf.format(student.getDob()) %>"/>

<aui:select name="gender" value="<%=student.getGender() %>">
<aui:option value="">Select</aui:option>
<aui:option value="Male" >Male</aui:option>
<aui:option value="Female" >Female</aui:option>
<aui:option value="Other" >Other</aui:option>
</aui:select>

<aui:button type="submit" value="Update"/>


</aui:form>