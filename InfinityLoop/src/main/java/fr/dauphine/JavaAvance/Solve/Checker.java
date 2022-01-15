package fr.dauphine.JavaAvance.Solve;



import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class Checker {
    public static Grid readGrid(String inputFile) throws FileNotFoundException {
        BufferedReader fr = new BufferedReader(new FileReader(inputFile));
        try {
            Grid grille = new Grid(Integer.parseInt(fr.readLine()),Integer.parseInt(fr.readLine()));
            for(int i = 0; i < grille.getHeight(); i++){
                for(int j = 0; j < grille.getWidth(); j++){
                    String[] str = fr.readLine().split(" ");
                    if(str.length != 0){
                        grille.setPiece(i,j,new Piece(i,j, PieceType.getTypeFromValue(Integer.parseInt(str[0])), Orientation.getOriFromValue(Integer.parseInt(str[1]))));
                    }
                }
            }
            return grille;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isConnected(Piece neighbor, Orientation expectedOrientation) {
        return neighbor.getConnectors().contains(expectedOrientation);
    }

    public static boolean isSolved(Grid grid) {
        for(int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                for (Orientation orientation : grid.getPiece(i, j).getConnectors()){
                    switch (orientation) {
                        case NORTH:
                            if (i == 0 || !isConnected(grid.getPiece(i - 1, j), Orientation.SOUTH)) {
                                System.out.println(grid.getPiece(i, j));
                                System.out.println("false");
                                return false;
                            }
                            break;
                        case EAST:
                            if (j == grid.getWidth() - 1 || !isConnected(grid.getPiece(i, j + 1), Orientation.WEST)) {
                                System.out.println(grid.getPiece(i, j));
                                System.out.println("false");
                                return false;
                            }
                            break;
                        case SOUTH:
                            if (i == grid.getHeight() - 1 || !isConnected(grid.getPiece(i + 1, j), Orientation.NORTH)) {
                                System.out.println(grid.getPiece(i, j));
                                System.out.println("false");
                                return false;
                            }
                            break;
                        case WEST:
                            if (j == 0 || !isConnected(grid.getPiece(i, j - 1), Orientation.EAST)) {
                                System.out.println(grid.getPiece(i, j));
                                System.out.println("false");
                                return false;
                            }
                            break;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isSolved2(Grid grille) {
        for(int i = 0; i < grille.getHeight(); i++) {
            for (int j = 0; j < grille.getWidth(); j++) {
                System.out.println(grille.getPiece(i, j));
                // We check piece by piece if their connectors match
                LinkedList<Orientation> currConnectors = new LinkedList<>(grille.getPiece(i, j).getConnectors());
                for (Orientation ori : currConnectors){
                    boolean matched = false;
                    switch (ori.getValue()) {
                        // NORTH, we check if the piece above has a connector SOUTH
                        case 0:
                            try{
                                for(Orientation oriNeighbor : grille.getPiece(i-1, j).getConnectors()){
                                    if(oriNeighbor.getValue()==2){
                                        matched = true;
                                    }
                                }
                                // if no matched connector had been found, return false
                                if(matched == false){return false;}
                            } catch(IndexOutOfBoundsException e){
                                return false;
                            }
                            break;
                        // EAST, we check if the piece on the right has a connector WEST
                        case 1:
                            try{
                                for(Orientation oriNeighbor : grille.getPiece(i, j+1).getConnectors()){
                                    if(oriNeighbor.getValue()==3){
                                        matched = true;
                                    }
                                }
                                // if no matched connector had been found, return false
                                if(matched == false){return false;}
                            } catch(IndexOutOfBoundsException e){
                                return false;
                            }
                            break;
                        // SOUTH, we check if the piece below has a connector NORTH
                        case 2:
                            try{
                                for(Orientation oriNeighbor : grille.getPiece(i+1, j).getConnectors()){
                                    if(oriNeighbor.getValue()==0){
                                        matched = true;
                                    }
                                }
                                // if no matched connector had been found, return false
                                if(matched == false){return false;}
                            } catch(IndexOutOfBoundsException e){
                                return false;
                            }
                            break;
                        // WEST, we check if the piece on the left has a connector EAST
                        case 3:
                            try{
                                for(Orientation oriNeighbor : grille.getPiece(i, j- 1).getConnectors()){
                                    if(oriNeighbor.getValue()==1){
                                        matched =true;
                                    }
                                }
                                // if no matched connector had been found, return false
                                if(matched == false){return false;}
                            } catch(IndexOutOfBoundsException e){
                                return false;
                            }
                            break;
                    }
                }
            }
        }
        return true;
    }

}
