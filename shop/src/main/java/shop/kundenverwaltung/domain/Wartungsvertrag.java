package shop.kundenverwaltung.domain;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.logging.Logger;

import shop.util.persistence.AbstractAuditable;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@XmlRootElement
@Entity
// TODO MySQL 5.7 kann einen Index nicht 2x anlegen
@Table(indexes = @Index(columnList = "kunde_fk"))
@NamedQueries({
	@NamedQuery(name  = Wartungsvertrag.FIND_WARTUNGSVERTRAEGE_BY_KUNDE_ID,
                query = "SELECT w"
                        + " FROM   Wartungsvertrag w"
			            + " WHERE  w.kunde.id = :" + Wartungsvertrag.PARAM_KUNDE_ID)
})
@IdClass(Wartungsvertrag.WartungsvertragId.class)
public class Wartungsvertrag extends AbstractAuditable {
	private static final long serialVersionUID = 4024746327993094633L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final String PREFIX = "Wartungsvertrag.";
	public static final String FIND_WARTUNGSVERTRAEGE_BY_KUNDE_ID = PREFIX + "findWartungsvertraegeByKundeId";
	public static final String PARAM_KUNDE_ID = "kundeId";

	@Id
	@Basic(optional = false)
	private long nr;

	@Id
	@Temporal(DATE)
	@NotNull(message = "{wartungsvertrag.datum.notNull}")
	private Date datum;

	@NotNull(message = "{wartungsvertrag.inhalt.notNull}")
	private String inhalt;

	@ManyToOne
	@JoinColumn(name = "kunde_fk", nullable = false, insertable = false, updatable = false)
	@NotNull(message = "{wartungsvertrag.kunde.notNull}")
	@XmlTransient
	private AbstractKunde kunde;

	public Wartungsvertrag() {
		super();
	}
	
	public Wartungsvertrag(long nr, Date datum, String inhalt) {
		super();
		this.nr = nr;
		this.datum = datum == null ? null : (Date) datum.clone();
		this.inhalt = inhalt;
	}
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neuer Wartungsvertrag mit nr=%d/datum=%s", nr, datum);
	}

	public long getNr() {
		return nr;
	}

	public void setNr(long nr) {
		this.nr = nr;
	}

	public Date getDatum() {
		return datum == null ? null : (Date) datum.clone();
	}

	public void setDatum(Date datum) {
		this.datum = datum == null ? null : (Date) datum.clone();
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public String toString() {
		return "Wartungsvertrag [nr=" + nr + ", datum=" + datum + ", inhalt=" + inhalt
				+ ", " + super.toString() + ']';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inhalt == null) ? 0 : inhalt.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
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
		final Wartungsvertrag other = (Wartungsvertrag) obj;
		
		if (inhalt == null) {
			if (other.inhalt != null) {
				return false;
			}
		}
		else if (!inhalt.equals(other.inhalt)) {
			return false;
		}
		
		if (kunde == null) {
			if (other.kunde != null) {
				return false;
			}
		}
		else if (!kunde.equals(other.kunde)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Klasse f&uuml;r den zusammengesetzten Prim&auml;rschl&uuml;ssel bei Wartungsvertr&auml;gen 
	 */
	@Embeddable
	public static class WartungsvertragId implements Serializable {
		private static final long serialVersionUID = 5524909768642735623L;
		
		private long nr;
		private Date datum;
		
		@Override
		public String toString() {
			return "WartungsvertragId [nr=" + nr + ", datum=" + datum + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((datum == null) ? 0 : datum.hashCode());
			result = prime * result + (int) (nr ^ (nr >>> 32));
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
			final WartungsvertragId other = (WartungsvertragId) obj;
			
			if (nr != other.nr) {
				return false;
			}
			
			if (datum == null) {
				if (other.datum != null) {
					return false;
				}
			}
			else if (!datum.equals(other.datum)) {
				return false;
			}

			return true;
		}

		public long getNr() {
			return nr;
		}

		public void setNr(long nr) {
			this.nr = nr;
		}

		public Date getDatum() {
			return datum == null ? null : (Date) datum.clone();
		}

		public void setDatum(Date datum) {
			this.datum = datum == null ? null : (Date) datum.clone();
		}
	}
}
