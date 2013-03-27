package battleship.view;

import java.util.HashMap;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import battleship.game.Game;

public abstract class BattleShipView extends BasicGame {

	// Dimensions de la fenêtre
	protected int height;
	protected int width;
	
	// Décrit le clavier avec des Strings
	protected String[] firstRowTitles = {"a","z","e","r","t","y","u","i","o","p"};
	protected String[] secondRowTitles = {"q","s","d","f","g","h","j","k","l","m"};
	protected String[] thirdRowTitles = {"w","x","c","v","b","n"};
	
	// Contient toutes les cases du clavier
	// <int ref touche, Case>
	protected HashMap<Integer, Case> cases ;
	
	// Fait correspondre un string représentant une lettre avec
	// un entier représentant une réference de touche de clavier
	protected HashMap<String, Integer> matchKeys;
	
	// Hameçon vers la classe principale
	protected Game hook;
	
	protected BattleShipView(String title) 
	{
		super(title);
	}

	@Override
	public abstract void render(GameContainer arg0, Graphics arg1);

	@Override
	public abstract void init(GameContainer arg0);

	@Override
	public abstract void update(GameContainer arg0, int arg1);

}
