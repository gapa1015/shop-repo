package shop.kundenverwaltung.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@DiscriminatorValue("F")
public class Firmenkunde extends AbstractKunde {

	private static final long serialVersionUID = 1709962112941740212L;

}
