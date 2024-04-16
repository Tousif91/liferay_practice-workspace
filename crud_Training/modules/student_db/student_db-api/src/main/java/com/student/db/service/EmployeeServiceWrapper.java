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
 * Provides a wrapper for {@link EmployeeService}.
 *
 * @author Brian Wing Shun Chan
 * @see EmployeeService
 * @generated
 */
public class EmployeeServiceWrapper
	implements EmployeeService, ServiceWrapper<EmployeeService> {

	public EmployeeServiceWrapper() {
		this(null);
	}

	public EmployeeServiceWrapper(EmployeeService employeeService) {
		_employeeService = employeeService;
	}

	@Override
	public com.student.db.model.Employee addEmployee(
			long groupId, String name, java.util.Date dob, String gender,
			String email, String address,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeeService.addEmployee(
			groupId, name, dob, gender, email, address, serviceContext);
	}

	@Override
	public com.student.db.model.Employee deleteEmployee(long empId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeeService.deleteEmployee(empId);
	}

	@Override
	public com.student.db.model.Employee getEmployee(long empId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeeService.getEmployee(empId);
	}

	@Override
	public java.util.List<com.student.db.model.Employee> getEmployeeByGroupId(
		long groupId) {

		return _employeeService.getEmployeeByGroupId(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _employeeService.getOSGiServiceIdentifier();
	}

	@Override
	public com.student.db.model.Employee updateEmployee(
			long empId, String name, java.util.Date dob, String gender,
			String email, String address)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _employeeService.updateEmployee(
			empId, name, dob, gender, email, address);
	}

	@Override
	public EmployeeService getWrappedService() {
		return _employeeService;
	}

	@Override
	public void setWrappedService(EmployeeService employeeService) {
		_employeeService = employeeService;
	}

	private EmployeeService _employeeService;

}