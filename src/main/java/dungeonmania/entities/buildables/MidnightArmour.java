package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;

public class MidnightArmour extends Buildable implements BattleItem {
        public static final int DEFAULT_MIDNIGHTARMOUR_HEALTH = 0;
        public static final int DEFAULT_MIDNIGHTARMOUR_ATTACK = 0;
        public static final int DEFAULT_MIDNIGHTARMOUR_DAMAGEREDUCER = 0;

        private int midnightArmourAttack;
        private int midnightArmourDefense;

        public MidnightArmour(int midnightArmourAttack, int midnightArmourDefense) {
                super(null);
                this.midnightArmourAttack = midnightArmourAttack;
                this.midnightArmourDefense = midnightArmourDefense;
        }

        @Override
        public BattleStatistics applyBuff(BattleStatistics origin) {
                return BattleStatistics.applyBuff(origin, createNewBattleStatistics());
        }

        private BattleStatistics createNewBattleStatistics() {
                return new BattleStatistics(DEFAULT_MIDNIGHTARMOUR_HEALTH, DEFAULT_MIDNIGHTARMOUR_ATTACK,
                                midnightArmourDefense, midnightArmourAttack, DEFAULT_MIDNIGHTARMOUR_DAMAGEREDUCER);
        }

        @Override
        public void use(Game game) {
                // do nothing
        }

        @Override
        public int getDurability() {
                return 0;
        }
}
