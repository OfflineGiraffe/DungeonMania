package dungeonmania.entities.enemies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.Game;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class RandomMovement implements MovementStrategy {
        private Random randGen = new Random();

        @Override
        public Position move(Enemy enemy, Game game) {
                GameMap map = game.getMap();
                List<Position> pos = enemy.getPosition().getCardinallyAdjacentPositions();
                pos = pos.stream().filter(p -> map.canMoveTo(enemy, p)).collect(Collectors.toList());
                if (pos.size() == 0) {
                        return enemy.getPosition();
                } else {
                        return pos.get(randGen.nextInt(pos.size()));
                }
        }

}
