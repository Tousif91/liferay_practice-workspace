<%@page import="com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowLog"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.Serializable"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowInstance"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@page import="com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.model.WorkflowInstanceLink"%>
<%@page import="com.common.db.service.NewsLocalServiceUtil"%>
<%@page import="com.common.db.model.News"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="init.jsp" %>

<%
	long newsId = ParamUtil.getLong(request, "id");
	News news = NewsLocalServiceUtil.getNews(newsId);

%>

<%
if(Validator.isNotNull(news)){
%>

<portlet:renderURL var="renderURL">
 	<portlet:param name="mvcPath" value="/newsNotification.jsp"/>
</portlet:renderURL>

<portlet:actionURL name="#" var="editNewsURL"/>

<aui:form action="<%=editNewsURL %>" method="post" >
	<aui:input name="id" type="hidden" value="<%=news.getId() %>" label="News ID"/>
	<aui:input name="title" type="text" value="<%=news.getTitle() %>" label="Title" disabled="true"/>
	<aui:input name="subtitle" type="text" value="<%=news.getSubTitle() %>" label="Sub-Title" disabled="true"/>
	<aui:input name="description" type="text" value="<%=news.getDescription() %>" label="Description" disabled="true"/>
</aui:form>

<aui:button href="<%=renderURL %>" value="Back"/>

<%
long companyId = themeDisplay.getCompanyId();
long groupId = themeDisplay.getScopeGroupId();
long userId = themeDisplay.getUserId();

WorkflowInstanceLink wil = null;
try{
	wil = WorkflowInstanceLinkLocalServiceUtil.getWorkflowInstanceLink(companyId, groupId, News.class.getName(), news.getId());
}
catch(PortalException e){}
if(Validator.isNotNull(wil)){
	WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(companyId, wil.getWorkflowInstanceId());
	
	Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();
	List<Integer> logTypes_assign = new ArrayList<Integer>();
	logTypes_assign.add(WorkflowLog.TASK_ASSIGN);
	List<WorkflowLog> workflowLogs_assign = WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(companyId, wil.getWorkflowInstanceId(), logTypes_assign, QueryUtil.ALL_POS, QueryUtil.ALL_POS, WorkflowComparatorFactoryUtil.getLogCreateDateComparator(true));
	if(workflowLogs_assign.size() > 0){           
	    long workflowTaskId = workflowLogs_assign.get(workflowLogs_assign.size()-1).getWorkflowTaskId();
	    if(workflowLogs_assign.get(workflowLogs_assign.size()-1).getRoleId() > 0){
			for(long roleId : themeDisplay.getUser().getRoleIds()){
				if(roleId == workflowLogs_assign.get(workflowLogs_assign.size()-1).getRoleId()){
				%>
					<input type="button" name="btn" class="pif-primary-btn mx-2" onClick="setTransition('assignToMe')" value="<liferay-ui:message key="assign-to-me"/>"/>
				<%	
					break;
				}
			}
	    } else {
			if(userId == workflowLogs_assign.get(workflowLogs_assign.size()-1).getUserId()){
				List<String> transitionNames = WorkflowTaskManagerUtil.getNextTransitionNames(companyId, userId, workflowTaskId);
				for(String transitionName : transitionNames){
				%>
				<input type="button" class="pif-primary-btn mx-2 pif-green-btn" name="btn" onClick="setTransition('<%=transitionName %>')" value="<liferay-ui:message key="<%=transitionName %>"/> "></input>
				<%
				}
			}
	    }
	}
}
%>
<portlet:actionURL var="updateNewsWFURL" name="updateNewsWF" />


<aui:form name="newsWF" action="<%=updateNewsWFURL.toString()%>">
	<%-- newsPublishingId : <%=news.getId()%><br/>
	newsPublishingStatus : <%=news.getStatus() %><br/> --%>
	<aui:input type="hidden" name="redirect" value="<%=themeDisplay.getURLCurrent() %>"></aui:input>
	<aui:input type="hidden" name="newsId" value="<%=news.getId() %>"></aui:input>
	<aui:input type="hidden" name="transitionName" id="transitionName"></aui:input>
	
	<%
	if(Validator.isNotNull(wil)){
	%>
		<aui:input type="text" name="comment" placeholder="comment here..."/>
	<%
		}
	%>
</aui:form>
<%
}else{
%>
<h1>No Record Found</h1>
<%
}
%>
<script type="text/javascript">
function setTransition(transitionName){
	console.log("transitionName "+transitionName);
	$("#<portlet:namespace />transitionName").val(transitionName);
	$("#<portlet:namespace />newsWF").submit();
}
</script>