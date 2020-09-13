
public class Kalkulator {

	public Kalkulator() {

	}

	public double KontrollerKvalifiseringOgKalkulerDagsats(int grunnbeløp, int[] treSisteÅrslønner) {
		double arbeidsdagerPerÅr = 260;

		if (ErKvalifisert(grunnbeløp, treSisteÅrslønner)) {

			double dagpengegrunnlag = RegnUtDagpengegrunnlag(grunnbeløp, treSisteÅrslønner);

			double dagsats = RegnUtDagsats(dagpengegrunnlag, arbeidsdagerPerÅr);

			return dagsats;

		} else {
			return 0;
		}

	}

	public boolean ErKvalifisert(int grunnbeløp, int[] treSisteÅrslønner) {
		int fjoråretsLønn = treSisteÅrslønner[0];

		// For å være kvalifisert til å få dagpenger, må man ha hatt arbeidsinntekt
		// minst det siste kalenderåret.
		if (fjoråretsLønn > 0) {

			 
			if (ErKvalifisertPgaTreSisteÅrslønner(grunnbeløp, treSisteÅrslønner)
					|| ErKvalifisertPgaFjoråretsLønn(grunnbeløp, fjoråretsLønn)) {
				return true;
			} else
				return false;

		} else
			return false;

	}

	// For å få innvilget dagpenger må man enten ha tjent til sammen over 3G de
	// siste 3 kalenderårene...
	public boolean ErKvalifisertPgaTreSisteÅrslønner(int grunnbeløp, int[] treSisteÅrslønner) {
		int nedreTerskelForKvalifisering = 3 * grunnbeløp;
		int sumTreSisteÅrslønner = treSisteÅrslønner[0] + treSisteÅrslønner[1] + treSisteÅrslønner[2];

		if (sumTreSisteÅrslønner > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;
	}
	
	//...eller ha tjent over 1.5G forrige kalenderår.
	public boolean ErKvalifisertPgaFjoråretsLønn(int grunnbeløp, int fjoråretsLønn) {
		double nedreTerskelForKvalifisering = 1.5 * grunnbeløp;

		if (fjoråretsLønn > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;
	}

	public double RegnUtDagpengegrunnlag(int grunnbeløp, int[] treSisteÅrslønner) {
		double dagpengegrunnlag = 0;
		int maksVerdiPåDagpengegrunnlag = 6 * grunnbeløp;// Unngår hardkoding
		double sumTreSisteÅrslønner = treSisteÅrslønner[0] + treSisteÅrslønner[1] + treSisteÅrslønner[2];
		double gjennomsnittsinntektTreSisteÅr = sumTreSisteÅrslønner / 3;

		// Dagpengegrunnlaget er den høyeste verdien av enten inntekten siste
		// kalenderåret, eller gjennomsnittsinntekten de siste tre kalenderårene.
		if (gjennomsnittsinntektTreSisteÅr > treSisteÅrslønner[0]) {
			dagpengegrunnlag = gjennomsnittsinntektTreSisteÅr;
		} else
			dagpengegrunnlag = treSisteÅrslønner[0];

		// Dagpengegrunnlaget kan ikke være høyere enn 6G.
		if (dagpengegrunnlag > maksVerdiPåDagpengegrunnlag) {
			dagpengegrunnlag = maksVerdiPåDagpengegrunnlag;
		}

		return dagpengegrunnlag;
	}

	// For å finne dagsatsen deler man dagpengegrunnlaget på antall arbeidsdager i
	// året, rundet opp.
	public double RegnUtDagsats(double dagpengegrunnlag, double arbeidsdager) {
		double dagsatsen = dagpengegrunnlag / arbeidsdager;
		double dagsatsenRundetOpp = Math.ceil(dagsatsen);

		return dagsatsenRundetOpp;
	}
}
