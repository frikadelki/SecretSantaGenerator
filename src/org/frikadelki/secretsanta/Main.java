package org.frikadelki.secretsanta;

import org.frikadelki.secretsanta.control.EmailerTemplate;
import org.frikadelki.secretsanta.email.MailSenderParameters;
import org.frikadelki.secretsanta.model.Person;

/**
 * Created with IntelliJ IDEA.
 * User: ein
 * Date: 12/10/12
 * Time: 5:11 PM
 */
public class Main
{
	private static final boolean ENABLE_LOGGING  = true;

	private static final String SMTP_HOST = "smtp.gmail.com";
	private static final String SMTP_EMAIL = "";
	private static final String SMTP_PASSWORD = "";

	private static final String MESSAGE_SUBJECT = "Сикрет Санта 2012-2013 Тест #2. Надеюсь последний.";
	private static final String MESSAGE_BODY_TEMPLATE =
					"Привет!\n" +
					"Это тестовое письмо от сикрет санты. " +
					"Оно необходимо что бы все могли увидеть что генератор и рассылка работают. " +
					"Пожалуйста убедитесь что я не спам!\n\n" +
					"На время теста вам выпала бумажка:\n" +
					"--------------------------------\n" +
					"%1$s\n" +
					"--------------------------------\n\n" +
					"Спасибо за внимание!";

	public static void main(final String[] args)
	{
		final MailSenderParameters mailSenderParameters = new MailSenderParameters( SMTP_HOST, SMTP_EMAIL, SMTP_PASSWORD);
		final EmailerTemplate emailerTemplate = new EmailerTemplate( MESSAGE_SUBJECT, MESSAGE_BODY_TEMPLATE);

		final Distributor distributor = new Distributor( ENABLE_LOGGING, mailSenderParameters, emailerTemplate);

		//----ADDING EXCEPTIONS----

		//-------------------------

		System.out.println( "Started distributing.");
		distributor.distribute();
		System.out.println( "Done. Check your emails.");
	}
}
