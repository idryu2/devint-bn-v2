package battleship.tutorial;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import battleship.ai.AIPlayer;
import battleship.config.Config;
import battleship.game.Game;
import battleship.game.IGame;
import battleship.game.PlayerContext;
import battleship.services.sounds.Phrase;
import battleship.services.sounds.SoundPlayer;
import battleship.view.KeyBoardTutorial;
import battleship.view.KeyboardBattle;
import battleship.view.KeyboardPlacement;

public class TutorialGame extends StateBasedGame implements IGame {
	
	private SoundPlayer soundPlayer;
	private KeyBoardTutorial kbtView;
	
	protected boolean isSoundEnabled;
	
	public TutorialGame() 
	{
		super(Config.WINDOW_TITLE);
		this.soundPlayer = new SoundPlayer(this);
		Phrase.SOUND_PLAYER = this.soundPlayer;

		this.kbtView = new KeyBoardTutorial(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
		this.isSoundEnabled = true;
		
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		this.addState(this.kbtView);
	}

	@Override
	public boolean isSoundEnabled() 
	{
		return this.isSoundEnabled;
	}

	@Override
	public SoundPlayer getSoundPlayer() 
	{
		return this.soundPlayer;
	}
	
	
	// useless ici
	//
	@Override
	public void endBattle(boolean b) 
	{ }

	@Override
	public AIPlayer getAiplayer() 
	{ return null; }

	@Override
	public PlayerContext getRealPlayerContext() 
	{ return null; }

}
