package battleship.models.boats;

import battleship.config.Config;
import battleship.game.Case;

/**
 * ThreeSlotsBoat
 * Bateau de trois cases
 * 
 * @author Baptiste Viale
 *
 */
public class TwoSlotsBoat extends Boat {

	public TwoSlotsBoat()
	{
		this.cases = new Case[2];
		this.nbPoints = Config.TWO_SLOTS_BOAT_SCORE;
	}
	
	public void Place(Case c1, Case c2)
	{
		this.cases[0] = c1;
		this.cases[1] = c2;
	}
}
