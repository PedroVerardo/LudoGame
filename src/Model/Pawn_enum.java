package Model;

import java.awt.Color;

public enum Pawn_enum {
	BLACK(Color.black),
	GREEN(Color.green),
	BLUE(Color.blue),
	YELLOW(Color.yellow),
	RED(Color.red);

	private final Color color;
	
	Pawn_enum(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
