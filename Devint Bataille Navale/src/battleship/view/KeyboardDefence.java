package battleship.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class KeyboardDefence extends BasicGame {
	private int height;
	private int width;
	
	private String[] firstRowTitles = {"a","z","e","r","t","y","u","i","o","p"};
	private String[] secondRowTitles = {"q","s","d","f","g","h","j","k","l","m"};
	private String[] thirdRowTitles = {"w","x","c","v","b","n"};
	
	private HashMap<Integer,Case> cases ;
	private HashMap<String, Integer> matchKeys;
	
	private LinkedList<Case> tmpBoat;
	private LinkedList<LinkedList<Case>> finalBoats;

	public KeyboardDefence(int h, int w) {
		super("SimpleTest");
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

	@Override
	public void init(GameContainer container) throws SlickException {
		container.getGraphics().setBackground(org.newdawn.slick.Color.blue );

	}

	private boolean isCaseInFinalBoats(Case c)
	{
		for (LinkedList<Case> lc : this.finalBoats)
			if (lc.contains(c))
				return true;
		return false;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			maCase.getValue().setColor(Color.white);
			if(input.isKeyDown(maCase.getKey()))
			{
				if ( !tmpBoat.contains(maCase.getValue()) && !isCaseInFinalBoats(maCase.getValue()))
				{
					if (tmpBoat.size() >= 3)
						tmpBoat.removeFirst();

					tmpBoat.addLast(maCase.getValue());
					System.out.println("Case ajoutee");
				}

				System.out.println("Touche "+maCase.getValue().getName()+" enfoncee");
				break;
			}

			if (tmpBoat.contains(maCase.getValue()))
				maCase.getValue().setColor(Color.green);

			for (LinkedList<Case> lc : this.finalBoats)
				if (lc.contains(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
		}
		if (input.isKeyDown(Input.KEY_ENTER)){
			if(this.tmpBoat.size() == 3)
			{
				System.out.print("Etat liste : ");
				for (Case c : this.tmpBoat) System.out.print(c.getName());
				System.out.println("");
				this.finalBoats.addLast(this.tmpBoat);
				this.tmpBoat = new LinkedList<>();
			}
			else
			{
				this.tmpBoat = new LinkedList<>();
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(org.newdawn.slick.Color.white);
		for(Entry<Integer, Case> maCase : cases.entrySet())
		{
			Case c = maCase.getValue();

			g.setColor(c.getColor());
			g.fill(c);
			g.setColor(org.newdawn.slick.Color.black);
			g.drawString(c.getName(), c.getCenterX(),c.getCenterY());
		}

	}

	private HashMap<Integer,Case> CreateKeyBoard(int taille){
		System.out.println(matchKeys.size()+"\n");
		HashMap<Integer,Case> listCases =new HashMap<>();
		int firstRow = taille/2;
		int secondRow = taille;
		int thirdRow = taille+taille/2;

		listCases.put(matchKeys.get(firstRowTitles[0]), new Case(firstRow,taille,taille,taille, firstRowTitles[0]));
		listCases.put(matchKeys.get(secondRowTitles[0]), new Case(secondRow,2*taille+1,taille,taille,secondRowTitles[0]));
		listCases.put(matchKeys.get(thirdRowTitles[0]), new Case(thirdRow,3*taille+2,taille,taille,thirdRowTitles[0]));

		for (int i=1; i<10 ; i++){
			listCases.put(matchKeys.get(firstRowTitles[i]), new Case(firstRow+i*(taille+1),taille,taille,taille, firstRowTitles[i]));
			listCases.put(matchKeys.get(secondRowTitles[i]), new Case(secondRow+i*(taille+1),2*taille+1,taille,taille,secondRowTitles[i]));
		}
		for (int i=0; i<6;i++){
			listCases.put(matchKeys.get(thirdRowTitles[i]),new Case(thirdRow+i*(taille+1),3*taille+2,taille,taille,thirdRowTitles[i]));
		}
		return listCases;
	}
}