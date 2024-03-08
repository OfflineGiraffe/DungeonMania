package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class PlayerMovementStrategy implements MovementStrategy {
        @Override
        public Position move(Enemy enemy, Game game) {
                GameMap map = game.getMap();
                Player player = game.getPlayer();
                Position plrDiff = Position.calculatePositionBetween(player.getPosition(), enemy.getPosition());
                Position moveX = Position.translateBy(enemy.getPosition(),
                                (plrDiff.getX() >= 0) ? Direction.RIGHT : Direction.LEFT);
                Position moveY = Position.translateBy(enemy.getPosition(),
                                (plrDiff.getY() >= 0) ? Direction.UP : Direction.DOWN);

                if (plrDiff.getY() == 0 && map.canMoveTo(enemy, moveX)) {
                        return moveX;
                } else if (plrDiff.getX() == 0 && map.canMoveTo(enemy, moveY)) {
                        return moveY;
                } else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
                        if (map.canMoveTo(enemy, moveX)) {
                                return moveX;
                        } else if (map.canMoveTo(enemy, moveY)) {
                                return moveY;
                        }
                } else {
                        if (map.canMoveTo(enemy, moveY)) {
                                return moveY;
                        } else if (map.canMoveTo(enemy, moveX)) {
                                return moveX;
                        }
                }

                // If no movement is possible, return the current position
                return enemy.getPosition();
        }
}
