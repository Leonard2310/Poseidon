package poseidon.entity;

public class Porto {
	private String citta;

	public Porto(String citta) {
		super();
		this.citta = citta;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	@Override
	public boolean equals(Object o) {
		Porto p = (Porto)o;
		return (this.citta == p.citta);
	}
	
}
