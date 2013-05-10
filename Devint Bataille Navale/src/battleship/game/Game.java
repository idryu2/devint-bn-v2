package battleship.game;

import java.util.Arrays;
import java.util.LinkedList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

import battleship.ai.AIPlayer;
import battleship.config.Config;
import battleship.models.boats.Boat;
import battleship.models.boats.ThreeSlotsBoat;
import battleship.services.sounds.*;
import battleship.view.Case;
import battleship.view.KeyboardBattle;
import battleship.view.KeyboardPlacement;

/**
 * Game
 * 
 * 
 * @author Baptiste Viale
 *
 */
public class Game extends StateBasedGame {

	private Level difficulty;
	private AIPlayer aiplayer;
	private PlayerContext realPlayerContext;
	private SoundPlayer soundPlayer;
	private KeyboardBattle kbbView;
	private KeyboardPlacement kbpView;
//	private FadeInTransition fit;
//	private FadeOutTransition fot;


	private boolean isSoundEnabled;

	public Game()
	{
		super(Config.WINDOW_TITLE);

		// default value
		this.difficulty = Level.EASY;

		this.aiplayer = new AIPlayer();
		this.realPlayerContext = new PlayerContext();
		this.soundPlayer = new SoundPlayer(this);
		Phrase.SOUND_PLAYER = this.soundPlayer;
		
		this.kbpView = new KeyboardPlacement(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
		this.kbbView = new KeyboardBattle(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);

		this.isSoundEnabled = true;
		
		//Config.PHRASES_DICTIONARY.get(PhraseType.PH2).play(Arrays.asList(SoundType.N3));
		//Config.PHRASES_DICTIONARY.get(PhraseType.PH10).play(null, Arrays.asList(new Case(0, 0, 0, 0, "A", SoundType.A), new Case(0, 0, 0, 0, "B", SoundType.B), new Case(0, 0, 0, 0, "C", SoundType.C)));
		
		this.prepareBattle();
	}

	public Level getDifficulty() 
	{
		return difficulty;
	}

	public AIPlayer getAiplayer()
	{
		return aiplayer;
	}

	public PlayerContext getRealPlayerContext() 
	{
		return realPlayerContext;
	}

	public SoundPlayer getSoundPlayer()
	{
		return soundPlayer;
	}

	public boolean isSoundEnabled()
	{
		return this.isSoundEnabled;
	}

	public void checkPlacement(LinkedList<LinkedList<Case>> finalBoats) 
	{
		if (finalBoats.size() != Config.NB_BOATS_TO_PLACE)
			return;

		for (LinkedList<Case> lc : finalBoats)
		{
			Boat b = null;
			if (lc.size() == 3)
			{
				b = new ThreeSlotsBoat().place(lc.get(0), lc.get(1), lc.get(2));
			}

			if (b != null)
				this.realPlayerContext.getBoats().add(b);
		}

		this.prepareBattle();
	}

	@SuppressWarnings("static-access")
	private void prepareBattle()
	{
		// add the boats to the ai player (debug)
		//
		Boat b1 = new ThreeSlotsBoat().place(this.kbbView.getCases().get(Input.KEY_B),
				this.kbbView.getCases().get(Input.KEY_N),
				this.kbbView.getCases().get(Input.KEY_H));

		Boat b2 = new ThreeSlotsBoat().place(this.kbbView.getCases().get(Input.KEY_K),
				this.kbbView.getCases().get(Input.KEY_L),
				this.kbbView.getCases().get(Input.KEY_M));

		Boat b3 = new ThreeSlotsBoat().place(this.kbbView.getCases().get(Input.KEY_I),
				this.kbbView.getCases().get(Input.KEY_O),
				this.kbbView.getCases().get(Input.KEY_P));

		this.aiplayer.getContext().getBoats().add(b1);
		this.aiplayer.getContext().getBoats().add(b2);
		this.aiplayer.getContext().getBoats().add(b3);

		/*try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		this.soundPlayer.playVoice(SoundType.P5);
		
		// change view
		//
		//this.enterState(this.kbbView.getID());
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		//this.addState(this.kbpView);
		this.addState(this.kbbView);
	}

	public void endBattle(boolean isPlayerWinner) 
	{
		if (isPlayerWinner)
		{
			this.soundPlayer.playVoice(SoundType.P16);
		}
		else
		{
			this.soundPlayer.playVoice(SoundType.P17);
		}
		
		this.soundPlayer.playVoice(SoundType.P18);
	}

}
