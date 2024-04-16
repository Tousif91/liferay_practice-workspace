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

package com.student.db.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TeacherService}.
 *
 * @author Brian Wing Shun Chan
 * @see TeacherService
 * @generated
 */
public class TeacherServiceWrapper
	implements ServiceWrapper<TeacherService>, TeacherService {

	public TeacherServiceWrapper() {
		this(null);
	}

	public TeacherServiceWrapper(TeacherService teacherService) {
		_teacherService = teacherService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _teacherService.getOSGiServiceIdentifier();
	}

	@Override
	public TeacherService getWrappedService() {
		return _teacherService;
	}

	@Override
	public void setWrappedService(TeacherService teacherService) {
		_teacherService = teacherService;
	}

	private TeacherService _teacherService;

}