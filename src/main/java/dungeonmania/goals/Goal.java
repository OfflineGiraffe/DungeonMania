package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.goals.states.GoalState;

public class Goal {
    private GoalState state;
    private Goal goal1;
    private Goal goal2;

    public Goal(GoalState state) {
        this.state = state;
    }

    public Goal(GoalState state, Goal goal1, Goal goal2) {
        this.state = state;
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public boolean achieved(Game game) {
        if (game.getPlayer() == null) {
            return false;
        }
        return state.achieved(game);
    }

    public String toString(Game game) {
        if (this.achieved(game)) {
            return "";
        }
        return state.toString(game);
    }
}
