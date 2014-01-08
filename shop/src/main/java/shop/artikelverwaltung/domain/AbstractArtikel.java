package shop.artikelverwaltung.domain;

import static shop.util.Constants.KEINE_ID;

import java.lang.invoke.MethodHandles;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.jboss.logging.Logger;

import shop.util.persistence.AbstractAuditable;

@Entity
@Table(name = "artikel", indexes = @Index(columnList = "name"))
@XmlRootElement
@XmlSeeAlso({ Ersatzteil.class, Rad.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
                @Type(value = Ersatzteil.class, name = AbstractArtikel.ERSATZTEIL),
                @Type(value = Rad.class, name = AbstractArtikel.RAD) })
@NamedQueries({
	@NamedQuery(name =AbstractArtikel.FIND_VERFUEGBARE_ARTIKEL,
			query = "SELECT a"
					+ " FROM		 AbstractArtikel a"
					+ " ORDER BY a.id ASC"),
	@NamedQuery(name = AbstractArtikel.FIND_ARTIKEL_BY_NAME,
				query = "SELECT      a"
						+ " FROM		 AbstractArtikel a"
						+ " WHERE     a.name LIKE :" +AbstractArtikel.PARAM_NAME
						+ " ORDER BY a.id ASC")
})
public abstract class AbstractArtikel extends AbstractAuditable {
	private static final long serialVersionUID = -6383194126780965236L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	private static final String PREFIX = "AbstractArtikel";
	public static final String FIND_VERFUEGBARE_ARTIKEL = PREFIX + "findVerfuegbareArtikel";
	public static final String FIND_ARTIKEL_BY_NAME = PREFIX + "findArtikelByName";
	
	public static final String ERSATZTEIL = "E";
	public static final String RAD = "R";
	
	public static final String PARAM_NAME = "name";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	@Size(min = 2, message = "{artikel.name.size}")
	@NotNull (message = "{artikel.name.notNull}")
	private String name;

	@NotNull (message = "{artikel.preis.notNull}")
	@Digits(integer = 10, fraction = 2, message = "{artikel.preis.digits}")
	private int preis;
	
	@OneToOne
	@NotNull (message = "{artikel.hersteller.notNull}")
	@Valid
	private Hersteller hersteller;

	@OneToOne
	@NotNull (message = "{artikel.lieferant.notNull}")
	@Valid
	private Lieferant lieferant;

	@PostPersist
	private void postPersist() {
		LOGGER.debugf("Neues Ersatzteil/Rad mit ID=%d", id);
	}
	
	public AbstractArtikel() {
		super();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	public Hersteller getHersteller() {
		return hersteller;
	}

	public void setHersteller(Hersteller hersteller) {
		this.hersteller = hersteller;
	}

	public Lieferant getLieferant() {
		return lieferant;
	}

	public void setLieferant(Lieferant lieferant) {
		this.lieferant = lieferant;
	}

	@Override
	public String toString() {
		return "AbstractArtikel [id=" + id + ", name=" + name + ", preis="
				+ preis + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + preis;
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
		AbstractArtikel other = (AbstractArtikel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (preis != other.preis)
			return false;
		return true;
	}
}
