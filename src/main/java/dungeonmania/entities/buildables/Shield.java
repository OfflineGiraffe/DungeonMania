package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;

public class Shield extends Buildable implements BattleItem {
        public static final int DEFAULT_SHIELD_HEALTH = 0;
        public static final int DEFAULT_SHIELD_ATTACK = 0;
        public static final int DEFAULT_SHIELD_ATTACK_MAGNIFIER = 1;
        public static final int DEFAULT_SHIELD_DAMAGEREDUCER = 1;

        private int durability;
        private double defence;

        public Shield(int durability, double defence) {
                super(null);
                this.durability = durability;
                this.defence = defence;
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
                return new BattleStatistics(DEFAULT_SHIELD_HEALTH, DEFAULT_SHIELD_ATTACK, defence,
                                DEFAULT_SHIELD_ATTACK_MAGNIFIER, DEFAULT_SHIELD_DAMAGEREDUCER);
        }

        private void destroyItem(Game game) {
                game.getPlayer().remove(this);
        }

        @Override
        public int getDurability() {
                return durability;
        }

}
