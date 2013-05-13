package battleship.view;

import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import battleship.services.sounds.SoundType;
import battleship.tutorial.TutorialGame;
import battleship.tutorial.TutorialPhase;

public class KeyBoardTutorial extends BattleShipView {

	private TutorialPhase currentPhase;
	private boolean soundLaunched;
	private boolean nextStepKeyPressed;
	private Case greenCase;
	private Case caseAttacked;

	public KeyBoardTutorial(int h, int w, TutorialGame g) {
		super(h, w, g);

		this.currentPhase = TutorialPhase.P1;
		this.soundLaunched = false;
		this.nextStepKeyPressed = false;
		this.greenCase = this.cases.get(Input.KEY_G);
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
	 * Gère les updates de couleurs sur les composants
	 * 
	 */
	@Override
	public void update(GameContainer container, StateBasedGame base, int delta) 
	{
		super.update(container, base, delta);
		Input input = container.getInput();

		for (Entry<Integer, Case> maCase : cases.entrySet())
		{
			if (input.isKeyDown(maCase.getKey()))
			{
				this.caseAttacked = maCase.getValue();
				this.hook.getSoundPlayer().playSound(SoundType.TRY_NEVER);
			}

		}
		if (input.isKeyDown(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_SPACE) )
		{
			nextStepKeyPressed = true;
		}

		switch(this.currentPhase) 
		{
		case P1 :

			// Jouer le son s'il n'a pas encore été lancé
			if (!this.soundLaunched)
			{
				this.hook.getSoundPlayer().playVoice(SoundType.P21);
				this.soundLaunched = true;
				this.nextStepKeyPressed = true;
			}


			// Passer à la phase suivante si le son est terminé
			if (!this.hook.getSoundPlayer().isSoundPlaying() && nextStepKeyPressed)
			{
				this.soundLaunched = false;
				this.nextStepKeyPressed = false;
				this.currentPhase = TutorialPhase.P2;
			}

			break;

		case P2:

			// Jouer le son s'il n'a pas encore été lancé
			if (!this.soundLaunched)
			{
				this.hook.getSoundPlayer().playVoice(SoundType.P22);
				this.soundLaunched = true;
			}

			// Passer à la phase suivante si le son est terminé
			if (!this.hook.getSoundPlayer().isSoundPlaying() && nextStepKeyPressed)
			{
				this.soundLaunched = false;
				this.nextStepKeyPressed = false;
				this.currentPhase = TutorialPhase.P3;
			}

			break;

		case P3 :

			// Jouer le son s'il n'a pas encore été lancé
			if (!this.soundLaunched)
			{
				this.hook.getSoundPlayer().playVoice(SoundType.P23);
				this.soundLaunched = true;
			}
			// Colorier la case
			this.greenCase.setColor(Color.green);
			//System.out.println("cases = "+caseAttacked.equals(greenCase)+" son = "+soundLaunched+ " enter = "+nextStepKeyPressed);
			if (soundLaunched && caseAttacked.equals(greenCase) && nextStepKeyPressed)
			{
				this.soundLaunched = false;
				this.nextStepKeyPressed = false;
				this.greenCase.setColor(Color.white);
				this.currentPhase = TutorialPhase.P4;
			}



			break;

		case P4 :
			if (!this.soundLaunched)
			{
				this.hook.getSoundPlayer().playVoice(SoundType.P24);
				this.soundLaunched = true;
			}
			break;
		default :
			break;
		}
		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			if(!(maCase.getKey().equals(Input.KEY_G)&& this.currentPhase.equals(TutorialPhase.P3)))
			{
				maCase.getValue().setColor(Color.white);
				if(maCase.getValue().equals(this.caseAttacked))
					maCase.getValue().setColor(Color.orange);
			}

		}

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
