package battleship.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import battleship.game.Game;

public abstract class BattleShipView extends BasicGame {

	// Dimensions de la fenêtre
	protected int height;
	protected int width;

	// Décrit le clavier avec des Strings
	protected String[] numbersRowTitles = {"1","2","3","4","5","6","7","8","9","0"};
	protected String[] firstRowTitles = {"a","z","e","r","t","y","u","i","o","p"};
	protected String[] secondRowTitles = {"q","s","d","f","g","h","j","k","l","m"};
	protected String[] thirdRowTitles = {"w","x","c","v","b","n"};

	// Contient toutes les cases du clavier
	// <int ref touche, Case>
	protected HashMap<Integer, Case> cases ;

	// Fait correspondre un string représentant une lettre avec
	// un entier représentant une réference de touche de clavier
	protected LinkedHashMap<String, Integer> matchKeys;

	// Hameçon vers la classe principale
	protected Game hook;

	protected BattleShipView(String title, int h , int w, Game g) 
	{
		super(title);

		this.height = h;
		this.width = w;
		this.hook = g;

		matchKeys = new LinkedHashMap<>();
		matchKeys.put(numbersRowTitles[0], Input.KEY_1);
		matchKeys.put(numbersRowTitles[1], Input.KEY_2);
		matchKeys.put(numbersRowTitles[2], Input.KEY_3);
		matchKeys.put(numbersRowTitles[3], Input.KEY_4);
		matchKeys.put(numbersRowTitles[4], Input.KEY_5);
		matchKeys.put(numbersRowTitles[5], Input.KEY_6);
		matchKeys.put(numbersRowTitles[6], Input.KEY_7);
		matchKeys.put(numbersRowTitles[7], Input.KEY_8);
		matchKeys.put(numbersRowTitles[8], Input.KEY_9);
		matchKeys.put(numbersRowTitles[9], Input.KEY_0);
		
		matchKeys.put(firstRowTitles[0], Input.KEY_A);
		matchKeys.put(firstRowTitles[1], Input.KEY_Z);
		matchKeys.put(firstRowTitles[2], Input.KEY_E);
		matchKeys.put(firstRowTitles[3], Input.KEY_R);
		matchKeys.put(firstRowTitles[4], Input.KEY_T);
		matchKeys.put(firstRowTitles[5], Input.KEY_Y);
		matchKeys.put(firstRowTitles[6], Input.KEY_U);
		matchKeys.put(firstRowTitles[7], Input.KEY_I);
		matchKeys.put(firstRowTitles[8], Input.KEY_O);
		matchKeys.put(firstRowTitles[9], Input.KEY_P);

		matchKeys.put(secondRowTitles[0], Input.KEY_Q);
		matchKeys.put(secondRowTitles[1], Input.KEY_S);
		matchKeys.put(secondRowTitles[2], Input.KEY_D);
		matchKeys.put(secondRowTitles[3], Input.KEY_F);
		matchKeys.put(secondRowTitles[4], Input.KEY_G);
		matchKeys.put(secondRowTitles[5], Input.KEY_H);
		matchKeys.put(secondRowTitles[6], Input.KEY_J);
		matchKeys.put(secondRowTitles[7], Input.KEY_K);
		matchKeys.put(secondRowTitles[8], Input.KEY_L);
		matchKeys.put(secondRowTitles[9], Input.KEY_M);

		matchKeys.put(thirdRowTitles[0], Input.KEY_W);
		matchKeys.put(thirdRowTitles[1], Input.KEY_X);
		matchKeys.put(thirdRowTitles[2], Input.KEY_C);
		matchKeys.put(thirdRowTitles[3], Input.KEY_V);
		matchKeys.put(thirdRowTitles[4], Input.KEY_B);
		matchKeys.put(thirdRowTitles[5], Input.KEY_N);

		this.cases = CreateKeyBoard(this.width/14);
		
	}
	
	public HashMap<Integer, Case> getCases() 
	{
		return cases;
	}

	/**
	 * Créé le clavier virtuel
	 * 
	 * @param taille de la case s'adaptant a la taille de la fenetre
	 * @return
	 */
	protected HashMap<Integer,Case> CreateKeyBoard(int taille)
	{
		HashMap<Integer,Case> listCases = new HashMap<>();
		int numbersRow = taille/2;
		int firstRow = taille;
		int secondRow = taille+taille/2;
		int thirdRow = 2*taille;

		listCases.put(matchKeys.get(numbersRowTitles[0]), new Case(numbersRow,taille,taille,taille, numbersRowTitles[0]));
		listCases.put(matchKeys.get(firstRowTitles[0]), new Case(firstRow,2*taille+1,taille,taille, firstRowTitles[0]));
		listCases.put(matchKeys.get(secondRowTitles[0]), new Case(secondRow,3*taille+2,taille,taille,secondRowTitles[0]));
		listCases.put(matchKeys.get(thirdRowTitles[0]), new Case(thirdRow,4*taille+3,taille,taille,thirdRowTitles[0]));

		for (int i=1; i<10 ; i++)
		{
			listCases.put(matchKeys.get(numbersRowTitles[i]), new Case(numbersRow+i*(taille+1),taille,taille,taille,numbersRowTitles[i]));
			listCases.put(matchKeys.get(firstRowTitles[i]), new Case(firstRow+i*(taille+1),2*taille+1,taille,taille, firstRowTitles[i]));
			listCases.put(matchKeys.get(secondRowTitles[i]), new Case(secondRow+i*(taille+1),3*taille+2,taille,taille,secondRowTitles[i]));
			
		}

		for (int i=0; i<6;i++)
			listCases.put(matchKeys.get(thirdRowTitles[i]), new Case(thirdRow+i*(taille+1),4*taille+3,taille,taille,thirdRowTitles[i]));

		return listCases;
	}
	
	@Override
	public void render(GameContainer container, Graphics g){
		g.setColor(org.newdawn.slick.Color.white);
		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			Case c = maCase.getValue();

			g.setColor(c.getColor());
			g.fill(c);
			g.setColor(org.newdawn.slick.Color.black);
			g.drawString(c.getName(), c.getCenterX(), c.getCenterY());
		}
	}

	@Override
	public abstract void init(GameContainer arg0);

	@Override
	public void update(GameContainer container, int arg1)
	{
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE))
			this.hook.exit();
	}
}
