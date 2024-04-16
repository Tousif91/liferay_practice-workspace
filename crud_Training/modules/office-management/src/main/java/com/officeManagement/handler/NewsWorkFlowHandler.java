package com.officeManagement.handler;

import com.common.db.model.News;
import com.common.db.service.NewsLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.office.management.constants.OfficeManagementPortletKeys;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"model.class.name=com.common.db.model.News"
		},
		service = WorkflowHandler.class
	)

public class NewsWorkFlowHandler extends BaseWorkflowHandler<News>{

	
	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return News.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		// TODO Auto-generated method stub
		return "News tasks";
	}

	@Override
	public News updateStatus(int status, Map<String, Serializable> workflowContext) throws PortalException {
		
		
		// TODO Auto-generated method stub
		long id = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		News flowObject = NewsLocalServiceUtil.updateNews(id, status);
		return null;
	}

	

	
	
}
