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

package com.student.db.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.student.db.model.Employee;
import com.student.db.service.base.EmployeeServiceBaseImpl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=tr",
		"json.web.service.context.path=Employee"
	},
	service = AopService.class
)
public class EmployeeServiceImpl extends EmployeeServiceBaseImpl {
	
	public Employee addEmployee(long groupId, String name, Date dob, String gender, String email, String address, ServiceContext serviceContext ) throws PortalException{
			return employeeLocalService.addEmployee(groupId,name,dob,gender,email,address,serviceContext);
	}

	public Employee updateEmployee(long empId, String name, Date dob, String gender, String email, String address) throws PortalException{
		return employeeLocalService.updateEmployee(empId, name, dob, gender, email, address);
	}
	
	public List<Employee> getEmployeeByGroupId(long groupId){
		return employeePersistence.findByGroupId(groupId);
	}
	
	public Employee getEmployee(long empId) throws PortalException{
		return employeeLocalService.getEmployee(empId);
	}
	
	public Employee deleteEmployee(long empId) throws PortalException{
		return employeeLocalService.deleteEmployee(empId);
	}
}