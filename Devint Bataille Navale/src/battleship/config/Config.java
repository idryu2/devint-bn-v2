package battleship.config;

import java.util.HashMap;

import battleship.services.sounds.*;


/**
 * Config
 * Configuration du programme
 * 
 * @author Baptiste Viale
 *
 */
public class Config {
	
	// -- Scores
	//
	public static final int HIGH_SCORE = 100;
	
	public static final int ONE_SLOTS_BOAT_SCORE = 10;
	public static final int TWO_SLOTS_BOAT_SCORE = 15;
	public static final int THREE_SLOTS_BOAT_SCORE = 20;
	
	public static HashMap<SoundType, String> SOUNDS_PATHS_DICTIONARY = new HashMap<SoundType, String>();
	static 
	{
		SOUNDS_PATHS_DICTIONARY.put(SoundType.EXPLOSION, "../resources/sounds/explosion.wav");
	}
	
	// -- Paths
	//
	public static final String PICTURES_PATH = "../resources/pictures/";
}
