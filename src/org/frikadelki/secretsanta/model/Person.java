package org.frikadelki.secretsanta.model;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/10/12
 * Time: 6:32 PM
 */
public enum Person
{
	;

	public final String email;

	private Person(String email)
	{
		if( null == email )
		{
			throw new IllegalArgumentException("Parameter 'email' can't be null.");
		}
		this.email = email;
	}
}
