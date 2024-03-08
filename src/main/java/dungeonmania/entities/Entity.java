package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import java.util.UUID;

public abstract class Entity {
    public static final int FLOOR_LAYER = 0;
    public static final int ITEM_LAYER = 1;
    public static final int DOOR_LAYER = 2;
    public static final int CHARACTER_LAYER = 3;

    private Position position;
    private Position previousPosition;
    private Position previousDistinctPosition;
    private Direction facing;
    private String entityId;

    private int movementDelay;

    public Entity(Position position) {
        this.position = position;
        this.previousPosition = position;
        this.previousDistinctPosition = null;
        this.entityId = UUID.randomUUID().toString();
        this.facing = null;
        this.movementDelay = 0;
    }

    public boolean canMoveOnto(GameMap map, Entity entity) {
        return false;
    }

    // use setPosition
    @Deprecated(forRemoval = true)
    public void translate(Direction direction) {
        previousPosition = this.position;
        if (movementDelay == 0) {
            this.position = Position.translateBy(this.position, direction);
            if (!previousPosition.equals(this.position)) {
                previousDistinctPosition = previousPosition;
            }
        } else {
            this.movementDelay -= 1;
        }
    }

    // use setPosition
    @Deprecated(forRemoval = true)
    public void translate(Position offset) {
        if (movementDelay == 0) {
            this.position = Position.translateBy(this.position, offset);
        } else {
            this.movementDelay -= 1;
        }
    }

    public void onOverlap(GameMap map, Entity entity) {
        return;
    }

    public void onMovedAway(GameMap map, Entity entity) {
        return;
    }

    public void onDestroy(GameMap gameMap) {
        return;
    }

    public Position getPosition() {
        return position;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public Position getPreviousDistinctPosition() {
        return previousDistinctPosition;
    }

    public String getId() {
        return entityId;
    }

    public void setPosition(Position position) {
        previousPosition = this.position;
        if (movementDelay == 0) {
            this.position = position;
            if (!previousPosition.equals(this.position)) {
                previousDistinctPosition = previousPosition;
            }
        } else {
            movementDelay = movementDelay - 1;
        }
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public Direction getFacing() {
        return this.facing;
    }

    public void resetMovementDelay() {
        this.movementDelay = 0;
    }

    public int getMovementDelay() {
        return this.movementDelay;
    }

    public void setMovementDelay(int amount) {
        this.movementDelay = amount;
    }

}
