package shop.bestellverwaltung.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Logger;

import shop.bestellverwaltung.domain.Lieferung;
import shop.bestellverwaltung.domain.Bestellposition;
import shop.kundenverwaltung.domain.AbstractKunde;
import shop.util.persistence.AbstractAuditable;

@XmlRootElement
@Cacheable
public class Bestellung extends AbstractAuditable {
	private static final long serialVersionUID = 308578161399323975L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	@XmlTransient
	private AbstractKunde kunde;
	
	@Transient
	private URI kundeURI;
	
	@OneToMany(fetch = EAGER, cascade = { PERSIST, REMOVE })
	@JoinColumn(name = "bestellung_fk", nullable = false)
	@NotEmpty(message = "{bestellung.bestellpositionen.notEmpty}")
	@Valid
	private Set<Bestellposition> bestellpositionen;

	@ManyToMany
	@JoinTable(name = "bestellung_lieferung",
			   joinColumns = @JoinColumn(name = "bestellung_fk"),
			                 inverseJoinColumns = @JoinColumn(name = "lieferung_fk"))
	@XmlTransient
	private Set<Lieferung> lieferungen;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	public URI getKundeURI() {
		return kundeURI;
	}

	public void setKundeURI(URI kundeURI) {
		this.kundeURI = kundeURI;
	}

	public Date getBestelldatum(Date bestelldatum) {
		return getErzeugt();
	}

	public void setBestelldatum(Date bestelldatum) {
		setErzeugt(bestelldatum);
	}

	public Bestellung() {
		super();
	}
	
	public Bestellung(Set<Bestellposition> bestellpositionen) {
		super();
		this.bestellpositionen = bestellpositionen;
	}
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Bestellung mit ID=%d", id);
	}
	
	public Set<Bestellposition> getBestellpositionen() {
		if (bestellpositionen == null) {
			return null;
		}
		
		return Collections.unmodifiableSet(bestellpositionen);
	}
	
	public void setBestellpositionen(Set<Bestellposition> bestellpositionen) {
		if (this.bestellpositionen == null) {
			this.bestellpositionen = bestellpositionen;
			return;
		}
		this.bestellpositionen.clear();
		if (bestellpositionen != null) {
			this.bestellpositionen.addAll(bestellpositionen);
		}
	}
	
	public Bestellung addBestellposition(Bestellposition bestellposition) {
		if (bestellpositionen == null) {
			bestellpositionen = new HashSet<>();
		}
		bestellpositionen.add(bestellposition);
		return this;
	}
	
	public Set<Lieferung> getLieferungen() {
		return lieferungen == null ? null : Collections.unmodifiableSet(lieferungen);
	}
	
	public void setLieferungen(Set<Lieferung> lieferungen) {
		if (this.lieferungen == null) {
			this.lieferungen = lieferungen;
			return;
		}
		this.lieferungen.clear();
		if (lieferungen != null) {
			this.lieferungen.addAll(lieferungen);
		}
	}
	
	public void addLieferung(Lieferung lieferung) {
		if (lieferungen == null) {
			lieferungen = new HashSet<>();
		}
		lieferungen.add(lieferung);
	}
	
	@XmlTransient
	public List<Lieferung> getLieferungenAsList() {
		return lieferungen == null ? null : new ArrayList<>(lieferungen);
	}

	public void setLieferungenAsList(List<Lieferung> lieferungen) {
		this.lieferungen = lieferungen == null ? null : new HashSet<>(lieferungen);
	}
	
	@Override
	public String toString() {
		final Long kundeId = kunde == null ? null : kunde.getId();
		return "Bestellung [id=" + id + ", kundeId=" + kundeId + ", kundeUri=" + kundeURI
				+ ", " + super.toString() + ']';
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
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
		Bestellung other = (Bestellung) obj;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} else if (!kunde.equals(other.kunde))
			return false;
		return true;
	}
}
