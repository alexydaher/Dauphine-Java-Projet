package fr.dauphine.JavaAvance.Components;

import java.util.HashMap;

/**
 * 
 * Orientation of the piece enum
 * 
 */
public enum Orientation {
	NORTH (0) {
		public Orientation turn90() {
			return EAST;
		}

		public int[] getOpposedPieceCoordinates(Piece p) {
			return new int[]{p.getPosY()-1,p.getPosX()};
		}

		public Orientation getOpposedOrientation() {
			return SOUTH;
		}
	}, EAST (1) {
		public Orientation turn90() {
			return SOUTH;
		}

		public int[] getOpposedPieceCoordinates(Piece p) {
			return new int[]{p.getPosY(),p.getPosX()+1};
		}

		public Orientation getOpposedOrientation() {
			return WEST;
		}
	}, SOUTH (2) {
		public Orientation turn90() {
			return WEST;
		}

		public int[] getOpposedPieceCoordinates(Piece p) {
			return new int[]{p.getPosY()+1,p.getPosX()};
		}

		public Orientation getOpposedOrientation() {
			return NORTH;
		}
	}, WEST (3) {
		public Orientation turn90() {
			return NORTH;
		}

		public int[] getOpposedPieceCoordinates(Piece p) {
			return new int[]{p.getPosY(),p.getPosX()-1};
		}

		public Orientation getOpposedOrientation() {
			return EAST;
		}
	};
	
    /* Implement all the possible orientations and
	 *  required methods to rotate
	 */
	public final int value;
	Orientation(int value) {
		this.value = value;
	}

	public static Orientation getOriFromValue(int value) {
		switch (value) {
			case 0:
				return NORTH;
			case 1:
				return EAST;
			case 2:
				return SOUTH;
			case 3:
				return WEST;
		}
		return null;
	}

	public int getValue() {
		return this.value;
	}

	abstract public Orientation turn90();
	abstract public int[] getOpposedPieceCoordinates(Piece p);
	abstract public Orientation getOpposedOrientation();
}
