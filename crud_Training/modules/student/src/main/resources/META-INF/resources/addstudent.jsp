<%@ include file="init.jsp" %>

<portlet:actionURL name="addStudent" var="studentURL"/>

<aui:form action="<%=studentURL %>" method="post">

<aui:input name="name" type="text"/>

<aui:input name="dob" type="Date"/>

<aui:select name="gender">
<aui:option value="">Select</aui:option>
<aui:option value="Male">Male</aui:option>
<aui:option value="Female">Female</aui:option>
<aui:option value="Other">Other</aui:option>
</aui:select>

<aui:button type="submit" value="Submit"/>


</aui:form>