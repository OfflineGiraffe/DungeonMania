package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.util.Position;

public abstract class Spawner extends Entity {
    public Spawner(Position position, int spawnInterval) {
        super(position);
    }

    public abstract void spawn(Game game);
}
