package battleship.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import battleship.config.Config;
import battleship.game.Game;
import battleship.models.boats.Boat;
import battleship.services.sounds.PhraseType;
import battleship.services.sounds.SoundType;

public class KeyboardBattle extends BattleShipView {
	private Case caseAttacked;
	private LinkedList<Case> playerListCaseShooted;
	private Case actualCase;
	private boolean isGameOver;
	
	public KeyboardBattle(int h ,int w, Game g) 
	{
		super(h, w, g);
		this.ID = 1;

		playerListCaseShooted = new LinkedList<>();
		
		actualCase = null;
		this.isGameOver = false;
	}


	@Override
	public void init(GameContainer container, StateBasedGame base) 
	{
		super.init(container, base);
		container.getGraphics().setBackground(Color.blue);
	}


	@Override
	public void render(GameContainer container, StateBasedGame base, Graphics g) 
	{	
		super.render(container, base, g);
		
		g.drawString("C'est à toi de jouer, choisis la case que tu souhaites attaquer et appuie sur entrée", 200, this.height-100);
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(GameContainer container, StateBasedGame base, int arg2) {
		super.update(container, base, arg2);

		if (isGameOver)
			return;

		// Choix du joueur
		Input input = container.getInput();
		boolean isSongPlayed = true;
			
		for (Entry<Integer, Case> maCase : cases.entrySet())
		{
			// Attaque du joueur
			maCase.getValue().setColor(Color.white);
			if (input.isKeyDown(maCase.getKey()))
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
			
			if (this.caseAttacked != null)
				playSounds();
			
			boatSinked();
			
			//if (endOfTurn && !boatSinked())
				//this.changeTurn(container);

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
		
		if (this.playerListCaseShooted.contains(case1))
		{
			if (this.boatTouched(case1))
				case1.setColor(Color.red);
			else
				case1.setColor(container.getGraphics().getBackground());
		}
		
		setColorsBoatSinked();
	}

	private boolean checkTry() {

		if (!playerListCaseShooted.contains(caseAttacked) && caseAttacked!=null){
			this.playerListCaseShooted.addLast(this.caseAttacked);
		}
		else
			return false;
		
		return true;
	}


	private void playSounds() {

		if (this.boatTouched(caseAttacked))
			this.hook.getSoundPlayer().playSound(SoundType.EXPLOSION);
		else
			this.hook.getSoundPlayer().playSound(SoundType.MISS);
	
		if (this.boatTouched(caseAttacked))
			System.out.println("[Sound] Explosion !");
		else
			System.out.println("[Sound] Miss !");
	}

	private boolean boatSinked() 
	{
		ArrayList<Boat> boats ;
		LinkedList<Case> cases;


		boats = this.hook.getAiplayer().getContext().getBoats();
		cases = this.playerListCaseShooted;
			

			
		for (Boat b : boats)
		{
			int nbCaseTouched = 0;
			for (Case c1 : cases)
			{
				for (Case c2 : b.getCases())
				{
					if (c1.equals(c2))
						nbCaseTouched++;
				}
			}
			
			// Bateau coulé
			if (nbCaseTouched == b.getCases().length && !b.isSinked())
			{
				b.setSinked(true);
				Config.PHRASES_DICTIONARY.get(PhraseType.PH5).play(null, Arrays.asList(b.getCases()));
				
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
		{
			this.hook.endBattle(true);
			this.setColorsBoatSinked();
			this.isGameOver = true;
			return true;
		}
			
		
		sinkedBoats = 0;

		for (Boat b : this.hook.getRealPlayerContext().getBoats())
			if (b.isSinked())
				sinkedBoats++;
			else
				break;

		/*if (sinkedBoats == this.hook.getRealPlayerContext().getBoats().size())
		{
			this.hook.endBattle(false);
			this.setColorsBoatSinked();
			this.isGameOver = true;
			return true;
		}*/
		
		return false;
			
	}

	private void setColorsBoatSinked()
	{
		List<Boat> boats = 	this.hook.getAiplayer().getContext().getBoats();
		
		for(Boat b : boats)
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
		this.switchView(container);
	}

	private void switchView(GameContainer container)
	{
		// Set the offensive view

		container.getGraphics().setBackground(org.newdawn.slick.Color.blue);
		
	}

	private boolean boatTouched(Case ca) 
	{
		if(ca == null)
			return false;


		for (Boat b : this.hook.getAiplayer().getContext().getBoats())
			for (Case c : b.getCases())
				if (c.equals(ca))
					return true;
		

		return false;
	}
}
