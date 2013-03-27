package battleship.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

//import t2s.SIVOXDevint;

public class BaseMenu extends BasePanel implements KeyListener, ActionListener {
    /**
     * Nom du jeu
     */
    protected final String nomJeu;

    /**
     * Nom des choix du menu
     */
    private String[] nomOptions;

    /**
     * Les boutons de chacuns des choix du menu
     */
    private JButton[] boutonOption;

    /**
     * Le nombre de choix du menu.
     */
    private int nbOption;

    /**
     * Police de carac�re des boutons, et couleurs du menu. Pour une plus grande
     * accessibilit�, il est recommand� de garder de forts contrastes entre les
     * couleurs (par exemple une police noir sur fond blanc, et �viter par
     * exemple une police jaune sur fond blanc).
     */
    /**
     * Police de caract�res des boutons
     */
    protected Font fonteBouton;

    /**
     * Couleur du background des boutons.
     */
    protected Color couleurBouton;

    /**
     * Couleur du background du bouton s�lectionn�
     */
    protected Color couleurBoutonSelectionne;

    /**
     * Couleur du texte des boutons
     */
    protected Color couleurTexte;

    /**
     * Couleur du texte du bouton s�lectionn�
     */
    protected Color couleurTexteSelectionne;

    /**
     * L'id de l'option du menu actuellement s�lectionn�e.
     */
    private int optionCourante;

    /**
     * Layout du menu.
     */
    private GridBagLayout placement;

    /**
     * R�gles de placement des �l�ments au sein du layout.
     */
    private GridBagConstraints regles;

    // -------------------------------------------------
    // M�thodes � d�finir par h�ritage.

    /**
     * Renvoi le tableau contenant les noms des options du menu. M�thode
     * abstraite � d�finir par h�ritage.
     * 
     * @return nomOptions le tableau contenant les noms des choix du menu.
     */
    protected String[] nomOptions() {
        return new String[1];
    }

    /**
     * Lance l'action associ� au bouton s�lectionn�. M�thode abstraite � d�finir
     * par h�ritage.
     * 
     * @param i
     *            l'id de l'option dont il faut lancer l'action.
     */
    protected void lancerOption(int i) {
    }

    // -------------------------------------------------------
    // M�thode utilis�es par le constructeur servant � d�finir les diff�rents
    // �l�ments g�n�raux du menu

    /**
     * D�fini les �l�ments de style des boutons (couleurs, police, ...). Appel�
     * uniquement par le constructeur.
     */
    protected void creerAttributs() {
        // couleur des textes
        couleurTexte = Color.WHITE;
        couleurTexteSelectionne = new Color(10, 0, 150);

        // police du texte
        fonteBouton = new Font("Tahoma", 1, 56);

        // couleur du background des boutons
        couleurBouton = couleurTexteSelectionne;
        couleurBoutonSelectionne = couleurTexte;
    }

    /**
     * D�fini le layout servant � placer les composants. Appel� uniquement par
     * le constructeur.
     */
    private void creerLayout() {
        placement = new GridBagLayout();
        regles = new GridBagConstraints();
        setLayout(placement);
        // par d�faut les composants sont �tir�s verticalement et
        // horizontalement.
        regles.fill = GridBagConstraints.BOTH;
        // par d�faut, tout les composants ont un poids de 1 (soit, la m�me
        // importance), ils sont donc tous r�partis �quitablement sur la grille
        regles.weightx = 1;
        regles.weighty = 1;
        // espaces au bord des composants
        regles.insets = new Insets(10, 50, 10, 50);
        // pour placer en haut des zones
        regles.anchor = GridBagConstraints.NORTH;
    }

    /**
     * Cr�� l'en-t�te du menu avec le nom du jeu. Appel� uniquement par le
     * constructeur.
     */
    public void creerEntete() {
        // dispotition de l'en-t�te
        JPanel entete = new JPanel();
        FlowLayout enteteLayout = new FlowLayout();
        enteteLayout.setAlignment(FlowLayout.CENTER);
        entete.setLayout(enteteLayout);
        entete.setBorder(new LineBorder(Color.GRAY, 8));

        // le label
        JLabel lb1 = new JLabel(nomJeu);
        lb1.setFont(new Font("Georgia", 1, 96));
        lb1.setForeground(couleurTexteSelectionne);
        lb1.setBackground(couleurBoutonSelectionne);
        entete.add(lb1);

        // placement de l'ancre de l'en-t�te � la premi�re ligne et la premi�re
        // colonne de la grille d'affichage
        regles.gridx = 1;
        regles.gridy = 1;
        placement.setConstraints(entete, regles);
        add(entete);
    }

    /**
     * Cr�� sur la grille d'affichage les boutons associ�s aux noms des items du
     * menu. Appel� uniquement par le constructeur.
     * 
     * @param noms
     *            tableaux contenant les noms des �l�ments du menu
     */
    private void creerOption(String[] noms) {
        // panel des boutons
        JPanel boutons = new JPanel();
        boutons.setLayout(new GridLayout(nbOption, 1));
        // les boutons
        boutonOption = new JButton[nbOption];
        for (int i = 0; i < nbOption; i++) {
            creerBouton(i, noms[i]);
            boutons.add(boutonOption[i]);
        }
        // poids relatif de 3 ; comme l'en-t�te a un poid de 1, chacun des
        // boutons est 3 fois plus grand que l'en-t�te
        regles.weighty = 4;
        // placement de l'ancre des boutons en 2�me ligne, premi�re colonne de
        // la grille d'affichage
        regles.gridx = 1;
        regles.gridy = 2;
        placement.setConstraints(boutons, regles);
        add(boutons);
    }

    /**
     * Cr�� un bouton du menu associ� � un texte donn�. Appel� uniquement par
     * "creerOption"
     * 
     * @param i
     *            id du bouton
     * @param texte
     *            texte � afficher sur le bouton
     */
    private void creerBouton(int i, String texte) {
        boutonOption[i] = new JButton();
        boutonOption[i].setText(texte);
        setPropertiesButton(boutonOption[i]);
    }

    /**
     * Mets � jour les propri�t�s des boutons. Appel� uniquement par
     * "creerBouton".
     * 
     * @param b
     *            bouton � g�rer.
     */
    protected void setPropertiesButton(JButton b) {
        b.setFocusable(false);
        b.setBackground(couleurBouton);
        b.setForeground(couleurTexte);
        b.setFont(fonteBouton);
        b.setBorder(new LineBorder(Color.BLACK, 5));
        b.addActionListener(this);
    }

    // -------------------------------------------------------
    /**
     * Construit un menu abstrait.
     * 
     * @param title
     *            nom du jeu.
     * @param wavAide
     *            wav de l'aide
     * @param wavAccueil
     *            wav � lire lors de l'apparition du menu
     * @param nomOptions
     *            noms des diff�rents choix du menu
     * @param voix
     *            voix du synth�tiseur vocal
     */
/*    public BaseMenu(String title, String wavAide, String wavAccueil,
            String[] nomOptions, SIVOXDevint voix) {
        super(voix, wavAccueil, wavAide);
        this.sonAide = wavAide;
        this.sonAccueil = wavAccueil;
        this.nomJeu = title; // d�fini le nom du jeu
        this.optionCourante = -1; // il n'y a pas d'option s�lectionn� par
                                  // d�faut
        this.nomOptions = nomOptions; // d�fini les noms des choix du menu
        this.nbOption = this.nomOptions.length; // nombre de choix du menu

        creerAttributs(); // d�fini les couleurs et police de texte du menu
        creerLayout(); // d�fini le layout du menu
        creerEntete(); // d�fini l'en-t�te du menu
        creerOption(nomOptions);

        this.voix.playWav(this.sonAccueil); // texte du menu, celle de l'aide
        // n'est pas modifiable
    }
*/
    /**
     * Constructeur avec voix, sans wav d'aide ni d'accueil, � utiliser
     * uniquement dans des versions de d�veloppement, en attendant la mise en
     * place de la synth�se vocale.
     * 
     * @param title
     *            nom du jeu.
     * @param nomOptions
     *            noms des diff�rents choix du menu
     * @param voix
     *            voix du synth�tiseur vocal
     */
/*    public BaseMenu(String title, String[] nomOptions, SIVOXDevint voix) {
        super(voix);
        this.nomJeu = title; // d�fini le nom du jeu
        this.optionCourante = -1; // il n'y a pas d'option s�lectionn� par
                                  // d�faut
        this.nomOptions = nomOptions; // d�fini les noms des choix du menu
        this.nbOption = this.nomOptions.length; // nombre de choix du menu

        creerAttributs(); // d�fini les couleurs et police de texte du menu
        creerLayout(); // d�fini le layout du menu
        creerEntete(); // d�fini l'en-t�te du menu
        creerOption(nomOptions);
    }
*/
    /**
     * Constructeur sans voix, � utiliser uniquement dans des versions de
     * d�veloppement, en attendant la mise en place de la synth�se vocale.
     * 
     * @param title
     *            nom du jeu.
     * @param nomOptions
     *            noms des diff�rents choix du menu
     */
    public BaseMenu(String title, String[] nomOptions) {
        super();
        this.nomJeu = title; // d�fini le nom du jeu
        this.optionCourante = -1; // il n'y a pas d'option s�lectionn� par
                                  // d�faut
        this.nomOptions = nomOptions; // d�fini les noms des choix du menu
        this.nbOption = this.nomOptions.length; // nombre de choix du menu

        creerAttributs(); // d�fini les couleurs et police de texte du menu
        creerLayout(); // d�fini le layout du menu
        creerEntete(); // d�fini l'en-t�te du menu
        creerOption(nomOptions);
    }

    // -------------------------------------------------------
    // M�thodes d�finissant les actions � effectuer lors de l'utilisation du
    // clavier ou de la souris.

    /**
     * Action � effectuer lorsqu'une touche vient d'�tre enfonc�e. Par d�faut,
     * action � effectuer : touche echape, sortir du menu - touche F1, jouer le
     * fichier audio d'aide - touche entr�e, lance l'action associ�e � l'�l�ment
     * du menu s�lectionn�e, via le biais de la m�thde abstraite de cette classe
     * "lancerOption(int id)" - touche fl�che du bas, place le focus sur
     * l'�l�ment du menu suivant - touche fl�che du haut, place le focus sur
     * l'�l�ment du menu pr�c�dent.
     */
    public void baseKeyPressed(KeyEvent e) {
  //      if(voix != null) voix.stop();
        
        // enter = s�lectionner l'option
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("enter");
 //           voix.stop();
            lancerOption(optionCourante);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("enter");
//            voix.stop();
            lancerOption(optionCourante);
        }
        // fl�che du bas = se d�placer dans les options vers le bas
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down arrow");
            if (optionCourante == -1) {
                optionCourante = 0;
                setFocusedButton(optionCourante);
            } else {
                unFocusedButton(optionCourante);
                optionCourante = (optionCourante + 1) % nbOption;
                setFocusedButton(optionCourante);
            }
        }
        // fl�che du haut = se d�placer dans les options vers le haut
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("up arrow");
            if (optionCourante == -1) {
                optionCourante = 0;
                setFocusedButton(optionCourante);
            } else {
                unFocusedButton(optionCourante);
                optionCourante = optionCourante - 1;
                if (optionCourante == -1)
                    optionCourante = nbOption - 1;
                setFocusedButton(optionCourante);
            }
        }
    }

    /**
     * Activation d'un �l�ment du menu sur lequel l'utilisateur a cliqu� avec sa
     * souris.
     * 
     * @param ae
     *            event (en l'occurence un clique de souris) � traiter
     */
    public void actionPerformed(ActionEvent ae) {
//        if(voix != null) voix.stop();
        Object source = ae.getSource();
        for (int i = 0; i < nbOption; i++) {
            if (source == boutonOption[i]) {
                if (optionCourante != -1)
                    unFocusedButton(optionCourante);
                optionCourante = i;
                setFocusedButton(optionCourante);
                lancerOption(i);
            }
        }
    }

    /**
     * Place le focus (et ses indicateurs) sur un �l�ment du menu donn�.
     * 
     * @param i
     *            id de l'option � traiter
     */
    private void setFocusedButton(int i) {
    	//if (voix != null)
         //   voix.playShortText(boutonOption[i].getText());
        boutonOption[i].setBackground(couleurBoutonSelectionne);
        boutonOption[i].setForeground(couleurTexteSelectionne);
    }

    /**
     * Enlever le focus (et ses indicateurs) d'un �l�ment du menu.
     * 
     * @param i
     *            id de l'option � traiter
     */
    private void unFocusedButton(int i) {
        boutonOption[i].setBackground(couleurBouton);
        boutonOption[i].setForeground(couleurTexte);
    }

    /**
     * A lancer quand on aura fini d'initialiser le fonctionnement de
     * SIVOXDevint.
     * 
     * @param args
     */
    public static void main(String[] args) {
        String[] menuItemTest = new String[3];
        menuItemTest[0] = "Item 1";
        menuItemTest[1] = "Item 2";
        menuItemTest[2] = "Item 3";
        
//        BaseMenu bm = new BaseMenu("Test menu", menuItemTest, new SIVOXDevint());
        BaseMenu bm = new BaseMenu("Test menu", menuItemTest);
        JFrame frame = new JFrame();
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit()
                .getScreenSize();
        int hauteur = (int) tailleEcran.getHeight();
        int largeur = (int) tailleEcran.getWidth();
        frame.setSize(largeur, hauteur);
        frame.getContentPane().add(bm);
        frame.setVisible(true);
        bm.requestFocus();
    }
}
