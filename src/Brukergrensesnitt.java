import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Brukergrensesnitt implements DocumentListener {

	final int grunnbeløp = 101351; // Grunnbeløpet (G) per 1. mai 2020 er kr. 101351.
	final int antallRader = 6;// Unngår hardkoding
	final int antallKolonner = 2;

	LocalDate dato = LocalDate.now();
	final int ifjor = dato.getYear() - 1;
	final int forfjor = dato.getYear() - 2;
	final int forforfjor = dato.getYear() - 3;
	int[] årstall = { ifjor, forfjor, forforfjor };

	// Modus og Times New Roman skal brukes som hovedfonter for NAV - Designmanual,
	// Visuell profil for NAV
	Font navFontModus = new Font("Modus", Font.PLAIN, 14);

	KandidatInfo kandidat = new KandidatInfo();
	Kalkulator kalkulator = new Kalkulator();

	JTextField fjoråretsLønnTekstboks = new JTextField("0");
	JTextField forfjoråretsLønnTekstboks = new JTextField("0");
	JTextField forforfjoråretsLønnTekstboks = new JTextField("0");
	JTextField grunnbeløpTekstboks = new JTextField("101351");
	JTextField rettPåDagpengerTekstboks = new JTextField("Nei");
	JTextField dagsatsTekstboks = new JTextField("0");

	JFrame frame = new JFrame("Dagpenger");

	public Brukergrensesnitt() {

		JTextField[] tekstbokser = LagTekstbokser();
		JLabel[] tekstboksbeskrivelser = LagBeskrivelserForTekstbokser();

		frame.setLayout(new GridLayout(antallRader, antallKolonner));
		frame.setBounds(600, 400, 350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 0; i < antallRader; i++) {
			frame.add(tekstboksbeskrivelser[i]);
			frame.add(tekstbokser[i]);
		}

		frame.setVisible(true);
	}

	public JTextField[] LagTekstbokser() {

		grunnbeløpTekstboks.setEditable(false);
		dagsatsTekstboks.setEditable(false);
		rettPåDagpengerTekstboks.setEditable(false);

		JTextField tekstbokser[] = { fjoråretsLønnTekstboks, forfjoråretsLønnTekstboks, forforfjoråretsLønnTekstboks,
				grunnbeløpTekstboks, rettPåDagpengerTekstboks, dagsatsTekstboks };

		for (int i = 0; i < antallRader; i++) {
			tekstbokser[i].setFont(navFontModus);
		}

		// Kun de tre første boksene som trenger å kunne endres
		for (int i = 0; i < 3; i++) {
			tekstbokser[i].getDocument().addDocumentListener(this);
		}

		return tekstbokser;
	}

	public JLabel[] LagBeskrivelserForTekstbokser() {

		JLabel[] beskrivelser = { new JLabel("Lønn " + ifjor + ":"), new JLabel("Lønn " + forfjor + ":"),
				new JLabel("Lønn " + forforfjor + ":"), new JLabel("Grunnbeløp (G):"), new JLabel("Rett på dagpenger:"),
				new JLabel("Dagsats:") };

		for (int i = 0; i < antallRader; i++) {
			beskrivelser[i].setFont(navFontModus);
		}

		return beskrivelser;
	}

	public void HåndterOppdatertTekst() {

		String[] tekstboksInndata = { fjoråretsLønnTekstboks.getText(), forfjoråretsLønnTekstboks.getText(),
				forforfjoråretsLønnTekstboks.getText() };

		int[] treSisteÅrslønner = SjekkInndata(tekstboksInndata);

		kandidat.setTreSisteÅrslønner(treSisteÅrslønner);

		int dagsats = (int) kalkulator.KontrollerKvalifiseringOgKalkulerDagsats(grunnbeløp,
				kandidat.getTreSisteÅrslønner());

		if (dagsats == 0) {
			rettPåDagpengerTekstboks.setText("Nei");
		} else if (dagsats > 0) {
			rettPåDagpengerTekstboks.setText("Ja");
		}

		kandidat.setDagsats(dagsats);

		dagsatsTekstboks.setText(Integer.toString(kandidat.getDagsats()));
	}

	public int[] SjekkInndata(String[] inndataFraTekstbokser) {

		int[] treSisteÅrslønner = { 0, 0, 0 };

		for (int i = 0; i < 3; i++) {
			treSisteÅrslønner[i] = SjekkInndataForTastefeil(treSisteÅrslønner[i], inndataFraTekstbokser[i],
					"Lønn " + årstall[i]);
		}

		return treSisteÅrslønner;
	}

	// For å forhindre problemer med 0 vs. "" i tekstfeltene og fange opp ugylig
	// inntastning (NumberFormatException).
	public int SjekkInndataForTastefeil(int årslønn, String inndataFraEnTekstboks, String tekstForanTekstboks) {
		if (inndataFraEnTekstboks.equals("")) {
			årslønn = 0;
		} else {
			try {
				årslønn = Integer.parseInt(inndataFraEnTekstboks);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				Feilmelding(tekstForanTekstboks);
			}
		}
		return årslønn;
	}

	public void Feilmelding(String navnPåTekstboksMedFeil) {
		JOptionPane.showMessageDialog(frame, "Ugyldig tall er oppgitt i " + navnPåTekstboksMedFeil, "Tastefeil",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		HåndterOppdatertTekst();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		HåndterOppdatertTekst();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		HåndterOppdatertTekst();
	}

}
