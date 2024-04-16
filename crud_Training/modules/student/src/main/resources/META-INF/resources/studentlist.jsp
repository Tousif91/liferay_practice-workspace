<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.Collections"%>


<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.student.db.service.StudentLocalServiceUtil"%>
<%@page import="com.student.db.model.Student"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>


<style type="text/css">
	table, th, td {
	
	border: 1px solid;
}

</style>

<portlet:renderURL var="addStudentURL">
	<portlet:param name="mvcPath" value="/addstudent.jsp"/>
</portlet:renderURL>

<portlet:actionURL name="searchStudent" var="searchStudentURL"/>

<%
	Object name = request.getAttribute("studentName");
	List<Student> studentsList = (null==name)?Collections.EMPTY_LIST:(List<Student>)name;
	//List<Student> studentsList = (List<Student>)request.getAttribute("studentName");
	
	List<Student> students = StudentLocalServiceUtil.getStudents(-1, -1);
	
	if(Validator.isNotNull(name)){
		students = 	(List<Student>)name;
	}
%>


<%
	//List<Student> students = StudentLocalServiceUtil.getStudents(-1, -1);
	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("mvcPath", "/studentlist.jsp");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>


<aui:form action="<%=searchStudentURL %>" method="post">
 	<div class="row">
 		<div class="col-md-6">
 			<aui:input name="name" type="text" placeholder="Enter name to search" label=""/>
 		</div>
 		<div class="col-md-3">
 			<aui:button type="submit" value="Search"/>
 		</div>
 		
 		<div class="col-md-3">
 			<aui:button  href="<%=addStudentURL %>" value="Add Student"/>
 		</div>
 	</div>
</aui:form>


<liferay-ui:search-container delta="5" emptyResultsMessage="No Data" total="<%=students.size() %>" iteratorURL="<%=iteratorURL %>">
<liferay-ui:search-container-results results="<%=ListUtil.subList(students, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
<liferay-ui:search-container-row className="com.student.db.model.Student" modelVar="st">
<liferay-ui:search-container-column-text name="Student ID" property="studentId"/>
<liferay-ui:search-container-column-text name="Student Name" property="name"/>
<liferay-ui:search-container-column-text name="Date of Birth" value="<%=sdf.format(st.getDob()) %>"/>
<liferay-ui:search-container-column-text name="Gender" property="gender"/>
<liferay-ui:search-container-column-jsp path="/action.jsp" name="Action"/>



</liferay-ui:search-container-row>
<liferay-ui:search-iterator paginate="true"/>
</liferay-ui:search-container>

<br>
<br>
<br>



<%if(!studentsList.isEmpty()){ %>

<table style="width: 100%">
	<tr>
		<th style="text-align: center;"> Student ID</th>
		<th style="text-align: center;"> Student Name</th>	
		<th style="text-align: center;"> Date of Birth</th>
		<th style="text-align: center;"> Gender</th>
	</tr>
<%
	for(Student std : studentsList){
%>
	<tr>
		<td style="text-align: center;"><%=std.getStudentId() %> </td>
		<td style="text-align: center;"><%=std.getName() %> </td>
		<td style="text-align: center;"><%=sdf.format(std.getDob()) %> </td>
		<td style="text-align: center;"><%=std.getGender() %> </td>
		
	</tr>
<%} %>
</table>
<%
}else{%>
 <p>No Results found. </p>
<%	
}
%>






