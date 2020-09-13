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
		kandidat.setTreSisteÅrslønner(treTall);
		
		assertNotNull(kandidat.getDagsats());
		assertNotNull(kandidat.getTreSisteÅrslønner());
		assertArrayEquals(kandidat.getTreSisteÅrslønner(),treTall);
	}

}
