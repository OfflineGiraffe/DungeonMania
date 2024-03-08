package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.entities.Player;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
    }

    private MovementStrategy movementStrategy;

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    @Override
    public void move(Game game) {
        Position nextPos;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        if (player.getEffectivePotion() instanceof InvincibilityPotion) {
            this.movementStrategy = new PlayerMovementStrategy();
            Position offset = movementStrategy.move(this, game);
            nextPos = offset;
        } else {
            this.movementStrategy = new RandomMovement();
            nextPos = movementStrategy.move(this, game);

        }
        map.moveTo(this, nextPos);

    }

}
