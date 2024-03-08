package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuildablesTest {
        @Test
        @Tag("5-1")
        @DisplayName("Test IllegalArgumentException is raised when attempting to build an unknown entity - sword")
        public void buildSwordIllegalArgumentException() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                dmc.newGame("d_BuildablesTest_BuildSwordIllegalArgumentException",
                                "c_BuildablesTest_BuildSwordIllegalArgumentException");
                assertThrows(IllegalArgumentException.class, () -> dmc.build("sword"));
        }

        @Test
        @Tag("5-2")
        @DisplayName("Test InvalidActionException is raised when the player "
                        + "does not have sufficient items to build a bow or shield")
        public void buildInvalidActionException() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                dmc.newGame("d_BuildablesTest_BuildInvalidArgumentException",
                                "c_BuildablesTest_BuildInvalidArgumentException");
                assertThrows(InvalidActionException.class, () -> dmc.build("bow"));

                assertThrows(InvalidActionException.class, () -> dmc.build("shield"));
        }

        @Test
        @Tag("5-3")
        @DisplayName("Test building a bow")
        public void buildBow() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildBow", "c_BuildablesTest_BuildBow");

                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "arrow").size());

                // Pick up Wood
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "wood").size());

                // Pick up Arrow x3
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);
                assertEquals(3, TestUtils.getInventory(res, "arrow").size());

                // Build Bow
                assertEquals(0, TestUtils.getInventory(res, "bow").size());
                res = assertDoesNotThrow(() -> dmc.build("bow"));
                assertEquals(1, TestUtils.getInventory(res, "bow").size());

                // Materials used in construction disappear from inventory
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        }

        @Test
        @Tag("5-4")
        @DisplayName("Test building a shield with a key")
        public void buildShieldWithKey() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildShieldWithKey",
                                "c_BuildablesTest_BuildShieldWithKey");

                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "key").size());

                // Pick up Wood x2
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);
                assertEquals(2, TestUtils.getInventory(res, "wood").size());

                // Pick up Key
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "key").size());

                // Build Shield
                assertEquals(0, TestUtils.getInventory(res, "shield").size());
                res = assertDoesNotThrow(() -> dmc.build("shield"));
                assertEquals(1, TestUtils.getInventory(res, "shield").size());

                // Materials used in construction disappear from inventory
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "key").size());
        }

        @Test
        @Tag("5-5")
        @DisplayName("Test building a shield with treasure")
        public void buildShieldWithTreasure() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildShieldWithTreasure",
                                "c_BuildablesTest_BuildShieldWithTreasure");
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "treasure").size());

                // Pick up Wood x2
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);
                assertEquals(2, TestUtils.getInventory(res, "wood").size());

                // Pick up Treasure
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "treasure").size());

                // Build Shield
                assertEquals(0, TestUtils.getInventory(res, "shield").size());
                res = assertDoesNotThrow(() -> dmc.build("shield"));
                assertEquals(1, TestUtils.getInventory(res, "shield").size());

                // Materials used in construction disappear from inventory
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        }

        @Test
        @Tag("5-6")
        @DisplayName("Test responsse buildables parameter is a list of buildables that the player can currently build")
        public void dungeonResponseBuildables() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_DungeonResponseBuildables",
                                "c_BuildablesTest_DungeonResponseBuildables");

                List<String> buildables = new ArrayList<>();
                assertEquals(buildables, res.getBuildables());

                // Gather entities to build bow
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);

                // Bow added to buildables list
                buildables.add("bow");
                assertEquals(buildables, res.getBuildables());

                // Gather entities to build shield
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);

                // Shield added to buildables list
                buildables.add("shield");
                assertEquals(buildables.size(), res.getBuildables().size());
                assertTrue(buildables.containsAll(res.getBuildables()));
                assertTrue(res.getBuildables().containsAll(buildables));

                // Build bow
                res = assertDoesNotThrow(() -> dmc.build("bow"));
                assertEquals(1, TestUtils.getInventory(res, "bow").size());

                // Bow disappears from buildables list
                buildables.remove("bow");
                assertEquals(buildables, res.getBuildables());

                // Build shield
                res = assertDoesNotThrow(() -> dmc.build("shield"));
                assertEquals(1, TestUtils.getInventory(res, "shield").size());

                // Shield disappears from buildables list
                buildables.remove("shield");
                assertEquals(buildables, res.getBuildables());
        }

        @Test
        @Tag("5-7")
        @DisplayName("Test building a sceptre with wood, key, sunstone")
        public void buildSceptreWithWood() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildSceptreWithWood",
                                "c_BuildablesTest_buildSceptre");
                // Assert empty inventory
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
                assertEquals(0, TestUtils.getInventory(res, "key").size());

                // Pick up Wood
                res = dmc.tick(Direction.RIGHT);

                // Pick up Sun Stone
                res = dmc.tick(Direction.RIGHT);

                // Pick up Key
                res = dmc.tick(Direction.RIGHT);

                assertEquals(1, TestUtils.getInventory(res, "wood").size());
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
                assertEquals(1, TestUtils.getInventory(res, "key").size());

                // Build Sceptre
                assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
                res = assertDoesNotThrow(() -> dmc.build("sceptre"));
                assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

                // Materials used in construction disappear from inventory
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "key").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        }

        @Test
        @Tag("5-8")
        @DisplayName("Test building a sceptre with arrows, treasure, sunstone")
        public void buildSceptreWithArrows() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildSceptreWithArrows",
                                "c_BuildablesTest_buildSceptre");
                // Assert empty inventory
                assertEquals(0, TestUtils.getInventory(res, "arrow").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
                assertEquals(0, TestUtils.getInventory(res, "treasure").size());

                // Pick up Arrows twice
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);

                // Pick up Sun Stone
                res = dmc.tick(Direction.RIGHT);

                // Pick up Treasure
                res = dmc.tick(Direction.RIGHT);

                assertEquals(2, TestUtils.getInventory(res, "arrow").size());
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
                assertEquals(1, TestUtils.getInventory(res, "treasure").size());

                // Build Sceptre
                assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
                res = assertDoesNotThrow(() -> dmc.build("sceptre"));
                assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

                // Materials used in construction disappear from inventory
                assertEquals(0, TestUtils.getInventory(res, "arrow").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
                assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        }

        @Test
        @Tag("5-9")
        @DisplayName("Test building a sceptre with wood, sun_stone, sun_stone")
        public void buildSceptreWithSunStoneReplacement() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildSceptreWithSunStone",
                                "c_BuildablesTest_buildSceptre");
                // Assert empty inventory
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

                // Pick up Wood
                res = dmc.tick(Direction.RIGHT);

                // Pick up Sun Stone twice
                res = dmc.tick(Direction.RIGHT);
                res = dmc.tick(Direction.RIGHT);

                assertEquals(1, TestUtils.getInventory(res, "wood").size());
                assertEquals(2, TestUtils.getInventory(res, "sun_stone").size());

                // Check treasure and key numbers are equal to 0
                assertEquals(0, TestUtils.getInventory(res, "treasure").size());
                assertEquals(0, TestUtils.getInventory(res, "key").size());

                // Build Sceptre
                assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
                res = assertDoesNotThrow(() -> dmc.build("sceptre"));
                assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

                // Materials used in construction disappear from inventory except for retained sun stone
                assertEquals(0, TestUtils.getInventory(res, "wood").size());
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        }

        @Test
        @Tag("5-10")
        @DisplayName("Test use Sceptre")
        public void sceptreUse() throws IllegalArgumentException, InvalidActionException {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_SceptreUsage", "c_BuildablesTest_SceptreUsage");
                String mercenaryId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();
                assertEquals(1, TestUtils.getEntities(res, "mercenary").size());

                // Pick up Wood
                res = dmc.tick(Direction.RIGHT);

                // Pick up Sun Stone
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

                // Pick up Key
                res = dmc.tick(Direction.RIGHT);

                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
                assertEquals(1, TestUtils.getInventory(res, "wood").size());
                assertEquals(1, TestUtils.getInventory(res, "key").size());

                // Build Sceptre
                assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
                res = assertDoesNotThrow(() -> dmc.build("sceptre"));
                assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

                // Interact with merc and assassin and sceptre is kept
                assertEquals(1, TestUtils.getInventory(res, "sceptre").size());
                res = assertDoesNotThrow(() -> dmc.interact(mercenaryId));
                assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

                // Assert that enemies are now allied
                assertEquals(true, TestUtils.checkifAllied(res, mercenaryId));

                // Tick twice
                res = dmc.tick(Direction.LEFT);
                res = dmc.tick(Direction.LEFT);

                // Assert mind control has finished and mercs/assassins are not allied
                assertEquals(false, TestUtils.checkifAllied(res, mercenaryId));
        }

        @Test
        @Tag("5-11")
        @DisplayName("Test building midnight armour with a sword and sun stone")
        public void buildMidnightArmour() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildMidnightArmour",
                                "c_BuildablesTest_BuildMidnightArmour");
                // Assert empty inventory
                assertEquals(0, TestUtils.getInventory(res, "sword").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

                // Pick up Sword
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "sword").size());

                // Pick up Sun Stone
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

                // Build Midnight Armour
                assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
                res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
                assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

                // Materials used in construction disappear from inventory
                assertEquals(0, TestUtils.getInventory(res, "sword").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());
        }

        @Test
        @Tag("5-12")
        @DisplayName("Test building midnight armour with a sword and sun stone with zombie toasts. this test fails")
        public void buildMidnightArmourFail() {
                DungeonManiaController dmc;
                dmc = new DungeonManiaController();
                DungeonResponse res = dmc.newGame("d_BuildablesTest_BuildMidnightArmourWithZombie",
                                "c_BuildablesTest_BuildMidnightArmour");
                // Assert empty inventory
                assertEquals(0, TestUtils.getInventory(res, "sword").size());
                assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

                // Pick up Sword
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "sword").size());

                // Pick up Sun Stone
                res = dmc.tick(Direction.RIGHT);
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

                // Build Midnight Armour
                assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
                assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));
                assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());

                // Assert materials not used
                assertEquals(1, TestUtils.getInventory(res, "sword").size());
                assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        }
}
