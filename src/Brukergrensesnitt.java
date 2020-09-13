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

	int grunnbel�p = 101351; // Grunnbel�pet (G) per 1. mai 2020 er kr 101 351.

	// Modus og Times New Roman skal brukes som hovedfonter for NAV - Designmanual,
	// Visuell profil for NAV
	Font navFontModus = new Font("Modus", Font.PLAIN, 14);

	KandidatInfo kandidat = new KandidatInfo();
	Kalkulator kalkulator = new Kalkulator();

	JTextField fjor�retsL�nnTextboks = new JTextField("0");
	JTextField forfjor�retsL�nnTextboks = new JTextField("0");
	JTextField forforfjor�retsL�nnTextboks = new JTextField("0");
	JTextField grunnbel�pTextboks = new JTextField("101351");
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

		grunnbel�pTextboks.setEditable(false);

		dagsatsTextboks.setEditable(false);

		JTextField tekstbokser[] = { fjor�retsL�nnTextboks, forfjor�retsL�nnTextboks, forforfjor�retsL�nnTextboks,
				grunnbel�pTextboks, dagsatsTextboks };

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

		JLabel[] beskrivelser = { new JLabel("L�nn " + ifjor + ":"), new JLabel("L�nn " + forfjor + ":"),
				new JLabel("L�nn " + forforfjor + ":"), new JLabel("Grunnbel�p (G):"), new JLabel("Dagsats:") };

		for (int i = 0; i < 5; i++) {
			beskrivelser[i].setFont(navFontModus);
		}

		return beskrivelser;

	}
	/*
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * if(e.getSource().equals(fjor�retsL�nnTextboks)){
	 * System.out.println(fjor�retsL�nnTextboks.getText()); }
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
		System.out.println(fjor�retsL�nnTextboks.getText() + " " + forfjor�retsL�nnTextboks.getText() + " "
				+ forforfjor�retsL�nnTextboks.getText());

		int[] treSiste�rsl�nner = { Integer.parseInt(fjor�retsL�nnTextboks.getText()),
				Integer.parseInt(forfjor�retsL�nnTextboks.getText()),
				Integer.parseInt(forforfjor�retsL�nnTextboks.getText()) };

		kandidat.setTreSiste�rsl�nner(treSiste�rsl�nner);
		
		int dagsats = (int) kalkulator.KontrollerKvalifiseringOgKalkulerDagsats(grunnbel�p, treSiste�rsl�nner);
		kandidat.setDagsats(dagsats);
		
		dagsatsTextboks.setText(Integer.toString(dagsats));

		
	}

}
