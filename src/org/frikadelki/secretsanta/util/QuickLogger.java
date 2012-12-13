package org.frikadelki.secretsanta.util;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/13/12
 * Time: 1:46 PM
 */
public class QuickLogger
{
	private final boolean enabled;

	public QuickLogger(boolean enabled)
	{
		this.enabled = enabled;
	}

	public void log(final String s, final boolean endOfLine)
	{
		if( !enabled )
		{
			return;
		}

		if( endOfLine )
		{
			System.out.println( s);
		}
		else
		{
			System.out.print( s);
		}
	}
}
