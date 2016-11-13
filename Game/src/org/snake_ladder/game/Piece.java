package org.snake_ladder.game;
class Piece {
	private String colour;
	private int id;

	public Piece(String c) {
		/*
			Pre-conditions: None
			Post-conditions: colour will be set to c and id will be set to -1
		*/
		colour = c;
		id = -1;
	}

	public Piece(int i) {
		/*
			Pre-conditions: None
			Post-conditions: id will be set to i
		*/
		id = i;
		colour = "";
	}

	String getColour() {
		/*
			Pre-conditions: None
			Post-conditions: colour will be returned.
		*/
		return colour;
	}

	int getId() {
		/*
			Pre-conditions: None
			Post-conditions: id will be returned.
		*/
		return id;
	}

	public boolean equals(Object obj) {
		Piece p = (Piece) obj;
		if(id != -1) {
			return id == p.getId();
		}
		else {
			return colour.equals(p.getColour());
		}
	}
}