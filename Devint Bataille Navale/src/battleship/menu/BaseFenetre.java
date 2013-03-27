package battleship.menu;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * 
 * @author Alexandra Muge
 *
 */
public abstract class BaseFenetre extends JFrame {

    /**
     * @param title
     *            : titre de la fenêtre
     */
    
    int hauteur;
    int largeur;
    
    public BaseFenetre(String title) {
        super(title);
        requestFocus();
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        hauteur = (int) tailleEcran.getHeight();
        largeur = (int) tailleEcran.getWidth();
        setSize(largeur, hauteur);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        init();
        this.setVisible(true);
    }

    
    public int getHauteur() {
        return hauteur;
    }


    /**
     * méthode abstraite à implémenter pour définir ce qu'il y a dans le Frame
     */
    protected abstract void init();
    
    public abstract void notifyBackToMenu();
}
