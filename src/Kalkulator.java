
public class Kalkulator {

	public Kalkulator() {

	}

	public double KontrollerKvalifiseringOgKalkulerDagsats(int grunnbel�p, int[] treSiste�rsl�nner) {
		double arbeidsdagerPer�r = 260;

		if (ErKvalifisert(grunnbel�p, treSiste�rsl�nner)) {

			double dagpengegrunnlag = RegnUtDagpengegrunnlag(grunnbel�p, treSiste�rsl�nner);

			double dagsats = RegnUtDagsats(dagpengegrunnlag, arbeidsdagerPer�r);

			return dagsats;

		} else {
			return 0;
		}

	}

	public boolean ErKvalifisert(int grunnbel�p, int[] treSiste�rsl�nner) {
		int fjor�retsL�nn = treSiste�rsl�nner[0];

		// For � v�re kvalifisert til � f� dagpenger, m� man ha hatt arbeidsinntekt
		// minst det siste kalender�ret.
		if (fjor�retsL�nn > 0) {

			 
			if (ErKvalifisertPgaTreSiste�rsl�nner(grunnbel�p, treSiste�rsl�nner)
					|| ErKvalifisertPgaFjor�retsL�nn(grunnbel�p, fjor�retsL�nn)) {
				return true;
			} else
				return false;

		} else
			return false;

	}

	// For � f� innvilget dagpenger m� man enten ha tjent til sammen over 3G de
	// siste 3 kalender�rene...
	public boolean ErKvalifisertPgaTreSiste�rsl�nner(int grunnbel�p, int[] treSiste�rsl�nner) {
		int nedreTerskelForKvalifisering = 3 * grunnbel�p;
		int sumTreSiste�rsl�nner = treSiste�rsl�nner[0] + treSiste�rsl�nner[1] + treSiste�rsl�nner[2];

		if (sumTreSiste�rsl�nner > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;
	}
	
	//...eller ha tjent over 1.5G forrige kalender�r.
	public boolean ErKvalifisertPgaFjor�retsL�nn(int grunnbel�p, int fjor�retsL�nn) {
		double nedreTerskelForKvalifisering = 1.5 * grunnbel�p;

		if (fjor�retsL�nn > nedreTerskelForKvalifisering) {
			return true;
		} else
			return false;
	}

	public double RegnUtDagpengegrunnlag(int grunnbel�p, int[] treSiste�rsl�nner) {
		double dagpengegrunnlag = 0;
		int maksVerdiP�Dagpengegrunnlag = 6 * grunnbel�p;// Unng�r hardkoding
		double sumTreSiste�rsl�nner = treSiste�rsl�nner[0] + treSiste�rsl�nner[1] + treSiste�rsl�nner[2];
		double gjennomsnittsinntektTreSiste�r = sumTreSiste�rsl�nner / 3;

		// Dagpengegrunnlaget er den h�yeste verdien av enten inntekten siste
		// kalender�ret, eller gjennomsnittsinntekten de siste tre kalender�rene.
		if (gjennomsnittsinntektTreSiste�r > treSiste�rsl�nner[0]) {
			dagpengegrunnlag = gjennomsnittsinntektTreSiste�r;
		} else
			dagpengegrunnlag = treSiste�rsl�nner[0];

		// Dagpengegrunnlaget kan ikke v�re h�yere enn 6G.
		if (dagpengegrunnlag > maksVerdiP�Dagpengegrunnlag) {
			dagpengegrunnlag = maksVerdiP�Dagpengegrunnlag;
		}

		return dagpengegrunnlag;
	}

	// For � finne dagsatsen deler man dagpengegrunnlaget p� antall arbeidsdager i
	// �ret, rundet opp.
	public double RegnUtDagsats(double dagpengegrunnlag, double arbeidsdager) {
		double dagsatsen = dagpengegrunnlag / arbeidsdager;
		double dagsatsenRundetOpp = Math.ceil(dagsatsen);

		return dagsatsenRundetOpp;
	}
}
