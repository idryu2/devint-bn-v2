package battleship.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

@SuppressWarnings("serial")
public class Case extends Rectangle {
	
	private String name;
	private Color color;
	
	public Case(float x, float y, float width, float height, String name) 
	{
		super(x, y, width, height);
		this.name = name;
		this.color = Color.white;
	}

	public String getName() 
	{
		return name;
	}

	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Case))
			return false;
		return this.name.equals(((Case)o).name);
					
	}
	

}
