package dungeonmania.entities.collectables.potions;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.collectables.Collectables;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Potion extends Collectables implements BattleItem {
        private int duration;

        public Potion(Position position, int duration) {
                super(position);
                this.duration = duration;
        }

        @Override
        public void onDestroy(GameMap gameMap) {
                return;
        }

        @Override
        public void onMovedAway(GameMap map, Entity entity) {
                return;
        }

        @Override
        public void use(Game game) {
                return;
        }

        public int getDuration() {
                return duration;
        }

        @Override
        public BattleStatistics applyBuff(BattleStatistics origin) {
                return origin;
        }

        @Override
        public int getDurability() {
                return 1;
        }
}
