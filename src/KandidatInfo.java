
public class KandidatInfo {

	//private int grunnbeløp;// Også kalt G, oppgis gjerne i multiplikater (1G, 2G, 5G osv.)
	private int antallArbeidsdagerPrAnno = 260;
	private int[] treSisteÅrslønner = new int[3];
	private Boolean harRettPåDagpenger;
	private int dagsats = 0;
/*
	public int getGrunnbeløp() {
		return grunnbeløp;
	}

	public void setGrunnbeløp(int grunnbeløp) {
		this.grunnbeløp = grunnbeløp;
	}
*/
	public int getAntallArbeidsdagerPrAnno() {
		return antallArbeidsdagerPrAnno;
	}

	public void setAntallArbeidsdagerPrAnno(int antallArbeidsdagerPrAnno) {
		this.antallArbeidsdagerPrAnno = antallArbeidsdagerPrAnno;
	}

	public int[] getTreSisteÅrslønner() {
		return treSisteÅrslønner;
	}

	public void setTreSisteÅrslønner(int[] treSisteÅrslønner) {
		this.treSisteÅrslønner = treSisteÅrslønner;
	}

	public Boolean getHarRettPåDagpenger() {
		return harRettPåDagpenger;
	}

	public void setHarRettPåDagpenger(Boolean harRettPåDagpenger) {
		this.harRettPåDagpenger = harRettPåDagpenger;
	}

	public int getDagsats() {
		return dagsats;
	}

	public void setDagsats(int dagsats) {
		this.dagsats = dagsats;
	}
}
