import java.util.List;

public class WorkoutPlan {
    private String goal;
    private List<Exercise> exercises;
    private int minReps;
    private int maxReps;

    public WorkoutPlan(String goal, List<Exercise> exercises, int minReps, int maxReps) {
        this.goal = goal;
        this.exercises = exercises;
        this.minReps = minReps;
        this.maxReps = maxReps;
    }

    public String getGoal() {
        return goal;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public int getMinReps() {
        return minReps;
    }

    public int getMaxReps() {
        return maxReps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Goal: ").append(goal).append("\n");
        sb.append("Rep Range: ").append(minReps).append(" - ").append(maxReps).append("\n");
        sb.append("Exercises:\n");
        for (Exercise exercise : exercises) {
            sb.append(exercise.getName()).append(" - ").append(exercise.getMuscleGroup());
            if (exercise.getSets() > 0) {
                sb.append(" - Sets: ").append(exercise.getSets());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
