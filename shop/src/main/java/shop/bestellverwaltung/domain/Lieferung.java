package shop.bestellverwaltung.domain;

import static shop.util.Constants.KEINE_ID;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Logger;

import shop.util.persistence.AbstractAuditable;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name  = Lieferung.FIND_LIEFERUNGEN_BY_LIEFERNR,
                query = "SELECT l"
                	    + " FROM Lieferung l"
			            + " WHERE l.liefernr LIKE :" + Lieferung.PARAM_LIEFERNR)
})
@NamedEntityGraphs({
	@NamedEntityGraph(name = Lieferung.GRAPH_BESTELLUNGEN,
					  attributeNodes = @NamedAttributeNode("bestellungen"))
})
public class Lieferung extends AbstractAuditable {
	private static final long serialVersionUID = 7560752199018702446L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final int LIEFERNR_LENGTH = 12;
	
	private static final String PREFIX = "Lieferung.";
	public static final String FIND_LIEFERUNGEN_BY_LIEFERNR = PREFIX + "findLieferungenByLieferNr";
	public static final String PARAM_LIEFERNR = "lieferNr";
	
	public static final String GRAPH_BESTELLUNGEN = PREFIX + "bestellungen";

	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;

	@NotNull(message = "{lieferung.liefernr.notNull}")
	@Size(max = LIEFERNR_LENGTH, message = "{lieferung.liefernr.max}")
	@Column(unique = true)
	private String liefernr;
	
	@Column(name = "transport_art", length = 3)
	@Convert(converter = TransportTypeConverter.class)
	private TransportType transportArt;

	@ManyToMany(mappedBy = "lieferungen", cascade = { PERSIST, MERGE })
	@NotEmpty(message = "{lieferung.bestellungen.notEmpty}")
	@Valid
	@XmlTransient
	private Set<Bestellung> bestellungen;

	public Lieferung() {
		super();
	}
	
	public Lieferung(String liefernr, TransportType transportArt) {
		super();
		this.liefernr = liefernr;
		this.transportArt = transportArt;
	}
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Lieferung mit ID=%d", id);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLiefernr() {
		return liefernr;
	}
	public void setLiefernr(String liefernr) {
		this.liefernr = liefernr;
	}

	public TransportType getTransportArt() {
		return transportArt;
	}

	public void setTransportArt(TransportType transportArt) {
		this.transportArt = transportArt;
	}

	public Set<Bestellung> getBestellungen() {
		return bestellungen == null ? null : Collections.unmodifiableSet(bestellungen);
	}
	
	public void setBestellungen(Set<Bestellung> bestellungen) {
		if (this.bestellungen == null) {
			this.bestellungen = bestellungen;
			return;
		}
		
		// Wiederverwendung der vorhandenen Collection
		this.bestellungen.clear();
		if (bestellungen != null) {
			this.bestellungen.addAll(bestellungen);
		}
	}
	
	public void addBestellung(Bestellung bestellung) {
		if (bestellungen == null) {
			bestellungen = new HashSet<>();
		}
		bestellungen.add(bestellung);
	}
	
	public List<Bestellung> getBestellungenAsList() {
		return bestellungen == null ? null : new ArrayList<>(bestellungen);
	}
	
	public void setBestellungenAsList(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen == null ? null : new HashSet<>(bestellungen);
	}

	@Override
	public String toString() {
		return "Lieferung [id=" + id + ", liefernr=" + liefernr + ", transportArt=" + transportArt
				+ ", " + super.toString() + ']';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((liefernr == null) ? 0 : liefernr.hashCode());
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
		final Lieferung other = (Lieferung) obj;
		
		if (liefernr == null) {
			if (other.liefernr != null) {
				return false;
			}
		}
		else if (!liefernr.equals(other.liefernr)) {
			return false;
		}

		return true;
	}
}
