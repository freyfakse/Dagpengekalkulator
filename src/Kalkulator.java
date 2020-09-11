
public class Kalkulator {

	public Kalkulator() {

	}

	public int KalkulerDagsats(int grunnbel�p, int[] treSiste�rsl�nner) {
		int arbeidsdagerPer�r = 260;

		if (ErKvalifisert(grunnbel�p, treSiste�rsl�nner)) {

			int dagpengegrunnlag = RegnUtDagpengegrunnlag(grunnbel�p, treSiste�rsl�nner);

			int dagsats = RegnUtDagsats(dagpengegrunnlag, arbeidsdagerPer�r);

			return dagsats;

		} else {
			return 0;
		}

	}

	// For � f� innvilget dagpenger m� man enten ha tjent til sammen over 3G de
	// siste 3 kalender�rene,
	// eller ha tjent over 1.5G forrige kalender�r.
	public boolean ErKvalifisert(int grunnbel�p, int[] treSiste�rsl�nner) {
		int fjor�retsL�nn = treSiste�rsl�nner[0];

		if (ErKvalifisertPgaFjor�retsL�nn(grunnbel�p, fjor�retsL�nn)) {
			return true;
		}
		if (ErKvalifisertPgaTreSiste�rsl�nner(grunnbel�p, treSiste�rsl�nner)) {
			return true;
		} else
			return false;
	}

	public boolean ErKvalifisertPgaFjor�retsL�nn(int grunnbel�p, int fjor�retsL�nn) {
		double nedreTerskelForKvalifisering = 1.5 * grunnbel�p;

		if (fjor�retsL�nn > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;

	}

	public boolean ErKvalifisertPgaTreSiste�rsl�nner(int grunnbel�p, int[] treSiste�rsl�nner) {
		int nedreTerskelForKvalifisering = 3 * grunnbel�p;
		int sumTreSiste�rsl�nner = treSiste�rsl�nner[0] + treSiste�rsl�nner[1] + treSiste�rsl�nner[2];

		if (sumTreSiste�rsl�nner > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;

	}

	// Dagpengegrunnlaget er den h�yeste verdien av enten inntekten siste
	// kalender�ret,
	// eller gjennomsnittsinntekten de siste tre kalender�rene.
	// Dagpengegrunnlaget kan ikke v�re h�yere enn 6G.
	public int RegnUtDagpengegrunnlag(int grunnbel�p, int[] treSiste�rsl�nner) {
		int dagpengegrunnlag;
		int maksVerdiP�Dagpengegrunnlag = 6 * grunnbel�p;// Unng�r hardkoding
		int h�yeste�rsl�nn = 0;
		int sumTreSiste�rsl�nner = treSiste�rsl�nner[0] + treSiste�rsl�nner[1] + treSiste�rsl�nner[2];
		int gjennomsnittsinntektTreSiste�r = sumTreSiste�rsl�nner / 3;

		for (int i = 0; i < treSiste�rsl�nner.length; i++) {
			if (h�yeste�rsl�nn < treSiste�rsl�nner[i]) {
				h�yeste�rsl�nn = treSiste�rsl�nner[i];
			}
		}

		if (gjennomsnittsinntektTreSiste�r > h�yeste�rsl�nn) {
			dagpengegrunnlag = gjennomsnittsinntektTreSiste�r;

		} else
			dagpengegrunnlag = h�yeste�rsl�nn;

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
