package shop.bestellverwaltung.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

import shop.bestellverwaltung.domain.Bestellposition;
import shop.bestellverwaltung.domain.Bestellung;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.util.interceptor.Log;
import shop.util.mail.AbsenderMail;
import shop.util.mail.AbsenderName;

@ApplicationScoped
@Log
public class BestellungObserver implements Serializable {
	private static final long serialVersionUID = -1567643645881819340L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	private static final String NEWLINE = System.getProperty("line.separator");
	
	@Inject
	private transient Session session;
	
	@Inject
	@AbsenderMail
	private String absenderMail;
	
	@Inject
	@AbsenderName
	private String absenderName;

	@PostConstruct
	private void postConstruct() {
		if (absenderMail == null) {
			LOGGER.warn("Der Absender fuer Bestellung-Emails ist nicht gesetzt.");
			return;
		}
		LOGGER.infof("Absender fuer Bestellung-Emails: %s <%s>", absenderName, absenderMail);
	}
	
	public void onCreateBestellung(@Observes @NeueBestellung Bestellung bestellung) {
		final AbstractKunde kunde = bestellung.getKunde();
		final String empfaengerMail = kunde.getEmail();
		if (absenderMail == null || empfaengerMail == null) {
			return;
		}
		final String vorname = kunde.getVorname() == null ? "" : kunde.getVorname();
		final String empfaengerName = vorname + " " + kunde.getNachname();
		
		final MimeMessage message = new MimeMessage(session);

		try {
			final InternetAddress absenderObj = new InternetAddress(absenderMail, absenderName);
			message.setFrom(absenderObj);
			
			final InternetAddress empfaenger = new InternetAddress(empfaengerMail, empfaengerName);
			message.setRecipient(RecipientType.TO, empfaenger);   // RecipientType: TO, CC, BCC

			message.setSubject("Neue Bestellung Nr. " + bestellung.getId());
			
			final StringBuilder sb = new StringBuilder(256);
			sb.append("<h3>Neue Bestellung Nr. <b>" + bestellung.getId() + "</b></h3>" + NEWLINE);
			for (Bestellposition bp : bestellung.getBestellpositionen()) {
				sb.append(bp.getAnzahl() + "\t" + bp.getArtikel().getName() + "<br/>" + NEWLINE);
			}
			final String text = sb.toString();
			LOGGER.trace(text);
			message.setContent(text, "text/html;charset=iso-8859-1");

			Transport.send(message);
		}
		catch (MessagingException | UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
			return;
		}
	}
}
