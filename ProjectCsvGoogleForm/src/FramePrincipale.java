

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;



public class FramePrincipale extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	try {
		
		avviaProgramma();
			
	} catch (Exception ex) {System.out.println(ex);}
	}    

	public static void avviaProgramma() {
		
			
		FramePrincipale frame = new FramePrincipale();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					
	}
	
	
	public FramePrincipale() {
			
		super();
		setTitle("Modulo Google .csv in BBCode");
    	JPanelPrincipale panel1 = new JPanelPrincipale();
    	getContentPane().add(panel1, BorderLayout.CENTER);
    	pack();
    	setVisible(true);
 		setExtendedState(Frame.MAXIMIZED_BOTH);

    }

	 

     
}
