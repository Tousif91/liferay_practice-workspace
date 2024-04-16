package com.teacher.headless.tracker.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import com.teacher.headless.tracker.dto.v1_0.TeacherEntry;
import com.teacher.headless.tracker.resource.v1_0.TeacherEntryResource;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author developer
 * @generated
 */
@Generated("")
public class Query {

	public static void setTeacherEntryResourceComponentServiceObjects(
		ComponentServiceObjects<TeacherEntryResource>
			teacherEntryResourceComponentServiceObjects) {

		_teacherEntryResourceComponentServiceObjects =
			teacherEntryResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teacher{id, name, address, message, data}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "get teacher")
	public TeacherEntry teacher() throws Exception {
		return _applyComponentServiceObjects(
			_teacherEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			teacherEntryResource -> teacherEntryResource.getTeacher());
	}

	@GraphQLName("TeacherEntryPage")
	public class TeacherEntryPage {

		public TeacherEntryPage(Page teacherEntryPage) {
			actions = teacherEntryPage.getActions();

			items = teacherEntryPage.getItems();
			lastPage = teacherEntryPage.getLastPage();
			page = teacherEntryPage.getPage();
			pageSize = teacherEntryPage.getPageSize();
			totalCount = teacherEntryPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<TeacherEntry> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			TeacherEntryResource teacherEntryResource)
		throws Exception {

		teacherEntryResource.setContextAcceptLanguage(_acceptLanguage);
		teacherEntryResource.setContextCompany(_company);
		teacherEntryResource.setContextHttpServletRequest(_httpServletRequest);
		teacherEntryResource.setContextHttpServletResponse(
			_httpServletResponse);
		teacherEntryResource.setContextUriInfo(_uriInfo);
		teacherEntryResource.setContextUser(_user);
		teacherEntryResource.setGroupLocalService(_groupLocalService);
		teacherEntryResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<TeacherEntryResource>
		_teacherEntryResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}