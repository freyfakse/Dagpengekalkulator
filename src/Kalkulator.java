
public class Kalkulator {

	// int dagsats;
	// int dagpengegrunnlag;

	public Kalkulator() {

	}

	public void Kalkuler(int G, int[] l�nn, int arbeidsdagerPer�r) {

		if (erKvalifisert()) {

			int dagpengegrunnlag = RegnUtDagpengegrunnlag(G);

			int dagsats = RegnUtDagsats(dagpengegrunnlag, arbeidsdagerPer�r);

		} else if (!erKvalifisert()) {
		}

	}

	// For � f� innvilget dagpenger m� man enten ha tjent til sammen over 3G de
	// siste 3 kalender�rene,
	// eller ha tjent over 1.5G forrige kalender�r.
	public boolean erKvalifisert() {
		if (erKvalifisertPgaFjor�retsL�nn()) {
			return true;
		}
		if (erKvalifisertPgaTreSiste�rsl�nner()) {
			return true;
		} else
			return false;
	}

	public boolean erKvalifisertPgaFjor�retsL�nn() {
		return true;
	}

	public boolean erKvalifisertPgaTreSiste�rsl�nner() {
		return false;
	}

	// Dagpengegrunnlaget er den h�yeste verdien av enten inntekten siste
	// kalender�ret,
	// eller gjennomsnittsinntekten de siste tre kalender�rene.
	// Dagpengegrunnlaget kan ikke v�re h�yere enn 6G.
	public int RegnUtDagpengegrunnlag(int G) {
		int dagpengegrunnlag = 0;
		int maksVerdiP�Dagpengegrunnlag = 6 * G;

		if (dagpengegrunnlag > maksVerdiP�Dagpengegrunnlag) {
			dagpengegrunnlag = maksVerdiP�Dagpengegrunnlag;
		}

		return dagpengegrunnlag;
	}

	// For � finne dagsatsen deler man dagpengegrunnlaget p� antall arbeidsdager i
	// �ret, rundet opp.
	public int RegnUtDagsats(int dagpengegrunnlag, int arbeidsdager) {
		int dagsats = dagpengegrunnlag / arbeidsdager;

		return dagsats;
	}
}
