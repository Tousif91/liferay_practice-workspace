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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.student.db.model.Employee;
import com.student.db.model.impl.EmployeeImpl;
import com.student.db.service.EmployeeLocalServiceUtil;
import com.student.db.service.base.EmployeeLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.student.db.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {
	
	public Employee getInstance() {
		Employee employee = new EmployeeImpl();
		return employee;
	}
	
	public List<Employee> getEmployeeByGender(String gender){
		return employeeFinder.getEmployeeByGender(gender);
	}
	
	public List<Employee> findEmployeesByGender(String gender){
		
		ClassLoader classLoader = EmployeeImpl.class.getClassLoader();
		DynamicQuery dynamicQuery = EmployeeLocalServiceUtil.dynamicQuery();	
		dynamicQuery = DynamicQueryFactoryUtil.forClass(Employee.class,"", classLoader);
		dynamicQuery.add(PropertyFactoryUtil.forName("gender").eq(gender));
		List<Employee> empList = EmployeeLocalServiceUtil.dynamicQuery(dynamicQuery);
		
		return empList;
	}
	
	public Employee addEmployee(long empId, String name, Date dob, String gender, String email, String address, ServiceContext serviceContext) throws PortalException {
		Group group = GroupLocalServiceUtil.getGroup(empId);
		long userId = serviceContext.getUserId();
		User user = userLocalService.getUser(userId);
		
		Employee employee = createEmployee(empId);
		employee.setAddress(address);
		employee.setDob(dob);
		employee.setEmail(email);
		employee.setName(name);
		employee.setGender(gender);
		
		return super.addEmployee(employee);
	}
	
	public Employee updateEmployee(long empId, String name, Date dob, String gender, String email, String address) throws PortalException{
		
		Employee emp = getEmployee(empId);
		
		emp.setName(name);
		emp.setDob(dob);
		emp.setGender(gender);
		emp.setEmail(email);
		emp.setAddress(address);
		
		return super.updateEmployee(emp);
	}
	
	@Override
	public Employee addEmployee(Employee employee) {
		throw new UnsupportedOperationException("Not supported :: ");
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		throw new UnsupportedOperationException("Not supported :: ");
	}
}