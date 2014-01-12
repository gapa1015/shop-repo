package shop.kundenverwaltung.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
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
import org.hibernate.validator.constraints.ScriptAssert;

import shop.bestellverwaltung.domain.Bestellung;
import shop.util.persistence.AbstractAuditable;

@ScriptAssert(lang = "javascript",
                         script = "_this.password != null && !_this.password.equals(\"\")"
                                        + " && _this.password.equals(_this.passwordWdh)",
                         message = "{kunde.password.notEqual}")
@Entity
@NamedQueries({
	@NamedQuery(name  = AbstractKunde.FIND_KUNDEN,
                query = "SELECT k"
				        + " FROM   AbstractKunde k"),
	@NamedQuery(name  = AbstractKunde.FIND_KUNDEN_ORDER_BY_ID,
		        query = "SELECT   k"
				        + " FROM  AbstractKunde k"
		                + " ORDER BY k.id"),
	@NamedQuery(name  = AbstractKunde.FIND_IDS_BY_PREFIX,
		        query = "SELECT   k.id"
		                + " FROM  AbstractKunde k"
		                + " WHERE CONCAT('', k.id) LIKE :" + AbstractKunde.PARAM_KUNDE_ID_PREFIX
		                + " ORDER BY k.id"),
	@NamedQuery(name  = AbstractKunde.FIND_KUNDEN_BY_NACHNAME,
	            query = "SELECT k"
				        + " FROM   AbstractKunde k"
	            		+ " WHERE  UPPER(k.nachname) = UPPER(:" + AbstractKunde.PARAM_KUNDE_NACHNAME + ")"),
	@NamedQuery(name  = AbstractKunde.FIND_NACHNAMEN_BY_PREFIX,
   	            query = "SELECT   DISTINCT k.nachname"
				        + " FROM  AbstractKunde k "
	            		+ " WHERE UPPER(k.nachname) LIKE UPPER(:"
	            		+ AbstractKunde.PARAM_KUNDE_NACHNAME_PREFIX + ")"),
   	@NamedQuery(name  = AbstractKunde.FIND_KUNDE_BY_EMAIL,
   	            query = "SELECT DISTINCT k"
   			            + " FROM   AbstractKunde k"
   			            + " WHERE  k.email = :" + AbstractKunde.PARAM_KUNDE_EMAIL),
    @NamedQuery(name  = AbstractKunde.FIND_KUNDEN_BY_PLZ,
	            query = "SELECT k"
				        + " FROM  AbstractKunde k"
			            + " WHERE k.adresse.plz = :" + AbstractKunde.PARAM_KUNDE_ADRESSE_PLZ),
	@NamedQuery(name = AbstractKunde.FIND_PRIVATKUNDEN_FIRMENKUNDEN,
			    query = "SELECT k"
			            + " FROM  AbstractKunde k"
			    		+ " WHERE TYPE(k) IN (Privatkunde, Firmenkunde)")
})
@NamedEntityGraphs({
		@NamedEntityGraph(name = "bestellungen", attributeNodes = @NamedAttributeNode("bestellungen"))
		})
@Table(name = "kunde", indexes = @Index(columnList = "nachname"))
@Cacheable
@Inheritance
@DiscriminatorColumn(name = "art", length = 1)
@XmlRootElement
@XmlSeeAlso({ Firmenkunde.class, Privatkunde.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Privatkunde.class, name = AbstractKunde.PRIVATKUNDE),
		@Type(value = Firmenkunde.class, name = AbstractKunde.FIRMENKUNDE) })
public abstract class AbstractKunde extends AbstractAuditable {
	private static final long serialVersionUID = -424504155716043120L;

	private static final String NAME_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+";
	private static final String PREFIX_ADEL = "(o'|von|von der|von und zu|van)?";
	
	public static final String NACHNAME_PATTERN = PREFIX_ADEL + NAME_PATTERN + "(-" + NAME_PATTERN + ")?";
	
	public static final String PRIVATKUNDE = "P";
	public static final String FIRMENKUNDE = "F";
	
	private static final String PREFIX = "AbstractKunde.";
	public static final String FIND_KUNDEN = PREFIX + "findKunden";
	public static final String FIND_KUNDEN_ORDER_BY_ID = PREFIX + "findKundenOrderById";
	public static final String FIND_IDS_BY_PREFIX = PREFIX + "findIdsByPrefix";
	public static final String FIND_KUNDEN_BY_NACHNAME = PREFIX + "findKundenByNachname";
	public static final String FIND_NACHNAMEN_BY_PREFIX = PREFIX + "findNachnamenByPrefix";
	public static final String FIND_KUNDE_BY_EMAIL = PREFIX + "findKundeByEmail";
	public static final String FIND_KUNDEN_BY_PLZ = PREFIX + "findKundenByPlz";
	public static final String FIND_KUNDEN_BY_DATE = PREFIX + "findKundenByDate";
	public static final String FIND_PRIVATKUNDEN_FIRMENKUNDEN = PREFIX + "findPrivatkundenFirmenkunden";
	
	public static final String PARAM_KUNDE_ID = "kundeId";
	public static final String PARAM_KUNDE_ID_PREFIX = "idPrefix";
	public static final String PARAM_KUNDE_NACHNAME = "nachname";
	public static final String PARAM_KUNDE_NACHNAME_PREFIX = "nachnamePrefix";
	public static final String PARAM_KUNDE_ADRESSE_PLZ = "plz";
	public static final String PARAM_KUNDE_SEIT = "seit";
	public static final String PARAM_KUNDE_EMAIL = "email";
	
	public static final String GRAPH_BESTELLUNGEN = PREFIX + "bestellungen";
	public static final String GRAPH_WARTUNGSVERTRAEGE = PREFIX + "wartungsvertraege";


	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Long id;

	@NotNull(message = "{kunde.vorname.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "kunde.vorname.pattern")
	@Size(min = 2, max = 32, message = "kunde.vorname.size")
	private String vorname;

	@NotNull(message = "{kunde.nachname.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "kunde.nachname.pattern")
	@Size(min = 2, max = 32, message = "kunde.nachname.size")
	private String nachname;

	@NotNull(message = "{kunde.adresse.notnull}")
	@Valid
	@OneToOne(cascade = { PERSIST, REMOVE })
	private Adresse adresse;

	@NotNull(message = "{kunde.geburtstag.notnull}")
	@Past
	private Date geburtstag;

	@NotNull(message = "{kunde.telefon.notnull}")
	@Pattern(regexp = "[0-9]+", message = "kunde.telefon.pattern")
	private String telefon;

	@NotNull(message = "{kunde.email.notnull}")
	@Email(message = "{kunde.email.pattern}")
	@Column(unique = true)
	private String email;

	@NotNull(message = "{kunde.bankdaten.notnull}")
	@Valid
	@OneToOne(cascade = { PERSIST, REMOVE })
	private Bankdaten bankdaten;

	@Column(length = 1)
	@Convert(converter = GeschlechtTypeConverter.class)
	private GeschlechtType geschlecht;
	
	@NotNull
	@Size(min = 7, max = 20)
    private String password;
    
    @Transient
    private String passwordWdh;

	@XmlTransient
	@OneToMany
	@JoinColumn(name = "kunde_fk", nullable = false)
	@OrderColumn(name = "idx")
	private List<Bestellung> bestellungen;

	@Transient
	private URI bestellungUri;

    @PostLoad
    protected void postLoad() {
            passwordWdh = password;
    }
	
	public void setValues(AbstractKunde k) {
		nachname = k.nachname;
		vorname = k.vorname;
		adresse = k.adresse;
		geburtstag = k.geburtstag;
		telefon = k.telefon;
		email = k.email;
		bankdaten = k.bankdaten;
		geschlecht = k.geschlecht;
		password = k.password;
		passwordWdh = k.password;
	}
    
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
	public String getPassword() {
        return password;
	}

	public void setPassword(String password) {
        this.password = password;
	}

	public String getPasswordWdh() {
        return passwordWdh;
	}

	public void setPasswordWdh(String passwordWdh) {
        this.passwordWdh = passwordWdh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		final AbstractKunde other = (AbstractKunde) obj;
		
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		}
		else if (!email.equals(other.email)) {
			return false;
		}
		
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
		if (bestellungen == null) {
			return null;
		}		
		return Collections.unmodifiableList(bestellungen);
	}

	public void setBestellungen(List<Bestellung> bestellungen) {
		if (this.bestellungen == null) {
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
