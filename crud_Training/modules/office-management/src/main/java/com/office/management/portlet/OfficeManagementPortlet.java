package com.office.management.portlet;

import com.office.management.constants.OfficeManagementPortletKeys;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.common.db.model.News;
import com.common.db.service.NewsLocalService;
import com.common.db.service.NewsLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author developer
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=OfficeManagement",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + OfficeManagementPortletKeys.OFFICEMANAGEMENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class OfficeManagementPortlet extends MVCPortlet {
	
	
	private static Log _log = LogFactoryUtil.getLog(OfficeManagementPortlet.class);
	
	@Reference
	private NewsLocalService  newsLocalService;
	
	@Override
		public void render(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<News> newsList = null;
			
			
			try {
				Role contentEditer = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "content editor");
				Role contentPublisher = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "content publisher");
				
				boolean isContentEditor = themeDisplay.getUser().getRoles().contains(contentEditer);
				boolean isContentPublisher = themeDisplay.getUser().getRoles().contains(contentPublisher);
				
				if(isContentEditor) {
					newsList = NewsLocalServiceUtil.findByStatus(1);
				
				}else if(isContentPublisher) {
					newsList = NewsLocalServiceUtil.findByStatus(2);
					
				}else if(themeDisplay.getUserId() > 0){
					newsList = NewsLocalServiceUtil.findByUserId(themeDisplay.getUserId());
				}
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			renderRequest.setAttribute("newsList", newsList);
			super.render(renderRequest, renderResponse);
		}
	
	public void addNews(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String title = ParamUtil.getString(actionRequest, "title");
		String subTitle = ParamUtil.getString(actionRequest, "subTitle");
		String description = ParamUtil.getString(actionRequest, "description");	
		
		_log.info("New Title : " + title);
		_log.info("New Sub Title : " + subTitle);
		_log.info("New description : " + description);
		
		News news = newsLocalService.getInstance();
		news.setTitle(title);
		news.setUserId(themeDisplay.getUserId());
		news.setSubTitle(subTitle);
		news.setDescription(description);
		
		newsLocalService.addNews(news);
		_log.info("News Added successfully ::: ");		
		
		ServiceContext serviceContext;
		try {
			
			serviceContext = ServiceContextFactory.getInstance(News.class.getName(), actionRequest);
			
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.updateEntry(themeDisplay.getUserId(), serviceContext.getScopeGroupId(), new Date(), new Date(), News.class.getName(), news.getId(), news.getUuid(),
									0, null, null, true, false, new Date(), null, new Date(), null, ContentTypes.TEXT_HTML, "News", "News", null, null, null, 0, 0, null);
			Indexer<News> indexer = IndexerRegistryUtil.nullSafeGetIndexer(News.class);
			indexer.reindex(news);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), News.class.getName(), news.getPrimaryKey(), news, serviceContext);
		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
	
	}
	
	
	public void editNews(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		long id = ParamUtil.getLong(actionRequest, "id");
		String title = ParamUtil.getString(actionRequest, "title");
		String subTitle = ParamUtil.getString(actionRequest, "subtitle");
		String description = ParamUtil.getString(actionRequest, "description");
		
		_log.info("News Id : " + id);
		_log.info("News title : " + title);
		_log.info("News subTitle : " + subTitle);
		_log.info("News description : " + description);
		
		if(id > 0) {
			try {
				News news = newsLocalService.getNews(id);
				news.setTitle(title);
				news.setSubTitle(subTitle);
				news.setDescription(description);
				
				newsLocalService.updateNews(news);
				_log.info("News updated successfully :::: ");
				
			} catch (PortalException e) {
				_log.error("Unable to update news ");
				e.printStackTrace();
			}
		}
	}
}