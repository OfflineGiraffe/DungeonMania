package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyGoalTest {
    @Test
    @Tag("16-1")
    @DisplayName("Test 1 enemy and no spawner")
    public void enemies() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicEnemyGoalTest_noSpawner", "c_basicEnemyGoalTest_noSpawner");
        assertTrue(TestUtils.countEntityOfType(res.getEntities(), "spider") == 1);

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // move player to right
        res = dmc.tick(Direction.RIGHT);

        // check if spider was destroyed
        assertTrue(TestUtils.countEntityOfType(res.getEntities(), "spider") == 0);

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("16-2")
    @DisplayName("Test 1 spawner and no enemy")
    public void spawner() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicEnemyGoalTest_noEnemy", "c_basicEnemyGoalTest_noEnemy");
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());
        String spawnerId = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();

        // cardinally adjacent: true, has sword: false
        assertThrows(InvalidActionException.class, () -> dmc.interact(spawnerId));
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());

        // pick up sword
        res = dmc.tick(Direction.LEFT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // cardinally adjacent: false, has sword: true
        assertThrows(InvalidActionException.class, () -> dmc.interact(spawnerId));
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());

        // move right
        res = dmc.tick(Direction.RIGHT);

        // cardinally adjacent: true, has sword: true, but invalid_id
        assertThrows(IllegalArgumentException.class, () -> dmc.interact("random_invalid_id"));

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // cardinally adjacent: true, has sword: true
        res = assertDoesNotThrow(() -> dmc.interact(spawnerId));
        assertEquals(0, TestUtils.countType(res, "zombie_toast_spawner"));

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("16-3")
    @DisplayName("Test complex goal achievement")
    public void complex() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicEnemyGoalTest_complex", "c_basicEnemyGoalTest_complex");
        String spawnerId = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        String spawnerId2 = TestUtils.getEntities(res, "zombie_toast_spawner").get(1).getId();

        assertTrue(TestUtils.countEntityOfType(res.getEntities(), "spider") == 1);
        assertTrue(TestUtils.countEntityOfType(res.getEntities(), "zombie_toast_spawner") == 2);

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // move player to right
        res = dmc.tick(Direction.RIGHT);

        // check if spider was destroyed
        assertTrue(TestUtils.countEntityOfType(res.getEntities(), "spider") == 0);

        // move player to sword and then to spawner
        res = dmc.tick(Direction.LEFT);
        res = dmc.tick(Direction.LEFT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());
        res = dmc.tick(Direction.RIGHT);

        // cardinally adjacent: true, has sword: true
        res = assertDoesNotThrow(() -> dmc.interact(spawnerId));
        assertEquals(1, TestUtils.countType(res, "zombie_toast_spawner"));

        // assert goal not met
        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        // move player to sword and then to spawner
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.LEFT);
        assertEquals(2, TestUtils.getInventory(res, "sword").size());
        res = dmc.tick(Direction.RIGHT);

        // cardinally adjacent: true, has sword: true
        res = assertDoesNotThrow(() -> dmc.interact(spawnerId2));
        assertEquals(0, TestUtils.countType(res, "zombie_toast_spawner"));

        // assert goal met
        assertEquals("", TestUtils.getGoals(res));
    }

}
