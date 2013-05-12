package battleship.tutorial;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import battleship.config.Config;
import battleship.game.Game;
import battleship.services.sounds.Phrase;
import battleship.services.sounds.SoundPlayer;
import battleship.view.KeyBoardTutorial;
import battleship.view.KeyboardBattle;
import battleship.view.KeyboardPlacement;

public class TutorialGame extends Game {
	private SoundPlayer soundPlayer;
//	private KeyboardBattle kbbView;
//	private KeyboardPlacement kbpView;
	private KeyBoardTutorial kbtView;
	
	public TutorialGame() 
	{
		super();
		this.soundPlayer = new SoundPlayer(this);
		Phrase.SOUND_PLAYER = this.soundPlayer;

		//this.kbbView = new KeyboardBattle(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
		//this.kbpView = new KeyboardPlacement(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
		this.kbtView = new KeyBoardTutorial(Config.WINDOW_HEIGHT,Config.WINDOW_WIDTH, this);
		this.isSoundEnabled = true;
		
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		this.addState(this.kbtView);
	}



}
