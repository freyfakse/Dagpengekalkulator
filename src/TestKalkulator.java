import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TestKalkulator {

	Kalkulator Kalk = new Kalkulator();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void TestKontrollerKvalifiseringOgKalkulerDagsats() {
		int forventetDagsats = 1924;
		int grunnbel�p = 101351; //Grunnbel�pet (G) per 1. mai 2020 er kr 101 351.
		int[] treSiste�rsl�nner = { 500000, 450000, 400000 };

		double faktiskDagsats = Kalk.KontrollerKvalifiseringOgKalkulerDagsats(grunnbel�p, treSiste�rsl�nner);

		assertEquals(forventetDagsats, faktiskDagsats);
	}

	@Test
	void TestErKvalifisert() {
		int grunnbel�p = 100000;
		int[] treSiste�rsl�nner = { 500000, 450000, 400000 };

		boolean erKvalifisert = Kalk.ErKvalifisert(grunnbel�p, treSiste�rsl�nner);
		assertTrue(erKvalifisert);

	}

	@Test
	void TestErKvalifisertPgaFjor�retsL�nn() {
		int grunnbel�p = 100000;
		int fjor�retsL�nn = 500000;

		boolean erKvalifisert = Kalk.ErKvalifisertPgaFjor�retsL�nn(grunnbel�p, fjor�retsL�nn);
		assertTrue(erKvalifisert);

	}

	@Test
	void TestErKvalifisertTreSiste�rsl�nner() {
		int grunnbel�p = 100000;
		int[] treSiste�rsl�nner = { 500000, 450000, 400000 };

		boolean erKvalifisert = Kalk.ErKvalifisertPgaTreSiste�rsl�nner(grunnbel�p, treSiste�rsl�nner);
		assertTrue(erKvalifisert);

	}

	@Test
	void TestRegnUtDagpengegrunnlag() {
		int grunnbel�p = 100000;
		int[] treSiste�rsl�nner = { 500000, 450000, 400000 };
		int forventetDagpengegrunnlag = 500000;
		int faktiskDagpengegrunnlag = Kalk.RegnUtDagpengegrunnlag(grunnbel�p, treSiste�rsl�nner);

		assertEquals(forventetDagpengegrunnlag, faktiskDagpengegrunnlag,
				"Ikke samme verdi" + forventetDagpengegrunnlag + " og " + faktiskDagpengegrunnlag);

		/** Teste om dagpengegrunnlag kan overskride 6G */
		grunnbel�p = 10;
		forventetDagpengegrunnlag = 60;
		faktiskDagpengegrunnlag = Kalk.RegnUtDagpengegrunnlag(grunnbel�p, treSiste�rsl�nner);

		assertEquals(forventetDagpengegrunnlag, faktiskDagpengegrunnlag,
				"Ikke samme verdi: " + forventetDagpengegrunnlag + " og " + faktiskDagpengegrunnlag);
	}

	@Test
	void TestRegnUtDagsats() {
		double dagpengegrunnlag = 500000;
		double arbeidsdager = 260;
		double forventetDagsats = Math.ceil(dagpengegrunnlag / arbeidsdager);
		double faktiskDagsats = Kalk.RegnUtDagsats(dagpengegrunnlag, arbeidsdager);

		assertEquals(forventetDagsats, faktiskDagsats);
	}

}
