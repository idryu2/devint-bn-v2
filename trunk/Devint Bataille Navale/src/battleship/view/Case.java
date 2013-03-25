package battleship.view;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Case extends Rectangle{
	String name;
	org.newdawn.slick.Color color;
	
	public Case(float x, float y, float width, float height, String name) {
		super(x, y, width, height);
		this.name = name;
		this.color = org.newdawn.slick.Color.white;
	}

	public String getName() {
		return name;
	}

	public org.newdawn.slick.Color getColor() {
		return color;
	}

	public void setColor(org.newdawn.slick.Color color) {
		this.color = color;
	}
	

}
