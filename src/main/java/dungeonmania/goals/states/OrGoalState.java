package dungeonmania.goals.states;

import dungeonmania.Game;
import dungeonmania.goals.Goal;

public class OrGoalState implements GoalState {
    private Goal goal1;
    private Goal goal2;

    public OrGoalState(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    @Override
    public boolean achieved(Game game) {
        return goal1.achieved(game) || goal2.achieved(game);
    }

    @Override
    public String toString(Game game) {
        if (achieved(game)) {
            return "";
        } else {
            return "(" + goal1.toString(game) + " OR " + goal2.toString(game) + ")";
        }
    }
}
