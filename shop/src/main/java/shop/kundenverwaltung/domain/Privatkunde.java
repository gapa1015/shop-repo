package shop.kundenverwaltung.domain;

import static shop.kundenverwaltung.domain.AbstractKunde.PRIVATKUNDE;
import static javax.persistence.FetchType.EAGER;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@XmlRootElement
@Entity
@DiscriminatorValue(PRIVATKUNDE)
@Cacheable
public class Privatkunde extends AbstractKunde {
	private static final long serialVersionUID = 133152931415808605L;
	
	@Column(length = 2)
	//@Convert(converter = FamilienstandTypeConverter.class)
	private FamilienstandType familienstand = FamilienstandType.VERHEIRATET;
	
	@Column(length = 1)
	private GeschlechtType geschlecht = GeschlechtType.WEIBLICH;
	
	@ElementCollection(fetch = EAGER)
	@CollectionTable(name = "kunde_hobby",
	                 joinColumns = @JoinColumn(name = "kunde_fk", nullable = false),
	                 uniqueConstraints =  @UniqueConstraint(columnNames = { "kunde_fk", "hobby" }),
	                 indexes = @Index(columnList = "kunde_fk"))
	@Column(table = "kunde_hobby", name = "hobby", length = 2, nullable = false)
	private Set<HobbyType> hobbies;

	public FamilienstandType getFamilienstand() {
		return familienstand;
	}
	public void setFamilienstand(FamilienstandType familienstand) {
		this.familienstand = familienstand;
	}

	public GeschlechtType getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(GeschlechtType geschlecht) {
		this.geschlecht = geschlecht;
	}
	public Set<HobbyType> getHobbies() {
		return hobbies;
	}
	
	public void setHobbies(Set<HobbyType> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Privatkunde [" + super.toString() + ", familienstand=" + familienstand
			   + ", geschlecht=" + geschlecht + ", hobbies=" + hobbies + ']';
	}
}
