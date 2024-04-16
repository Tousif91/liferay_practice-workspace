<%@ include file="init.jsp" %>

<portlet:renderURL var="addStudentURL">
<portlet:param name="jspPage" value="/addstudent.jsp"/>
</portlet:renderURL>


<aui:a href="<%=addStudentURL %>">go to add Student</aui:a>

<br>
<br>
<portlet:renderURL var="studentListURL">
<portlet:param name="jspPage" value="/studentlist.jsp"/>
</portlet:renderURL>


<aui:a href="<%=studentListURL %>">go to Student list</aui:a>

<br>
<br>

<portlet:renderURL var="searchStudentURL">
<portlet:param name="jspPage" value="/searchStudent.jsp"/>
</portlet:renderURL>


<aui:a href="<%=searchStudentURL %>">go to Search Student page</aui:a>