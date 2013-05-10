package battleship.menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import battleship.config.Config;
import battleship.game.Game;
import battleship.menu.BaseMenu;

@SuppressWarnings("serial")
public class Menu extends BaseMenu {

	/**
	 * Constructeur sans voix, à utiliser uniquement dans des versions de
	 * développement, en attendant la mise en place de la synthèse vocale.
	 * 
	 * @param title
	 *            nom du jeu
	 * @param nomOptions
	 *            tableau contenant les nom des options
	 */
	public Menu(String title, String[] nomOptions) {
		super(title, nomOptions);
	}

	protected void lancerOption(int i) {
		
		// Launch game
		if(i ==0) 
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
		
		// Launch tutoriel
		if (i == 1)
		{
			
		}
		
		// Exit game
		if (i == 2)
			System.exit(0);
	
	}

}
