import java.util.List;
import java.util.Random;

public class GoalCategory {
    private String name;
    private List<String> exerciseMuscleGroups;
    private int minReps;
    private int maxReps;

    public GoalCategory(String name, List<String> exerciseMuscleGroups, int minReps, int maxReps) {
        this.name = name;
        this.exerciseMuscleGroups = exerciseMuscleGroups;
        this.minReps = minReps;
        this.maxReps = maxReps;
    }

    public String getName() {
        return name;
    }

    public List<String> getExerciseMuscleGroups() {
        return exerciseMuscleGroups;
    }

    public int getMinReps() {
        return minReps;
    }

    public int getMaxReps() {
        return maxReps;
    }

    public int getRandomRepRange() {
        // Generate a random number within the specified range
        Random random = new Random();
        return random.nextInt(maxReps - minReps + 1) + minReps;
    }
}