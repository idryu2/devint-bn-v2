package battleship.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import battleship.game.Game;


public class KeyboardDefence extends BattleShipView {
	
	// Liste des 3 cases formant le bateau courant
	private LinkedList<Case> tmpBoat;
	
	// Liste des listes de 3 cases représentant les bateaux retenus
	private LinkedList<LinkedList<Case>> finalBoats;

	
	public KeyboardDefence(int h, int w, Game g) {
		super("KeyboardDefence");
		
		this.hook = g;
		this.height = h;
		this.width = w;
		this.tmpBoat = new LinkedList<>();
		this.finalBoats = new LinkedList<>();

	}
	
	/**
	 * Initialisation
	 * 
	 */
	@Override
	public void init(GameContainer container) 
	{
		container.getGraphics().setBackground(org.newdawn.slick.Color.blue );
	}
	
	/**
	 * Détermine si une case fait partie des bateaux finaux
	 * 
	 * @param c
	 * @return
	 */
	private boolean isCaseInFinalBoats(Case c)
	{
		for (LinkedList<Case> lc : this.finalBoats)
			if (lc.contains(c))
				return true;
		return false;
	}
	
	private boolean arrayContains(String[] array, String element)
	{
		for (String s : array)
			if (s.equals(element))
				return true;
		return false;
	}
	
	/**
	 * Détermine si la forme engendrée par les 3 cases représente un bateau correct
	 * ou non
	 * 
	 * @param lc
	 * @return
	 */
	private boolean isBoatCorrect(LinkedList<Case> lc)
	{
		if (lc.size() != 3)
			return false;
		
		String firstCase = lc.getFirst().getName();
		
		if (arrayContains(this.firstRowTitles, firstCase))
		{
			
		}
		else if (arrayContains(this.secondRowTitles, firstCase))
		{
			
		}
		else if (arrayContains(this.thirdRowTitles, firstCase))
		{
			
		}
		
		return true;
	}
	
	/**
	 * Gère les updates de couleurs sur les composants
	 * 
	 */
	@Override
	public void update(GameContainer container, int delta) 
	{
		Input input = container.getInput();

		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			maCase.getValue().setColor(Color.white);
			if(input.isKeyDown(maCase.getKey()))
			{
				if (!tmpBoat.contains(maCase.getValue()) && !isCaseInFinalBoats(maCase.getValue()))
				{
					if (tmpBoat.size() >= 3)
						tmpBoat.removeFirst();

					tmpBoat.addLast(maCase.getValue());
					System.out.println("Case ajoutee");
				}

				break;
			}

			if (tmpBoat.contains(maCase.getValue()))
				maCase.getValue().setColor(Color.green);

			for (LinkedList<Case> lc : this.finalBoats)
				if (lc.contains(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
		}
		
		if (input.isKeyDown(Input.KEY_ENTER))
		{
			if(this.tmpBoat.size() == 3)
				this.finalBoats.addLast(this.tmpBoat);
				
			this.tmpBoat = new LinkedList<>();
		}
		
		if (input.isKeyDown(Input.KEY_BACK))
			this.tmpBoat = new LinkedList<>();
	}

	/**
	 * Dessine l'UI
	 * 
	 */
	@Override
	public void render(GameContainer container, Graphics g)
	{
		g.setColor(org.newdawn.slick.Color.white);
		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			Case c = maCase.getValue();

			g.setColor(c.getColor());
			g.fill(c);
			g.setColor(org.newdawn.slick.Color.black);
			g.drawString(c.getName(), c.getCenterX(), c.getCenterY());
		}

	}

	
}