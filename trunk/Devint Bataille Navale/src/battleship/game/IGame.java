package battleship.game;

import battleship.ai.AIPlayer;
import battleship.services.sounds.SoundPlayer;

public interface IGame {
	
	boolean isSoundEnabled();
	
	AIPlayer getAiplayer();
	
	PlayerContext getRealPlayerContext();
	
	SoundPlayer getSoundPlayer();
	
	void endBattle(boolean b);
}
