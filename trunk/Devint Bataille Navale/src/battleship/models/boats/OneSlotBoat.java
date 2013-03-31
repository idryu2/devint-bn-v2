package battleship.models.boats;

import battleship.config.Config;
import battleship.view.Case;

/**
 * OneSlotBoat
 * Bateau d'une case
 * 
 * @author Baptiste Viale
 *
 */
@Deprecated
public class OneSlotBoat extends Boat {

	public OneSlotBoat()
	{
		this.cases = new Case[1];
		this.nbPoints = Config.ONE_SLOTS_BOAT_SCORE;
	}
	
	public void Place(Case c1)
	{
		this.cases[0] = c1;
	}
}
