package battleship.services.sounds;

import battleship.config.Config;

/**
 * SoundPlayer
 * Classe g�rant les sons (exclusivement les bruitages pour le moment).
 * Voix communiquant avec le malvoyant ?
 * 
 * @author Baptiste Viale
 *
 */
public class SoundPlayer {

	/**
	 * Joue un son donn�
	 * 
	 * @param st
	 */
	public void PlaySound(SoundType st)
	{
		String soundPath = Config.SOUNDS_PATHS_DICTIONARY.get(st);
		
		if (soundPath == null)
			return;
	}
	
}
