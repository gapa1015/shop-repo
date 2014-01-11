package shop.bestellverwaltung.domain;

import static shop.util.Constants.KEINE_ID;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Logger;

import shop.kundenverwaltung.domain.AbstractKunde;
import shop.util.persistence.AbstractAuditable;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@XmlRootElement
@Entity
// TODO MySQL 5.7 kann einen Index nicht 2x anlegen
@Table(indexes = {
	@Index(columnList = "kunde_fk"),
	@Index(columnList = "erzeugt")
})
@NamedQueries({
	@NamedQuery(name  = Bestellung.FIND_BESTELLUNGEN_BY_KUNDE,
                query = "SELECT b"
			            + " FROM   Bestellung b"
						+ " WHERE  b.kunde = :" + Bestellung.PARAM_KUNDE),
	@NamedQuery(name  = Bestellung.FIND_KUNDE_BY_ID,
 			    query = "SELECT b.kunde"
                        + " FROM   Bestellung b"
  			            + " WHERE  b.id = :" + Bestellung.PARAM_ID)
})
@NamedEntityGraphs({
	@NamedEntityGraph(name = Bestellung.GRAPH_LIEFERUNGEN,
					  attributeNodes = @NamedAttributeNode("lieferungen"))
})
@Cacheable
@Transactional
public class Bestellung extends AbstractAuditable {
	private static final long serialVersionUID = 7560752199018702446L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String PREFIX = "Bestellung.";
	public static final String FIND_BESTELLUNGEN_BY_KUNDE = PREFIX + "findBestellungenByKunde";
	public static final String FIND_KUNDE_BY_ID = PREFIX + "findBestellungKundeById";
	
	public static final String PARAM_KUNDE = "kunde";
	public static final String PARAM_ID = "id";
	
	public static final String GRAPH_LIEFERUNGEN = PREFIX + "lieferungen";

	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;

	@ManyToOne
	@JoinColumn(name = "kunde_fk", nullable = false, insertable = false, updatable = false)
	@XmlTransient
	private AbstractKunde kunde;
	
	@Transient
	private URI kundeUri;

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

	@XmlElement
	public Date getDatum() {
		return getErzeugt();
	}
	
	public void setDatum(Date datum) {
		setErzeugt(datum);
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		
		// Wiederverwendung der vorhandenen Collection
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

	public AbstractKunde getKunde() {
		return kunde;
	}
	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	public URI getKundeUri() {
		return kundeUri;
	}

	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}

	public Set<Lieferung> getLieferungen() {
		return lieferungen == null ? null : Collections.unmodifiableSet(lieferungen);
	}
	
	public void setLieferungen(Set<Lieferung> lieferungen) {
		if (this.lieferungen == null) {
			this.lieferungen = lieferungen;
			return;
		}
		
		// Wiederverwendung der vorhandenen Collection
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
		return "Bestellung [id=" + id + ", kundeId=" + kundeId + ", kundeUri=" + kundeUri
				+ ", " + super.toString() + ']';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((getErzeugt() == null) ? 0 : getErzeugt().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Bestellung other = (Bestellung) obj;
		
		if (kunde == null) {
			if (other.kunde != null) {
				return false;
			}
		}
		else if (!kunde.equals(other.kunde)) {
			return false;
		}
		
		if (getErzeugt() == null) {
			if (other.getErzeugt() != null) {
				return false;
			}
		}
		else if (!getErzeugt().equals(other.getErzeugt())) {
			return false;
		}
		
		return true;
	}
}
