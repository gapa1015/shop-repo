package shop.artikelverwaltung.domain;

import static shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.jboss.logging.Logger;

import shop.kundenverwaltung.domain.Adresse;
import shop.util.persistence.AbstractAuditable;

public class Hersteller extends AbstractAuditable {
	private static final long serialVersionUID = -2394662794541413156L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;

	@Size(min = 2, message = "{hersteller.name.size}")
	@NotNull (message = "{hersteller.name.notNull}")
	@Pattern(regexp = "[A-Z���][a-z���]+(-[A-Z���][a-z���]+)?", message = "{artikel.hersteller.pattern}")
	private String name;
	
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
