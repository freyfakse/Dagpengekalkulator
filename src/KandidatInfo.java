
public class KandidatInfo {

	//private int grunnbel�p;// Ogs� kalt G, oppgis gjerne i multiplikater (1G, 2G, 5G osv.)
	private int antallArbeidsdagerPrAnno = 260;
	private int[] treSiste�rsl�nner = new int[3];
	private Boolean harRettP�Dagpenger;
	private int dagsats = 0;
/*
	public int getGrunnbel�p() {
		return grunnbel�p;
	}

	public void setGrunnbel�p(int grunnbel�p) {
		this.grunnbel�p = grunnbel�p;
	}
*/
	public int getAntallArbeidsdagerPrAnno() {
		return antallArbeidsdagerPrAnno;
	}

	public void setAntallArbeidsdagerPrAnno(int antallArbeidsdagerPrAnno) {
		this.antallArbeidsdagerPrAnno = antallArbeidsdagerPrAnno;
	}

	public int[] getTreSiste�rsl�nner() {
		return treSiste�rsl�nner;
	}

	public void setTreSiste�rsl�nner(int[] treSiste�rsl�nner) {
		this.treSiste�rsl�nner = treSiste�rsl�nner;
	}

	public Boolean getHarRettP�Dagpenger() {
		return harRettP�Dagpenger;
	}

	public void setHarRettP�Dagpenger(Boolean harRettP�Dagpenger) {
		this.harRettP�Dagpenger = harRettP�Dagpenger;
	}

	public int getDagsats() {
		return dagsats;
	}

	public void setDagsats(int dagsats) {
		this.dagsats = dagsats;
	}
}
