package battleship.menu;

import java.util.Scanner;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

//import t2s.SIVOXDevint;

import battleship.game.Game;
import battleship.menu.BaseMenu;

public class MenuDifficulte extends BaseMenu {

	private String listeJoueur = "joueur.ser";
//	private Joueur joueur;
	private String str = null;
	private String str2 = null;

	/**
	 * Construit un menu
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
/*	public Menu(String title, String wavAide, String wavAccueil,
			String[] nomOptions, SIVOXDevint voix) {
		super(title, wavAide, wavAccueil, nomOptions, voix);
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
//	public Menu(String title, String[] nomOptions, SIVOXDevint voix) {
//		super(title, nomOptions, voix);
//	}

	/**
	 * Constructeur sans voix, � utiliser uniquement dans des versions de
	 * d�veloppement, en attendant la mise en place de la synth�se vocale.
	 * 
	 * @param title
	 *            nom du jeu
	 * @param nomOptions
	 *            tableau contenant les nom des options
	 */
	public MenuDifficulte(String title, String[] nomOptions) {
		super(title, nomOptions);
	}

	protected void lancerOption(int i) {
/*		if (i == 0) {
			if (str == null) {
				voix.playText("Quel est ton nom ? Tape le, et valide le avec la touche entrer.");
				new JOptionPane();
				str = JOptionPane.showInputDialog(null, "Quel est ton nom ?",
						"Identification", JOptionPane.QUESTION_MESSAGE);
			}

			File f = new File(listeJoueur);
			if (f.exists() && str != null) {
				try {
					FileInputStream fichier = new FileInputStream(listeJoueur);
					ObjectInputStream ois = new ObjectInputStream(fichier);
					while ((joueur = (Joueur) ois.readObject()) != null) {
						if (joueur.getNom().equals(str)) {
							new InterfaceNiveau(joueur);
						}
						else {
							new InterfaceNiveau(new Joueur(str));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} 
			
			else {
				if (str != null) {
					new InterfaceNiveau(new Joueur(str));
				}
			}
		}
		
		 else if (i == 1) {
		voix.playText("Entre un chiffre de 2 � 9 pour choisir la taille de la grille.");
			

		} else 
		
		*/
		
		if(i ==0) 
		{
	        new Game();
		}
		else if(i == 1)
		{
			new Game();
		}
		else if(i==2)
		{
			new Game();
		}
			
	
	}

}
