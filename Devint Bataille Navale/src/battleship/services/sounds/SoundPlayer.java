package battleship.services.sounds;

import java.util.LinkedList;

import org.newdawn.slick.Sound;

import battleship.config.Config;
import battleship.game.IGame;
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
	private QueueManagerThread manager;
	private IGame hook;
	
	public SoundPlayer(IGame g) 
	{
		this.manager = new QueueManagerThread(this);
		this.manager.start();
		this.hook = g;
	}

	public void playSound(SoundType st)
	{
		this.manager.playSoundImmediately(st);
	}
	
	public void playVoice(SoundType st)
	{
		System.out.println("[SoundPlayer] Son ajouté à la file d'attente : " + st.name());
		this.manager.addSoundToPlay(st);
	}
	
	protected Sound getSound()
	{
		return this.currentSound;
	}
	
	protected void setSound(Sound s)
	{
		this.currentSound = s;
	}
	
	public boolean isSoundPlaying()
	{
		if (this.currentSound == null)
			return false;
		
		return this.currentSound.playing();
	}
	
	public boolean isSoundAllowed()
	{
		return this.hook.isSoundEnabled();
	}
	
	public void stop()
	{
		try
		{
			this.manager.interrupt();
		}
		catch(Exception e){ }
	}
	
	private class QueueManagerThread extends Thread
	{
		private LinkedList<SoundType> soundsToPlay;
		private SoundPlayer hook;
		
		public QueueManagerThread(SoundPlayer sp)
		{
			this.hook = sp;
			this.soundsToPlay = new LinkedList<SoundType>();
		}
		
		@Override
		public void run()
		{
			while (true)
			{
				while (this.soundsToPlay.size() == 0)
				{
					try 
					{
						Thread.sleep(100);
					} 
					catch (InterruptedException e) { }
				}
				
				try
				{
					while (this.hook.isSoundPlaying() || !this.hook.isSoundAllowed())
					{					
						try 
						{
							Thread.sleep(100);
						} 
						catch (InterruptedException e) { }
					}
				}
				catch(Throwable t) { }
				
				System.out.println("[Manager] Lancement de " + this.soundsToPlay.getFirst().name());
				SoundLaunchThread t = new SoundLaunchThread(this.hook, this.soundsToPlay.removeFirst());
				t.start();
				
				try 
				{
					Thread.sleep(300);
				} 
				catch (InterruptedException e) { }
			}
		}
		
		public void addSoundToPlay(SoundType st)
		{
			this.soundsToPlay.addLast(st);
		}
		
		public void playSoundImmediately(SoundType st)
		{
			if (this.hook.currentSound != null)
				this.hook.currentSound.stop();
			this.soundsToPlay.clear();
			this.soundsToPlay.addLast(st);
		}
		
	}
	
	private class SoundLaunchThread extends Thread
	{
		private SoundPlayer hook;
		private SoundType soundToPlay;
		
		public SoundLaunchThread(SoundPlayer sp, SoundType st)
		{
			this.hook = sp;
			this.soundToPlay = st;
		}
		
		@Override
		public void run()
		{
			String soundPath = Config.SOUNDS_PATHS_DICTIONARY.get(this.soundToPlay);
			
			if (soundPath == null)
				return;
			
			try 
			{
				this.hook.setSound(new Sound(soundPath));
				this.hook.getSound().play();
			} 
			catch (Throwable e) 
			{
				System.out.println("Audio file not supported\n"+e.getMessage());
			}
		}
	}
	
}
