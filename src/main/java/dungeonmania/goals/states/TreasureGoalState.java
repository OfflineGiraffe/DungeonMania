package dungeonmania.goals.states;

import dungeonmania.Game;

public class TreasureGoalState implements GoalState {
    private int target;

    public TreasureGoalState(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        return game.getCollectedTreasureCount() >= target;
    }

    @Override
    public String toString(Game game) {
        return ":treasure";
    }
}
