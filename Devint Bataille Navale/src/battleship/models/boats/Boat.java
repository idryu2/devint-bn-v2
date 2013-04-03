package battleship.models.boats;

import battleship.view.Case;


/**
 * Boat
 * Classe mère des différents bateaux
 * 
 * @author Baptiste Viale
 *
 */
public abstract class Boat {

	protected int nbPoints;
	protected Case[] cases;
	protected boolean isSinked;
	
	public Boat()
	{
		this.isSinked = false;
	}
	public boolean isSinked() {
		return isSinked;
	}

	public void setSinked(boolean isSinked) {
		this.isSinked = isSinked;
	}
	
	public Case[] getCases() {
		return cases;
	}
	
}
