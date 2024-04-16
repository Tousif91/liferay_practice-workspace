<%@ include file="init.jsp" %>

<h1>Welcome to Employee home page</h1>

<portlet:renderURL var="addEmployeeURL">
	<portlet:param name="mvcPath" value="/addEmployee.jsp"/>
</portlet:renderURL>

<aui:a href="<%=addEmployeeURL %>">Add Employee</aui:a>

<br>

<portlet:renderURL var="employeeListURL">
	<portlet:param name="mvcPath" value="/employeeList.jsp"/>
</portlet:renderURL>

<aui:a href="<%=employeeListURL %>">List of Employees</aui:a>

<br>


