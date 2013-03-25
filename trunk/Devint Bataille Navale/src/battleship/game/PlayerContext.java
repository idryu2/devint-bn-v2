package battleship.game;

import java.util.ArrayList;

import battleship.models.boats.Boat;


/**
 * PlayerContext
 * Représente le contexte d'un joueur (pts + bateaux)
 * 
 * @author Baptiste Viale
 *
 */
public class PlayerContext {
	
	private ArrayList<Boat> boats;
	private int score;
	
	public PlayerContext()
	{
		this.boats = new ArrayList<Boat>();
		this.score = 0;
	}
}
