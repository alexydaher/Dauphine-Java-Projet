package fr.dauphine.JavaAvance.Solve;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;

import java.util.ArrayList;

public class Solver {
	private Grid outputGrid;

	/**
	 * function that solves the grid. return null if unsolvable
	 * @param grid
	 * @return
	 */
	public static Grid solveGrid(Grid grid) {
		return aux(0, 0, grid);
	}

	/**
	 * auxiliary function for solveGrid
	 * @param i
	 * @param j
	 * @param grid
	 * @return
	 */
	public static Grid aux(int i, int j, Grid grid) {
		boolean voidType = false;
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
		if (grid.getPiece(i, j).getType() != PieceType.VOID) {
			grid2.getPiece(i, j).turn();
			grid3.getPiece(i, j).turn();
			grid3.getPiece(i, j).turn();
			grid4.getPiece(i, j).turn();
			grid4.getPiece(i, j).turn();
			grid4.getPiece(i, j).turn();
		} else {
			voidType = true;
		}
		if (j == grid.getWidth() - 1) {
			j = 0;
			i += 1;
		} else {
			j += 1;
		}
		if (voidType) {
			return aux(i, j, grid1);
		}
		Grid grid1New = aux(i, j, grid1);
		if (grid1New != null) {
			return grid1New;
		}
		Grid grid2New = aux(i, j, grid2);
		if (grid2New != null) {
			return grid2New;
		}
		Grid grid3New = aux(i, j, grid3);
		if (grid3New != null) {
			return grid3New;
		}
		return aux(i, j, grid4);
	}

	public static void main(String[] args) {

		// To be implemented

	}
}
