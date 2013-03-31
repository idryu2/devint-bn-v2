package battleship.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import battleship.config.Config;
import battleship.game.Game;
import battleship.services.sounds.SoundType;


public class KeyboardPlacement extends BattleShipView {
	
	// Liste des 3 cases formant le bateau courant
	private LinkedList<Case> tmpBoat;
	
	// Liste des listes de 3 cases représentant les bateaux retenus
	private LinkedList<LinkedList<Case>> finalBoats;

	
	public KeyboardPlacement(int h, int w, Game g) {
		super(Config.WINDOW_TITLE, h, w, g);

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

		this.hook.getSoundPlayer().PlaySound(SoundType.EXPLOSION);

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
	
	private int getIndexOf(String[] array, String element)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(element))
				return i;
		return -1;
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
		
		// Order the list
		LinkedList<Case> nlc = new LinkedList<Case>();
		for (Entry<String, Integer> en : this.matchKeys.entrySet())
			for (Case c : lc)
				if (c.getName().equals(en.getKey()))
					nlc.add(c);
		
		String firstCase = nlc.get(0).getName();
		String secondCase = nlc.get(1).getName();
		String thirdCase = nlc.get(2).getName();
		
		int ind1 = getIndexOf(this.firstRowTitles, firstCase);
		int ind2, ind3;
		
		if (ind1 != -1)
		{
			ind2 = getIndexOf(this.firstRowTitles, secondCase);
			
			if (ind2 > -1 && (Math.abs(ind2 - ind1) == 1))
			{
				// Ligne
				ind3 = getIndexOf(this.firstRowTitles, thirdCase);
				if (ind3 > -1 && ((Math.abs(ind3 - ind2) == 1) || (Math.abs(ind3 - ind2) == 1)))
					return true;
				
				// Triangle sup
				ind3 = getIndexOf(this.secondRowTitles, thirdCase);
				int shortest = (ind1 > ind2) ? ind2 : ind1;
				int largest = (ind1 > ind2) ? ind1 : ind2;
				
				if (ind3 == shortest)
					return true;
				
				return false;
			}
			
			ind2 = getIndexOf(this.secondRowTitles, secondCase);
			ind3 = getIndexOf(this.secondRowTitles, thirdCase);
			
			// Triangle inf
			if (ind2 > -1 && ind3 > -1 && (Math.abs(ind3 - ind2) == 1))
			{
				int shortest = (ind2 > ind3) ? ind3 : ind2;
				int largest = (ind2 > ind3) ? ind2 : ind3;
				
				if (ind1 == largest)
					return true;
			}
		}
		
		ind1 = getIndexOf(this.secondRowTitles, firstCase);
		if (ind1 != -1)
		{
			ind2 = getIndexOf(this.secondRowTitles, secondCase);
			
			if (ind2 > -1 && (Math.abs(ind2 - ind1) == 1))
			{
				ind3 = getIndexOf(this.secondRowTitles, thirdCase);
				if (ind3 > -1 && ((Math.abs(ind3 - ind2) == 1) || (Math.abs(ind3 - ind2) == 1)))
					return true;
				
				ind3 = getIndexOf(this.thirdRowTitles, thirdCase);
				int shortest = (ind1 > ind2) ? ind2 : ind1;
				int largest = (ind1 > ind2) ? ind1 : ind2;
				
				if (ind3 == shortest)
					return true;
				
				return false;
			}
			
			ind2 = getIndexOf(this.thirdRowTitles, secondCase);
			ind3 = getIndexOf(this.thirdRowTitles, thirdCase);
			
			// Triangle inf
			if (ind2 > -1 && ind3 > -1 && (Math.abs(ind3 - ind2) == 1))
			{
				int shortest = (ind2 > ind3) ? ind3 : ind2;
				int largest = (ind2 > ind3) ? ind2 : ind3;
				
				if (ind1 == largest)
					return true;
			}
			
		}
		
		ind1 = getIndexOf(this.thirdRowTitles, firstCase);
		if (ind1 != -1)
		{
			ind2 = getIndexOf(this.thirdRowTitles, secondCase);
			
			//  Bateau en ligne
			if (ind2 > -1 && (Math.abs(ind2 - ind1) == 1))
			{
				ind3 = getIndexOf(this.thirdRowTitles, thirdCase);
				if (ind3 > -1 && ((Math.abs(ind3 - ind2) == 1) || (Math.abs(ind3 - ind2) == 1)))
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gère les updates de couleurs sur les composants
	 * 
	 */
	@Override
	public void update(GameContainer container, int delta) 
	{
		super.update(container, delta);
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
				}

				break;
			}

			if (tmpBoat.contains(maCase.getValue()))
				if (isBoatCorrect(tmpBoat))
					maCase.getValue().setColor(Color.orange);
				else	
					maCase.getValue().setColor(Color.green);

			for (LinkedList<Case> lc : this.finalBoats)
				if (lc.contains(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
		}
		
		if (input.isKeyDown(Input.KEY_ENTER))
		{
			if(this.isBoatCorrect(this.tmpBoat))
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
		super.render(container,g);

	}

	
}