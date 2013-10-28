package shop.artikelverwaltung;

import java.util.List;

public class Lieferant {

	private Long id;
	private String nmae;
	private List<Rad> raeder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmae() {
		return nmae;
	}

	public void setNmae(String nmae) {
		this.nmae = nmae;
	}

	public List<Rad> getRaeder() {
		return raeder;
	}

	public void setRaeder(List<Rad> raeder) {
		this.raeder = raeder;
	}

	public void addRad(Rad rad) {
		raeder.add(rad);
	}

	@Override
	public String toString() {
		return "Lieferant [id=" + id + ", nmae=" + nmae + ", raeder=" + raeder
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nmae == null) ? 0 : nmae.hashCode());
		result = prime * result + ((raeder == null) ? 0 : raeder.hashCode());
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
		Lieferant other = (Lieferant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nmae == null) {
			if (other.nmae != null)
				return false;
		} else if (!nmae.equals(other.nmae))
			return false;
		if (raeder == null) {
			if (other.raeder != null)
				return false;
		} else if (!raeder.equals(other.raeder))
			return false;
		return true;
	}
}
