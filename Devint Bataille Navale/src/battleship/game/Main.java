package battleship.game;

import java.awt.HeadlessException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main {

	/**
	 * Point d'entrée du programme
	 * 
	 * @param args
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		try 
		{
			container.setDisplayMode(
					java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,
					java.awt.Toolkit.getDefaultToolkit().getScreenSize().height, 
					true);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			container.start();
		} 
		catch (SlickException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
