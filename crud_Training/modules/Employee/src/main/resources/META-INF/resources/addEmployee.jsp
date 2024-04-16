<%@ include file="init.jsp" %>

<portlet:actionURL name="addEmployee" var="addEmployeeURL"/>

<aui:form action="<%=addEmployeeURL %>" method="post" >
	<aui:input name="employeeName" type="text" required="true" label="Employee Name" placeholder="Enter Employee Name"/>
	<aui:input name="dob" type="Date" required="true" label="Date of Birth" />
	<aui:select name="gender" label="Gender" required="true">
		<aui:option value="">Select your gender</aui:option>
		<aui:option value="male">Male</aui:option>
		<aui:option value="female">Female</aui:option>
		<aui:option value="other">Other</aui:option>
	</aui:select>
	<aui:input name="email" type="email" required="true" label="Email" placeholder="abcd@gmail.com"/>
	<aui:input name="address" type="text" required="true" label="Address" placeholder="Enter your full address"/>
	
	<aui:button type="submit" value="Add Employee"/>

</aui:form>