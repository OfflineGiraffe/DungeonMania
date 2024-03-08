package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SunStoneTest {
        @Test
        @Tag("10-1")
        @DisplayName("Test player can walkthrough a door using a sunstone. the position will change")
        public void useSunStoneWalkThroughDoorWithSunStone() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_sunStoneTest_unlockDoorWithSunStone",
                                "c_sunStoneTest_unlockDoorWithSunStone");

                // pick up sun stone
                res = dmc.tick(Direction.RIGHT);

                Position position = TestUtils.getEntities(res, "player").get(0).getPosition();
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

                // walk through door and check sun stone stays
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
                assertNotEquals(position, TestUtils.getEntities(res, "player").get(0).getPosition());
        }

        @Test
        @Tag("10-2")
        @DisplayName("Test player can use a sun stone to fulfill treasure goal")
        public void sunStoneTreasureGoalWithSunStones() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_sunStoneTest_treasureGoalAchieved",
                                "c_sunStoneTest_treasureGoalAchieved");

                // move player to right
                res = dmc.tick(Direction.RIGHT);

                // assert goal not met
                assertTrue(TestUtils.getGoals(res).contains(":treasure"));

                // collect treasure
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

                assertNotEquals(3, TestUtils.getInventory(res, "sun_stone").size());

                // collect treasure
                res = dmc.tick(Direction.RIGHT);
                assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());

                // assert goal not met
                assertTrue(TestUtils.getGoals(res).contains(":treasure"));

                // collect treasure
                res = dmc.tick(Direction.RIGHT);
                assertEquals(3, TestUtils.getInventory(res, "sun_stone").size());

                // assert goal met
                assertEquals("", TestUtils.getGoals(res));
        }

}
