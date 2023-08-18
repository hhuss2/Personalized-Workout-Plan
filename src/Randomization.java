import java.util.List;
import java.util.Random;

public class Randomization {
    private Random random;

    public Randomization() {
        random = new Random();
    }

    public Random getRandom() {
        return random;
    }

    public int getRandomRepRange(int minReps, int maxReps) {
        return random.nextInt(maxReps - minReps + 1) + minReps;
    }

    public int getRandomCardioTime(int minTime, int maxTime) {
        return random.nextInt(maxTime - minTime + 1) + minTime;
    }

    public Exercise getRandomExercise(List<Exercise> exercises) {
        return exercises.get(random.nextInt(exercises.size()));
    }

    public int getRandomIndex(int maxIndex) {
        return random.nextInt(maxIndex);
    }

    // You can add more randomization methods as needed
}