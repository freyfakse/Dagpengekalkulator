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
		LocalDate dato = LocalDate.now();
		JLabel[] faktiskeBeskrivelser = GUI.LagBeskrivelserForTekstbokser();

		assertNotNull(faktiskeBeskrivelser);
	}

}
