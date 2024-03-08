package dungeonmania.goals.states;

import dungeonmania.Game;

public interface GoalState {
    boolean achieved(Game game);

    String toString(Game game);
}
