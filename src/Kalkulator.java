
public class Kalkulator {

	// int dagsats;
	// int dagpengegrunnlag;

	public Kalkulator() {

	}

	public void Kalkuler(int G, int[] lønn, int arbeidsdagerPerÅr) {

		if (erKvalifisert()) {

			int dagpengegrunnlag = RegnUtDagpengegrunnlag(G);

			int dagsats = RegnUtDagsats(dagpengegrunnlag, arbeidsdagerPerÅr);

		} else if (!erKvalifisert()) {
		}

	}

	// For å få innvilget dagpenger må man enten ha tjent til sammen over 3G de
	// siste 3 kalenderårene,
	// eller ha tjent over 1.5G forrige kalenderår.
	public boolean erKvalifisert() {
		if (erKvalifisertPgaFjoråretsLønn()) {
			return true;
		}
		if (erKvalifisertPgaTreSisteÅrslønner()) {
			return true;
		} else
			return false;
	}

	public boolean erKvalifisertPgaFjoråretsLønn() {
		return true;
	}

	public boolean erKvalifisertPgaTreSisteÅrslønner() {
		return false;
	}

	// Dagpengegrunnlaget er den høyeste verdien av enten inntekten siste
	// kalenderåret,
	// eller gjennomsnittsinntekten de siste tre kalenderårene.
	// Dagpengegrunnlaget kan ikke være høyere enn 6G.
	public int RegnUtDagpengegrunnlag(int G) {
		int dagpengegrunnlag = 0;
		int maksVerdiPåDagpengegrunnlag = 6 * G;

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
