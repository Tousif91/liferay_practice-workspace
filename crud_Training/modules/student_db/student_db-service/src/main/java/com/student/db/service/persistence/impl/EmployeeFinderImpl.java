package com.student.db.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.student.db.model.Employee;
import com.student.db.model.impl.EmployeeImpl;
import com.student.db.service.persistence.EmployeeFinder;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = EmployeeFinder.class)
public class EmployeeFinderImpl extends EmployeeFinderBaseImpl implements EmployeeFinder{
	
	@Reference
	private CustomSQL customSQL;
	
	private static Log _log = LogFactoryUtil.getLog(EmployeeFinderImpl.class);
	
	public List<Employee> getEmployeeByGender(String gender){
		Session session = null;
		List<Employee> employeelist = Collections.EMPTY_LIST;
		
		try {
			session = openSession();
			String sql = customSQL.get(getClass(), "getResultByGender");
			//_log.info("SQL query ===== ::: " + sql);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(false);
			sqlQuery.addEntity("Employee", EmployeeImpl.class);
			QueryPos queryPos = QueryPos.getInstance(sqlQuery);
			queryPos.add(gender);
			employeelist = (List<Employee>)sqlQuery.list();
			return employeelist;
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			closeSession(session);
		}
		
		return employeelist;
	}

}
