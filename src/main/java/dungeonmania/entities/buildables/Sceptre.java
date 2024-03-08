package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Mercenary;

public class Sceptre extends Buildable {
        private int duration;

        public Sceptre(int duration) {
                super(null);
                this.duration = duration;
        }

        public void interactWithEnemies(Game game, Mercenary enemy) {
                int tickCount = game.getTick();
                enemy.setAllied(true);
                enemy.setStopMindControlTick(tickCount + duration);
        }

}
