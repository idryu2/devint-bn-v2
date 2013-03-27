package battleship.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import battleship.game.Game;


public class KeyboardDefence extends BattleShipView {
	
	// Liste des 3 cases formant le bateau courant
	private LinkedList<Case> tmpBoat;
	
	// Liste des listes de 3 cases représentant les bateaux retenus
	private LinkedList<LinkedList<Case>> finalBoats;

	
	public KeyboardDefence(int h, int w, Game g) {
		super("KeyboardDefence");
		
		this.hook = g;
		this.height = h;
		this.width = w;
		this.tmpBoat = new LinkedList<>();
		this.finalBoats = new LinkedList<>();

		matchKeys = new HashMap<>();
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
	
	/**
	 * Initialisation
	 * 
	 */
	@Override
	public void init(GameContainer container) 
	{
		container.getGraphics().setBackground(org.newdawn.slick.Color.blue );
	}
	
	/**
	 * Détermine si une case fait partie des bateaux finaux
	 * 
	 * @param c
	 * @return
	 */
	private boolean isCaseInFinalBoats(Case c)
	{
		for (LinkedList<Case> lc : this.finalBoats)
			if (lc.contains(c))
				return true;
		return false;
	}
	
	private boolean arrayContains(String[] array, String element)
	{
		for (String s : array)
			if (s.equals(element))
				return true;
		return false;
	}
	
	/**
	 * Détermine si la forme engendrée par les 3 cases représente un bateau correct
	 * ou non
	 * 
	 * @param lc
	 * @return
	 */
	private boolean isBoatCorrect(LinkedList<Case> lc)
	{
		if (lc.size() != 3)
			return false;
		
		String firstCase = lc.getFirst().getName();
		
		if (arrayContains(this.firstRowTitles, firstCase))
		{
			
		}
		else if (arrayContains(this.secondRowTitles, firstCase))
		{
			
		}
		else if (arrayContains(this.thirdRowTitles, firstCase))
		{
			
		}
		
		return true;
	}
	
	/**
	 * Gère les updates de couleurs sur les composants
	 * 
	 */
	@Override
	public void update(GameContainer container, int delta) 
	{
		Input input = container.getInput();

		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			maCase.getValue().setColor(Color.white);
			if(input.isKeyDown(maCase.getKey()))
			{
				if (!tmpBoat.contains(maCase.getValue()) && !isCaseInFinalBoats(maCase.getValue()))
				{
					if (tmpBoat.size() >= 3)
						tmpBoat.removeFirst();

					tmpBoat.addLast(maCase.getValue());
					System.out.println("Case ajoutee");
				}

				break;
			}

			if (tmpBoat.contains(maCase.getValue()))
				maCase.getValue().setColor(Color.green);

			for (LinkedList<Case> lc : this.finalBoats)
				if (lc.contains(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
		}
		
		if (input.isKeyDown(Input.KEY_ENTER))
		{
			if(this.tmpBoat.size() == 3)
				this.finalBoats.addLast(this.tmpBoat);
				
			this.tmpBoat = new LinkedList<>();
		}
		
		if (input.isKeyDown(Input.KEY_BACK))
			this.tmpBoat = new LinkedList<>();
	}

	/**
	 * Dessine l'UI
	 * 
	 */
	@Override
	public void render(GameContainer container, Graphics g)
	{
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

	/**
	 * Créé le clavier virtuel
	 * 
	 * @param taille
	 * @return
	 */
	private HashMap<Integer,Case> CreateKeyBoard(int taille)
	{
		HashMap<Integer,Case> listCases = new HashMap<>();
		int firstRow = taille/2;
		int secondRow = taille;
		int thirdRow = taille+taille/2;

		listCases.put(matchKeys.get(firstRowTitles[0]), new Case(firstRow,taille,taille,taille, firstRowTitles[0]));
		listCases.put(matchKeys.get(secondRowTitles[0]), new Case(secondRow,2*taille+1,taille,taille,secondRowTitles[0]));
		listCases.put(matchKeys.get(thirdRowTitles[0]), new Case(thirdRow,3*taille+2,taille,taille,thirdRowTitles[0]));

		for (int i=1; i<10 ; i++)
		{
			listCases.put(matchKeys.get(firstRowTitles[i]), new Case(firstRow+i*(taille+1),taille,taille,taille, firstRowTitles[i]));
			listCases.put(matchKeys.get(secondRowTitles[i]), new Case(secondRow+i*(taille+1),2*taille+1,taille,taille,secondRowTitles[i]));
		}
		
		for (int i=0; i<6;i++)
			listCases.put(matchKeys.get(thirdRowTitles[i]), new Case(thirdRow+i*(taille+1),3*taille+2,taille,taille,thirdRowTitles[i]));
		
		return listCases;
	}
}