package battleship.view;

import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import battleship.config.Config;
import battleship.game.Game;
import battleship.models.boats.Boat;
import battleship.services.sounds.SoundType;

public class KeyboardBattle extends BattleShipView {
	private boolean isAIPlayerTurn;
	private Case caseAttacked;
	private LinkedList<Case> playerListCaseShooted;
	private LinkedList<Case> aiListCaseShooted;
	
	public KeyboardBattle(int h ,int w, Game g) 
	{
		super(Config.WINDOW_TITLE, h, w, g);
		this.ID = 1;
		
		playerListCaseShooted = new LinkedList<>();
		aiListCaseShooted = new LinkedList<>();
		
		isAIPlayerTurn = false;
	}


	@Override
	public void init(GameContainer container, StateBasedGame base) 
	{
		// Offensive background
		container.getGraphics().setBackground(org.newdawn.slick.Color.green);		
	}


	@Override
	public void render(GameContainer container, StateBasedGame base, Graphics g) 
	{	
		super.render(container, base, g);
		if (this.isAIPlayerTurn)
			g.drawString("C'est à l'ordinateur de jouer, appuis sur entrée", 200,this.height-100);
		else
			g.drawString("C'est à toi de jouer, choisis la case que tu souhaite attaquer et appuis sur entrée", 200,this.height-100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame base, int arg2) {
		super.update(container, base, arg2);
		
		if (isAIPlayerTurn)
		{
			if (this.caseAttacked == null)
			{
				this.caseAttacked = this.hook.getAiplayer().play(this.cases, this.aiListCaseShooted);
				
				if (this.caseAttacked == null)
				{
					System.out.println("The AI player has chosen to skip his turn");
					this.changeTurn(container);
					return;
				}
				else
				{
					System.out.println("The AI player has chosen to fire in " + this.caseAttacked.getName());
				}
			}
		}
		
		Input input = container.getInput();
		
		for (Entry<Integer, Case> maCase : cases.entrySet())
		{
			maCase.getValue().setColor(Color.white);
			if (!this.isAIPlayerTurn && input.isKeyDown(maCase.getKey()))
			{
				this.caseAttacked = maCase.getValue();
			}

			if (!this.isAIPlayerTurn && this.playerListCaseShooted.contains(maCase.getValue()))
				if (this.boatTouched(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
				else
					maCase.getValue().setColor(container.getGraphics().getBackground());
			
			if (this.isAIPlayerTurn && this.aiListCaseShooted.contains(maCase.getValue()))
				if (this.boatTouched(maCase.getValue()))
					maCase.getValue().setColor(Color.red);
				else
					maCase.getValue().setColor(container.getGraphics().getBackground());
		}
		
		if (this.caseAttacked != null)
			this.caseAttacked.setColor(Color.orange);
		
		
		if (input.isKeyDown(Input.KEY_ENTER))
		{
			boolean endOfTurn = true;
			
			if (isAIPlayerTurn)
			{
				if (!aiListCaseShooted.contains(caseAttacked))
					this.aiListCaseShooted.addLast(this.caseAttacked);
				
				if (this.boatTouched(this.caseAttacked))
					this.caseAttacked.setColor(Color.red);
				else
					this.caseAttacked.setColor(Color.green);
				
			}
			else
			{
				if (!playerListCaseShooted.contains(caseAttacked))
					this.playerListCaseShooted.addLast(this.caseAttacked);
				else
					endOfTurn = false;
			}
			
			if (this.boatTouched(caseAttacked))
				this.hook.getSoundPlayer().playSound(SoundType.EXPLOSION);
			else
				this.hook.getSoundPlayer().playSound(SoundType.MISS);
			
			if (endOfTurn)
			{
				this.changeTurn(container);
			}
		}
		if(input.isKeyDown(Input.KEY_TAB))
		{
			// TODO;
		}
	}

	@SuppressWarnings("static-access")
	private void changeTurn(GameContainer container)
	{
		this.isAIPlayerTurn = !this.isAIPlayerTurn;
		this.caseAttacked = null;
		this.switchView(container);
		
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void switchView(GameContainer container)
	{
		// Set the defensive view
		if (this.isAIPlayerTurn)
		{
			container.getGraphics().setBackground(org.newdawn.slick.Color.cyan);
		}
		// Set the offensive view
		else
		{
			container.getGraphics().setBackground(org.newdawn.slick.Color.blue);
		}
	}
	
	private boolean boatTouched(Case ca) 
	{
		if(ca == null)
			return false;
		
		if (isAIPlayerTurn)
		{
			for (Boat b : this.hook.getRealPlayerContext().getBoats())
				for (Case c : b.getCases())
					if (c.equals(ca))
						return true;
		}
		else
		{
			for (Boat b : this.hook.getAiplayer().getContext().getBoats())
				for (Case c : b.getCases())
					if (c.equals(ca))
						return true;
		}
		
		return false;
	}
	
}
