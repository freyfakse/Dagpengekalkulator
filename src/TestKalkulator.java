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
		int grunnbeløp = 101351; //Grunnbeløpet (G) per 1. mai 2020 er kr 101 351.
		int[] treSisteÅrslønner = { 500000, 450000, 400000 };

		double faktiskDagsats = Kalk.KontrollerKvalifiseringOgKalkulerDagsats(grunnbeløp, treSisteÅrslønner);

		assertEquals(forventetDagsats, faktiskDagsats);
	}

	@Test
	void TestErKvalifisert() {
		int grunnbeløp = 100000;
		int[] treSisteÅrslønner = { 500000, 450000, 400000 };

		boolean erKvalifisert = Kalk.ErKvalifisert(grunnbeløp, treSisteÅrslønner);
		assertTrue(erKvalifisert);

	}

	@Test
	void TestErKvalifisertPgaFjoråretsLønn() {
		int grunnbeløp = 100000;
		int fjoråretsLønn = 500000;

		boolean erKvalifisert = Kalk.ErKvalifisertPgaFjoråretsLønn(grunnbeløp, fjoråretsLønn);
		assertTrue(erKvalifisert);

	}

	@Test
	void TestErKvalifisertTreSisteÅrslønner() {
		int grunnbeløp = 100000;
		int[] treSisteÅrslønner = { 500000, 450000, 400000 };

		boolean erKvalifisert = Kalk.ErKvalifisertPgaTreSisteÅrslønner(grunnbeløp, treSisteÅrslønner);
		assertTrue(erKvalifisert);

	}

	@Test
	void TestRegnUtDagpengegrunnlag() {
		int grunnbeløp = 100000;
		int[] treSisteÅrslønner = { 500000, 450000, 400000 };
		int forventetDagpengegrunnlag = 500000;
		int faktiskDagpengegrunnlag = Kalk.RegnUtDagpengegrunnlag(grunnbeløp, treSisteÅrslønner);

		assertEquals(forventetDagpengegrunnlag, faktiskDagpengegrunnlag,
				"Ikke samme verdi" + forventetDagpengegrunnlag + " og " + faktiskDagpengegrunnlag);

		/** Teste om dagpengegrunnlag kan overskride 6G */
		grunnbeløp = 10;
		forventetDagpengegrunnlag = 60;
		faktiskDagpengegrunnlag = Kalk.RegnUtDagpengegrunnlag(grunnbeløp, treSisteÅrslønner);

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
