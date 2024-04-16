/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.common.db.service.impl;

import com.common.db.model.News;
import com.common.db.model.impl.NewsImpl;
import com.common.db.service.NewsLocalServiceUtil;
import com.common.db.service.base.NewsLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.common.db.model.News",
	service = AopService.class
)
public class NewsLocalServiceImpl extends NewsLocalServiceBaseImpl {
	
	public News getInstance() {
		News news = new NewsImpl();
		return news;
	}
	
	public News updateNewsWFStatus(long userId, long newsId,int status, ServiceContext serviceContext) {
		News newsObj = NewsLocalServiceUtil.fetchNews(newsId);
		newsObj.setStatus(status);
		newsObj = NewsLocalServiceUtil.updateNews(newsObj);
		
		try {
			if(status == WorkflowConstants.STATUS_APPROVED) {
				assetEntryLocalService.updateEntry(News.class.getName(), newsId, new Date(), null, true, true);
			}else {
				assetEntryLocalService.updateVisible(News.class.getName(), newsId, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newsObj;
	}
	
	public News updateNews(long newsId, int status) {
		
		News newsObj = NewsLocalServiceUtil.fetchNews(newsId);
		newsObj.setStatus(status);
		newsObj = NewsLocalServiceUtil.updateNews(newsObj);
		
		try {
			if(status == WorkflowConstants.STATUS_APPROVED) {
				assetEntryLocalService.updateEntry(News.class.getName(), newsId, new Date(), null, true, true);
			}else {
				assetEntryLocalService.updateVisible(News.class.getName(), newsId, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newsObj;
		
	}
	
	public List<News> findByUserId(long userId){
		return newsPersistence.findByUserId(userId);
	}
	
	public List<News> findByStatus(int status){
		return newsPersistence.findByStatus(status);
	}
}