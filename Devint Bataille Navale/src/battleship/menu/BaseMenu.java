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
     * Police de caracère des boutons, et couleurs du menu. Pour une plus grande
     * accessibilité, il est recommandé de garder de forts contrastes entre les
     * couleurs (par exemple une police noir sur fond blanc, et éviter par
     * exemple une police jaune sur fond blanc).
     */
    /**
     * Police de caractères des boutons
     */
    protected Font fonteBouton;

    /**
     * Couleur du background des boutons.
     */
    protected Color couleurBouton;

    /**
     * Couleur du background du bouton sélectionné
     */
    protected Color couleurBoutonSelectionne;

    /**
     * Couleur du texte des boutons
     */
    protected Color couleurTexte;

    /**
     * Couleur du texte du bouton sélectionné
     */
    protected Color couleurTexteSelectionne;

    /**
     * L'id de l'option du menu actuellement sélectionnée.
     */
    private int optionCourante;

    /**
     * Layout du menu.
     */
    private GridBagLayout placement;

    /**
     * Règles de placement des éléments au sein du layout.
     */
    private GridBagConstraints regles;

    // -------------------------------------------------
    // Méthodes à définir par héritage.

    /**
     * Renvoi le tableau contenant les noms des options du menu. Méthode
     * abstraite à définir par héritage.
     * 
     * @return nomOptions le tableau contenant les noms des choix du menu.
     */
    protected String[] nomOptions() {
        return new String[1];
    }

    /**
     * Lance l'action associé au bouton sélectionné. Méthode abstraite à définir
     * par héritage.
     * 
     * @param i
     *            l'id de l'option dont il faut lancer l'action.
     */
    protected void lancerOption(int i) {
    }

    // -------------------------------------------------------
    // Méthode utilisées par le constructeur servant à définir les différents
    // éléments généraux du menu

    /**
     * Défini les éléments de style des boutons (couleurs, police, ...). Appelé
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
     * Défini le layout servant à placer les composants. Appelé uniquement par
     * le constructeur.
     */
    private void creerLayout() {
        placement = new GridBagLayout();
        regles = new GridBagConstraints();
        setLayout(placement);
        // par défaut les composants sont étirés verticalement et
        // horizontalement.
        regles.fill = GridBagConstraints.BOTH;
        // par défaut, tout les composants ont un poids de 1 (soit, la même
        // importance), ils sont donc tous répartis équitablement sur la grille
        regles.weightx = 1;
        regles.weighty = 1;
        // espaces au bord des composants
        regles.insets = new Insets(10, 50, 10, 50);
        // pour placer en haut des zones
        regles.anchor = GridBagConstraints.NORTH;
    }

    /**
     * Créé l'en-tête du menu avec le nom du jeu. Appelé uniquement par le
     * constructeur.
     */
    public void creerEntete() {
        // dispotition de l'en-tête
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

        // placement de l'ancre de l'en-tête à la première ligne et la première
        // colonne de la grille d'affichage
        regles.gridx = 1;
        regles.gridy = 1;
        placement.setConstraints(entete, regles);
        add(entete);
    }

    /**
     * Créé sur la grille d'affichage les boutons associés aux noms des items du
     * menu. Appelé uniquement par le constructeur.
     * 
     * @param noms
     *            tableaux contenant les noms des éléments du menu
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
        // poids relatif de 3 ; comme l'en-tête a un poid de 1, chacun des
        // boutons est 3 fois plus grand que l'en-tête
        regles.weighty = 4;
        // placement de l'ancre des boutons en 2ème ligne, première colonne de
        // la grille d'affichage
        regles.gridx = 1;
        regles.gridy = 2;
        placement.setConstraints(boutons, regles);
        add(boutons);
    }

    /**
     * Créé un bouton du menu associé à un texte donné. Appelé uniquement par
     * "creerOption"
     * 
     * @param i
     *            id du bouton
     * @param texte
     *            texte à afficher sur le bouton
     */
    private void creerBouton(int i, String texte) {
        boutonOption[i] = new JButton();
        boutonOption[i].setText(texte);
        setPropertiesButton(boutonOption[i]);
    }

    /**
     * Mets à jour les propriétés des boutons. Appelé uniquement par
     * "creerBouton".
     * 
     * @param b
     *            bouton à gérer.
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
     *            wav à lire lors de l'apparition du menu
     * @param nomOptions
     *            noms des différents choix du menu
     * @param voix
     *            voix du synthétiseur vocal
     */
/*    public BaseMenu(String title, String wavAide, String wavAccueil,
            String[] nomOptions, SIVOXDevint voix) {
        super(voix, wavAccueil, wavAide);
        this.sonAide = wavAide;
        this.sonAccueil = wavAccueil;
        this.nomJeu = title; // défini le nom du jeu
        this.optionCourante = -1; // il n'y a pas d'option sélectionné par
                                  // défaut
        this.nomOptions = nomOptions; // défini les noms des choix du menu
        this.nbOption = this.nomOptions.length; // nombre de choix du menu

        creerAttributs(); // défini les couleurs et police de texte du menu
        creerLayout(); // défini le layout du menu
        creerEntete(); // défini l'en-tête du menu
        creerOption(nomOptions);

        this.voix.playWav(this.sonAccueil); // texte du menu, celle de l'aide
        // n'est pas modifiable
    }
*/
    /**
     * Constructeur avec voix, sans wav d'aide ni d'accueil, à utiliser
     * uniquement dans des versions de développement, en attendant la mise en
     * place de la synthèse vocale.
     * 
     * @param title
     *            nom du jeu.
     * @param nomOptions
     *            noms des différents choix du menu
     * @param voix
     *            voix du synthétiseur vocal
     */
/*    public BaseMenu(String title, String[] nomOptions, SIVOXDevint voix) {
        super(voix);
        this.nomJeu = title; // défini le nom du jeu
        this.optionCourante = -1; // il n'y a pas d'option sélectionné par
                                  // défaut
        this.nomOptions = nomOptions; // défini les noms des choix du menu
        this.nbOption = this.nomOptions.length; // nombre de choix du menu

        creerAttributs(); // défini les couleurs et police de texte du menu
        creerLayout(); // défini le layout du menu
        creerEntete(); // défini l'en-tête du menu
        creerOption(nomOptions);
    }
*/
    /**
     * Constructeur sans voix, à utiliser uniquement dans des versions de
     * développement, en attendant la mise en place de la synthèse vocale.
     * 
     * @param title
     *            nom du jeu.
     * @param nomOptions
     *            noms des différents choix du menu
     */
    public BaseMenu(String title, String[] nomOptions) {
        super();
        this.nomJeu = title; // défini le nom du jeu
        this.optionCourante = -1; // il n'y a pas d'option sélectionné par
                                  // défaut
        this.nomOptions = nomOptions; // défini les noms des choix du menu
        this.nbOption = this.nomOptions.length; // nombre de choix du menu

        creerAttributs(); // défini les couleurs et police de texte du menu
        creerLayout(); // défini le layout du menu
        creerEntete(); // défini l'en-tête du menu
        creerOption(nomOptions);
    }

    // -------------------------------------------------------
    // Méthodes définissant les actions à effectuer lors de l'utilisation du
    // clavier ou de la souris.

    /**
     * Action à effectuer lorsqu'une touche vient d'être enfoncée. Par défaut,
     * action à effectuer : touche echape, sortir du menu - touche F1, jouer le
     * fichier audio d'aide - touche entrée, lance l'action associée à l'élément
     * du menu sélectionnée, via le biais de la méthde abstraite de cette classe
     * "lancerOption(int id)" - touche flèche du bas, place le focus sur
     * l'élément du menu suivant - touche flèche du haut, place le focus sur
     * l'élément du menu précédent.
     */
    public void baseKeyPressed(KeyEvent e) {
  //      if(voix != null) voix.stop();
        
        // enter = sélectionner l'option
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
        // flèche du bas = se déplacer dans les options vers le bas
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
        // flèche du haut = se déplacer dans les options vers le haut
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
     * Activation d'un élément du menu sur lequel l'utilisateur a cliqué avec sa
     * souris.
     * 
     * @param ae
     *            event (en l'occurence un clique de souris) à traiter
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
     * Place le focus (et ses indicateurs) sur un élément du menu donné.
     * 
     * @param i
     *            id de l'option à traiter
     */
    private void setFocusedButton(int i) {
    	//if (voix != null)
         //   voix.playShortText(boutonOption[i].getText());
        boutonOption[i].setBackground(couleurBoutonSelectionne);
        boutonOption[i].setForeground(couleurTexteSelectionne);
    }

    /**
     * Enlever le focus (et ses indicateurs) d'un élément du menu.
     * 
     * @param i
     *            id de l'option à traiter
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
