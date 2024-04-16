package com.student.portlet;

import com.student.constants.StudentPortletKeys;
import com.student.db.model.Student;
import com.student.db.service.StudentLocalService;
import com.student.db.service.StudentLocalServiceUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

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
		"javax.portlet.display-name=Student",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StudentPortletKeys.STUDENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class StudentPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(StudentPortlet.class);
	
	public void addStudent(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = ParamUtil.getString(actionRequest, "name");
		Date dob = ParamUtil.getDate(actionRequest, "dob", sdf);
		String gender = ParamUtil.getString(actionRequest, "gender");
		
		System.out.println("name ::" + name + ",,, dob ::" +sdf.format(dob)+ ",, gender" + gender);
		
		Student student = studentLocalService.getInstance();
		student.setName(name);
		student.setDob(dob);
		student.setGender(gender);
		
		studentLocalService.addStudent(student);
		
		System.out.println("Student added successfully ::: ");
		
		PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, themeDisplay.getPpid(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
		redirectURL.setParameter("mvcPath", "/studentlist.jsp");
		actionResponse.sendRedirect(redirectURL.toString());
		
	}
	
	
		public void editStudent(ActionRequest actionRequest, ActionResponse actionResponse)
				throws IOException, PortletException {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			long studentId = ParamUtil.getLong(actionRequest, "studentId");
			String name = ParamUtil.getString(actionRequest, "name");
			Date dob = ParamUtil.getDate(actionRequest, "dob", sdf);
			String gender = ParamUtil.getString(actionRequest, "gender");
			
			System.out.println("Gender : " +gender );
			
			
			if(studentId > 0) {
			
				Student stdent = studentLocalService.fetchStudent(studentId);
				stdent.setName(name);
				stdent.setDob(dob);
				stdent.setGender(gender);
				studentLocalService.updateStudent(stdent);
				
				System.out.println("Student details updated successfully ::: ");
			}
			
			PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, themeDisplay.getPpid(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			redirectURL.setParameter("mvcPath", "/studentlist.jsp");
			actionResponse.sendRedirect(redirectURL.toString());
			
		}
		
	
		public void deleteStudent(ActionRequest actionRequest, ActionResponse actionResponse)
					throws IOException, PortletException {
				
				ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
				long studentId = ParamUtil.getLong(actionRequest, "studentId");
				
				Student student = studentLocalService.fetchStudent(studentId);
				
				if(Validator.isNotNull(student)) {
					studentLocalService.deleteStudent(student);
					System.out.println("Student deleted successfully :: ");
				}
				
				PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, themeDisplay.getPpid(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
				redirectURL.setParameter("mvcPath", "/studentlist.jsp");
				actionResponse.sendRedirect(redirectURL.toString());
			}
			
		
		public void searchStudent(ActionRequest actionRequest, ActionResponse actionResponse)
				throws IOException, PortletException {
			_log.info("Entered into the Search Student Method ::: ");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			String name = ParamUtil.getString(actionRequest, "name");
			
			_log.info("Student Name : " + name);
			
			List<Student> studentsList = studentLocalService.findStudentByName(name);
			actionRequest.setAttribute("studentName",studentsList);
			actionResponse.setRenderParameter("mvcPath", "/studentlist.jsp");
			//actionResponse.setProperty(RenderResponse.CACHE_SCOPE, "/searchStudent.jsp");
			
		/*	PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, themeDisplay.getPpid(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			redirectURL.setParameter("mvcPath", "/searchStudent.jsp");
			actionResponse.sendRedirect(redirectURL.toString());  */
		}
		
		@Reference
		private StudentLocalService studentLocalService;
		
}