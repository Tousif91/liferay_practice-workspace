<%@page import="java.util.Collections"%>
<%@page import="com.student.db.model.Student"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<style type="text/css">
	table, th, td {
	
	border: 1px solid;
}

</style>


<portlet:actionURL name="searchStudent" var="searchStudentURL"/>


<%
	Object name = request.getAttribute("studentName");
	List<Student> studentsList = (null==name)?Collections.EMPTY_LIST:(List<Student>)name;
	//List<Student> studentsList = (List<Student>)request.getAttribute("studentName");
%>



<aui:form action="<%=searchStudentURL %>" method="post">
 	<div class="row">
 		<div class="col-md-6">
 			<aui:input name="name" type="text" placeholder="Enter name to search" label=""/>
 		</div>
 		<div class="col-md-6">
 			<aui:button type="submit" value="Search"/>
 		</div>
 	</div>
</aui:form>

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
		<td style="text-align: center;"><%=std.getDob() %> </td>
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