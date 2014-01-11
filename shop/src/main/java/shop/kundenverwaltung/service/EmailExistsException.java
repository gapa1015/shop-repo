package shop.kundenverwaltung.service;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public class EmailExistsException extends AbstractKundeServiceException {
	private static final long serialVersionUID = 4867667611097919943L;
	
	private static final String MESSAGE_KEY = "kunde.emailExists";
	private final String email;
	
	public EmailExistsException(String email) {
		super("Die Email-Adresse " + email + " existiert bereits");
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
}
