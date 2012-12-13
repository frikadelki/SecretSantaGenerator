package org.frikadelki.secretsanta.control;

import org.frikadelki.secretsanta.model.Assignment;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/13/12
 * Time: 3:25 PM
 */
public class EmailerTemplate
{
	private final String subject;
	private final String messageTemplate;

	public EmailerTemplate(final String subject, final String messageTemplate)
	{
		if( null == subject )
		{
			throw new IllegalArgumentException("Parameter 'subject' can't be null.");
		}

		if( null == messageTemplate )
		{
			throw new IllegalArgumentException("Parameter 'messageTemplate' can't be null.");
		}

		this.subject = subject;
		this.messageTemplate = messageTemplate;
	}

	public String getSubject()
	{
		return subject;
	}

	public String makeMessage(final Assignment assignment)
	{
		return String.format( messageTemplate, assignment.toString());
	}
}
