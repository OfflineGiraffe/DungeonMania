package dungeonmania.entities.collectables.potions;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.util.Position;

public class InvincibilityPotion extends Potion {
        public static final int DEFAULT_INVINCIBILE_HEALTH = 0;
        public static final int DEFAULT_INVINCIBILE_ATTACK = 0;
        public static final int DEFAULT_INVINCIBILE_DEFENCE = 0;
        public static final int DEFAULT_INVINCIBILE_ATTACK_MAGNIFIER = 1;
        public static final int DEFAULT_INVINCIBILE_DAMAGEREDUCER = 1;

        public static final int DEFAULT_DURATION = 8;

        public InvincibilityPotion(Position position, int duration) {
                super(position, duration);
        }

        @Override
        public BattleStatistics applyBuff(BattleStatistics origin) {
                return BattleStatistics.applyBuff(origin, createNewBattleStatistics());
        }

        private BattleStatistics createNewBattleStatistics() {
                return new BattleStatistics(DEFAULT_INVINCIBILE_HEALTH, DEFAULT_INVINCIBILE_ATTACK,
                                DEFAULT_INVINCIBILE_DEFENCE, DEFAULT_INVINCIBILE_ATTACK_MAGNIFIER,
                                DEFAULT_INVINCIBILE_DAMAGEREDUCER, true, true);
        }

}
