package battleship.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import battleship.game.Game;

public class KeyboardBattle extends BattleShipView {
	private boolean isAIPlayer;
	private Case caseAttacked;
	
	public KeyboardBattle(int h ,int w,Game g) {
		super("KeyboardBattle", h, w, g);
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
				
//
//				if (tmpBoat.contains(maCase.getValue()))
//					maCase.getValue().setColor(Color.green);
//
//				for (LinkedList<Case> lc : this.finalBoats)
//					if (lc.contains(maCase.getValue()))
//						maCase.getValue().setColor(Color.red);
			}
			
//			if (input.isKeyDown(Input.KEY_ENTER))
//			{
//				if(this.tmpBoat.size() == 3)
//					this.finalBoats.addLast(this.tmpBoat);
//					
//				this.tmpBoat = new LinkedList<>();
//			}
//			
//			if (input.isKeyDown(Input.KEY_BACK))
//				this.tmpBoat = new LinkedList<>();
		}
		
	}

}
