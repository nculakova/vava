package model;

public class Rok {
	private int rok_1;
	private int rok_2;

	public Rok(int rok_1, int rok_2) {
		this.rok_1 = rok_1;
		this.rok_2 = rok_2;
	}

	public int getRok1() {
		return this.rok_1;
	}

	public int getRok2() {
		return this.rok_2;
	}

	public String toString() {
		return this.rok_1 + "/" + this.rok_2;
	}
}
