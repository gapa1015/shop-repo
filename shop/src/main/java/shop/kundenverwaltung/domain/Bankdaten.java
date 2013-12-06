package shop.kundenverwaltung.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

public class Bankdaten implements Serializable {
	
	@NotNull
	@Pattern(regexp ="[A-Z���][a-z���]+")
	private String bankname;
	
	@NotNull
	@Pattern(regexp= "\\d{8} [0-9]+")
	private String blz;
	
	@NotNull
	@Pattern(regexp= "[0-9]+")
	@Size(min=8 , max =10)
	private String kontonummer;
	
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
	public void setKontonummer(String kontonummer) {
		this.kontonummer = kontonummer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankname == null) ? 0 : bankname.hashCode());
		result = prime * result + ((blz == null) ? 0 : blz.hashCode());
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
		Bankdaten other = (Bankdaten) obj;
		if (bankname == null) {
			if (other.bankname != null)
				return false;
		} else if (!bankname.equals(other.bankname))
			return false;
		if (blz == null) {
			if (other.blz != null)
				return false;
		} else if (!blz.equals(other.blz))
			return false;
		if (kontonummer == null) {
			if (other.kontonummer != null)
				return false;
		} else if (!kontonummer.equals(other.kontonummer))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} else if (!kunde.equals(other.kunde))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bankdaten [bankname=" + bankname + ", blz=" + blz
				+ ", kontonummer=" + kontonummer + ", kunde=" + kunde + "]";
	}
	public AbstractKunde getKunde() {
		return kunde;
	}
	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}
	

}
