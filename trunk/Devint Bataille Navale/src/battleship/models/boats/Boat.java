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
	
	@Override
	public String toString()
	{
		String s = "Bateau (";
		
		for (Case c : this.cases)
			s += c.toString() + " ";
		
		s += ")";
		return s;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Boat))
			return false;
		
		for (Case c1 : ((Boat)o).cases)
		{
			boolean found = false;
			
			for (Case c2 : this.cases)
				if (c2.equals(c1))
					found = true;
			
			if (!found)
				return false;
		}
			
		return true;	
	}
}
