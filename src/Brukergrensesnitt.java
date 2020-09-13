import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Brukergrensesnitt implements DocumentListener {

	int grunnbel�p = 101351; // Grunnbel�pet (G) per 1. mai 2020 er kr 101 351.
	int antallRader = 6;// Unng�r hardkoding
	int antallKolonner = 2;

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
		LocalDate dato = LocalDate.now();
		int ifjor = dato.getYear() - 1;
		int forfjor = dato.getYear() - 2;
		int forforfjor = dato.getYear() - 3;

		JLabel[] beskrivelser = { new JLabel("L�nn " + ifjor + ":"), new JLabel("L�nn " + forfjor + ":"),
				new JLabel("L�nn " + forforfjor + ":"), new JLabel("Grunnbel�p (G):"), new JLabel("Rett p� dagpenger:"),
				new JLabel("Dagsats:") };

		for (int i = 0; i < antallRader; i++) {
			beskrivelser[i].setFont(navFontModus);
		}

		return beskrivelser;

	}

	public void H�ndterOppdatertTekst() {
		int fjor�retsL�nn, forfjor�retsL�nn, forforfjor�retsL�nn;

		// For � forhindre problemer med 0 vs. NULL i tekstfeltene
		if (fjor�retsL�nnTekstboks.getText().equals("")) {
			fjor�retsL�nn = 0;
		} else
			fjor�retsL�nn = Integer.parseInt(fjor�retsL�nnTekstboks.getText());

		if (forfjor�retsL�nnTekstboks.getText().equals("")) {
			forfjor�retsL�nn = 0;
		} else
			forfjor�retsL�nn = Integer.parseInt(forfjor�retsL�nnTekstboks.getText());

		if (forforfjor�retsL�nnTekstboks.getText().equals("")) {
			forforfjor�retsL�nn = 0;
		} else
			forforfjor�retsL�nn = Integer.parseInt(forforfjor�retsL�nnTekstboks.getText());

		int[] treSiste�rsl�nner = { fjor�retsL�nn, forfjor�retsL�nn, forforfjor�retsL�nn };

		kandidat.setTreSiste�rsl�nner(treSiste�rsl�nner);

		int dagsats = (int) kalkulator.KontrollerKvalifiseringOgKalkulerDagsats(grunnbel�p, treSiste�rsl�nner);

		if (dagsats == 0) {
			rettP�DagpengerTekstboks.setText("Nei");
		} else if (dagsats > 0) {
			rettP�DagpengerTekstboks.setText("Ja");
		}

		kandidat.setDagsats(dagsats);

		dagsatsTekstboks.setText(Integer.toString(dagsats));
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
