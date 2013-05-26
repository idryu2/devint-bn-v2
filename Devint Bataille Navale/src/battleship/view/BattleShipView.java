package battleship.view;

import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import battleship.game.IGame;
import battleship.services.sounds.SoundType;

public abstract class BattleShipView extends BasicGameState {
	
	protected int ID;
	
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

	// Correspondance touche / son
	protected HashMap<String, SoundType> casesSounds;
	
	// Fait correspondre un string représentant une lettre avec
	// un entier représentant une réference de touche de clavier
	protected LinkedHashMap<String, Integer> matchKeys;

	// Hameçon vers la classe principale
	protected IGame hook;

	//Gestion de la police
	protected org.newdawn.slick.Font fontLetters;
	protected org.newdawn.slick.Font defaultFont;

	private boolean isF1pressed;
	
	protected BattleShipView(int h, int w , IGame g) 
	{
		super();
		
		this.ID = -1;
		this.height = h;
		this.width = w;
		this.hook = g;
		
		matchKeys = new LinkedHashMap<>();					casesSounds = new HashMap<>();
		matchKeys.put(numbersRowTitles[0], Input.KEY_1);	casesSounds.put(numbersRowTitles[0], SoundType.N1);
		matchKeys.put(numbersRowTitles[1], Input.KEY_2);	casesSounds.put(numbersRowTitles[1], SoundType.N2);
		matchKeys.put(numbersRowTitles[2], Input.KEY_3);	casesSounds.put(numbersRowTitles[2], SoundType.N3);
		matchKeys.put(numbersRowTitles[3], Input.KEY_4);	casesSounds.put(numbersRowTitles[3], SoundType.N4);
		matchKeys.put(numbersRowTitles[4], Input.KEY_5);	casesSounds.put(numbersRowTitles[4], SoundType.N5);
		matchKeys.put(numbersRowTitles[5], Input.KEY_6);	casesSounds.put(numbersRowTitles[5], SoundType.N6);
		matchKeys.put(numbersRowTitles[6], Input.KEY_7);	casesSounds.put(numbersRowTitles[6], SoundType.N7);
		matchKeys.put(numbersRowTitles[7], Input.KEY_8);	casesSounds.put(numbersRowTitles[7], SoundType.N8);
		matchKeys.put(numbersRowTitles[8], Input.KEY_9);	casesSounds.put(numbersRowTitles[8], SoundType.N9);
		matchKeys.put(numbersRowTitles[9], Input.KEY_0);	casesSounds.put(numbersRowTitles[9], SoundType.N0);
		
		matchKeys.put(firstRowTitles[0], Input.KEY_A);		casesSounds.put(firstRowTitles[0], SoundType.A);
		matchKeys.put(firstRowTitles[1], Input.KEY_Z);		casesSounds.put(firstRowTitles[1], SoundType.Z);
		matchKeys.put(firstRowTitles[2], Input.KEY_E);		casesSounds.put(firstRowTitles[2], SoundType.E);
		matchKeys.put(firstRowTitles[3], Input.KEY_R);		casesSounds.put(firstRowTitles[3], SoundType.R);
		matchKeys.put(firstRowTitles[4], Input.KEY_T);		casesSounds.put(firstRowTitles[4], SoundType.T);
		matchKeys.put(firstRowTitles[5], Input.KEY_Y);		casesSounds.put(firstRowTitles[5], SoundType.Y);
		matchKeys.put(firstRowTitles[6], Input.KEY_U);		casesSounds.put(firstRowTitles[6], SoundType.U);
		matchKeys.put(firstRowTitles[7], Input.KEY_I);		casesSounds.put(firstRowTitles[7], SoundType.I);
		matchKeys.put(firstRowTitles[8], Input.KEY_O);		casesSounds.put(firstRowTitles[8], SoundType.O);
		matchKeys.put(firstRowTitles[9], Input.KEY_P);		casesSounds.put(firstRowTitles[9], SoundType.P);

		
		matchKeys.put(secondRowTitles[0], Input.KEY_Q);		casesSounds.put(secondRowTitles[0], SoundType.Q);
		matchKeys.put(secondRowTitles[1], Input.KEY_S);		casesSounds.put(secondRowTitles[1], SoundType.S);
		matchKeys.put(secondRowTitles[2], Input.KEY_D);		casesSounds.put(secondRowTitles[2], SoundType.D);
		matchKeys.put(secondRowTitles[3], Input.KEY_F);		casesSounds.put(secondRowTitles[3], SoundType.F);
		matchKeys.put(secondRowTitles[4], Input.KEY_G);		casesSounds.put(secondRowTitles[4], SoundType.G);
		matchKeys.put(secondRowTitles[5], Input.KEY_H);		casesSounds.put(secondRowTitles[5], SoundType.H);
		matchKeys.put(secondRowTitles[6], Input.KEY_J);		casesSounds.put(secondRowTitles[6], SoundType.J);
		matchKeys.put(secondRowTitles[7], Input.KEY_K);		casesSounds.put(secondRowTitles[7], SoundType.K);
		matchKeys.put(secondRowTitles[8], Input.KEY_L);		casesSounds.put(secondRowTitles[8], SoundType.L);
		matchKeys.put(secondRowTitles[9], Input.KEY_M);		casesSounds.put(secondRowTitles[9], SoundType.M);

		matchKeys.put(thirdRowTitles[0], Input.KEY_W);		casesSounds.put(thirdRowTitles[0], SoundType.W);
		matchKeys.put(thirdRowTitles[1], Input.KEY_X);		casesSounds.put(thirdRowTitles[1], SoundType.X);
		matchKeys.put(thirdRowTitles[2], Input.KEY_C);		casesSounds.put(thirdRowTitles[2], SoundType.C);
		matchKeys.put(thirdRowTitles[3], Input.KEY_V);		casesSounds.put(thirdRowTitles[3], SoundType.V);
		matchKeys.put(thirdRowTitles[4], Input.KEY_B);		casesSounds.put(thirdRowTitles[4], SoundType.B);
		matchKeys.put(thirdRowTitles[5], Input.KEY_N);		casesSounds.put(thirdRowTitles[5], SoundType.N);
		
		this.cases = CreateKeyBoard(this.width/12);
		
		this.isF1pressed = false;
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

		listCases.put(matchKeys.get(numbersRowTitles[0]), new Case(numbersRow,taille,taille,taille, numbersRowTitles[0], casesSounds.get(numbersRowTitles[0])));
		listCases.put(matchKeys.get(firstRowTitles[0]), new Case(firstRow,2*taille+1,taille,taille, firstRowTitles[0], casesSounds.get(firstRowTitles[0])));
		listCases.put(matchKeys.get(secondRowTitles[0]), new Case(secondRow,3*taille+2,taille,taille,secondRowTitles[0], casesSounds.get(secondRowTitles[0])));
		listCases.put(matchKeys.get(thirdRowTitles[0]), new Case(thirdRow,4*taille+3,taille,taille,thirdRowTitles[0], casesSounds.get(thirdRowTitles[0])));

		for (int i=1; i<10 ; i++)
		{
			listCases.put(matchKeys.get(numbersRowTitles[i]), new Case(numbersRow+i*(taille+1),taille,taille,taille,numbersRowTitles[i], casesSounds.get(numbersRowTitles[i])));
			listCases.put(matchKeys.get(firstRowTitles[i]), new Case(firstRow+i*(taille+1),2*taille+1,taille,taille, firstRowTitles[i], casesSounds.get(firstRowTitles[i])));
			listCases.put(matchKeys.get(secondRowTitles[i]), new Case(secondRow+i*(taille+1),3*taille+2,taille,taille,secondRowTitles[i], casesSounds.get(secondRowTitles[i])));
			
		}

		for (int i=0; i<6;i++)
			listCases.put(matchKeys.get(thirdRowTitles[i]), new Case(thirdRow+i*(taille+1),4*taille+3,taille,taille,thirdRowTitles[i], casesSounds.get(thirdRowTitles[i])));

		return listCases;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
	{
		this.defaultFont = arg0.getDefaultFont();
		Font awtFont = new Font(Font.SANS_SERIF, Font.BOLD, 60);
		this.fontLetters = new TrueTypeFont(awtFont, false);   
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame base, Graphics g)
	{
		g.setColor(org.newdawn.slick.Color.white);
		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			Case c = maCase.getValue();

			g.setColor(c.getColor());
			g.fill(c);
			g.setColor(org.newdawn.slick.Color.black);
			g.setLineWidth(5);
			
			if (this.fontLetters != null && this.defaultFont != null)
			{
				g.setFont(this.fontLetters);
				g.drawString(c.getName().toUpperCase(), c.getCenterX() - 20, c.getCenterY() -40);
				g.setFont(this.defaultFont);
			}
		}
	}

	
	@Override
	public void update(GameContainer container, StateBasedGame base, int arg2)
	{
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE))
			System.exit(0);
		
		if (container.getInput().isKeyDown(Input.KEY_F1) && !isF1pressed)
		{
		   this.hook.getSoundPlayer().playSound(SoundType.N9);
		   isF1pressed = true;
		}
		if (isF1pressed)
		{
		   isF1pressed = false;
		}
	}
	
	@Override
	public int getID() 
	{
		return this.ID;
	}
}
