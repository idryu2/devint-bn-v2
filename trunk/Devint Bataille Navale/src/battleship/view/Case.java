package battleship.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import battleship.services.sounds.SoundType;

@SuppressWarnings("serial")
public class Case extends Rectangle {
	
	private String name;
	private Color color;
	private SoundType sound;
	
	public Case(float x, float y, float width, float height, String name, SoundType st) 
	{
		super(x, y, width, height);
		this.name = name;
		this.color = Color.white;
		this.sound = st;
	}

	public String getName() 
	{
		return name;
	}

	public Color getColor() 
	{
		return color;
	}
	
	public SoundType getSound() 
	{
		return sound;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public void setSound(SoundType sound) 
	{
		this.sound = sound;
	}



	@Override
	public boolean equals(Object o){
		if (!(o instanceof Case))
			return false;
		return this.name.equals(((Case)o).name);
					
	}
	
	@Override
	public String toString(){
		return name;
	}
	

}
