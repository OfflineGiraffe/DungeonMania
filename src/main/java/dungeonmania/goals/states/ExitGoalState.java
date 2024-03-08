package dungeonmania.goals.states;

import dungeonmania.Game;
import dungeonmania.entities.Player;

import java.util.List;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Exit;
import dungeonmania.util.Position;

public class ExitGoalState implements GoalState {
    @Override
    public boolean achieved(Game game) {
        Player character = game.getPlayer();
        Position pos = character.getPosition();
        List<Exit> es = game.getMap().getEntities(Exit.class);
        if (es == null || es.size() == 0)
            return false;
        return es.stream().map(Entity::getPosition).anyMatch(pos::equals);
    }

    @Override
    public String toString(Game game) {
        return ":exit";
    }
}
