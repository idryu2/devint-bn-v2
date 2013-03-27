package battleship.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import battleship.game.Game;
import battleship.models.boats.Boat;
import battleship.models.boats.ThreeSlotsBoat;

public class KeyboardBattle extends BattleShipView {
	private boolean isAIPlayer;
	private Case caseAttacked;
	private LinkedList<Case> listCaseShooted;
	private LinkedList<Case> listCaseTouched;
	ThreeSlotsBoat b;

	public KeyboardBattle(int h ,int w,Game g) {
		super("KeyboardBattle", h, w, g);
		listCaseShooted = new LinkedList<>();
		listCaseTouched = new LinkedList<>();

		b = new ThreeSlotsBoat();
		b.place(this.cases.get(this.matchKeys.get("a")),this.cases.get(this.matchKeys.get("z")),this.cases.get(this.matchKeys.get("e")));
		//this.hook.getAiplayer().getContext().getBoats().add(b);
	}


	@Override
	public void init(GameContainer container) {
		container.getGraphics().setBackground(org.newdawn.slick.Color.green );		
	}


	@Override
	public void render(GameContainer container, Graphics g) {	
		super.render(container, g);

	}

	@Override
	public void update(GameContainer container, int arg1) {

		if(isAIPlayer){
			//this.hook.AIPlay();
			isAIPlayer = !isAIPlayer;
		}
		else
		{
			Input input = container.getInput();

			for(Entry<Integer, Case> maCase : cases.entrySet())
			{
				maCase.getValue().setColor(Color.white);
				if(input.isKeyDown(maCase.getKey()))
				{
					this.caseAttacked = maCase.getValue();
				}

				if (maCase.getValue().equals(this.caseAttacked))
					maCase.getValue().setColor(Color.orange);
				
				if(this.listCaseShooted.contains(maCase.getValue()))
					maCase.getValue().setColor(Color.green);
				
				if(this.listCaseTouched.contains(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
			}

			if (input.isKeyDown(Input.KEY_ENTER))
			{
				if(!listCaseShooted.contains(caseAttacked))
					this.listCaseShooted.addLast(this.caseAttacked);
				if (!listCaseTouched.contains(caseAttacked) && boatTouched(caseAttacked))
					this.listCaseTouched.addLast(this.caseAttacked);

			}

		}

	}


	private boolean boatTouched(Case ca) {
		//for (Boat b : this.hook.getCurrentPlayer().getBoats()){
		//for(Boat b: this.)
		if(ca == null)
			return false;
		for (Case c : b.getCases()){
			if (c.equals(ca))
				return true;
		}
		//}
		return false;
	}

}
