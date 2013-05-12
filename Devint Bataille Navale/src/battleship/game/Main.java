package battleship.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import battleship.config.Config;

/**
 * Main du jeu (� recopier dans le code du menu)
 * 
 * @author Baptiste
 *
 */
public class Main {

	/**
	 * Point d'entr�e du programme
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)
	{
		AppGameContainer container = null;
		
		Game g = new Game();
		
		try 
		{
			container = new AppGameContainer(g);
			//g.initStatesList(container);
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
			return;
		}
		
		try 
		{
			container.setDisplayMode(Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT,false);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			container.setShowFPS(false);
			container.start();
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
			
	}

}
