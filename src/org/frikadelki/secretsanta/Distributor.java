package org.frikadelki.secretsanta;

import org.frikadelki.secretsanta.control.Emailer;
import org.frikadelki.secretsanta.control.EmailerTemplate;
import org.frikadelki.secretsanta.control.Generator;
import org.frikadelki.secretsanta.email.MailSenderParameters;
import org.frikadelki.secretsanta.model.Assignment;
import org.frikadelki.secretsanta.model.Person;
import org.frikadelki.secretsanta.util.QuickLogger;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/13/12
 * Time: 3:22 PM
 */
public class Distributor
{
	private final Generator generator;
	private final Emailer emailer;

	public Distributor(final boolean enableLogging, final MailSenderParameters mailSenderParameters, final EmailerTemplate emailerTemplate)
	{
		generator = new Generator( new QuickLogger( enableLogging));
		emailer = new Emailer( mailSenderParameters, emailerTemplate);
	}

	public void addGeneratorException(final Person one, final Person two)
	{
		generator.addException( one, two);
	}

	public void distribute()
	{
		final Assignment[] assignments = generator.generate();
		emailer.sendAssignments( assignments);
	}
}
