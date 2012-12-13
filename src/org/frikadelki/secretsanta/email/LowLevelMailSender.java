package org.frikadelki.secretsanta.email;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/13/12
 * Time: 2:29 PM
 */
public class LowLevelMailSender
{
	private final MailSenderParameters parameters;

	private Session session = null;
	private Transport transport = null;

	public LowLevelMailSender(MailSenderParameters parameters)
	{
		if( null == parameters )
		{
			throw new IllegalArgumentException("Parameter 'parameters' can't be null.");
		}
		this.parameters = parameters;
	}

	public boolean isConnected()
	{
		if( null == transport )
		{
			return false;
		}

		if( !transport.isConnected() )
		{
			disconnect();
			return false;
		}

		return true;
	}

	public void connect() throws ConnectionException
	{
		if( isConnected() )
		{
			return;
		}

		final Properties properties = parameters.makeProperties();
		session = Session.getInstance( properties);

		try
		{
			transport = session.getTransport( parameters.getSessionProtocol());
			transport.connect(
					parameters.getSmtpHost(),
					parameters.getEmail(),
					parameters.getPassword());
		}
		catch(final NoSuchProviderException e)
		{
			throw new RuntimeException( "Bad mailing API impl.", e);
		}
		catch(final MessagingException e)
		{
			throw new ConnectionException( "Connection failed.", e);
		}
	}

	public void disconnect()
	{
		try
		{
			transport.close();
		}
		catch(final MessagingException ignore)
		{
		}
		transport = null;
		session = null;
	}

	public void sendMessage(final String email, final String subject, final String message)
			throws ConnectionException, AddressException, SendFailedException
	{
		if( !isConnected() )
		{
			throw new ConnectionException( "Not connected!");
		}

		final InternetAddress emailAddress = new InternetAddress( email);

		final MimeMessage envelope = new MimeMessage( session);
		try
		{
			envelope.setSubject( subject);
			envelope.setText( message);
			envelope.addRecipient( Message.RecipientType.TO, emailAddress);
		}
		catch(final MessagingException e)
		{
			throw new RuntimeException( "Smth really bad happened. Failed to compose message.", e);
		}

		try
		{
			transport.sendMessage( envelope, envelope.getAllRecipients());
		}
		catch(final SendFailedException e)
		{
			throw e;
		}
		catch(final MessagingException e)
		{
			disconnect();
			throw new ConnectionException( e);
		}
	}
}
