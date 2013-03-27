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

	public ArrayList<Boat> getBoats() {
		return boats;
	}

	public void setBoats(ArrayList<Boat> boats) {
		this.boats = boats;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
