package fr.dauphine.JavaAvance.Solve;



import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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

// To be implemented
}
