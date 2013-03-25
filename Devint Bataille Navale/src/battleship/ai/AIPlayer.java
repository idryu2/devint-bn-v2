package battleship.ai;

import battleship.game.*;

/**
 * AIPlayer
 * Joueur virtuel
 * 
 * @author Baptiste Viale
 *
 */
public class AIPlayer implements IPlayer {

	private PlayerContext context;
	
	public AIPlayer()
	{
		this.context = new PlayerContext();
	}
	
	@Override
	public Case Play() {
		
		return null;
	}

}
