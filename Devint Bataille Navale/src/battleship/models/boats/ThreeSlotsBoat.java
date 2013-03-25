package battleship.models.boats;

import battleship.config.Config;
import battleship.game.Case;

/**
 * TwoSlotsBoat
 * Bateau de deux cases
 * 
 * @author Baptiste Viale
 *
 */
public class ThreeSlotsBoat extends Boat {

	public ThreeSlotsBoat()
	{
		this.cases = new Case[3];
		this.nbPoints = Config.THREE_SLOTS_BOAT_SCORE;
	}
	
	public void Place(Case c1, Case c2, Case c3)
	{
		this.cases[0] = c1;
		this.cases[1] = c2;
		this.cases[2] = c3;
	}
}
