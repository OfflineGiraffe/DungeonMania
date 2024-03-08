package dungeonmania.goals.states;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Spawner;

public class EnemiesGoalState implements GoalState {
    private int target;

    public EnemiesGoalState(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        return game.getEnemiesDestroyed() >= target && game.getMap().getEntities(Spawner.class).size() <= 0;
    }

    @Override
    public String toString(Game game) {
        return ":enemies";
    }
}
