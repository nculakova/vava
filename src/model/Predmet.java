package model;

public class Predmet {
	private int id;
	private String nazov;
	private String kod;
	private int pocet_studentov;
	private double a;
	private double b;
	private double c;
	private double d;
	private double e;
	private double fx;

	public Predmet() {
		this.pocet_studentov = 0;
		this.a = 0;
		this.b = 0;
		this.c = 0;
		this.d = 0;
		this.e = 0;
		this.fx = 0;
	}

	public Predmet(int id, String nazov, String kod) {
		setId(id);
		setNazov(nazov);
		setKod(kod);
	}

	public Predmet(int id, String nazov) {
		setId(id);
		setNazov(nazov);
	}

	public Predmet(String kod, String nazov) {
		setKod(kod);
		setNazov(nazov);
	}

	public Predmet(String nazov) {
		setNazov(nazov);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public void setPocetStudentov(int pocet_studentov) {
		this.pocet_studentov = pocet_studentov;
	}

	public void setZnamky(Double[] percenta) {
		this.a = percenta[0];
		this.b = percenta[1];
		this.c = percenta[2];
		this.d = percenta[3];
		this.e = percenta[4];
		this.fx = percenta[5];
	}

	public int getId() {
		return this.id;
	}

	public String getNazov() {
		return this.nazov;
	}

	public String getKod() {
		return this.kod;
	}

	public int getPocetStudentov() {
		return this.pocet_studentov;
	}

	public double getA() {
		return this.a;
	}

	public double getB() {
		return this.b;
	}

	public double getC() {
		return this.c;
	}

	public double getD() {
		return this.d;
	}

	public double getE() {
		return this.e;
	}

	public double getFx() {
		return this.fx;
	}

	public String toString() {
		return this.getKod() + " " + this.getNazov() + " " + this.getPocetStudentov() + " " + this.getA();
	}
}
