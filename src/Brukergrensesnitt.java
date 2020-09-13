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

		JTextField fjor�retsL�nnTextboks = new JTextField("0");
		JTextField forfjor�retsL�nnTextboks = new JTextField("0");
		JTextField forforfjor�retsL�nnTextboks = new JTextField("0");

		JTextField grunnbel�pTextboks = new JTextField("101351");
		grunnbel�pTextboks.setEditable(false);

		JTextField dagsatsTextboks = new JTextField("0");
		dagsatsTextboks.setEditable(false);

		JTextField tekstbokser[] = { fjor�retsL�nnTextboks, forfjor�retsL�nnTextboks, forforfjor�retsL�nnTextboks,
				grunnbel�pTextboks, dagsatsTextboks };

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

		JLabel[] beskrivelser = { new JLabel("L�nn " + ifjor + ":"), new JLabel("L�nn " + forfjor + ":"),
				new JLabel("L�nn " + forforfjor + ":"), new JLabel("Grunnbel�p (G):"), new JLabel("Dagsats:") };

		for (int i = 0; i < 5; i++) {
			beskrivelser[i].setFont(navFontModus);
		}

		return beskrivelser;

	}
}
