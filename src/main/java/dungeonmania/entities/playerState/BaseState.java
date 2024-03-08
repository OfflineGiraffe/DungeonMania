package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Player;

public class BaseState extends PlayerState {
        public static final int DEFAULT_BASTESTATE_HEALTH = 0;
        public static final int DEFAULT_BASTESTATE_ATTACK = 0;
        public static final int DEFAULT_BASTESTATE_DEFENCE = 0;
        public static final int DEFAULT_BASTESTATE_ATTACK_MAGNIFIER = 0;
        public static final int DEFAULT_BASTESTATE_DAMAGEREDUCER = 0;

        public BaseState(Player player) {
                super(player, false, false);
        }

        public BattleStatistics applyBuff(BattleStatistics origin) {
                return BattleStatistics.applyBuff(origin, createNewBaseState());
        }

        public BattleStatistics createNewBaseState() {
                return new BattleStatistics(DEFAULT_BASTESTATE_HEALTH, DEFAULT_BASTESTATE_ATTACK,
                                DEFAULT_BASTESTATE_DEFENCE, DEFAULT_BASTESTATE_ATTACK_MAGNIFIER,
                                DEFAULT_BASTESTATE_DAMAGEREDUCER, false, false);
        }

}
