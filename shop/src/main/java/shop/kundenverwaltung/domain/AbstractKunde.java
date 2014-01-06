package shop.kundenverwaltung.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.validator.constraints.Email;

import shop.bestellverwaltung.domain.Bestellung;

@Entity
@Table(indexes = @Index(columnList = "nachname"))
@XmlRootElement
@XmlSeeAlso({ Firmenkunde.class, Privatkunde.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
                @Type(value = Privatkunde.class, name = "P"),
                @Type(value = Firmenkunde.class, name = "F") })
public abstract class AbstractKunde implements Serializable {
	private static final long serialVersionUID = -424504155716043120L;

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Long id;
	
	@NotNull(message = "{kunde.vorname.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "AbstractKunde.vorname.pattern")
	@Size(min = 2, max = 32, message = "AbstractKunde.vorname.size")
	private String vorname;
	
	@NotNull(message = "{kunde.nachname.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "AbstractKunde.nachname.pattern")
	@Size(min = 2, max = 32, message = "AbstractKunde.nachname.size")
	private String nachname;
	
	@NotNull(message = "{kunde.adresse.notnull}")
	@Valid
	@OneToOne(mappedBy = "kunde")
	private Adresse adresse;
	
	@NotNull(message = "{kunde.geburtstag.notnull}")
	@Past
	private Date geburtstag;
	
	@NotNull(message = "{kunde.telefon.notnull}")
	@Pattern(regexp = "[0-9]+", message = "AbstractKunde.telefon.pattern")
	private String telefon;
	
	@NotNull(message = "{kunde.email.notnull}")
	@Email(message = "{kunde.email.pattern}")
	//@Pattern(regexp = "[\\w.%-]+@[\\w.%-]+\\.[A-Za-z] {2,4}")
	@Column(unique = true)
	private String email;
	
	@NotNull(message = "{kunde.bankdaten.notnull}")
	@Valid
	@OneToOne(mappedBy = "kunde")
	private Bankdaten  bankdaten;
	
	@Column(length = 1)
	@Convert(converter = GeschlechtTypeConverter.class)
	private GeschlechtType geschlecht;
	
	@XmlTransient
	@OneToMany
	@JoinColumn(name = "kunde_fk", nullable = false)
	@OrderColumn(name = "idx")
	private List<Bestellung> bestellungen;
	
	private URI bestellungUri;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public Date getGeburtstag(Date geburtstag) {
		return this.geburtstag = new Date(geburtstag.getTime());
	}
	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = new Date(geburtstag.getTime());
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Bankdaten getBankdaten() {
		return bankdaten;
	}
	public void setBankdaten(Bankdaten bankdaten) {
		this.bankdaten = bankdaten;
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
		result = prime * result
				+ ((bankdaten == null) ? 0 : bankdaten.hashCode());
		result = prime * result
				+ ((bestellungUri == null) ? 0 : bestellungUri.hashCode());
		result = prime * result
				+ ((bestellungen == null) ? 0 : bestellungen.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((geburtstag == null) ? 0 : geburtstag.hashCode());
		result = prime * result
				+ ((geschlecht == null) ? 0 : geschlecht.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
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
		AbstractKunde other = (AbstractKunde) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (bankdaten == null) {
			if (other.bankdaten != null)
				return false;
		} else if (!bankdaten.equals(other.bankdaten))
			return false;
		if (bestellungUri == null) {
			if (other.bestellungUri != null)
				return false;
		} else if (!bestellungUri.equals(other.bestellungUri))
			return false;
		if (bestellungen == null) {
			if (other.bestellungen != null)
				return false;
		} else if (!bestellungen.equals(other.bestellungen))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (geburtstag == null) {
			if (other.geburtstag != null)
				return false;
		} else if (!geburtstag.equals(other.geburtstag))
			return false;
		if (geschlecht != other.geschlecht)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		} else if (!nachname.equals(other.nachname))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AbstractKunde [id=" + id + ", vorname=" + vorname
				+ ", nachname=" + nachname + ", adresse=" + adresse
				+ ", geburtstag=" + geburtstag + ", telefon=" + telefon
				+ ", email=" + email + ", bankdaten=" + bankdaten
				+ ", geschlecht=" + geschlecht + ", bestellungen="
				+ bestellungen + ", bestellungUri=" + bestellungUri + "]";
	}
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}
	public void setBestellungen(List<Bestellung> bestellungen) {
		if(this.bestellungen == null) {
		this.bestellungen = bestellungen;
		return;
		}
		this.bestellungen.clear();
		if (bestellungen != null) {
			this.bestellungen.addAll(bestellungen);
		}
	}
	public AbstractKunde addBestellung(Bestellung bestellung) {
		if (bestellungen == null) {
			bestellungen = new ArrayList<>();
		}
		bestellungen.add(bestellung);
		return this;
	}
	public URI getBestellungUri() {
		return bestellungUri;
	}
	public void setBestellungUri(URI bestellungUri) {
		this.bestellungUri = bestellungUri;
	}
	public GeschlechtType getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(GeschlechtType geschlecht) {
		this.geschlecht = geschlecht;
	}

}
