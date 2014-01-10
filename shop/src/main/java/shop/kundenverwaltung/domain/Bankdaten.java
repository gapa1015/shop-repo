package shop.kundenverwaltung.domain;

import java.io.Serializable;

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

import shop.util.persistence.AbstractAuditable;

@Cacheable
@Entity
@Table(indexes = @Index(columnList = "kontonummer"))
public class Bankdaten extends AbstractAuditable  {

	private static final long serialVersionUID = 4004266924592845222L;

	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Long id;

	@NotNull(message = "{bank.bankname.notnull}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+(-[A-ZÄÖÜ][a-zäöü]+)?", message = "Bank.bankname.pattern")
	private String bankname;

	@NotNull(message = "{bank.blz.notnull}")
	@Pattern(regexp = "[0-9]+", message = "Bank.blz.pattern")
	private String blz;

	@NotNull(message = "{bank.kontonummer.notnull}")
	@Pattern(regexp = "[0-9]+", message = "Bank.kontonummer.pattern")
	@Size(min = 8, max = 10, message = "Bank.kontonummer.size")
	private String kontonummer;

	@OneToOne
	@JoinColumn(name = "kunde_fk", unique = true)
	@XmlTransient
	private AbstractKunde kunde;

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBlz() {
		return blz;
	}

	public void setBlz(String blz) {
		this.blz = blz;
	}

	public String getKontonummer() {
		return kontonummer;
	}

	public void setKontonummer(String kontonr) {
		this.kontonummer = kontonr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankname == null) ? 0 : bankname.hashCode());
		result = prime * result + ((blz == null) ? 0 : blz.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((kontonummer == null) ? 0 : kontonummer.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
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
		final Bankdaten other = (Bankdaten) obj;
		if (bankname == null) {
			if (other.bankname != null)
				return false;
		} 
		else if (!bankname.equals(other.bankname))
			return false;
		if (blz == null) {
			if (other.blz != null)
				return false;
		} 
		else if (!blz.equals(other.blz))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (kontonummer == null) {
			if (other.kontonummer != null)
				return false;
		} 
		else if (!kontonummer.equals(other.kontonummer))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} 
		else if (!kunde.equals(other.kunde))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bankdaten [id=" + id + ", bankname=" + bankname + ", blz="
				+ blz + ", kontonummer=" + kontonummer + ", kunde=" + kunde
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}
}
