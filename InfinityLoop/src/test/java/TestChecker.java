import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.GUI.Grid;
import fr.dauphine.JavaAvance.Solve.Checker;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static junit.framework.Assert.assertSame;

public class TestChecker {
    @Test
    void testReadGridFromFile() throws FileNotFoundException {
        Grid grid = Checker.readGrid("src/main/resources/LevelTest.txt");
        assert grid != null;
        assertSame(grid.getPiece(0, 0).getType(), PieceType.LTYPE);
        assertSame(grid.getPiece(1, 1).getType(), PieceType.ONECONN);
        assertSame(grid.getPiece(1, 2).getType(), PieceType.ONECONN);
        assertSame(grid.getPiece(2, 2).getType(), PieceType.VOID);
    }
}