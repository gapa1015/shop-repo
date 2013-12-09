package shop.kundenverwaltung.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;

public class Adresse implements Serializable {
	
	private static final long serialVersionUID = 6370717829606891773L;

	@NotNull(message = "Adresse.strasse.notnull")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "Adresse.strasse.pattern")
	private String strasse;
	
	@NotEmpty(message = "Adresse.haus)nummer.notempty")
	@Size(max = 6, message = "Adresse.hausnummer.size")
	private String hausnummer;
	
	@NotNull(message = "Adresse.plz.notnull")
	@Pattern(regexp = "\\d{5} [0-9]+", message = "Adresse.plz.pattern")
	private String plz;
	
	@NotNull(message = "Adresse.stadt.notnull")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "Adresse.stadt.pattern")
	private String stadt;
	
	
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
	@Override
	public String toString() {
		return "Adresse [strasse=" + strasse + ", hausnummer=" + hausnummer
				+ ", plz=" + plz + ", stadt=" + stadt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hausnummer == null) ? 0 : hausnummer.hashCode());
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
}
