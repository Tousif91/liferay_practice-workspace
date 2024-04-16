package com.employee.portlet;

import com.employee.constants.EmployeePortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.student.db.model.Employee;
import com.student.db.service.EmployeeLocalService;
import com.student.db.service.EmployeeLocalServiceUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
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
		"javax.portlet.display-name=Employee",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EmployeePortletKeys.EMPLOYEE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeePortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(EmployeePortlet.class);
	
	@Reference
	private EmployeeLocalService employeeLocalService;
	
	public void addEmployee(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String empName = ParamUtil.getString(actionRequest, "employeeName");
		Date dob = ParamUtil.getDate(actionRequest, "dob", sdf);
		String gender = ParamUtil.getString(actionRequest, "gender");
		String email = ParamUtil.getString(actionRequest, "email");
		String address = ParamUtil.getString(actionRequest, "address");
		
		_log.info("Employee Name : " + empName);
		_log.info("Employee Date of Birth : " + sdf.format(dob));
		_log.info("Employee Gender : " + gender);
		_log.info("Employee Email : " + email);
		_log.info("Employee Address : " + address);
		
		Employee employee = employeeLocalService.getInstance();
		employee.setName(empName);
		employee.setDob(dob);
		employee.setGender(gender);
		employee.setEmail(email);
		employee.setAddress(address);
		employeeLocalService.addEmployee(employee);
		
		_log.info("Employee added successfully ::: ");
		
		PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, themeDisplay.getPpid(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		redirectURL.setParameter("mvcPath", "/employeeList.jsp");
		actionResponse.sendRedirect(redirectURL.toString());
	}
	
/*
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
			
		_log.info("Started custom SQL ::::: ");
		
		String empGender = employeeLocalService.getEmployeeByGender("male").get(1).getName();
		
		//List<Employee> employeesGender = employeeLocalService.getEmployeeByGender(gender);
		_log.info("Successfully retrieved Employee :::::: " + empGender);
		super.doView(renderRequest, renderResponse);
	}
*/	
	
	public void searchEmployeeByGender(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		List<Employee> empList = null;
		
		String gender = ParamUtil.getString(actionRequest, "gender");
		
		
		/*		
		List<Employee> employeeList = employeeLocalService.getEmployeeByGender(gender);
		
		for(Employee employees : employeeList) {
			_log.info("Employee Id :: " + employees.getEmpId());
			_log.info("Employee Name :: " + employees.getName());
			_log.info("Employee gender :: " + employees.getGender());
		}
		
		
		actionRequest.setAttribute("empGender", employeeList);
		actionResponse.setRenderParameter("mvcPath", "/employeeList.jsp");
		
		*/
		
		try {
			
			_log.info("Dynamic Query  started ::: Retrieving User information ");
			
			empList = employeeLocalService.getEmployees(-1, -1);
			
			if(Validator.isNotNull(gender)) {
				
			
				empList = employeeLocalService.findEmployeesByGender(gender);
			 			  
				for(Employee employees : empList) {
					 _log.info("User Name : " + employees.getName());
					 _log.info("User Id : " + employees.getEmail());
					 _log.info("User Email Id : " + employees.getGender());
				  
				  _log.info("Stop retrieving user information ::: ");
				    
			  }
			} 
				  
			actionRequest.setAttribute("empGender", empList);
			actionResponse.setRenderParameter("mvcPath", "/employeeList.jsp");	  
			 
	
		  } catch (Exception e) { e.printStackTrace();
		  
		  	_log.error("Unable to print employee data" + e); 
		  }
		
		  _log.info("Dynamic query stoped :::: ");
		 
	}
	
	
	/*
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		_log.info("Entered into the doview method ::: ");
		
		try {
			
			
			  DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Employee.class,
			  PortletClassLoaderUtil.getClassLoader());
			  dynamicQuery.add(PropertyFactoryUtil.forName("emp.gender").eq("male"));
			  
			  List<Employee> employeeList1 =
			  employeeLocalService.dynamicQuery(dynamicQuery);
			  for(Employee emp1 : employeeList1) {
				  _log.info("Employee Name ::: " + emp1.getName());
				  _log.info("Employee Date of Birth ::: " + emp1.getDob());
				  _log.info("Employee Address ::: " + emp1.getAddress());
				  _log.info("Employee Email ::: " + emp1.getEmail());
				  
			  }
			 
			
			
		} catch (Exception e) {
			e.printStackTrace();
			_log.error("Unable to print employee data");
		}
		
	}
	
	*/	
}