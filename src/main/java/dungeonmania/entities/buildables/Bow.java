package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;

public class Bow extends Buildable implements BattleItem {
        private int durability;
        public static final int DEFAULT_BOW_HEALTH = 0;
        public static final int DEFAULT_BOW_ATTACK = 0;
        public static final int DEFAULT_BOW_DEFENCE = 0;
        public static final int DEFAULT_BOW_ATTACK_MAGNIFIER = 2;
        public static final int DEFAULT_BOW_DAMAGEREDUCER = 1;

        public Bow(int durability) {
                super(null);
                this.durability = durability;
        }

        @Override
        public void use(Game game) {
                durability--;
                if (durability <= 0) {
                        destroyItem(game);
                }
        }

        @Override
        public BattleStatistics applyBuff(BattleStatistics origin) {
                return BattleStatistics.applyBuff(origin, createNewBattleStatistics());
        }

        private BattleStatistics createNewBattleStatistics() {
                return new BattleStatistics(DEFAULT_BOW_HEALTH, DEFAULT_BOW_ATTACK, DEFAULT_BOW_DEFENCE,
                                DEFAULT_BOW_ATTACK_MAGNIFIER, DEFAULT_BOW_DAMAGEREDUCER);
        }

        private void destroyItem(Game game) {
                game.getPlayer().remove(this);
        }

        @Override
        public int getDurability() {
                return durability;
        }
}
