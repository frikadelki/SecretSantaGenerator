package org.frikadelki.secretsanta.model;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/10/12
 * Time: 6:32 PM
 */
public class Assignment
{
	public final Person from;
	public final Person to;

	public Assignment(Person from, Person to)
	{
		if( null == from )
		{
			throw new IllegalArgumentException("Parameter 'to' can't be null.");
		}

		if( null == to )
		{
			throw new IllegalArgumentException("Parameter 'to' can't be null.");
		}

		this.from = from;
		this.to = to;
	}

	@Override
	public boolean equals(Object o)
	{
		if( this == o )
		{
			return true;
		}
		if( o == null || getClass() != o.getClass() )
		{
			return false;
		}

		Assignment that = (Assignment)o;

		if( from != that.from )
		{
			return false;
		}
		if( to != that.to )
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = from.hashCode();
		result = 31*result + to.hashCode();
		return result;
	}

	@Override
	public String toString()
	{
		return from.toString() + " дарит подарок " + to.toString();
	}
}
