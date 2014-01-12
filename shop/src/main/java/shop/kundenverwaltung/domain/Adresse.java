package shop.kundenverwaltung.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import shop.artikelverwaltung.domain.Hersteller;
import shop.util.persistence.AbstractAuditable;

@Cacheable
@Entity
@Table(indexes = @Index(columnList = "plz"))
public class Adresse extends AbstractAuditable {

	private static final long serialVersionUID = 6370717829606891773L;

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Long id;

	@NotNull(message = "{Adresse.strasse.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "Adresse.strasse.pattern")
	private String strasse;

	@NotEmpty(message = "{adresse.haus)nummer.notempty}")
	@Size(max = 6, message = "Adresse.hausnummer.size")
	private String hausnummer;

	@NotNull(message = "{adresse.plz.notnull}")
	@Pattern(regexp = "[0-9]+", message = "Adresse.plz.pattern")
	private String plz;

	@NotNull(message = "{adresse.stadt.notnull")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "Adresse.stadt.pattern")
	private String stadt;

	@OneToOne
	@JoinColumn(name = "kunde_fk", unique = true)
	@XmlTransient
	private AbstractKunde kunde;
	
	@OneToOne
	@JoinColumn(name = "hersteller_fk", unique = true)
	@XmlTransient
	private Hersteller hersteller;

	public Adresse() {
		super();
	}
	
	public void setValues(String plz, String ort, String strasse, String hausnr, AbstractKunde kunde, Hersteller hersteller) {
		this.strasse = strasse;
		this.hausnummer = hausnr;
		this.plz = plz;
		this.stadt = ort;
		this.kunde = kunde;
		this.hersteller = hersteller;
		
	}
	
	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", strasse=" + strasse + ", hausnummer="
				+ hausnummer + ", plz=" + plz + ", stadt=" + stadt + ", kunde="
				+ kunde + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hausnummer == null) ? 0 : hausnummer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		result = prime * result + ((stadt == null) ? 0 : stadt.hashCode());
		result = prime * result + ((strasse == null) ? 0 : strasse.hashCode());
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
		final Adresse other = (Adresse) obj;
		if (hausnummer == null) {
			if (other.hausnummer != null)
				return false;
		}
		else if (!hausnummer.equals(other.hausnummer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} 
		else if (!kunde.equals(other.kunde))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		} 
		else if (!plz.equals(other.plz))
			return false;
		if (stadt == null) {
			if (other.stadt != null)
				return false;
		} 
		else if (!stadt.equals(other.stadt))
			return false;
		if (strasse == null) {
			if (other.strasse != null)
				return false;
		} 
		else if (!strasse.equals(other.strasse))
			return false;
		return true;
	}

	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	public Hersteller getHersteller() {
		return hersteller;
	}

	public void setHersteller(Hersteller hersteller) {
		this.hersteller = hersteller;
	}
}
