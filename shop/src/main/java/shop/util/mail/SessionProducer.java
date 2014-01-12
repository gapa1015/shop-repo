package shop.util.mail;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.mail.Session;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Dependent
public class SessionProducer {
	@Resource(lookup = "java:jboss/mail/Default")
	@Produces
	private Session session;
}
