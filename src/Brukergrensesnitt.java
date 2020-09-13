import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Brukergrensesnitt implements DocumentListener /* implements ActionListener */ {

	int grunnbeløp = 101351; // Grunnbeløpet (G) per 1. mai 2020 er kr 101 351.

	// Modus og Times New Roman skal brukes som hovedfonter for NAV - Designmanual,
	// Visuell profil for NAV
	Font navFontModus = new Font("Modus", Font.PLAIN, 14);

	KandidatInfo kandidat = new KandidatInfo();
	Kalkulator kalkulator = new Kalkulator();

	JTextField fjoråretsLønnTextboks = new JTextField("0");
	JTextField forfjoråretsLønnTextboks = new JTextField("0");
	JTextField forforfjoråretsLønnTextboks = new JTextField("0");
	JTextField grunnbeløpTextboks = new JTextField("101351");
	JTextField dagsatsTextboks = new JTextField("0");

	public Brukergrensesnitt() {

		JTextField[] tekstbokser = LagTekstbokser();
		JLabel[] tekstboksbeskrivelser = LagBeskrivelserForTekstbokser();

		JFrame frame = new JFrame("Dagpenger");
		frame.setLayout(new GridLayout(5, 2));
		frame.setBounds(600, 400, 350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 0; i < 5; i++) {
			frame.add(tekstboksbeskrivelser[i]);
			frame.add(tekstbokser[i]);
		}

		frame.setVisible(true);

	}

	public JTextField[] LagTekstbokser() {

		grunnbeløpTextboks.setEditable(false);

		dagsatsTextboks.setEditable(false);

		JTextField tekstbokser[] = { fjoråretsLønnTextboks, forfjoråretsLønnTextboks, forforfjoråretsLønnTextboks,
				grunnbeløpTextboks, dagsatsTextboks };

		for (int i = 0; i < 5; i++) {
			tekstbokser[i].setFont(navFontModus);
			// tekstbokser[i].addActionListener(this);
			
		}
		
		for(int i = 0; i < 3; i++) {
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
				new JLabel("Lønn " + forforfjor + ":"), new JLabel("Grunnbeløp (G):"), new JLabel("Dagsats:") };

		for (int i = 0; i < 5; i++) {
			beskrivelser[i].setFont(navFontModus);
		}

		return beskrivelser;

	}
	/*
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * if(e.getSource().equals(fjoråretsLønnTextboks)){
	 * System.out.println(fjoråretsLønnTextboks.getText()); }
	 * 
	 * }
	 */

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		String test = arg0.toString();
		Print(test);
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		String test = arg0.toString();
		Print(test);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		String test = arg0.toString();
		Print(test);
	}

	public void Print(String test) {
		
		System.out.println(test);
		System.out.println(fjoråretsLønnTextboks.getText() + " " + forfjoråretsLønnTextboks.getText() + " "
				+ forforfjoråretsLønnTextboks.getText());

		int[] treSisteÅrslønner = { Integer.parseInt(fjoråretsLønnTextboks.getText()),
				Integer.parseInt(forfjoråretsLønnTextboks.getText()),
				Integer.parseInt(forforfjoråretsLønnTextboks.getText()) };

		kandidat.setTreSisteÅrslønner(treSisteÅrslønner);
		
		int dagsats = (int) kalkulator.KontrollerKvalifiseringOgKalkulerDagsats(grunnbeløp, treSisteÅrslønner);
		kandidat.setDagsats(dagsats);
		
		dagsatsTextboks.setText(Integer.toString(dagsats));

		
	}

}
