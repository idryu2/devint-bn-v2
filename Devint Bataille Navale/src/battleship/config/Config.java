package battleship.config;

import java.util.Arrays;
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
		
		SOUNDS_PATHS_DICTIONARY.put(SoundType.TRY_MISS, "resources/sounds/missed.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.TRY_NEVER, "resources/sounds/neverTouched.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.TRY_TOUCH, "resources/sounds/touched.wav");
		
		SOUNDS_PATHS_DICTIONARY.put(SoundType.GENERIC_LETTER, null);
		SOUNDS_PATHS_DICTIONARY.put(SoundType.GENERIC_NUMBER, null);
		SOUNDS_PATHS_DICTIONARY.put(SoundType.GENERIC_ALPHANUM, null);
		SOUNDS_PATHS_DICTIONARY.put(SoundType.GENERIC_BOAT, null);
		
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
		
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N0, "resources/sounds/voice/numbers/0.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N1, "resources/sounds/voice/numbers/1.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N2, "resources/sounds/voice/numbers/2.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N3, "resources/sounds/voice/numbers/3.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N4, "resources/sounds/voice/numbers/4.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N5, "resources/sounds/voice/numbers/5.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N6, "resources/sounds/voice/numbers/6.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N7, "resources/sounds/voice/numbers/7.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N8, "resources/sounds/voice/numbers/8.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.N9, "resources/sounds/voice/numbers/9.wav");
		
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P1, "resources/sounds/voice/phrases/1.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P2, "resources/sounds/voice/phrases/2.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P3, "resources/sounds/voice/phrases/3.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P4, "resources/sounds/voice/phrases/4.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P5, "resources/sounds/voice/phrases/5.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P6, "resources/sounds/voice/phrases/6.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P7, "resources/sounds/voice/phrases/7.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P8, "resources/sounds/voice/phrases/8.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P9, "resources/sounds/voice/phrases/9.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P10, "resources/sounds/voice/phrases/10.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P11, "resources/sounds/voice/phrases/11.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P12, "resources/sounds/voice/phrases/12.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P13, "resources/sounds/voice/phrases/13.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P14, "resources/sounds/voice/phrases/14.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P15, "resources/sounds/voice/phrases/15.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P16, "resources/sounds/voice/phrases/16.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P17, "resources/sounds/voice/phrases/17.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P18, "resources/sounds/voice/phrases/18.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P19, "resources/sounds/voice/phrases/19.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P20, "resources/sounds/voice/phrases/20.wav");
		SOUNDS_PATHS_DICTIONARY.put(SoundType.P21, "resources/sounds/voice/phrases/21.wav");
	}
	
	public static HashMap<PhraseType, Phrase> PHRASES_DICTIONARY = new HashMap<>();
	static
	{
		PHRASES_DICTIONARY.put(PhraseType.PH1, new Phrase(PhraseType.PH1, Arrays.asList(SoundType.P1, SoundType.GENERIC_ALPHANUM)));
		PHRASES_DICTIONARY.put(PhraseType.PH2, new Phrase(PhraseType.PH2, Arrays.asList(SoundType.P3, SoundType.GENERIC_NUMBER, SoundType.P4)));
		PHRASES_DICTIONARY.put(PhraseType.PH3, new Phrase(PhraseType.PH3, Arrays.asList(SoundType.P7, SoundType.GENERIC_ALPHANUM)));
		PHRASES_DICTIONARY.put(PhraseType.PH4, new Phrase(PhraseType.PH4, Arrays.asList(SoundType.P8, SoundType.GENERIC_BOAT, SoundType.P9)));
		PHRASES_DICTIONARY.put(PhraseType.PH5, new Phrase(PhraseType.PH5, Arrays.asList(SoundType.P10, SoundType.GENERIC_BOAT, SoundType.P9)));
		PHRASES_DICTIONARY.put(PhraseType.PH6, new Phrase(PhraseType.PH6, Arrays.asList(SoundType.P19, SoundType.GENERIC_BOAT)));
		PHRASES_DICTIONARY.put(PhraseType.PH7, new Phrase(PhraseType.PH7, Arrays.asList(SoundType.P20, SoundType.GENERIC_BOAT)));
		PHRASES_DICTIONARY.put(PhraseType.PH8, new Phrase(PhraseType.PH8, Arrays.asList(SoundType.P13, SoundType.GENERIC_NUMBER, SoundType.P4)));
		PHRASES_DICTIONARY.put(PhraseType.PH9, new Phrase(PhraseType.PH9, Arrays.asList(SoundType.P14, SoundType.GENERIC_NUMBER, SoundType.P4, SoundType.P15)));
		PHRASES_DICTIONARY.put(PhraseType.PH10, new Phrase(PhraseType.PH10, Arrays.asList(SoundType.P11, SoundType.GENERIC_BOAT, SoundType.P12)));
	}
	
	// -- Paths
	//
	public static final String PICTURES_PATH = "resources/pictures/";
	
	// -- UI
	//
	public static final int WINDOW_WIDTH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int WINDOW_HEIGHT = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final String WINDOW_TITLE = "Bataille navale";
	public static final String TUTORIAL_WINDOW_TITLE = "Tutoriel - Bataille navale";
}
