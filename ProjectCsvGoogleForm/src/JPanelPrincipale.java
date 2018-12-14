



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class JPanelPrincipale extends JPanel{

	JTextArea textCSV;
	JTextArea textView;
	
	GestoreCsv gestore_csv;
	
	public JPanelPrincipale() {

		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		panel.add (Box.createVerticalStrut(10));
		textCSV = new JTextArea ();
		textCSV.setToolTipText(
				"Incollare con CTRL-V il contenuto del file csv "
				+ "ricavato dal proprio Modulo di Google in Google Drive");
		
		gestore_csv = new GestoreCsv();
		
		JScrollPane scroll = new JScrollPane(textCSV);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		panel.add (Box.createVerticalStrut(10));
		
		JButton button =new JButton ("Vedi matrice");

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String matrice = 
						gestore_csv.getMatrice(
								gestore_csv.getMatriceArray(textCSV.getText()));
				printMatrice(matrice);

			}

		});

		panel.add(button);
		panel.add (Box.createVerticalStrut(10));
		
		JButton button1 =new JButton ("Vedi BBCode");

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				printBBCode(textCSV.getText());

			}

		});

		panel.add(button1);
		panel.add (Box.createVerticalStrut(10));
		
		JButton button2 =new JButton ("Pulisci");

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				textCSV.setText("");
				textView.setText("");

			}

		});

		panel.add(button2);
		panel.add (Box.createVerticalStrut(10));
		
		textView = new JTextArea ();
		JScrollPane scroll1=new JScrollPane(textView);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scroll1);
		panel.add (Box.createVerticalStrut(10));


		add (Box.createHorizontalStrut(50));
		add (panel);
		add (Box.createHorizontalStrut(50));
		
	}


	
	
	private void printBBCode (String csv) {
		//textView.setText(getBBCode(getMatrice(csv)));
		textView.setText(
				gestore_csv.getBBCodePerRegioni(
						gestore_csv.getMatriceArray(csv)));
	}
	
	
	private void printMatrice (String matrice) {
		
		textView.setText(matrice);
	}
	
	
	
	
}
