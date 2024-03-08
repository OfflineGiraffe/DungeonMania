package dungeonmania.goals.states;

import dungeonmania.Game;
import dungeonmania.entities.Switch;

public class BouldersGoalState implements GoalState {
    @Override
    public boolean achieved(Game game) {
        return game.getMap().getEntities(Switch.class).stream().allMatch(s -> s.isActivated());
    }

    @Override
    public String toString(Game game) {
        return ":boulders";
    }
}
