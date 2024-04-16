package com.teacher.headless.tracker.internal.resource.v1_0;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.student.db.model.Teacher;
import com.student.db.service.TeacherLocalService;
import com.teacher.headless.tracker.dto.v1_0.TeacherEntry;
import com.teacher.headless.tracker.resource.v1_0.TeacherEntryResource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * @author developer
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/teacher-entry.properties",
	scope = ServiceScope.PROTOTYPE, service = TeacherEntryResource.class
)
public class TeacherEntryResourceImpl extends BaseTeacherEntryResourceImpl {
	
	private Log _log = LogFactoryUtil.getLog(TeacherEntryResourceImpl.class);
	
	@Reference
	private TeacherLocalService teacherLocalService;
	
	@Override
	public TeacherEntry getTeacher() throws Exception {
		
		List<Teacher> teacherEntity = teacherLocalService.getTeachers(-1, -1);
		JSONObject teacherObject = JSONFactoryUtil.createJSONObject();
		JSONArray dataObject = JSONFactoryUtil.createJSONArray();
		
		for(Teacher teachers : teacherEntity) {
			JSONObject teacherSingleObject = JSONFactoryUtil.createJSONObject();
			teacherSingleObject.put("id", teachers.getId());
			teacherSingleObject.put("name", teachers.getName());
			teacherSingleObject.put("address", teachers.getAddress());
			
			dataObject.put(teacherSingleObject);
		}
		
		teacherObject.put("data", dataObject);
		teacherObject.put("message", "Teachers data listed successfully ");
		return commonResponse(teacherObject);
		
	}
	
	@Override
	public TeacherEntry addTeacher(TeacherEntry teacherEntry) throws Exception {
		
		String name = teacherEntry.getName();
		String address = teacherEntry.getAddress();
		
		Teacher teacherEntity = teacherLocalService.getInstance();
		JSONObject teacherObject = JSONFactoryUtil.createJSONObject();
		JSONObject dataObject = JSONFactoryUtil.createJSONObject();
		
		teacherEntity.setName(name);
		teacherEntity.setAddress(address);
		teacherEntity = teacherLocalService.addTeacher(teacherEntity);
		
		dataObject.put("id", teacherEntity.getId());
		dataObject.put("name", teacherEntity.getName());
		dataObject.put("address", teacherEntity.getAddress());
		
		teacherObject.put("data", dataObject);
		teacherObject.put("message", "Teacher added successfully");
		
		return commonResponse(teacherObject);
	}
	
	@Override
	public TeacherEntry updateTeacher(TeacherEntry teacherEntry) throws Exception {
		
		long id = teacherEntry.getId();
		String name = teacherEntry.getName();
		String address = teacherEntry.getAddress();
		
		//String message = "No Message";
		JSONObject dataObject = JSONFactoryUtil.createJSONObject();
		JSONObject teacherObject = JSONFactoryUtil.createJSONObject();
		
		try {
			Teacher teacherEntity = teacherLocalService.fetchTeacher(id);
			
			teacherEntity.setName(name);
			teacherEntity.setAddress(address);
			
			teacherLocalService.updateTeacher(teacherEntity);
			dataObject.put("id", teacherEntity.getId());
			dataObject.put("name", teacherEntity.getName());
			dataObject.put("address", teacherEntity.getAddress());
			
			_log.info("Teacher's details updated successfully ::: ");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			_log.error("Teacher's details unable to updated ::: ");
		}
		
		teacherObject.put("data", dataObject);
		teacherObject.put("message", "Teacher updated successfully ");
		
		return commonResponse(teacherObject);
				
	}
	
	@Override
	public TeacherEntry deleteTeacher(@Valid @RequestBody Long id) throws Exception {
		
		JSONObject teacherObject = JSONFactoryUtil.createJSONObject();
		JSONObject dataObject = JSONFactoryUtil.createJSONObject();
		
		try {
			Teacher teacherEntity = teacherLocalService.fetchTeacher(id);
			teacherLocalService.deleteTeacher(teacherEntity.getId());
			
			_log.info("Teacher details deleted successfully :: ");
		} catch (Exception e) {
			// TODO: handle exception
			_log.error("Failed to delete teacher data :: ");
		}
		
		teacherObject.put("data", dataObject);
		teacherObject.put("message", "Teacher data deleted successfully :: ");
		
		return commonResponse(teacherObject);
		
	}
	
	protected TeacherEntry commonResponse(JSONObject teacherObject) throws Exception{
		return new TeacherEntry() {{
			data = teacherObject.get("data");
			message = teacherObject.get("message");
		}};
	}
}