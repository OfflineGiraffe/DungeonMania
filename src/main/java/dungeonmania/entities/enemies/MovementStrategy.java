package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.util.Position;

public interface MovementStrategy {

        Position move(Enemy enemy, Game game);

}
