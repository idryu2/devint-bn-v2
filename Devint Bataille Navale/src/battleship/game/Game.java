package battleship.game;

import org.newdawn.slick.*;

import battleship.ai.AIPlayer;
import battleship.config.Config;
import battleship.services.sounds.*;
import battleship.view.KeyboardDefence;

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
			this.gameContainer = new AppGameContainer(new KeyboardDefence(Config.WINDOW_HEIGHT, Config.WINDOW_WIDTH, this));
			this.gameContainer.setDisplayMode(1000, 550, false);
			this.gameContainer.start();
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
