package shop.kundenverwaltung.domain;



public class Kunde {
	private Long id;
	private String titel;
	private String vorname;
	private String zweitvorname;
	private String nachname;
	private String geburtstag;
	private String strasse;
	private String plz;
	private String stadt;
	private Long telefon;
	private String email;
	private Bankdaten  bankdaten;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getZweitvorname() {
		return zweitvorname;
	}
	public void setZweitvorname(String zweitvorname) {
		this.zweitvorname = zweitvorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getGeburtstag() {
		return geburtstag;
	}
	public void setGeburtstag(String geburtstag) {
		this.geburtstag = geburtstag;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
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
	public Long getTelefon() {
		return telefon;
	}
	public void setTelefon(Long telefon) {
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
	@Override
	public String toString() {
		return "Kunde [id=" + id + ", titel=" + titel + ", vorname=" + vorname
				+ ", zweitvorname=" + zweitvorname + ", nachname=" + nachname
				+ ", geburtstag=" + geburtstag + ", strasse=" + strasse
				+ ", plz=" + plz + ", stadt=" + stadt + ", telefon=" + telefon
				+ ", email=" + email + ", bankdaten=" + bankdaten + "]";
	}
}
