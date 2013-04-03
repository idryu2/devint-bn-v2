package battleship.services.sounds;

import java.util.List;

import battleship.config.Config;

public class Phrase {
	
	private PhraseType id;
	private List<SoundType> sounds;
	private boolean isPlaying;
	private SoundPlayer player;
	
	public Phrase(PhraseType id, List<SoundType> list, SoundPlayer sp)
	{
		this.id = id;
		this.sounds = list;
		this.isPlaying = false;
		this.player = sp;
	}
	
	public PhraseType getId()
	{
		return this.id;
	}
	
	public boolean isPlaying()
	{
		return this.isPlaying;
	}
	
	public void play(List<SoundType> dynamicSounds)
	{
		int i = 0;
		this.isPlaying = true;
		
		for (SoundType st : sounds)
		{
			while (this.player.isSoundPlaying())
			{
				System.out.println("Waiting the previous sound to stop...");
			}
			
			if (Config.SOUNDS_PATHS_DICTIONARY.get(st) == null)
			{
				this.player.playSound(dynamicSounds.get(i));
				i++;
			}
			else
				this.player.playSound(st);
		}
		
		this.isPlaying = false;
	}
}
