package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Player;

public class InvisibleState extends PlayerState {
        public static final int DEFAULT_INVISIBLESTATE_HEALTH = 0;
        public static final int DEFAULT_INVISIBLESTATE_ATTACK = 0;
        public static final int DEFAULT_INVISIBLESTATE_DEFENCE = 0;
        public static final int DEFAULT_INVISIBLESTATE_ATTACK_MAGNIFIER = 1;
        public static final int DEFAULT_INVISIBLESTATE_DAMAGEREDUCER = 1;

        public InvisibleState(Player player) {
                super(player, false, true);
        }

        public BattleStatistics applyBuff(BattleStatistics origin) {
                return BattleStatistics.applyBuff(origin, createNewInvisibleBuff());
        }

        public BattleStatistics createNewInvisibleBuff() {
                return new BattleStatistics(DEFAULT_INVISIBLESTATE_HEALTH, DEFAULT_INVISIBLESTATE_ATTACK,
                                DEFAULT_INVISIBLESTATE_DEFENCE, DEFAULT_INVISIBLESTATE_ATTACK_MAGNIFIER,
                                DEFAULT_INVISIBLESTATE_DAMAGEREDUCER, false, false);
        }
}
