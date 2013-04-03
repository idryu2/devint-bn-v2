package battleship.services.sounds;

import java.util.List;

import battleship.config.Config;
import battleship.view.Case;

public class Phrase {
	
	private PhraseType id;
	private List<SoundType> sounds;
	
	public static SoundPlayer SOUND_PLAYER;
	
	public Phrase(PhraseType id, List<SoundType> list)
	{
		this.id = id;
		this.sounds = list;
	}
	
	public PhraseType getId()
	{
		return this.id;
	}
	
	public void play(List<SoundType> dynamicSounds)
	{
		if (SOUND_PLAYER == null)
			return;
		
		int i = 0;
		
		for (SoundType st : sounds)
		{			
			if (Config.SOUNDS_PATHS_DICTIONARY.get(st) == null)
			{
				SOUND_PLAYER.playVoice(dynamicSounds.get(i));
				i++;
			}
			else
				SOUND_PLAYER.playVoice(st);
		}
		
	}
	
	public void play(List<SoundType> dynamicSounds, List<Case> boat)
	{
		if (SOUND_PLAYER == null)
			return;
		
		int i = 0;
		
		for (SoundType st : sounds)
		{			
			if (st.equals(SoundType.GENERIC_BOAT))
			{
				for (Case c : boat)
				{
					System.out.println("Playing " + c.getName());
					SOUND_PLAYER.playVoice(c.getSound());
				}
			}
			else if (Config.SOUNDS_PATHS_DICTIONARY.get(st) != null)
			{
				SOUND_PLAYER.playVoice(st);
				i++;
			}
		}
	}
}
