package com.teacher.headless.tracker.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import com.teacher.headless.tracker.internal.graphql.mutation.v1_0.Mutation;
import com.teacher.headless.tracker.internal.graphql.query.v1_0.Query;
import com.teacher.headless.tracker.internal.resource.v1_0.TeacherEntryResourceImpl;
import com.teacher.headless.tracker.resource.v1_0.TeacherEntryResource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author developer
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setTeacherEntryResourceComponentServiceObjects(
			_teacherEntryResourceComponentServiceObjects);

		Query.setTeacherEntryResourceComponentServiceObjects(
			_teacherEntryResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "TeacherHeadlessTracker";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/teacher-headless-tracker-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#addTeacher",
						new ObjectValuePair<>(
							TeacherEntryResourceImpl.class, "addTeacher"));
					put(
						"mutation#updateTeacher",
						new ObjectValuePair<>(
							TeacherEntryResourceImpl.class, "updateTeacher"));
					put(
						"mutation#deleteTeacher",
						new ObjectValuePair<>(
							TeacherEntryResourceImpl.class, "deleteTeacher"));

					put(
						"query#teacher",
						new ObjectValuePair<>(
							TeacherEntryResourceImpl.class, "getTeacher"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TeacherEntryResource>
		_teacherEntryResourceComponentServiceObjects;

}