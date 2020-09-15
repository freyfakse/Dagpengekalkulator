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

	final int grunnbel�p = 101351; // Grunnbel�pet (G) per 1. mai 2020 er kr. 101351.
	final int antallRader = 6;// Unng�r hardkoding
	final int antallKolonner = 2;

	LocalDate dato = LocalDate.now();
	final int ifjor = dato.getYear() - 1;
	final int forfjor = dato.getYear() - 2;
	final int forforfjor = dato.getYear() - 3;
	int[] �rstall = { ifjor, forfjor, forforfjor };

	// Modus og Times New Roman skal brukes som hovedfonter for NAV - Designmanual,
	// Visuell profil for NAV
	Font navFontModus = new Font("Modus", Font.PLAIN, 14);

	KandidatInfo kandidat = new KandidatInfo();
	Kalkulator kalkulator = new Kalkulator();

	JTextField fjor�retsL�nnTekstboks = new JTextField("0");
	JTextField forfjor�retsL�nnTekstboks = new JTextField("0");
	JTextField forforfjor�retsL�nnTekstboks = new JTextField("0");
	JTextField grunnbel�pTekstboks = new JTextField("101351");
	JTextField rettP�DagpengerTekstboks = new JTextField("Nei");
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

		grunnbel�pTekstboks.setEditable(false);
		dagsatsTekstboks.setEditable(false);
		rettP�DagpengerTekstboks.setEditable(false);

		JTextField tekstbokser[] = { fjor�retsL�nnTekstboks, forfjor�retsL�nnTekstboks, forforfjor�retsL�nnTekstboks,
				grunnbel�pTekstboks, rettP�DagpengerTekstboks, dagsatsTekstboks };

		for (int i = 0; i < antallRader; i++) {
			tekstbokser[i].setFont(navFontModus);
		}

		// Kun de tre f�rste boksene som trenger � kunne endres
		for (int i = 0; i < 3; i++) {
			tekstbokser[i].getDocument().addDocumentListener(this);
		}

		return tekstbokser;
	}

	public JLabel[] LagBeskrivelserForTekstbokser() {

		JLabel[] beskrivelser = { new JLabel("L�nn " + ifjor + ":"), new JLabel("L�nn " + forfjor + ":"),
				new JLabel("L�nn " + forforfjor + ":"), new JLabel("Grunnbel�p (G):"), new JLabel("Rett p� dagpenger:"),
				new JLabel("Dagsats:") };

		for (int i = 0; i < antallRader; i++) {
			beskrivelser[i].setFont(navFontModus);
		}

		return beskrivelser;
	}

	public void H�ndterOppdatertTekst() {

		String[] tekstboksInndata = { fjor�retsL�nnTekstboks.getText(), forfjor�retsL�nnTekstboks.getText(),
				forforfjor�retsL�nnTekstboks.getText() };

		int[] treSiste�rsl�nner = SjekkInndata(tekstboksInndata);

		kandidat.setTreSiste�rsl�nner(treSiste�rsl�nner);

		int dagsats = (int) kalkulator.KontrollerKvalifiseringOgKalkulerDagsats(grunnbel�p,
				kandidat.getTreSiste�rsl�nner());

		if (dagsats == 0) {
			rettP�DagpengerTekstboks.setText("Nei");
		} else if (dagsats > 0) {
			rettP�DagpengerTekstboks.setText("Ja");
		}

		kandidat.setDagsats(dagsats);

		dagsatsTekstboks.setText(Integer.toString(kandidat.getDagsats()));
	}

	public int[] SjekkInndata(String[] inndataFraTekstbokser) {

		int[] treSiste�rsl�nner = { 0, 0, 0 };

		for (int i = 0; i < 3; i++) {
			treSiste�rsl�nner[i] = SjekkInndataForTastefeil(treSiste�rsl�nner[i], inndataFraTekstbokser[i],
					"L�nn " + �rstall[i]);
		}

		return treSiste�rsl�nner;
	}

	// For � forhindre problemer med 0 vs. "" i tekstfeltene og fange opp ugylig
	// inntastning (NumberFormatException).
	public int SjekkInndataForTastefeil(int �rsl�nn, String inndataFraEnTekstboks, String tekstForanTekstboks) {
		if (inndataFraEnTekstboks.equals("")) {
			�rsl�nn = 0;
		} else {
			try {
				�rsl�nn = Integer.parseInt(inndataFraEnTekstboks);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				Feilmelding(tekstForanTekstboks);
			}
		}
		return �rsl�nn;
	}

	public void Feilmelding(String navnP�TekstboksMedFeil) {
		JOptionPane.showMessageDialog(frame, "Ugyldig tall er oppgitt i " + navnP�TekstboksMedFeil, "Tastefeil",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		H�ndterOppdatertTekst();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		H�ndterOppdatertTekst();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		H�ndterOppdatertTekst();
	}

}
