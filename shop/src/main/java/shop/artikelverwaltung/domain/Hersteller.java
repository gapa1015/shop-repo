package shop.artikelverwaltung.domain;

import java.util.List;

//import shop.kundenverwaltung.domain.Adresse;

public class Hersteller {

	private Long id;
	private String name;
//	private Adresse adresse;
	private List<Rad> raeder;

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

	public List<Rad> getReader() {
		return raeder;
	}

	public void setReader(List<Rad> reader) {
		this.raeder = reader;
	}

	public void addRad(Rad rad) {
		raeder.add(rad);
	}

//	public Adresse getAdresse() {
//		return adresse;
//	}
//
//	public void setAdresse(Adresse adresse) {
//		this.adresse = adresse;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		final Hersteller other = (Hersteller) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (raeder == null) {
			if (other.raeder != null)
				return false;
		}
		else if (!raeder.equals(other.raeder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hersteller [id=" + id + ", name=" + name + ", raeder=" + raeder
				+ "]";
	}

}
