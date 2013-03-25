package battleship.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import battleship.view.KeyboardDefence;


public class Main {

	/**
	 * Point d'entrée du programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int w= 1000;
		int h = 500;
		try {
			AppGameContainer app = new AppGameContainer(new KeyboardDefence(h,w));
			app.setDisplayMode(1000, 550, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
