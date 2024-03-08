package dungeonmania.entities.collectables.potions;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.util.Position;

public class InvisibilityPotion extends Potion {
        public static final int DEFAULT_INVISIBLE_HEALTH = 0;
        public static final int DEFAULT_INVISIBLE_ATTACK = 0;
        public static final int DEFAULT_INVISIBLE_DEFENCE = 0;
        public static final int DEFAULT_INVISIBLE_ATTACK_MAGNIFIER = 1;
        public static final int DEFAULT_INVISIBLE_DAMAGEREDUCER = 1;

        public static final int DEFAULT_DURATION = 8;

        public InvisibilityPotion(Position position, int duration) {
                super(position, duration);
        }

        @Override
        public BattleStatistics applyBuff(BattleStatistics origin) {
                return BattleStatistics.applyBuff(origin, createNewBattleStatistics());
        }

        private BattleStatistics createNewBattleStatistics() {
                return new BattleStatistics(DEFAULT_INVISIBLE_HEALTH, DEFAULT_INVISIBLE_ATTACK,
                                DEFAULT_INVISIBLE_DEFENCE, DEFAULT_INVISIBLE_ATTACK_MAGNIFIER,
                                DEFAULT_INVISIBLE_DAMAGEREDUCER, false, false);
        }

}
