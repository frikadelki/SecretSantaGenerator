package org.frikadelki.secretsanta.email;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/13/12
 * Time: 2:33 PM
 */
public class MailSenderParameters
{
	private static final String SESSION_PROTOCOL_SMTP = "smtp";

	private final String smtpHost;

	private final String email;
	private final String password;

	public MailSenderParameters(String smtpHost, String email, String password)
	{
		if( null == smtpHost )
		{
			throw new IllegalArgumentException("Parameter 'smtpHost' can't be null.");
		}

		if( null == email )
		{
			throw new IllegalArgumentException("Parameter 'email' can't be null.");
		}

		if( null == password )
		{
			throw new IllegalArgumentException("Parameter 'password' can't be null.");
		}

		this.smtpHost = smtpHost;
		this.email = email;
		this.password = password;
	}

	public String getSmtpHost()
	{
		return smtpHost;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public Properties makeProperties()
	{
		final Properties properties = new Properties();
		//magic
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.user", email);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		return properties;
	}

	public String getSessionProtocol()
	{
		return SESSION_PROTOCOL_SMTP;
	}
}
