package battleship.services.sounds;

import battleship.config.Config;
import org.newdawn.slick.*;

/**
 * SoundPlayer
 * Classe gérant les sons (exclusivement les bruitages pour le moment).
 * Voix communiquant avec le malvoyant ?
 * 
 * @author Baptiste Viale
 *
 */
public class SoundPlayer {

	private Sound currentSound;
	
	/**
	 * Joue un son donné
	 * 
	 * @param st
	 * @throws SlickException 
	 */
	public void PlaySound(SoundType st)
	{
		String soundPath = Config.SOUNDS_PATHS_DICTIONARY.get(st);
		
		if (soundPath == null)
			return;
		
		if (this.currentSound != null && this.currentSound.playing())
			this.currentSound.stop();
		
		try 
		{
			this.currentSound = new Sound(soundPath);
			this.currentSound.play();
		} 
		catch (Throwable e) 
		{
			System.out.println("Audio file not supported\n"+e.getMessage());
		}
		
	}
	
	
	
}
