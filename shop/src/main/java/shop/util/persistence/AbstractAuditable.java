package shop.util.persistence;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public abstract class AbstractAuditable implements Serializable {
	private static final long serialVersionUID = -6592056535978766351L;

	@Temporal(TIMESTAMP)
	@Basic(optional = false)
	@XmlTransient
	private Date erzeugt;

	@Temporal(TIMESTAMP)
	@Basic(optional = false)
	@XmlTransient
	private Date aktualisiert;
	
	@PrePersist
	protected void prePersist() {
		erzeugt = new Date();
		aktualisiert = new Date();
	}
	
	@PreUpdate
	protected void preUpdate() {
		aktualisiert = new Date();
	}
	
	public Date getErzeugt() {
		return erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public void setErzeugt(Date erzeugt) {
		this.erzeugt = erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public Date getAktualisiert() {
		return aktualisiert == null ? null : (Date) aktualisiert.clone();
	}

	public void setAktualisiert(Date aktualisiert) {
		this.aktualisiert = aktualisiert == null ? null : (Date) aktualisiert.clone();
	}

	@Override
	public String toString() {
		return "AbstractAuditable [erzeugt=" + erzeugt + ", aktualisiert=" + aktualisiert + "]";
	}
}
