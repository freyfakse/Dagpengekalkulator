import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Brukergrensesnitt implements DocumentListener {

	int grunnbeløp = 101351; // Grunnbeløpet (G) per 1. mai 2020 er kr 101 351.
	int antallRader = 6;// Unngår hardkoding
	int antallKolonner = 2;

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

	public Brukergrensesnitt() {

		JTextField[] tekstbokser = LagTekstbokser();
		JLabel[] tekstboksbeskrivelser = LagBeskrivelserForTekstbokser();

		JFrame frame = new JFrame("Dagpenger");
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
		LocalDate dato = LocalDate.now();
		int ifjor = dato.getYear() - 1;
		int forfjor = dato.getYear() - 2;
		int forforfjor = dato.getYear() - 3;

		JLabel[] beskrivelser = { new JLabel("Lønn " + ifjor + ":"), new JLabel("Lønn " + forfjor + ":"),
				new JLabel("Lønn " + forforfjor + ":"), new JLabel("Grunnbeløp (G):"), new JLabel("Rett på dagpenger:"),
				new JLabel("Dagsats:") };

		for (int i = 0; i < antallRader; i++) {
			beskrivelser[i].setFont(navFontModus);
		}

		return beskrivelser;

	}

	public void HåndterOppdatertTekst() {
		int fjoråretsLønn, forfjoråretsLønn, forforfjoråretsLønn;

		// For å forhindre problemer med 0 vs. NULL i tekstfeltene
		if (fjoråretsLønnTekstboks.getText().equals("")) {
			fjoråretsLønn = 0;
		} else
			fjoråretsLønn = Integer.parseInt(fjoråretsLønnTekstboks.getText());

		if (forfjoråretsLønnTekstboks.getText().equals("")) {
			forfjoråretsLønn = 0;
		} else
			forfjoråretsLønn = Integer.parseInt(forfjoråretsLønnTekstboks.getText());

		if (forforfjoråretsLønnTekstboks.getText().equals("")) {
			forforfjoråretsLønn = 0;
		} else
			forforfjoråretsLønn = Integer.parseInt(forforfjoråretsLønnTekstboks.getText());

		int[] treSisteÅrslønner = { fjoråretsLønn, forfjoråretsLønn, forforfjoråretsLønn };

		kandidat.setTreSisteÅrslønner(treSisteÅrslønner);

		int dagsats = (int) kalkulator.KontrollerKvalifiseringOgKalkulerDagsats(grunnbeløp, treSisteÅrslønner);

		if (dagsats == 0) {
			rettPåDagpengerTekstboks.setText("Nei");
		} else if (dagsats > 0) {
			rettPåDagpengerTekstboks.setText("Ja");
		}

		kandidat.setDagsats(dagsats);

		dagsatsTekstboks.setText(Integer.toString(dagsats));
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
