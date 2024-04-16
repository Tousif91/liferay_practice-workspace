<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.student.db.service.EmployeeLocalServiceUtil"%>
<%@page import="java.util.Collections"%>
<%@page import="com.student.db.model.Employee"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<portlet:actionURL name="searchEmployeeByGender" var="searchEmployeeURL"/>

<portlet:renderURL var="clearEmployeeURL">
	<portlet:param name="mvcPath" value="/employeeList.jsp"/>
</portlet:renderURL>

<portlet:renderURL var="addEmployeeURL">
	<portlet:param name="mvcPath" value="/addEmployee.jsp"/>
</portlet:renderURL>

<%
	Object empGender = request.getAttribute("empGender");
	List<Employee> empList = (null==empGender)?Collections.EMPTY_LIST:(List<Employee>)empGender;
	
	List<Employee> employeeList = EmployeeLocalServiceUtil.getEmployees(-1, -1);
	
	if(Validator.isNotNull(empGender)){
		employeeList = (List<Employee>)empGender;
	}
	
	PortletURL iteratorURL = renderResponse.createRenderURL();
	iteratorURL.setParameter("mvcPath", "/employeeList.jsp");
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>

	
<aui:form action="<%=searchEmployeeURL %>" method="post">
<div class="container">
	<div class="row">
		
		<div class="col-md-6">
			<aui:select name="gender" label="">
				<aui:option value="male">Male</aui:option>
				<aui:option value="female">Female</aui:option>
				<aui:option value="other">Other</aui:option>
			</aui:select>
		</div>
		<div class="col-md-2">
			<aui:button type="submit" value="Search" />
		</div>
		<div class="col-md-2">
			<aui:button type="reset" href="<%=clearEmployeeURL %>" value="Clear" />
		</div>
		<div class="col-md-2">
			<aui:button type="submit" href="<%=addEmployeeURL %>" value="Add Employee" />
		</div>
		</aui:form>
	</div>
</div>


<liferay-ui:search-container delta="5" emptyResultsMessage="No Employee Data Availabe" total="<%= employeeList.size()%>" iteratorURL="<%=iteratorURL %>">
	<liferay-ui:search-container-results results="<%=ListUtil.subList(employeeList, searchContainer.getStart(), searchContainer.getEnd()) %>"/>
	<liferay-ui:search-container-row className="com.student.db.model.Employee" modelVar="emp">
		<liferay-ui:search-container-column-text name="Employee ID" property="empId"/>
		<liferay-ui:search-container-column-text name="Employee Name" property="name"/>
		<liferay-ui:search-container-column-text name="Date of Birth" value="<%=sdf.format(emp.getDob()) %>"/>
		<liferay-ui:search-container-column-text name="Gender" property="gender"/>
		<liferay-ui:search-container-column-text name="Email" property="email"/>
		<liferay-ui:search-container-column-text name="Address" property="address"/>
		
	
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator paginate="true"/>
</liferay-ui:search-container>

<!-- <script>
    function clearSearch() {
        var form = document.forms[0];
        form.reset();
    }
</script> -->