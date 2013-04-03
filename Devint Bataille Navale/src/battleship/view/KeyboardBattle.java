package battleship.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

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
		super(h, w, g);
		this.ID = 1;

		playerListCaseShooted = new LinkedList<>();
		aiListCaseShooted = new LinkedList<>();

		isAIPlayerTurn = false;
	}


	@Override
	public void init(GameContainer container, StateBasedGame base) 
	{

	}


	@Override
	public void render(GameContainer container, StateBasedGame base, Graphics g) 
	{	
		super.render(container, base, g);
		if (this.isAIPlayerTurn)
			g.drawString("C'est à l'ordinateur de jouer, appuie sur entrée", 200, this.height-100);
		else
			g.drawString("C'est à toi de jouer, choisis la case que tu souhaite attaquer et appuie sur entrée", 200, this.height-100);
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(GameContainer container, StateBasedGame base, int arg2) {
		super.update(container, base, arg2);

		//System.out.println("In update. Ai turn = " + isAIPlayerTurn);

		if (isAIPlayerTurn && this.caseAttacked == null)
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

		Input input = container.getInput();

		for (Entry<Integer, Case> maCase : cases.entrySet())
		{
			// Attaque du joueur
			maCase.getValue().setColor(Color.white);
			if (!this.isAIPlayerTurn && input.isKeyDown(maCase.getKey()))
			{
				this.caseAttacked = maCase.getValue();
			}

			// Preparation de l'affichage
			setColors(container,maCase);

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

			}
			else
			{
				if (!playerListCaseShooted.contains(caseAttacked))
					this.playerListCaseShooted.addLast(this.caseAttacked);
				else
					endOfTurn = false;
			}

			if (this.hook.isSoundEnabled())
				if (this.boatTouched(caseAttacked))
					this.hook.getSoundPlayer().playSound(SoundType.EXPLOSION);
				else
					this.hook.getSoundPlayer().playSound(SoundType.MISS);
			else
				if (this.boatTouched(caseAttacked))
					System.out.println("[Sound] Explosion !");
				else
					System.out.println("[Sound] Miss !");

			if (endOfTurn)
			{
				this.changeTurn(container);
			}

			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			boatSinked();
			setColorsBoatSinked();
		}
		if(input.isKeyDown(Input.KEY_TAB))
		{
			// TODO;
		}


	}

	private void setColorsBoatSinked()
	{
		for(Boat b : this.hook.getRealPlayerContext().getBoats())
		{
			if(b.isSinked())
			{
				for(Case c : b.getCases())
				{
					c.setColor(Color.green);
				}

			}
		}
	}
	private void setColors(GameContainer container,Entry<Integer, Case> maCase) {
		if (!this.isAIPlayerTurn && this.playerListCaseShooted.contains(maCase.getValue()))
		{
			if (this.boatTouched(maCase.getValue()))
				maCase.getValue().setColor(Color.red);
			else
				maCase.getValue().setColor(container.getGraphics().getBackground());

		}

		if (this.isAIPlayerTurn && this.aiListCaseShooted.contains(maCase.getValue()))
			if (this.boatTouched(maCase.getValue())){
				maCase.getValue().setColor(Color.red);
			}

			else
				maCase.getValue().setColor(container.getGraphics().getBackground());

	}


	@SuppressWarnings("static-access")
	private void changeTurn(GameContainer container)
	{
		this.caseAttacked = null;
		this.isAIPlayerTurn = !this.isAIPlayerTurn;
		this.switchView(container);
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

	private void boatSinked(){
		ArrayList<Boat> boats ;
		LinkedList<Case> cases;
		if (isAIPlayerTurn)
		{
			boats = this.hook.getAiplayer().getContext().getBoats();
			cases = this.aiListCaseShooted;
		}
		else
		{
			boats = this.hook.getRealPlayerContext().getBoats();
			cases = this.playerListCaseShooted;
		}

		for(Boat b : boats)
		{
			int nbCaseTouched = 0;
			for(Case c : cases)
			{
				for (int i=0; i<b.getCases().length ; i++)
					
				{
					if (c.equals(b.getCases()[i]))
						nbCaseTouched++;
				}
			}
			System.out.println("IA Player = "+isAIPlayerTurn+" nb case touched = "+nbCaseTouched+" nb case boat ="+b.getCases().length+", taille de la liste de bateaux ="+boats.size());
			if (nbCaseTouched == b.getCases().length-1)
			{
				b.setSinked(true);
			}
		}
	}

}
