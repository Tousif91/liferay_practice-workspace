package com.teacher.headless.tracker.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author developer
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false",
		"osgi.jaxrs.application.base=/teacher-headless-tracker",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=TeacherHeadlessTracker"
	},
	service = Application.class
)
@Generated("")
public class TeacherHeadlessTrackerApplication extends Application {
}