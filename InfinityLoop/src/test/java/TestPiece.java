import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import org.junit.jupiter.api.Test;
import static junit.framework.Assert.assertSame;

public class TestPiece {
    @Test
    void testTurn() {
        Piece piece = new Piece(0, 0, 1, 0);
        piece.turn();
        assertSame(piece.getOrientation(), Orientation.EAST);
        piece.turn();
        assertSame(piece.getOrientation(), Orientation.SOUTH);
        piece.turn();
        assertSame(piece.getOrientation(), Orientation.WEST);
        piece.turn();
        assertSame(piece.getOrientation(), Orientation.NORTH);
    }
}
