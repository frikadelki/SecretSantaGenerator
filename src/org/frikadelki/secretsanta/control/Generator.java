package org.frikadelki.secretsanta.control;

import org.frikadelki.secretsanta.util.QuickLogger;
import org.frikadelki.secretsanta.model.Assignment;
import org.frikadelki.secretsanta.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/10/12
 * Time: 6:58 PM
 */
public class Generator
{
	private final Random random = new Random( System.currentTimeMillis());

	private final HashSet<Assignment> exceptions = new HashSet<Assignment>();

	private final QuickLogger logger;

	public Generator(QuickLogger logger)
	{
		if( null == logger )
		{
			throw new IllegalArgumentException("Parameter 'logger' can't be null.");
		}
		this.logger = logger;
	}

	public void addException(final Person one, final Person two)
	{
		exceptions.add( new Assignment( one, two));
		exceptions.add( new Assignment( two, one));
	}

	public Assignment[] generate()
	{
		int counter = 0;
		while( true )
		{
			logger.log( "Generation: " + counter++, true);
			final Assignment[] assignments = makeAssignments();
			final boolean valid = validate( assignments);
			if( valid )
			{
				return assignments;
			}
		}
	}

	private boolean validate(final Assignment[] assignments)
	{
		for(final Assignment assignment : assignments)
		{
			logger.log( "Assignment: " + assignment.toString(), false);
			if( assignment.from.equals( assignment.to) )
			{
				logger.log( " -- INVALID SAME PERSON", true);
				return false;
			}

			if( exceptions.contains(assignment) )
			{
				logger.log( " -- INVALID EXCEPTION", true);
				return false;
			}

			logger.log( " -- VALID", true);
		}

		return true;
	}

	private Assignment[] makeAssignments()
	{
		final Person[] from = Person.values();
		final Person[] to = makePermutation();

		return assign( from, to);
	}

	private Assignment[] assign(final Person[] from, final Person[] to)
	{
		final Assignment[] assignments = new Assignment[from.length];
		for(int i = 0; i < from.length; i++)
		{
			assignments[i] = new Assignment( from[i], to[i]);
		}

		return assignments;
	}

	private Person[] makePermutation()
	{
		final ArrayList<Person> toShuffle = new ArrayList<Person>();
		Collections.addAll(toShuffle, Person.values());
		Collections.shuffle( toShuffle, random);

		return toShuffle.toArray( new Person[toShuffle.size()]);
	}
}
