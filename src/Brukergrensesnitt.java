import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Brukergrensesnitt {

	// Modus og Times New Roman skal brukes som hovedfonter for NAV - Designmanual,
	// Visuell profil for NAV
	Font navFontModus = new Font("Modus", Font.PLAIN, 14);

	public Brukergrensesnitt() {
		KandidatInfo Info = new KandidatInfo();
		Kalkulator Kalkulator = new Kalkulator();

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

		JTextField fjoråretsLønnTextboks = new JTextField("0");
		JTextField forfjoråretsLønnTextboks = new JTextField("0");
		JTextField forforfjoråretsLønnTextboks = new JTextField("0");

		JTextField grunnbeløpTextboks = new JTextField("101351");
		grunnbeløpTextboks.setEditable(false);

		JTextField dagsatsTextboks = new JTextField("0");
		dagsatsTextboks.setEditable(false);

		JTextField tekstbokser[] = { fjoråretsLønnTextboks, forfjoråretsLønnTextboks, forforfjoråretsLønnTextboks,
				grunnbeløpTextboks, dagsatsTextboks };

		for (int i = 0; i < 5; i++) {
			tekstbokser[i].setFont(navFontModus);
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
}
