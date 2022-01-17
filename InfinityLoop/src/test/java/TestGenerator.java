import fr.dauphine.JavaAvance.GUI.Grid;
import fr.dauphine.JavaAvance.Solve.Generator;
import fr.dauphine.JavaAvance.Solve.Solver;
import org.junit.jupiter.api.Test;

public class TestGenerator {
    @Test
    void testIsSolvable() {
        Grid grid = Generator.generateLevel("src/main/resources/LevelTestGenerator.txt", 3, 3, 0);
        Grid gridSolved = Solver.solveGrid(grid);
        assert gridSolved != null;
    }
}
