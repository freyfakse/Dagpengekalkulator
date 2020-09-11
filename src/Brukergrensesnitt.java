import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Brukergrensesnitt extends JFrame{

	public Brukergrensesnitt() {
		KandidatInfo Info = new KandidatInfo();
		Kalkulator Kalkulator = new Kalkulator();

		JFormattedTextField ftf[] = new JFormattedTextField[3];
		String[] beskrivelser = new String[3];
		//JFormattedTextField ftf2 = new JFormattedTextField();
		
		
		
		JFrame frame = new JFrame("Dagpenger");
		//BoxLayout BL = new  BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//frame.setLayout(BL);
		frame.setBounds(0, 0, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		//add(ftf);
		
		// String des[] = new String[ftf.length]; // description of each field
		/*
		 * des[0] = "Date"; ftf[0] = new JFormattedTextField(new java.util.Date());
		 * 
		 * des[1] = "Integer"; ftf[1] = new JFormattedTextField(new Integer(90032221));
		 * 
		 * des[2] = "Float"; ftf[2] = new JFormattedTextField(new Float(3.14));
		 * 
		 * des[3] = "Float work-around"; // manually specify a NumberFormat ftf[3] = new
		 * JFormattedTextField(java.text.NumberFormat.getInstance());
		 * ftf[3].setValue(new Float(3.14));
		 * 
		 * des[4] = "currency"; ftf[4] = new
		 * JFormattedTextField(java.text.NumberFormat.getCurrencyInstance());
		 * ftf[4].setValue(new Float(5.99));
		 * 
		 * des[5] = "percent"; ftf[5] = new
		 * JFormattedTextField(java.text.NumberFormat.getPercentInstance());
		 * ftf[5].setValue(new Float(0.33));
		 * 
		 * JPanel myPanel = new JPanel(); BoxLayout BL = new BoxLayout(myPanel,
		 * BoxLayout.Y_AXIS); myPanel.setLayout(BL); for (int j = 0; j < ftf.length; j
		 * += 1) { JPanel borderPanel = new JPanel(new java.awt.BorderLayout());
		 * borderPanel.setBorder(new javax.swing.border.TitledBorder(des[j]));
		 * borderPanel.add(ftf[j], java.awt.BorderLayout.CENTER);
		 * myPanel.add(borderPanel); }
		 * 
		 */

		/*
		 * JFrame frame = new JFrame("Dagpenger"); frame.setBounds(0, 0, 1000, 500);
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setVisible(true);
		 */

	}
}
