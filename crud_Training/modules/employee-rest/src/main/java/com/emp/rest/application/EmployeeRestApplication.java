package com.emp.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.student.db.model.Employee;
import com.student.db.service.EmployeeLocalService;
import com.student.db.service.EmployeeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author developer
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/employee",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Employee.Rest"
	},
	service = Application.class
)
public class EmployeeRestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(EmployeeRestApplication.class);
	
	@Reference
	private EmployeeService employeeService;
	
	
	@Reference
	private EmployeeLocalService employeeLocalService;
	
	@Reference
	private CompanyService companyService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployeeByEmployeeId() {	
		
		try {
			
			List<Employee> employees = new ArrayList<Employee>();
			Company company = companyService.getCompanyById(PortalUtil.getDefaultCompanyId());
			
			List<Group> groups = GroupLocalServiceUtil.getGroups(company.getCompanyId(), 0, true);
			for(Group group :groups) {
				employees.addAll(employeeService.getEmployeeByGroupId(group.getGroupId()));
			}
			return JSONFactoryUtil.serialize(employees);
			
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}
	}

	// @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee() {
		
		try {
			
			List<Employee> employees = new ArrayList<Employee>();
			Company company = companyService.getCompanyById(PortalUtil.getDefaultCompanyId());
			
			List<Group> groups = GroupLocalServiceUtil.getGroups(company.getCompanyId(), 0, true);
			
			for(Group group: groups) {
				employees.addAll(employeeService.getEmployeeByGroupId(group.getGroupId()));
			}
			
			return Response.ok(employees).build();
			
		} catch (Exception e) {
			// TODO: handle exception
			return Response.ok().build();
		}
	}
	
	@GET
	@Path("/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees(@PathParam("empId") long empId) {
		try {
			return Response.ok(employeeService.getEmployee(empId)).build();
		} catch (Exception e) {
			// TODO: handle exception
			return Response.serverError().build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(EmployeeDto employeeDto, @Context HttpServletRequest request) {
		try {
			long userId = Long.parseLong(request.getUserPrincipal().getName());
			
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setRequest(request);
			serviceContext.setCompanyId(PortalUtil.getDefaultCompanyId());
			serviceContext.setUserId(userId);
			
			Employee employee = employeeService.addEmployee(employeeDto.getGroupId(), employeeDto.getName(),
								employeeDto.getDob(), employeeDto.getGender(), employeeDto.getEmail(),
								employeeDto.getAddress(), serviceContext);
			return Response.ok(employee).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	
	
	/*
	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

	@GET
	@Path("/morning")
	@Produces("text/plain")
	public String hello() {
		return "Good morning!";
	}

	@GET
	@Path("/morning/{name}")
	@Produces("text/plain")
	public String morning(
		@PathParam("name") String name,
		@QueryParam("drink") String drink) {

		String greeting = "Good Morning " + name;

		if (drink != null) {
			greeting += ". Would you like some " + drink + "?";
		}

		return greeting;
	}
	
	*/

}