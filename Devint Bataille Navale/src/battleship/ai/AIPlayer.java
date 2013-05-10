package battleship.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import battleship.game.*;
import battleship.models.boats.Boat;
import battleship.view.Case;

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
	public Case play(HashMap<Integer, Case> listCases, LinkedList<Case> casesShooted) {
		
		Random r = new Random();
		int stop;
		int i = 0;
		Case chosenCase = null;
		
		while (chosenCase == null)
		{
			stop = r.nextInt(listCases.size());
			i = 0;
			
			for (Entry<Integer, Case> entry : listCases.entrySet())
			{
				if (casesShooted.contains(entry.getValue()))
					continue;
				
				if (i >= stop)
					chosenCase = entry.getValue();
				
				i++;
			}
		}

		return chosenCase;
	}

	public PlayerContext getContext() {
		return context;
	}

	public List<Boat> placeBoats(HashMap<Integer, Case> listCases)
	{
		List<Boat> lb = new ArrayList<Boat>();
		Random r = new Random();
		int rand;
		
		for (int i = 0; i < 3; i++)
		{
			 rand = r.nextInt();
		}
		
		return lb;
	}
	
}
