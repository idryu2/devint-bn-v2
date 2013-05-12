package battleship.view;

import java.util.Arrays;
import java.util.LinkedList;
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

public class KeyBoardTutorial extends BattleShipView {

	private boolean isFirstLaunch;
	private LinkedList<Case> tmpBoat;
	private LinkedList<LinkedList<Case>> finalBoats;

	public KeyBoardTutorial(int h, int w, Game g) {
		super(h, w, g);
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
			// TODO: phrase "bienvenue dans le tuto"
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
				if (true)
					maCase.getValue().setColor(Color.orange);
				else	
					maCase.getValue().setColor(Color.green);

			for (LinkedList<Case> lc : this.finalBoats)
				if (lc.contains(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
		}
		
		if (input.isKeyDown(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_SPACE))
		{
			if(true)
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
		g.drawString("Tutoriel",200,this.height-100);
	}
	
}
