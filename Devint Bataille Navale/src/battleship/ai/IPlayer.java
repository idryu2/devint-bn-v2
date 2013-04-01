package battleship.ai;

import java.util.HashMap;
import java.util.LinkedList;

import battleship.view.Case;

/**
 * interface IPlayer
 * Fait implémenter les méthodes nécessaires à un joueur
 * 
 * @author Baptiste Viale
 *
 */
public interface IPlayer {

	Case play(HashMap<Integer, Case> listCases, LinkedList<Case> casesShooted);
	
}
