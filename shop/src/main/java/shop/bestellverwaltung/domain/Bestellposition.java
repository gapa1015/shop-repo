package shop.bestellverwaltung.domain;

import static shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;
import java.net.URI;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.logging.Logger;

import shop.util.persistence.AbstractAuditable;
import shop.artikelverwaltung.domain.AbstractArtikel;

public class Bestellposition extends AbstractAuditable {
	private static final long serialVersionUID = -6300237680332104210L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "artikel_fk", nullable = false)
	@XmlTransient
	private AbstractArtikel artikel;
	
	@Transient
	private URI artikelURI;
	
	@Min(value = 1, message = "{bestellposition.anzahl.min}")
	@Basic(optional = false)
	private short anzahl;
	
	public Bestellposition() {
		super();
	}
	
	public Bestellposition(AbstractArtikel artikel) {
		super();
		this.artikel = artikel;
		this.anzahl = 1;
	}
	
	public Bestellposition(AbstractArtikel artikel, short anzahl) {
		super();
		this.artikel = artikel;
		this.anzahl = anzahl;
	}
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Bestellposition mit ID=%d", id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractArtikel getArtikel() {
		return artikel;
	}

	public void setArtikel(AbstractArtikel artikel) {
		this.artikel = artikel;
	}

	public URI getArtikelURI() {
		return artikelURI;
	}

	public void setArtikelURI(URI artikelURI) {
		this.artikelURI = artikelURI;
	}

	public short getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(short anzahl) {
		this.anzahl = anzahl;
	}
	
	@Override
	public String toString() {
		final Long artikelId = artikel == null ? null : artikel.getId();
		return "Bestellposition [id=" + id + ", artikel=" + artikelId
			   + ", artikelUri=" + artikelURI + ", anzahl=" + anzahl
			   + ", " + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Bestellposition other = (Bestellposition) obj;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
