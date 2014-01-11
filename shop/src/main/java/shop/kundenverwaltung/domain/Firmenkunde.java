package shop.kundenverwaltung.domain;

import static shop.kundenverwaltung.domain.AbstractKunde.FIRMENKUNDE;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@XmlRootElement
@Entity
@Cacheable
@DiscriminatorValue(FIRMENKUNDE)
public class Firmenkunde extends AbstractKunde {
	private static final long serialVersionUID = 3224665468219250145L;
}
