package battleship.menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import battleship.config.Config;
import battleship.game.Game;
import battleship.menu.BaseMenu;
import battleship.tutorial.TutorialGame;

@SuppressWarnings("serial")
public class Menu extends BaseMenu {
	
	private AppGameContainer container;
	private Game game;
	private TutorialGame tutoGame;
	
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
			this.container = null;
			
			this.game = new Game();
			
			try 
			{
				this.container = new AppGameContainer(this.game);
				this.container.setVerbose(false);
			} 
			catch (SlickException e) 
			{
				e.printStackTrace();
				return;
			}
			
			try 
			{
				this.container.setDisplayMode(Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT,true);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				this.container.setShowFPS(false);
				this.container.setForceExit(false);
				this.container.start();
			} 
			catch (SlickException e) 
			{
				e.printStackTrace();
			}
		}
		
		// Launch tutoriel
		if (i == 1)
		{			
			this.tutoGame = new TutorialGame();
			
			try 
			{
				this.container = new AppGameContainer(this.tutoGame);
				this.container.setVerbose(false);
			} 
			catch (SlickException e) 
			{
				e.printStackTrace();
				return;
			}
			
			try 
			{
				this.container.setDisplayMode(Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT,true);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				this.container.setShowFPS(false);
				this.container.setForceExit(false);
				this.container.start();
			} 
			catch (SlickException e) 
			{
				e.printStackTrace();
			}
		}
		
		// Exit game
		if (i == 2)
			System.exit(0);
	
	}

}
