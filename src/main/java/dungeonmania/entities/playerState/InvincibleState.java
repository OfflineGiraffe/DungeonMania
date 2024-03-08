package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Player;

public class InvincibleState extends PlayerState {
        public static final int DEFAULT_INVINCIBLESTATE_HEALTH = 0;
        public static final int DEFAULT_INVINCIBLESTATE_ATTACK = 0;
        public static final int DEFAULT_INVINCIBLESTATE_DEFENCE = 0;
        public static final int DEFAULT_INVINCIBLESTATE_ATTACK_MAGNIFIER = 1;
        public static final int DEFAULT_INVINCIBLESTATE_DAMAGEREDUCER = 1;

        public InvincibleState(Player player) {
                super(player, true, false);
        }

        public BattleStatistics applyBuff(BattleStatistics origin) {
                return BattleStatistics.applyBuff(origin, createNewInvincibleBuff());
        }

        public BattleStatistics createNewInvincibleBuff() {
                return new BattleStatistics(DEFAULT_INVINCIBLESTATE_HEALTH, DEFAULT_INVINCIBLESTATE_ATTACK,
                                DEFAULT_INVINCIBLESTATE_DEFENCE, DEFAULT_INVINCIBLESTATE_ATTACK_MAGNIFIER,
                                DEFAULT_INVINCIBLESTATE_DAMAGEREDUCER, true, true);
        }
}
