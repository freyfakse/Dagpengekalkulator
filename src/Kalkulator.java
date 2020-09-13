
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

			// For å få innvilget dagpenger må man enten ha tjent til sammen over 3G de
			// siste 3 kalenderårene eller ha tjent over 1.5G forrige kalenderår.
			if (ErKvalifisertPgaTreSisteÅrslønner(grunnbeløp, treSisteÅrslønner)
					|| ErKvalifisertPgaFjoråretsLønn(grunnbeløp, fjoråretsLønn)) {
				return true;
			} else
				return false;

		} else
			return false;

	}

	public boolean ErKvalifisertPgaFjoråretsLønn(int grunnbeløp, int fjoråretsLønn) {
		double nedreTerskelForKvalifisering = 1.5 * grunnbeløp;

		if (fjoråretsLønn > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;

	}

	public boolean ErKvalifisertPgaTreSisteÅrslønner(int grunnbeløp, int[] treSisteÅrslønner) {
		int nedreTerskelForKvalifisering = 3 * grunnbeløp;
		int sumTreSisteÅrslønner = treSisteÅrslønner[0] + treSisteÅrslønner[1] + treSisteÅrslønner[2];

		if (sumTreSisteÅrslønner > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;

	}

	// Dagpengegrunnlaget er den høyeste verdien av enten inntekten siste
	// kalenderåret, eller gjennomsnittsinntekten de siste tre kalenderårene.
	// Dagpengegrunnlaget kan ikke være høyere enn 6G.
	public int RegnUtDagpengegrunnlag(int grunnbeløp, int[] treSisteÅrslønner) {
		int dagpengegrunnlag;
		int maksVerdiPåDagpengegrunnlag = 6 * grunnbeløp;// Unngår hardkoding
		int høyesteÅrslønn = 0;
		int sumTreSisteÅrslønner = treSisteÅrslønner[0] + treSisteÅrslønner[1] + treSisteÅrslønner[2];
		int gjennomsnittsinntektTreSisteÅr = sumTreSisteÅrslønner / 3;

		for (int i = 0; i < treSisteÅrslønner.length; i++) {
			if (høyesteÅrslønn < treSisteÅrslønner[i]) {
				høyesteÅrslønn = treSisteÅrslønner[i];
			}
		}

		if (gjennomsnittsinntektTreSisteÅr > høyesteÅrslønn) {
			dagpengegrunnlag = gjennomsnittsinntektTreSisteÅr;

		} else
			dagpengegrunnlag = høyesteÅrslønn;

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
