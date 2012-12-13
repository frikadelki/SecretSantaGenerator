package org.frikadelki.secretsanta.email;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/13/12
 * Time: 2:43 PM
 */
public class ConnectionException extends Exception
{
	public ConnectionException()
	{
	}

	public ConnectionException(final String s)
	{
		super(s);
	}

	public ConnectionException(final String s, final Throwable throwable)
	{
		super(s, throwable);
	}

	public ConnectionException(final Throwable throwable)
	{
		super(throwable);
	}
}
