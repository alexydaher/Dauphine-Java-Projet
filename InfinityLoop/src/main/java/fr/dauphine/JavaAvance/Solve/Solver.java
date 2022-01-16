package fr.dauphine.JavaAvance.Solve;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;

import java.util.ArrayList;

public class Solver {
	private Grid outputGrid;

	public static Grid solveGrid(int i, int j, Grid grid) {
		if (Checker.isSolved(grid)) {
			return grid;
		}

		if (i == grid.getHeight() || j == grid.getWidth()) {
			return null;
		}

		Grid grid1 = new Grid(grid);
		Grid grid2 = new Grid(grid);
		Grid grid3 = new Grid(grid);
		Grid grid4 = new Grid(grid);
		grid2.getPiece(i, j).turn();
		grid3.getPiece(i, j).turn();
		grid3.getPiece(i, j).turn();
		grid4.getPiece(i, j).turn();
		grid4.getPiece(i, j).turn();
		grid4.getPiece(i, j).turn();

		if (j == grid.getWidth() - 1) {
			j = 0;
			i += 1;
		} else {
			j += 1;
		}

		Grid grid1New = solveGrid(i, j, grid1);
		if (grid1New != null) {
			return grid1New;
		}

		Grid grid2New = solveGrid(i, j, grid2);
		if (grid2New != null) {
			return grid2New;
		}

		Grid grid3New = solveGrid(i, j, grid3);
		if (grid3New != null) {
			return grid3New;
		}

		return solveGrid(i, j, grid4);
	}
	public static void main(String[] args) {

		// To be implemented

	}
}
