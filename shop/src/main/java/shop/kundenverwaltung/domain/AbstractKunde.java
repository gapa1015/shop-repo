package shop.kundenverwaltung.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.List;

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


@XmlRootElement
@XmlSeeAlso({ Firmenkunde.class, Privatkunde.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
                @Type(value = Privatkunde.class, name = "P"),
                @Type(value = Firmenkunde.class, name = "F") })
public abstract class AbstractKunde implements Serializable {

	private static final long serialVersionUID = -424504155716043120L;

	private Long id;
	
	@NotNull(message = "{AbstractKunde.vorname.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "AbstractKunde.vorname.pattern")
	@Size(min = 2, max = 32, message = "AbstractKunde.vorname.size")
	private String vorname;
	
	@NotNull(message = "{AbstractKunde.nachname.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "AbstractKunde.nachname.pattern")
	@Size(min = 2, max = 32, message = "AbstractKunde.nachname.size")
	private String nachname;
	
	@NotNull(message = "{AbstractKunde.adresse.notnull}")
	@Valid
	private Adresse adresse;
	
	@NotNull(message = "{AbstractKunde.geburtstag.notnull}")
	@Past
	private Date geburtstag;
	
	@NotNull(message = "{AbstractKunde.telefon.notnull}")
	@Pattern(regexp = "[0-9]+", message = "AbstractKunde.telefon.pattern")
	private String telefon;
	
	@NotNull(message = "{AbstractKunde.email.notnull}")
	@Email(message = "{kunde.email.pattern}")
	//@Pattern(regexp = "[\\w.%-]+@[\\w.%-]+\\.[A-Za-z] {2,4}")
	private String email;
	
	@NotNull(message = "{AbstractKunde.bankdaten.notnull}")
	@Valid
	private Bankdaten  bankdaten;
	
	@XmlTransient
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
	public Date getGeburtstag() {
		return geburtstag;
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
		final AbstractKunde other = (AbstractKunde) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		}
		else if (!adresse.equals(other.adresse))
			return false;
		if (bankdaten == null) {
			if (other.bankdaten != null)
				return false;
		}
		else if (!bankdaten.equals(other.bankdaten))
			return false;
		if (bestellungUri == null) {
			if (other.bestellungUri != null)
				return false;
		}
		else if (!bestellungUri.equals(other.bestellungUri))
			return false;
		if (bestellungen == null) {
			if (other.bestellungen != null)
				return false;
		}
		else if (!bestellungen.equals(other.bestellungen))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;
		if (geburtstag == null) {
			if (other.geburtstag != null)
				return false;
		}
		else if (!geburtstag.equals(other.geburtstag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		}
		else if (!nachname.equals(other.nachname))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		}
		else if (!telefon.equals(other.telefon))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		}
		else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Kunde [id=" + id + ", vorname=" + vorname + ", nachname="
				+ nachname + ", adresse=" + adresse + ", geburtstag="
				+ geburtstag + ", telefon=" + telefon + ", email=" + email
				+ ", bankdaten=" + bankdaten + ", bestellungen=" + bestellungen
				+ ", bestellungUri=" + bestellungUri + "]";
	}
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}
	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
	public URI getBestellungUri() {
		return bestellungUri;
	}
	public void setBestellungUri(URI bestellungUri) {
		this.bestellungUri = bestellungUri;
	}

}
