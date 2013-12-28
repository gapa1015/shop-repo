package shop.kundenverwaltung.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@DiscriminatorValue("P")
public class Privatkunde extends AbstractKunde {

	private static final long serialVersionUID = -5633381676215754675L;

}
