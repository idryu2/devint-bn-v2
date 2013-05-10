package battleship.menu;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author Alexandra Muge
 * 
 */
@SuppressWarnings("serial")
public class BasePanel extends JPanel implements KeyListener {

	protected String sonAccueil;
	protected String sonAide;

	public BasePanel() 
	{
	    addKeyListener(this);
	} 

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		baseKeyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		{
			System.out.println("escape");
			System.exit(0); 
		}
	}

	/**
	 * Fonction qui est appellée par KeyPressed. Dans cette fonction vous pouvez
	 * définir les touches clavier utilisées par votre programme L'utilisation
	 * de F1,F2 et ESCAPE sont déjà configuré.
	 * 
	 * @param e
	 *            KeyEvent passé par KeyPressed
	 * 
	 */
	public void baseKeyPressed(KeyEvent e) {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		BasePanel panel = new BasePanel();
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		int hauteur = (int) tailleEcran.getHeight();
		int largeur = (int) tailleEcran.getWidth();
		frame.setSize(largeur, hauteur);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		panel.requestFocus();
	}
}