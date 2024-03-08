package dungeonmania.entities;

import dungeonmania.map.GameMap;

import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.enemies.Spider;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.util.Position;

public class Door extends Entity {
        private boolean open = false;
        private int number;

        public Door(Position position, int number) {
                super(position.asLayer(Entity.DOOR_LAYER));
                this.number = number;
        }

        @Override
        public boolean canMoveOnto(GameMap map, Entity entity) {
                if (open || entity instanceof Spider) {
                        return true;
                }
                return (entity instanceof Player && hasKeyOrSunStone((Player) entity));
        }

        @Override
        public void onOverlap(GameMap map, Entity entity) {
                if (!(entity instanceof Player))
                        return;

                Player player = (Player) entity;
                Inventory inventory = player.getInventory();
                SunStone sunStone = inventory.getFirst(SunStone.class);
                Key key = inventory.getFirst(Key.class);

                if (hasKeyOrSunStone(player) && sunStone != null) {
                        open();
                } else if (hasKeyOrSunStone(player) && key != null) {
                        inventory.remove(key);
                        open();
                }
        }

        private boolean hasKeyOrSunStone(Player player) {
                Inventory inventory = player.getInventory();
                SunStone sunStone = inventory.getFirst(SunStone.class);
                Key key = inventory.getFirst(Key.class);

                return (sunStone != null) || (key != null && key.getnumber() == number);
        }

        public boolean isOpen() {
                return open;
        }

        public void open() {
                open = true;
        }

}
