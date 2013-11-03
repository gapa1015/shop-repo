package shop.kundenverwaltung.domain;

public class Bankdaten {
	
	private String bankname;
	private int blz;
	private int kontonummer;
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public int getBlz() {
		return blz;
	}
	public void setBlz(int blz) {
		this.blz = blz;
	}
	public int getKontonummer() {
		return kontonummer;
	}
	public void setKontonummer(int kontonummer) {
		this.kontonummer = kontonummer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankname == null) ? 0 : bankname.hashCode());
		result = prime * result + blz;
		result = prime * result + kontonummer;
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
		if (blz != other.blz)
			return false;
		if (kontonummer != other.kontonummer)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bankdaten [bankname=" + bankname + ", blz=" + blz
				+ ", kontonummer=" + kontonummer + "]";
	}
	

}
