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
	void TestKalkulerDagsats() {
		int forventetDagsats = 1923;// 500.000 / 260 = 1923,0769...
		int grunnbeløp = 101351;
		int[] treSisteÅrslønner = { 500000, 450000, 400000 };

		int faktiskDagsats = Kalk.KalkulerDagsats(grunnbeløp, treSisteÅrslønner);

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
		int dagpengegrunnlag = 9;
		int arbeidsdager = 260;
		int forventetDagsats = dagpengegrunnlag / arbeidsdager;
		int faktiskDagsats = Kalk.RegnUtDagsats(dagpengegrunnlag, arbeidsdager);

		assertEquals(forventetDagsats, faktiskDagsats);
	}

}
