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
	
	public static final int NB_BOATS_TO_PLACE = 3;
	
	public static HashMap<SoundType, String> SOUNDS_PATHS_DICTIONARY = new HashMap<SoundType, String>();
	static 
	{
		SOUNDS_PATHS_DICTIONARY.put(SoundType.EXPLOSION, "resources/sounds/explosion.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.MISS, "resources/sounds/plouf.wav");
		
		SOUNDS_PATHS_DICTIONARY.put(SoundType.GENERIC_LETTER, null);
		SOUNDS_PATHS_DICTIONARY.put(SoundType.GENERIC_NUMBER, null);
		SOUNDS_PATHS_DICTIONARY.put(SoundType.GENERIC_ALPHANUM, null);
		
		SOUNDS_PATHS_DICTIONARY.put(SoundType.A, "resources/sounds/voice/letters/a.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.B, "resources/sounds/voice/letters/b.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.C, "resources/sounds/voice/letters/c.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.D, "resources/sounds/voice/letters/d.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.E, "resources/sounds/voice/letters/e.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.F, "resources/sounds/voice/letters/f.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.G, "resources/sounds/voice/letters/g.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.H, "resources/sounds/voice/letters/h.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.I, "resources/sounds/voice/letters/i.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.J, "resources/sounds/voice/letters/j.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.K, "resources/sounds/voice/letters/k.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.L, "resources/sounds/voice/letters/l.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.M, "resources/sounds/voice/letters/m.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N, "resources/sounds/voice/letters/n.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.O, "resources/sounds/voice/letters/o.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P, "resources/sounds/voice/letters/p.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.Q, "resources/sounds/voice/letters/q.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.R, "resources/sounds/voice/letters/r.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.S, "resources/sounds/voice/letters/s.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.T, "resources/sounds/voice/letters/t.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.U, "resources/sounds/voice/letters/u.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.V, "resources/sounds/voice/letters/v.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.W, "resources/sounds/voice/letters/w.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.X, "resources/sounds/voice/letters/x.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.Y, "resources/sounds/voice/letters/y.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.Z, "resources/sounds/voice/letters/z.wav");
		
	}
	
	// -- Paths
	//
	public static final String PICTURES_PATH = "resources/pictures/";
	
	// -- UI
	//
	public static final int WINDOW_WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int WINDOW_HEIGHT = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final String WINDOW_TITLE = "Bataille navale";
}
