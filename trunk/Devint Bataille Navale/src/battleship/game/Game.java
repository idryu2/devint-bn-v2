package battleship.game;

import java.util.LinkedList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

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
	
	private boolean isSoundEnabled;
	
	public Game()
	{
		super(Config.WINDOW_TITLE);
		
		// default value
		this.difficulty = Level.EASY;
		
		this.aiplayer = new AIPlayer();
		this.realPlayerContext = new PlayerContext();
		this.soundPlayer = new SoundPlayer();
		
		this.kbpView = new KeyboardPlacement(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
		this.kbbView = new KeyboardBattle(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
		
		this.isSoundEnabled = false;
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

	@SuppressWarnings("static-access")
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
		
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// change view
		//
		this.enterState(this.kbbView.getID());
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		this.addState(this.kbpView);
		this.addState(this.kbbView);
	}
	
}
