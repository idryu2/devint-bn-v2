package battleship.game;

import org.newdawn.slick.*;

import battleship.ai.AIPlayer;
import battleship.config.Config;
import battleship.services.sounds.*;
import battleship.view.BattleShipView;
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
//			this.currentview = new KeyboardPlacement(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
			this.currentview = new KeyboardBattle(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
			this.gameContainer = new AppGameContainer(this.currentview);
			this.gameContainer.setDisplayMode(Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT, false);
			this.gameContainer.start();
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
