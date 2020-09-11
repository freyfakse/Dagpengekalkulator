
public class Kalkulator {

	public Kalkulator() {

	}

	public int KalkulerDagsats(int grunnbeløp, int[] treSisteÅrslønner) {
		int arbeidsdagerPerÅr = 260;

		if (ErKvalifisert(grunnbeløp, treSisteÅrslønner)) {

			int dagpengegrunnlag = RegnUtDagpengegrunnlag(grunnbeløp, treSisteÅrslønner);

			int dagsats = RegnUtDagsats(dagpengegrunnlag, arbeidsdagerPerÅr);

			return dagsats;

		} else {
			return 0;
		}

	}

	// For å få innvilget dagpenger må man enten ha tjent til sammen over 3G de
	// siste 3 kalenderårene,
	// eller ha tjent over 1.5G forrige kalenderår.
	public boolean ErKvalifisert(int grunnbeløp, int[] treSisteÅrslønner) {
		int fjoråretsLønn = treSisteÅrslønner[0];

		if (ErKvalifisertPgaFjoråretsLønn(grunnbeløp, fjoråretsLønn)) {
			return true;
		}
		if (ErKvalifisertPgaTreSisteÅrslønner(grunnbeløp, treSisteÅrslønner)) {
			return true;
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
	// kalenderåret,
	// eller gjennomsnittsinntekten de siste tre kalenderårene.
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
	public int RegnUtDagsats(int dagpengegrunnlag, int arbeidsdager) {
		int dagsats = dagpengegrunnlag / arbeidsdager;

		return dagsats;
	}
}
