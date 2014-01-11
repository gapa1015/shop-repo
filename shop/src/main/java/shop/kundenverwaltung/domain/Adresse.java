package shop.kundenverwaltung.domain;

import static shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.logging.Logger;

import shop.util.persistence.AbstractAuditable;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Entity
@Table(indexes = @Index(columnList = "plz"))   // Zu kunde_fk wird unten ein UNIQUE Index definiert
public class Adresse extends AbstractAuditable {
	private static final long serialVersionUID = -5108148468525006134L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final int PLZ_LENGTH_MAX = 5;
	private static final int ORT_LENGTH_MIN = 2;
	private static final int ORT_LENGTH_MAX = 32;
	private static final int STRASSE_LENGTH_MIN = 2;
	private static final int STRASSE_LENGTH_MAX = 32;
	private static final int HAUSNR_LENGTH_MAX = 4;

	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;

	@NotNull(message = "{adresse.plz.notNull}")
	@Pattern(regexp = "\\d{" + PLZ_LENGTH_MAX + "}", message = "{adresse.plz}")
	@Column(length = PLZ_LENGTH_MAX)
	private String plz;

	@NotNull(message = "{adresse.ort.notNull}")
	@Size(min = ORT_LENGTH_MIN, max = ORT_LENGTH_MAX, message = "{adresse.ort.length}")
	private String ort;

	@NotNull(message = "{adresse.strasse.notNull}")
	@Size(min = STRASSE_LENGTH_MIN, max = STRASSE_LENGTH_MAX, message = "{adresse.strasse.length}")
	private String strasse;

	@Size(max = HAUSNR_LENGTH_MAX, message = "{adresse.hausnr.length}")
	private String hausnr;

	@OneToOne
	@JoinColumn(name = "kunde_fk", nullable = false, unique = true)
	//NICHT @NotNull, weil beim Anlegen ueber REST der Rueckwaertsverweis noch nicht existiert
	@XmlTransient
	private AbstractKunde kunde;
	
	public Adresse() {
		super();
	}
	
	public Adresse(String plz, String ort, String strasse, String hausnr, AbstractKunde kunde) {
		super();
		this.plz = plz;
		this.ort = ort;
		this.strasse = strasse;
		this.hausnr = hausnr;
		this.kunde = kunde;
	}
	
	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neue Adresse mit ID=%s", id);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnr() {
		return hausnr;
	}
	public void setHausnr(String hausnr) {
		this.hausnr = hausnr;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}
	public AbstractKunde getKunde() {
		return kunde;
	}
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", plz=" + plz + ", ort=" + ort + ", strasse=" + strasse + ", hausnr=" + hausnr
				+ ", " + super.toString() + ']';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hausnr == null) ? 0 : hausnr.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		result = prime * result + ((strasse == null) ? 0 : strasse.hashCode());
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
		final Adresse other = (Adresse) obj;
		
		if (plz == null) {
			if (other.plz != null) {
				return false;
			}
		}
		else if (!plz.equals(other.plz)) {
			return false;
		}
		
		if (ort == null) {
			if (other.ort != null) {
				return false;
			}
		}
		else if (!ort.equals(other.ort)) {
			return false;
		}
		
		if (strasse == null) {
			if (other.strasse != null) {
				return false;
			}
		}
		else if (!strasse.equals(other.strasse)) {
			return false;
		}
		
		if (hausnr == null) {
			if (other.hausnr != null) {
				return false;
			}
		}
		else if (!hausnr.equals(other.hausnr)) {
			return false;
		}
		
		return true;
	}
}
