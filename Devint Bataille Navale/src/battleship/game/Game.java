package battleship.game;

import battleship.ai.AIPlayer;

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
	
	public Game(Level level)
	{
		this.difficulty = level;
		this.aiplayer = new AIPlayer();
		this.realPlayerContext = new PlayerContext();
	}
	
}
