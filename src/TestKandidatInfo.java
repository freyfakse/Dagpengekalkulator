import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestKandidatInfo {
	
	KandidatInfo kandidat = new KandidatInfo();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void TestGettersOgSetters() {
		int[] treTall = {1,2,3};
		kandidat.setDagsats(100);
		kandidat.setTreSiste�rsl�nner(treTall);
		
		assertNotNull(kandidat.getDagsats());
		assertNotNull(kandidat.getTreSiste�rsl�nner());
		assertArrayEquals(kandidat.getTreSiste�rsl�nner(),treTall);
	}

}
