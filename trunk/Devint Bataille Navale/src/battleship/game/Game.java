package battleship.game;

import java.util.LinkedList;

import org.newdawn.slick.*;

import battleship.ai.AIPlayer;
import battleship.config.Config;
import battleship.models.boats.Boat;
import battleship.models.boats.ThreeSlotsBoat;
import battleship.services.sounds.*;
import battleship.view.BattleShipView;
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
public class Game {

	private Level difficulty;
	private AIPlayer aiplayer;
	private PlayerContext realPlayerContext;
	private SoundPlayer soundPlayer;
	private AppGameContainer gameContainer;
	private BattleShipView currentview;
	
	public Game()
	{
		// default value
		this.difficulty = Level.EASY;
		
		this.aiplayer = new AIPlayer();
		this.realPlayerContext = new PlayerContext();
		this.soundPlayer = new SoundPlayer();
		
		this.startBoatSelection();
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
	
	/**
	 * Launch the boat selection view
	 * 
	 */
	private void startBoatSelection() 
	{
		try 
		{
			this.currentview = new KeyboardPlacement(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
//			this.currentview = new KeyboardBattle(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
			this.gameContainer = new AppGameContainer(this.currentview);
			this.gameContainer.setDisplayMode(
					java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,
					java.awt.Toolkit.getDefaultToolkit().getScreenSize().height, 
					true);
			this.gameContainer.start();
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
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
		
		// add the boats to the ai player (debug)
		//
		Boat b1 = new ThreeSlotsBoat().place(this.currentview.getCases().get(Input.KEY_B),
											this.currentview.getCases().get(Input.KEY_N),
											this.currentview.getCases().get(Input.KEY_H));
		
		Boat b2 = new ThreeSlotsBoat().place(this.currentview.getCases().get(Input.KEY_K),
											this.currentview.getCases().get(Input.KEY_L),
											this.currentview.getCases().get(Input.KEY_M));
		
		Boat b3 = new ThreeSlotsBoat().place(this.currentview.getCases().get(Input.KEY_I),
											this.currentview.getCases().get(Input.KEY_O),
											this.currentview.getCases().get(Input.KEY_P));
		
		this.aiplayer.getContext().getBoats().add(b1);
		this.aiplayer.getContext().getBoats().add(b2);
		this.aiplayer.getContext().getBoats().add(b3);
		
		// change the view
		//
		try 
		{
			this.currentview = new KeyboardBattle(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
			
			this.gameContainer = new AppGameContainer(this.currentview);
			this.gameContainer.setDisplayMode(
					java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,
					java.awt.Toolkit.getDefaultToolkit().getScreenSize().height, 
					true);
			
			this.gameContainer.start();
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void exit()
	{
		this.gameContainer.destroy();
		this.gameContainer.exit();
		System.exit(0);
	}
	
}
