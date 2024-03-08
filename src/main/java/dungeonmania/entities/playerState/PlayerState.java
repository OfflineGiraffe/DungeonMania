package dungeonmania.entities.playerState;

import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;

public class PlayerState {
        private Player player;
        private boolean isInvincible = false;
        private boolean isInvisible = false;

        public PlayerState(Player player, boolean isInvincible, boolean isInvisible) {
                this.player = player;
                this.isInvincible = isInvincible;
                this.isInvisible = isInvisible;

        }

        public void changeState(Player player, int tick) {
                if (getPlayer().getQueue().isEmpty()) {
                        getPlayer().setInEffective(null);
                        player.changeState(new BaseState(player));
                        return;
                }

                getPlayer().setInEffective(getPlayer().getQueue().remove());

                if (getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
                        player.changeState(new InvincibleState(player));
                } else {
                        player.changeState(new InvisibleState(player));
                }

                getPlayer().setNextTrigger(tick + getPlayer().getEffectivePotion().getDuration());
        }

        public boolean isInvincible() {
                return isInvincible;
        };

        public boolean isInvisible() {
                return isInvisible;
        };

        public Player getPlayer() {
                return player;
        }

}
