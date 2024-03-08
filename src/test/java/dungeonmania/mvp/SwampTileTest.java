package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SwampTileTest {
    @Test
    @Tag("17-1")
    @DisplayName("Test player not affected by swamp tile")
    public void player() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicSwampTileTest_player", "c_basicSwampTileTest_player");

        // move player to right
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);

        // expected 1 past swamp tile
        Position expected = new Position(2, 1, 0);
        assertEquals(TestUtils.getPlayerPos(res), expected);
    }

    @Test
    @Tag("17-2")
    @DisplayName("Test zombieToast stuck for 4 ticks")
    public void zombieToast() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicSwampTileTest_zombie", "c_basicSwampTileTest_player");
        ArrayList<Position> positions = new ArrayList<>();

        // All swamp tile positions
        positions.add(new Position(3, 1, 0));
        positions.add(new Position(2, 0, 0));
        positions.add(new Position(2, 2, 0));
        positions.add(new Position(1, 1, 0));

        // move player to left
        res = dmc.tick(Direction.LEFT);

        // assert zombie is in swamp tile
        assertTrue(positions.contains(TestUtils.getEntityPos(res, "zombie_toast")));

        // move for 4 ticks
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.LEFT);

        // assert zombie is in swamp tile
        assertTrue(positions.contains(TestUtils.getEntityPos(res, "zombie_toast")));

        // move player to right
        res = dmc.tick(Direction.RIGHT);

        // assert zombie is no longer in swamp tile
        assertFalse(positions.contains(TestUtils.getEntityPos(res, "zombie_toast")));

    }

    @Test
    @Tag("17-3")
    @DisplayName("Test spider stuck for 4 ticks")
    public void spider() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicSwampTileTest_spider", "c_basicSwampTileTest_player");
        Position position = new Position(2, 0, 0);

        // move player to left
        res = dmc.tick(Direction.LEFT);

        // assert spider is in swamp tile
        assertEquals(position, TestUtils.getEntityPos(res, "spider"));

        // move for 4 ticks
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);

        // assert spider is in swamp tile
        assertEquals(position, TestUtils.getEntityPos(res, "spider"));

        // move player to right
        res = dmc.tick(Direction.RIGHT);

        // assert spider is no longer in swamp tile
        assertNotEquals(position, TestUtils.getEntityPos(res, "spider"));
    }

    @Test
    @Tag("17-4")
    @DisplayName("Test mercenary not allied stuck for 4 ticks")
    public void mercenary() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicSwampTileTest_mercenary", "c_basicSwampTileTest_player");
        Position position = new Position(1, 1, 0);

        // move player to left
        res = dmc.tick(Direction.LEFT);

        // assert mercenary is in swamp tile
        assertEquals(position, TestUtils.getEntityPos(res, "mercenary"));

        // move for 4 ticks
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);

        // assert mercenary is in swamp tile
        assertEquals(position, TestUtils.getEntityPos(res, "mercenary"));

        // move player to right
        res = dmc.tick(Direction.RIGHT);

        // assert spider is no longer in swamp tile
        assertNotEquals(position, TestUtils.getEntityPos(res, "mercenary"));
    }

    @Test
    @Tag("17-5")
    @DisplayName("Test mercenary allied not stuck")
    public void alliedMercenary() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicSwampTileTest_mercenary", "c_basicSwampTileTest_player");
        String mercenaryId = TestUtils.getEntities(res, "mercenary").get(0).getId();
        Position position = new Position(1, 1, 0);

        // move player to left
        res = dmc.tick(Direction.LEFT);

        // assert mercenary is in swamp tile
        assertEquals(position, TestUtils.getEntityPos(res, "mercenary"));

        // bribe the mercenary
        res = assertDoesNotThrow(() -> dmc.interact(mercenaryId));

        // move for 2 ticks
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);

        // assert spider is no longer in swamp tile
        assertNotEquals(position, TestUtils.getEntityPos(res, "mercenary"));
    }
}
