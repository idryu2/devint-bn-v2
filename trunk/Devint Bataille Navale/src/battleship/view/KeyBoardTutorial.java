package battleship.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import battleship.config.Config;
import battleship.models.boats.Boat;
import battleship.models.boats.ThreeSlotsBoat;
import battleship.services.sounds.PhraseType;
import battleship.services.sounds.SoundType;
import battleship.tutorial.TutorialGame;
import battleship.tutorial.TutorialPhase;

public class KeyBoardTutorial extends BattleShipView {

	private TutorialPhase currentPhase;
	private boolean soundLaunched;
	private boolean nextStepKeyPressed;
	private Case greenCase;
	private Case caseAttacked;
	private Boat boat;
	private LinkedList<Case> playerListCaseShooted;

	public KeyBoardTutorial(int h, int w, TutorialGame g) {
		super(h, w, g);

		this.currentPhase = TutorialPhase.P1;
		this.soundLaunched = false;
		this.nextStepKeyPressed = false;
		this.caseAttacked = new Case(12, 12, 12, 12, "Départ", null);
		this.greenCase = this.cases.get(Input.KEY_G);
		this.playerListCaseShooted = new LinkedList<>();
		// construction du bateau
		boat = new ThreeSlotsBoat().place(this.getCases().get(Input.KEY_F),
				this.getCases().get(Input.KEY_G),
				this.getCases().get(Input.KEY_H));
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
				{
					if (this.caseAttacked == null)
					{
						if (!this.hook.getSoundPlayer().isSoundPlaying())
							this.hook.getSoundPlayer().playSound(SoundType.TRY_NEVER);
					}
					else
					{
						if (!this.hook.getSoundPlayer().isSoundPlaying() && !maCase.getValue().equals(this.caseAttacked)){
							if(!playerListCaseShooted.contains(maCase.getValue()))
								this.hook.getSoundPlayer().playSound(SoundType.TRY_NEVER);
							else
								this.hook.getSoundPlayer().playSound(SoundType.TRY_TOUCH);
						}

					}
				}
				this.caseAttacked = maCase.getValue();
			}



		}
		if (input.isKeyDown(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_SPACE) )
		{
			nextStepKeyPressed = true;
			if(currentPhase.equals(TutorialPhase.P4) || this.currentPhase.equals(TutorialPhase.P5)){
				if (!this.playerListCaseShooted.contains(caseAttacked))
					this.playerListCaseShooted.add(caseAttacked);
				playSounds();
			}
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				//this.caseAttacked = new Case(12, 12, 12, 12, "Départ", null);
				this.currentPhase = TutorialPhase.P4;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}



			break;

		case P4 :
			if (!this.soundLaunched)
			{
				this.hook.getSoundPlayer().playVoice(SoundType.P24);
				this.soundLaunched = true;
			}
			else if (!this.hook.getSoundPlayer().isSoundPlaying())
			{
				if(boatTouched(caseAttacked) && nextStepKeyPressed)
				{
					this.soundLaunched = false;
					this.nextStepKeyPressed = false;
					currentPhase = TutorialPhase.P5;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		case P5 :
			if (!this.soundLaunched)
			{
				this.hook.getSoundPlayer().playVoice(SoundType.P25);
				this.soundLaunched = true;
			}
			else
			{
				if(boatSinked() && nextStepKeyPressed)
				{
					this.soundLaunched = false;
					this.nextStepKeyPressed = false;
					currentPhase = TutorialPhase.P6;
				}
			}
			break;
		case P6 :
			if (!this.soundLaunched)
			{
				this.hook.getSoundPlayer().playVoice(SoundType.P26);
				this.soundLaunched = true;
			}
			else
			{
				if(boatTouched(caseAttacked))
				{
					this.soundLaunched = false;
					this.nextStepKeyPressed = false;
					currentPhase = TutorialPhase.P7;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		case P7 :
			if (!this.hook.getSoundPlayer().isSoundPlaying() && this.nextStepKeyPressed)
				System.exit(0);
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
			if(this.currentPhase.equals(TutorialPhase.P4) || this.currentPhase.equals(TutorialPhase.P5) || this.currentPhase.equals(TutorialPhase.P6) )
			{
				for(Case c : boat.getCases()){
					if(!c.equals(this.caseAttacked))
						c.setColor(Color.red);
					else
						c.setColor(Color.orange);
						
				}
				for(Case c : playerListCaseShooted){
					if(boatTouched(c)){
						if(!c.equals(this.caseAttacked))
							c.setColor(Color.green);
						else
							c.setColor(Color.orange);
					}		
				}

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

	private boolean boatTouched(Case ca) 
	{
		if(ca == null)
			return false;

		for (Case c : boat.getCases())
			if (c.equals(ca))
				return true;

		return false;
	}

	private void playSounds() {

		if (this.boatTouched(caseAttacked))
			this.hook.getSoundPlayer().playSound(SoundType.EXPLOSION);
		else
			this.hook.getSoundPlayer().playSound(SoundType.MISS);

		if (this.boatTouched(caseAttacked))
			System.out.println("[Sound] Explosion !");
		else
			System.out.println("[Sound] Miss !");
	}

	private boolean boatSinked() 
	{
		LinkedList<Case> cases;
		cases = this.playerListCaseShooted;

		int nbCaseTouched = 0;
		for (Case c1 : cases)
		{
			for (Case c2 : boat.getCases())
			{
				if (c1.equals(c2))
					nbCaseTouched++;
			}
		}
		System.out.println("Nb case touched =" +nbCaseTouched);
		// Bateau coulé
		if (nbCaseTouched == boat.getCases().length && !boat.isSinked())
		{
			boat.setSinked(true);
			return true;
		}
		return false;

	}


}
