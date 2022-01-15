package fr.dauphine.JavaAvance.Solve;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;

import java.util.ArrayList;

public class Solver {
	private Grid outputGrid;
	

	public void solveGrid(Piece testPiece, Grid inputGrid) {
		if(this.outputGrid!=null){
			return;
		}/*
		System.out.println("Start");*/
		if (inputGrid.allPieceConnected()) {
			System.out.println("fini !");
			this.outputGrid = new Grid(inputGrid.getHeight(),inputGrid.getWidth());
			Generator.copyGrid(inputGrid,this.outputGrid,0,0);
			return;
		}
		if(testPiece!=null){
			Grid tempGrid = new Grid(inputGrid.getHeight(), inputGrid.getWidth());
			Generator.copyGrid(inputGrid, tempGrid, 0, 0);
			for (Orientation ori : testPiece.getPossibleOrientations()) {
				tempGrid.setPiece(testPiece.getPosY(), testPiece.getPosX(), new Piece(testPiece.getPosY(), testPiece.getPosX(), testPiece.getType(), ori));
				/*System.out.println(testPiece.getPosY()+" : "+testPiece.getPosX());
				System.out.println(tempGrid);*/
				solveGrid(tempGrid.getNextPiece(tempGrid.getPiece(testPiece.getPosY(), testPiece.getPosX())), tempGrid);
			}
		}
	}

	public Grid getSolvedGrid(){ return this.outputGrid; }

	public static Grid optimization(Grid inputGrid){
		Grid tempGrid = new Grid(inputGrid.getHeight(), inputGrid.getWidth());
		Piece[][] pieces = inputGrid.getAllPieces();
		for(int i=0;i<inputGrid.getHeight();i++){
			for(int j=0;j<inputGrid.getWidth();j++){
				if(i==0 && j==0){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.EAST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.EAST);
						ar.add(Orientation.SOUTH);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}else if(i==0 && j==inputGrid.getWidth()-1){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.SOUTH);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.WEST);
						ar.add(Orientation.SOUTH);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}else if(i==inputGrid.getHeight()-1 && j==inputGrid.getWidth()-1){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.WEST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						ar.add(Orientation.WEST);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}else if(i==inputGrid.getHeight()-1 && j==0){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						ar.add(Orientation.EAST);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}else if(i==0 && (j>0 && j<inputGrid.getWidth()-1)){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.EAST);
						ar.add(Orientation.SOUTH);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.EAST);
						ar.add(Orientation.SOUTH);
						ar.add(Orientation.WEST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.BAR)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.EAST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.TTYPE)){
						ArrayList<Orientation> ar = new ArrayList<>();
						ar.add(Orientation.SOUTH);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}else if((i>0 && i<inputGrid.getHeight()-1) && (j==inputGrid.getWidth()-1)){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.SOUTH);
						ar.add(Orientation.WEST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						ar.add(Orientation.SOUTH);
						ar.add(Orientation.WEST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.BAR)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.TTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.WEST);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}else if((i>0 && i<inputGrid.getHeight()-1) && j==0){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						ar.add(Orientation.EAST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						ar.add(Orientation.EAST);
						ar.add(Orientation.SOUTH);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.BAR)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.TTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.EAST);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}else if((i==inputGrid.getHeight()-1) && (j>0 && j<inputGrid.getWidth()-1)){
					if(pieces[i][j].getType().equals(PieceType.LTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.WEST);
						ar.add(Orientation.NORTH);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.ONECONN)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.WEST);
						ar.add(Orientation.NORTH);
						ar.add(Orientation.EAST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.BAR)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.EAST);
						pieces[i][j].setPossibleOrientations(ar);
					}else if(pieces[i][j].getType().equals(PieceType.TTYPE)){
						ArrayList<Orientation> ar = new ArrayList<Orientation>();
						ar.add(Orientation.NORTH);
						pieces[i][j].setPossibleOrientations(ar);
					}
				}
				tempGrid.setPiece(i,j,pieces[i][j]);
			}
		}
		return tempGrid;
	}
		/*for(int i=0;i<inputGrid.getHeight();i++) {
			for (int j = 0; j < inputGrid.getWidth(); j++) {

				if (!inputGrid.isTotallyConnected(pieces[i][j])) {
					int connectors = pieces[i][j].getType().getNbConnectors();
					while (inputGrid.listOfNeighbours(pieces[i][j]).size() != connectors) {
						pieces[i][j].setOrientation(Orientation.getValueFromOri(pieces[i][j].getOrientation().turn90()));
					}
					int temp = 0;
					while (temp != connectors) {
						for (Orientation ori : pieces[i][j].getConnectors()) {
							for (Piece p : inputGrid.listOfNeighbours(pieces[i][j])) {
								int compteur = 0;
								while (!inputGrid.isConnected(pieces[i][j], ori) && compteur < 4 && !pieces[p.getPosY()][p.getPosX()].isFixed()) {
									pieces[p.getPosY()][p.getPosX()].setOrientation(Orientation.getValueFromOri(pieces[p.getPosY()][p.getPosX()].getOrientation().turn90()));
									compteur++;
								}

							}
							if (inputGrid.isConnected(pieces[i][j], ori)) {
								temp++;
							}

						}
						if (temp != connectors) {
							pieces[i][j].setOrientation(Orientation.getValueFromOri(pieces[i][j].getOrientation().turn90()));
							temp = 0;
						}
					}
				}
				pieces[i][j].setFixed(true);
			}
		}
	}*/


	public static void main(String[] args) {

		// To be implemented

	}
}
