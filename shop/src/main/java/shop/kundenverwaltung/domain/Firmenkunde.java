package shop.kundenverwaltung.domain;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@DiscriminatorValue("F")
public class Firmenkunde extends AbstractKunde {

	private static final long serialVersionUID = 1709962112941740212L;
	
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöü]+", message = "kunde.firmennamen.pattern")
	@Size(min = 2, max = 32, message = "kunde.firmennamen.size")
	private String firmenname;
	
	private BigDecimal rabatt;
	
	private BigDecimal umsatz;
	
	public String getFirmenname() {
		return firmenname;
	}
	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}
	public BigDecimal getRabatt() {
		return rabatt;
	}
	public void setRabatt(BigDecimal rabatt) {
		this.rabatt = rabatt;
	}
	public BigDecimal getUmsatz() {
		return umsatz;
	}
	public void setUmsatz(BigDecimal umsatz) {
		this.umsatz = umsatz;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((firmenname == null) ? 0 : firmenname.hashCode());
		result = prime * result + ((rabatt == null) ? 0 : rabatt.hashCode());
		result = prime * result + ((umsatz == null) ? 0 : umsatz.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Firmenkunde other = (Firmenkunde) obj;
		if (firmenname == null) {
			if (other.firmenname != null)
				return false;
		}
		else if (!firmenname.equals(other.firmenname))
			return false;
		if (rabatt == null) {
			if (other.rabatt != null)
				return false;
		} 
		else if (!rabatt.equals(other.rabatt))
			return false;
		if (umsatz == null) {
			if (other.umsatz != null)
				return false;
		} 
		else if (!umsatz.equals(other.umsatz))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Firmenkunde [firmenname=" + firmenname + ", rabatt=" + rabatt
				+ ", umsatz=" + umsatz + "]";
	}

}
