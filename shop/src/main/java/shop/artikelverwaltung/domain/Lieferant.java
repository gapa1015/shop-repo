package shop.artikelverwaltung.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.logging.Logger;

import shop.kundenverwaltung.domain.Adresse;
import shop.util.persistence.AbstractAuditable;

@Entity
@Table(indexes = @Index(columnList = "name")) 
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = Lieferant.FIND_LIEFERANT_BY_ID, 
	query = "Select	  l" 
			+ " FROM  Lieferant l"
			+ " WHERE l.id = :id"
			+ Lieferant.PARAM_ID
			+ " ORDER BY l.id ASC"), 
	@NamedQuery(name = Lieferant.FIND_LIEFERANT_BY_NAME, 
	query = "SELECT		l"
			+ " FROM	Lieferant l"
			+ " WHERE   l.name LIKE :"
			+ Lieferant.PARAM_NAME + " ORDER BY l.id ASC")
})
@Cacheable
public class Lieferant extends AbstractAuditable {
	private static final long serialVersionUID = -491580271549710536L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String PREFIX = "Lieferant.";
	public static final String FIND_LIEFERANT_BY_ID = PREFIX + "findLieferantById";
	public static final String FIND_LIEFERANT_BY_NAME = PREFIX + "findHLieferantByName";
	
	public static final String PARAM_NAME = "name";
	public static final String PARAM_ID = "id";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;

	@NotNull (message = "{lieferant.name.notNull}")
	@Size(min = 2, message = "{lieferant.name.size}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+(-[A-ZÄÖÜ][a-zäöü]+)?", message = "{lieferant.name.pattern}")
	private String name;
	
	@OneToOne(cascade = { PERSIST, REMOVE })
	@NotNull(message = "AbstractKunde.adresse.notnull")
	@Valid
	private Adresse adresse;
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neuer Lieferant mit ID=%d ", id);
	}
	
	public Lieferant() {
		super();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Adresse getAdresse() {
	return adresse;
	}
	
	public void setAdresse(Adresse adresse) {
	this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Lieferant [id=" + id + ", name=" + name + ", adresse="
				+ adresse + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Lieferant other = (Lieferant) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} 
		else if (!adresse.equals(other.adresse))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} 
		else if (!name.equals(other.name))
			return false;
		return true;
	}

}
