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
	@NamedQuery(name = Hersteller.FIND_HERSTELLER_BY_ID, 
	query = "Select h" 
			+ " FROM Hersteller h"
			+ " WHERE h.id = :id"
			+ Hersteller.PARAM_ID
			+ " ORDER BY h.id ASC"), 
	@NamedQuery(name = Hersteller.FIND_HERSTELLER_BY_NAME, 
	query = "SELECT      h"
			+ " FROM		 Hersteller h"
			+ " WHERE     h.name LIKE :"
			+ Hersteller.PARAM_NAME + " ORDER BY h.id ASC")
})
@Cacheable
public class Hersteller extends AbstractAuditable {
	private static final long serialVersionUID = -2394662794541413156L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	private static final String PREFIX = "Herstller.";
	public static final String FIND_HERSTELLER_BY_ID = PREFIX + "findHerstllerById";
	public static final String FIND_HERSTELLER_BY_NAME = PREFIX + "findHerstllerByName";
	
	public static final String PARAM_NAME = "name";
	public static final String PARAM_ID = "id";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;

	@Size(min = 2, message = "{hersteller.name.size}")
	@NotNull (message = "{hersteller.name.notNull}")
	@Pattern(regexp = "[A-Z���][a-z���]+(-[A-Z���][a-z���]+)?", message = "{artikel.hersteller.pattern}")
	private String name;
	
	@OneToOne(cascade = { PERSIST, REMOVE })
	@NotNull(message = "AbstractKunde.adresse.notnull")
	@Valid
	private Adresse adresse;
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neuer Hersteller mit ID=%d", id);
	}
	
	public Hersteller() {
		super();
	}

	public void setValues(Hersteller h) {        
	        
	      name = h.name; 
	      adresse = h.adresse;
	        
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
		final Hersteller other = (Hersteller) obj;
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

	@Override
	public String toString() {
		return "Hersteller [id=" + id + ", name=" + name + "]";
	}

}
