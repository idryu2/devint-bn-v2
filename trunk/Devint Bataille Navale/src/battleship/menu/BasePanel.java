package battleship.menu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import t2s.SIVOXDevint;

/**
 * @author Alexandra Muge
 * 
 */
public class BasePanel extends JPanel implements KeyListener {
	//protected SIVOXDevint voix;

	protected String sonAccueil;

	protected String sonAide;

	/**
	 * @param voix
	 * @param sonAccueil
	 * @param sonAide
	 */
/*	public BasePanel(SIVOXDevint voix, String sonAccueil, String sonAide) {
		this((LayoutManager) new FlowLayout(), voix, sonAccueil, sonAide);
		addKeyListener(this);
	} 
	
	public BasePanel(SIVOXDevint voix) {
        this.voix=voix;
        addKeyListener(this);
    } 
*/	
	public BasePanel() {
	 //   voix=null;
	    addKeyListener(this);
	} 

	/**
	 * @param arg0
	 * @param voix
	 * @param sonAccueil
	 * @param sonAide
	 */
/*	public BasePanel(LayoutManager arg0, SIVOXDevint voix, String sonAccueil,
			String sonAide) {
		super(arg0);
		this.voix = voix;
		this.sonAccueil = sonAccueil;
		this.sonAide = sonAide;
		addKeyListener(this);
	}
*/
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
//		voix.stop();
		baseKeyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("escape");
			System.exit(0); 
		}
		if (e.getKeyCode() == KeyEvent.VK_F1) {
/*			voix.playText("Le but du jeu est de mettre toutes les cases en " +
            		"jaunes. Pour changer la couleur d'une case, " +
            		"il faut la sélectionner avec les flèches du clavier, " +
            		"et appuyer sur la touche entrer. En changeant la " +
            		"couleur d'une case, la couleur des cases situées à droite, " +
            		"à gauche, au-dessus, et en-dessous changera aussi. " +
            		"Appuyez sur èf 2 pour avoir la liste des touches " +
            		"utilisables.");
*/            		
		    System.out.println("F1");
		}
		if (e.getKeyCode() == KeyEvent.VK_F2) {
/*		    System.out.println("F2");
		    voix.playText("èf 1 donne des informations générales sur " +
            		"le jeu. èf 2 lance cette aide. Les flèches directionnelles permettent" +
            		" de se diriger dans le menu. Entrer pour " +
            		"lancer le choix. Echape pour " +
            		"quitter la partie.");
*/		}
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			System.out.println("enter");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
	//	BasePanel panel = new BasePanel(null, null, null);
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