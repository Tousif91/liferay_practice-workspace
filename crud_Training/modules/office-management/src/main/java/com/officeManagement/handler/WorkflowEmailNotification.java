package com.officeManagement.handler;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;


public class WorkflowEmailNotification {

	private static final Log _log = LogFactoryUtil.getLog(WorkflowEmailNotification.class);

	public static void sendWFEmailNotification(String emailId, String subject, String body,
			ActionRequest actionRequest) {

		_log.info("Entered into WF Email Notication method ::: ");

		String from = "mail.session.mail.smtp.user";
		//String to = "contenteditor@yopmail.com";
		PortletPreferences preferences = actionRequest.getPreferences();
		preferences.getValue(from, "default-value");

		try {
			InternetAddress fromAddress = new InternetAddress(from);
			InternetAddress toAddress = new InternetAddress(emailId);
			MailMessage mailMessage = new MailMessage();
			mailMessage.setFrom(fromAddress);
			mailMessage.setTo(toAddress);
			mailMessage.setSubject(subject);
			mailMessage.setBody(body);
			mailMessage.setHTMLFormat(true);
			MailServiceUtil.sendEmail(mailMessage);

			_log.info("Send mail with HTML Format : " + emailId);

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			_log.error("Error in email address: " + emailId);
			_log.error(e.getMessage());
		}
	}

}
