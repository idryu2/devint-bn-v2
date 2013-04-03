package battleship.view;

import java.util.*;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import battleship.config.Config;
import battleship.game.Game;
import battleship.services.sounds.PhraseType;
import battleship.services.sounds.SoundType;


public class KeyboardPlacement extends BattleShipView {
	
	private boolean isFirstLaunch;
	
	// Liste des 3 cases formant le bateau courant
	private LinkedList<Case> tmpBoat;
	
	// Liste des listes de 3 cases représentant les bateaux retenus
	private LinkedList<LinkedList<Case>> finalBoats;

	
	public KeyboardPlacement(int h, int w, Game g) 
	{
		super(h, w, g);
		this.ID = 0;
		
		this.tmpBoat = new LinkedList<>();
		this.finalBoats = new LinkedList<>();
		this.isFirstLaunch = true;
	}
	
	/**
	 * Initialisation
	 * 
	 */
	@Override
	public void init(GameContainer container, StateBasedGame base) 
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
	
	private int getIndexOf(String[] array, String element)
	{
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(element))
				return i;
		return -1;
	}
	
	private boolean checkRow(List<String[]> rows, int indCurrentRow, String firstCase, String secondCase, String thirdCase)
	{
		boolean isTopRow = (indCurrentRow == 0);
		boolean isBotRow = (indCurrentRow == (rows.size() - 1));
		int ind1, ind2, ind3;
		
		ind1 = getIndexOf(rows.get(indCurrentRow), firstCase);
		if (ind1 != -1)
		{
			ind2 = getIndexOf(rows.get(indCurrentRow), secondCase);
			
			if (ind2 > -1 && (Math.abs(ind2 - ind1) == 1))
			{
				// Ligne
				ind3 = getIndexOf(rows.get(indCurrentRow), thirdCase);
				if (ind3 > -1 && ((Math.abs(ind3 - ind2) == 1) || (Math.abs(ind3 - ind2) == 1)))
					return true;
				
				if (isBotRow)
					return false;
				
				// Triangle sup
				ind3 = getIndexOf(rows.get(indCurrentRow + 1), thirdCase);
				int shortest = (ind1 > ind2) ? ind2 : ind1;
				int largest = (ind1 > ind2) ? ind1 : ind2;
				
				if (ind3 == shortest)
					return true;
				
				return false;
			}
			
			if (isBotRow)
				return false;
			
			ind2 = getIndexOf(rows.get(indCurrentRow + 1), secondCase);
			ind3 = getIndexOf(rows.get(indCurrentRow + 1), thirdCase);
			
			// Triangle inf
			if (ind2 > -1 && ind3 > -1 && (Math.abs(ind3 - ind2) == 1))
			{
				int shortest = (ind2 > ind3) ? ind3 : ind2;
				int largest = (ind2 > ind3) ? ind2 : ind3;
				
				if (ind1 == largest)
					return true;
			}
		}
		
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
		
		// Order the list
		LinkedList<Case> nlc = new LinkedList<Case>();
		for (Entry<String, Integer> en : this.matchKeys.entrySet())
			for (Case c : lc)
				if (c.getName().equals(en.getKey()))
					nlc.add(c);
		
		String firstCase = nlc.get(0).getName();
		String secondCase = nlc.get(1).getName();
		String thirdCase = nlc.get(2).getName();
		
		List<String[]> rows = new ArrayList<String[]>();
		rows.add(this.numbersRowTitles);
		rows.add(this.firstRowTitles);
		rows.add(this.secondRowTitles);
		rows.add(this.thirdRowTitles);
		
		for (int i = 0; i < rows.size(); i++)
		{
			if (getIndexOf(rows.get(i), firstCase) != -1)
				return this.checkRow(rows, i, firstCase, secondCase, thirdCase);
				
		}
		
		return false;
	}
	
	/**
	 * Gère les updates de couleurs sur les composants
	 * 
	 */
	@Override
	public void update(GameContainer container, StateBasedGame base, int delta) 
	{
		super.update(container, base, delta);
		
		if (this.isFirstLaunch)
		{
			this.hook.getSoundPlayer().playVoice(SoundType.P21);
			Config.PHRASES_DICTIONARY.get(PhraseType.PH2).play(Arrays.asList(SoundType.N3));
			this.isFirstLaunch = false;
		}
		
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
			
			this.hook.checkPlacement(this.finalBoats);
		}
		
		if (input.isKeyDown(Input.KEY_BACK))
			this.tmpBoat = new LinkedList<>();
	}

	/**
	 * Dessine l'UI
	 * 
	 */
	@Override
	public void render(GameContainer container, StateBasedGame base, Graphics g)
	{
		super.render(container, base, g);

		g.drawString("Place tes bateaux",200,this.height-100);
		g.drawString("Il te reste "+(3-this.finalBoats.size())+(this.finalBoats.size()<2?" bateaux":" bateau")+" à placer !",200,this.height-80);
	}



	
}