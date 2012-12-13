package org.frikadelki.secretsanta.control;

import org.frikadelki.secretsanta.email.ConnectionException;
import org.frikadelki.secretsanta.email.LowLevelMailSender;
import org.frikadelki.secretsanta.email.MailSenderParameters;
import org.frikadelki.secretsanta.model.Assignment;

import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/13/12
 * Time: 2:09 PM
 */
public class Emailer
{
	private final MailSenderParameters parameters;
	private final EmailerTemplate template;

	public Emailer(final MailSenderParameters parameters, final EmailerTemplate template)
	{
		if( null == parameters )
		{
			throw new IllegalArgumentException("Parameter 'parameters' can't be null.");
		}

		if( null == template )
		{
			throw new IllegalArgumentException("Parameter 'template' can't be null.");
		}

		this.parameters = parameters;
		this.template = template;
	}

	public void sendAssignments(final Assignment[] assignments)
	{
		final LowLevelMailSender mailSender = new LowLevelMailSender( parameters);
		System.out.println( "Connecting to mail server...");
		try
		{
			mailSender.connect();
		}
		catch(final ConnectionException e)
		{
			throw new RuntimeException( "Failed to connect to mail server.", e);
		}

		for(final Assignment assignment : assignments)
		{
			try
			{
				sendAssignment( mailSender, assignment);
			}
			catch(final Exception e)
			{
				mailSender.disconnect();
				throw new RuntimeException( "Smth terrible happened while sending one of the assignments.");
			}
		}

		mailSender.disconnect();
	}

	private void sendAssignment(final LowLevelMailSender sender, final Assignment assignment)
			throws ConnectionException, SendFailedException, AddressException
	{
		final String email = assignment.from.email;
		final String subject = template.getSubject();
		final String message = template.makeMessage( assignment);

		System.out.println( "Sending email to: " + assignment.from.name());
		sender.sendMessage( email, subject, message);
	}
}
