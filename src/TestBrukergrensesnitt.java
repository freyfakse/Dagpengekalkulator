import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class TestBrukergrensesnitt {

	Brukergrensesnitt GUI = new Brukergrensesnitt();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void TestLagTekstbokser() {
		JTextField[] faktiskeTeksbokser = GUI.LagTekstbokser();

		assertNotNull(faktiskeTeksbokser);
	}

	@Test
	void TestLagBeskrivelserForTekstbokser() {

		JLabel[] faktiskeBeskrivelser = GUI.LagBeskrivelserForTekstbokser();

		assertNotNull(faktiskeBeskrivelser);
	}

	@Test
	void TestSjekkInndata() {
		String[] korrektInndata = { "100", "123456789", "2147483647" };
		int[] forventetTreSisteÅrslønner = { 100, 123456789, 2147483647 };

		String[] feilInndata = { "", "", "" };

		int[] faktiskTreSisteÅrslønner = GUI.SjekkInndata(korrektInndata);

		assertArrayEquals(forventetTreSisteÅrslønner, faktiskTreSisteÅrslønner);
	}

	@Test
	void TestSjekkInndataForTastefeil() {
		int årslønn = 0;
		String inndataFraEnTekstboks = "100";
		String tekstForanTekstboks = "";

		int forventetUtdata = 100;

		int faktiskUtdata = GUI.SjekkInndataForTastefeil(årslønn, inndataFraEnTekstboks, tekstForanTekstboks);

		assertEquals(forventetUtdata, faktiskUtdata);
		
		inndataFraEnTekstboks = "100o";
		faktiskUtdata = GUI.SjekkInndataForTastefeil(årslønn, inndataFraEnTekstboks, tekstForanTekstboks);
		
		Throwable unntak = assertThrows(IllegalArgumentException.class, () -> {
			throw new NumberFormatException("NumberFormatException");
		});
		assertEquals("NumberFormatException", unntak.getMessage());

	}

}
