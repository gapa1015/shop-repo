package shop.bestellverwaltung.domain;

import static shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;
import java.net.URI;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.logging.Logger;

import shop.artikelverwaltung.domain.AbstractArtikel;
import shop.util.persistence.AbstractAuditable;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Entity
// TODO MySQL 5.7 kann einen Index nicht 2x anlegen
@Table(indexes =  {
	@Index(columnList = "bestellung_fk"),
	@Index(columnList = "artikel_fk")
})
//@NamedQueries({
//    @NamedQuery(name  = Bestellposition.FIND_LADENHUETER,
//   	            query = "SELECT a"
//   	            	    + " FROM   Artikel a"
//   	            	    + " WHERE  a NOT IN (SELECT bp.artikel FROM Bestellposition bp)")
//})
public class Bestellposition extends AbstractAuditable {
	private static final long serialVersionUID = 2222771733641950913L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String PREFIX = "Bestellposition.";
	public static final String FIND_LADENHUETER = PREFIX + "findLadenhueter";
	private static final int ANZAHL_MIN = 1;
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "artikel_fk", nullable = false)
	@XmlTransient
	private AbstractArtikel artikel;

	@Transient
	private URI artikelUri;

	@Min(value = ANZAHL_MIN, message = "{bestellposition.anzahl.min}")
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
	
	public URI getArtikelUri() {
		return artikelUri;
	}
	
	public void setArtikelUri(URI artikelUri) {
		this.artikelUri = artikelUri;
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
			   + ", artikelUri=" + artikelUri + ", anzahl=" + anzahl
			   + ", " + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anzahl;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
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
		final Bestellposition other = (Bestellposition) obj;
		
		// Bei persistenten Bestellpositionen koennen zu verschiedenen Bestellungen gehoeren
		// und deshalb den gleichen Artikel (s.u.) referenzieren, deshalb wird Id hier beruecksichtigt
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		}
		else if (!id.equals(other.id)) {
			return false;
		}

		// Wenn eine neue Bestellung angelegt wird, dann wird jeder zu bestellende Artikel
		// genau 1x referenziert (nicht zu verwechseln mit der "anzahl")
		if (artikel == null) {
			if (other.artikel != null) {
				return false;
			}
		}
		else if (!artikel.equals(other.artikel)) {
			return false;
		}
		
		return true;
	}
}
