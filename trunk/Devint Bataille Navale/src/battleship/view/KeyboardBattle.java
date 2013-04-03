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
	private Case actualCase;

	public KeyboardBattle(int h ,int w, Game g) 
	{
		super(h, w, g);
		this.ID = 1;

		playerListCaseShooted = new LinkedList<>();
		aiListCaseShooted = new LinkedList<>();

		isAIPlayerTurn = false;
		
		actualCase = null;
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

		// Choix de l'IA
		if (isAIPlayerTurn && this.caseAttacked == null)
		{
			this.caseAttacked = this.hook.getAiplayer().play(this.cases, this.aiListCaseShooted);

			if (this.caseAttacked == null){
				System.out.println("The AI player has chosen to skip his turn");
				this.changeTurn(container);
				return;
			}
			else
				System.out.println("The AI player has chosen to fire in " + this.caseAttacked.getName());
		}

		// Choix du joueur
		Input input = container.getInput();
		boolean isSongPlayed = true;
			
		for (Entry<Integer, Case> maCase : cases.entrySet())
		{

			// Attaque du joueur
			maCase.getValue().setColor(Color.white);
			if (!this.isAIPlayerTurn && input.isKeyDown(maCase.getKey()))
			{
				
				this.caseAttacked = maCase.getValue();
				
				if( !caseAttacked.equals(actualCase))
				{
					isSongPlayed = false;
					actualCase = caseAttacked;
				}
				int tries = 0;
				for (Case c : playerListCaseShooted)
				{
					if(caseAttacked.equals(c)){
						if(c.getColor().equals(Color.red))
							tries = 2;
						else
							tries = 1;
					}
				}
				if(tries == -1)
					System.out.println("Error Tries");
				if(tries == 0 && !isSongPlayed){
					this.hook.getSoundPlayer().playSound(SoundType.TRY_NEVER);
					isSongPlayed = !isSongPlayed;
				}
				if(tries == 1 && !isSongPlayed){
					this.hook.getSoundPlayer().playSound(SoundType.TRY_MISS);
					isSongPlayed = !isSongPlayed;
				}
				if(tries == 2 && !isSongPlayed){
					this.hook.getSoundPlayer().playSound(SoundType.TRY_TOUCH);
					isSongPlayed = !isSongPlayed;
				}
			}

			// Preparation de l'affichage
			setColors(container,maCase.getValue());
		}

		// Changement de couleur de la case selectionnée
		if (this.caseAttacked != null)
			this.caseAttacked.setColor(Color.orange);

		// Validation du choix
		if (input.isKeyDown(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_SPACE) )
		{
			boolean endOfTurn = checkTry();
			playSounds();
			boatSinked();
			setColorsBoatSinked();
			if (endOfTurn)
				this.changeTurn(container);

			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(input.isKeyDown(Input.KEY_TAB))
		{
			// TODO;
		}
	}

	private void setColors(GameContainer container,Case case1) {
		if (!this.isAIPlayerTurn && this.playerListCaseShooted.contains(case1))
		{
			if (this.boatTouched(case1))
				case1.setColor(Color.red);
			else
				case1.setColor(container.getGraphics().getBackground());
		}
		else if (this.isAIPlayerTurn && this.aiListCaseShooted.contains(case1))
		{
			if (this.boatTouched(case1))
				case1.setColor(Color.red);
			else
				case1.setColor(container.getGraphics().getBackground());
		}
		if(true);
	}

	private boolean checkTry() {
		if (isAIPlayerTurn)
		{
			if (!aiListCaseShooted.contains(caseAttacked))
				this.aiListCaseShooted.addLast(this.caseAttacked);

			if (this.boatTouched(this.caseAttacked))
				this.caseAttacked.setColor(Color.red);
		}
		else
		{
			if (!playerListCaseShooted.contains(caseAttacked) && caseAttacked!=null){
				this.playerListCaseShooted.addLast(this.caseAttacked);
			}
			else
				return false;
		}	
		return true;
	}


	private void playSounds() {
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
	}

	private void boatSinked() 
	{
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


		// Verification des conditions de fin de partie
		//
		int sinkedBoats = 0;
		for (Boat b : this.hook.getAiplayer().getContext().getBoats())
			if (b.isSinked())
				sinkedBoats++;
			else
				break;

		if (sinkedBoats == this.hook.getAiplayer().getContext().getBoats().size())
			this.hook.endBattle(true);

		sinkedBoats = 0;

		for (Boat b : this.hook.getRealPlayerContext().getBoats())
			if (b.isSinked())
				sinkedBoats++;
			else
				break;

		if (sinkedBoats == this.hook.getRealPlayerContext().getBoats().size())
			this.hook.endBattle(true);
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
}
