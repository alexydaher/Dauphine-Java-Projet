import fr.dauphine.JavaAvance.Components.Orientation;
import org.junit.jupiter.api.Test;
import static junit.framework.Assert.assertSame;

public class TestOrientation {
    @Test
    void testOpposedPieceOrientation() {
        assertSame(Orientation.NORTH.getOpposedOrientation(), Orientation.SOUTH);
        assertSame(Orientation.SOUTH.getOpposedOrientation(), Orientation.NORTH);
        assertSame(Orientation.WEST.getOpposedOrientation(), Orientation.EAST);
        assertSame(Orientation.EAST.getOpposedOrientation(), Orientation.WEST);
    }

    @Test
    void testTurn90() {
        assertSame(Orientation.NORTH.turn90(), Orientation.EAST);
        assertSame(Orientation.EAST.turn90(), Orientation.SOUTH);
        assertSame(Orientation.SOUTH.turn90(), Orientation.WEST);
        assertSame(Orientation.WEST.turn90(), Orientation.NORTH);
    }
}
