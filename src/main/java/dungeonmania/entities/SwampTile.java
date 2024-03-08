package dungeonmania.entities;

import dungeonmania.util.Position;
import dungeonmania.map.GameMap;

import dungeonmania.entities.enemies.Mercenary;

public class SwampTile extends Entity {
    public static final int DEFAULT_FACTOR = 2;
    private int movementFactor;

    public SwampTile(Position position, int movementFactor) {
        super(position.asLayer(Entity.FLOOR_LAYER));
        this.movementFactor = movementFactor;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        // player case
        if (entity instanceof Player) {
            return;
        }
        // allied adjacent entity case
        if (entity instanceof Mercenary) {
            Mercenary mercenary = (Mercenary) entity;
            if (mercenary.isAllied() && mercenary.isAdjacentToPlayer()) {
                entity.resetMovementDelay();
                return;
            }
        }
        if (entity.getMovementDelay() == 0 && entity.getPreviousPosition() != entity.getPosition()) {
            entity.setMovementDelay(movementFactor);
        }
    }
}
