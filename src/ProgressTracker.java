import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProgressTracker implements Serializable {

    private List<ExerciseProgress> exerciseProgressList;

    public ProgressTracker() {
        exerciseProgressList = new ArrayList<>();
    }

    public void trackExerciseCompletion(String exerciseName, int sets, int reps, boolean isCardio) {
        ExerciseProgress exerciseProgress = new ExerciseProgress(exerciseName, sets, reps, isCardio);
        exerciseProgressList.add(exerciseProgress);
    }

    public void loadProgress(String progressText) {
        String[] lines = progressText.split("\n");
        exerciseProgressList.clear();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String exerciseName = parts[0].trim();
                int sets = Integer.parseInt(parts[1].trim());
                int reps = Integer.parseInt(parts[2].trim());
                boolean isCardio = parts.length >= 4 && Boolean.parseBoolean(parts[3].trim());

                ExerciseProgress exerciseProgress = new ExerciseProgress(exerciseName, sets, reps, isCardio);
                exerciseProgressList.add(exerciseProgress);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ExerciseProgress exerciseProgress : exerciseProgressList) {
            sb.append(exerciseProgress.toString()).append("\n");
        }
        return sb.toString();
    }

    public class ExerciseProgress implements Serializable {
        private String exerciseName;
        private int sets;
        private int reps;
        private boolean isCardio;

        public ExerciseProgress(String exerciseName, int sets, int reps, boolean isCardio) {
            this.exerciseName = exerciseName;
            this.sets = sets;
            this.reps = reps;
            this.isCardio = isCardio;
        }

        @Override
        public String toString() {
            if (isCardio) {
                return "Cardio: Minutes - " + reps;
            } else {
                return exerciseName + ": Sets - " + sets + ", Reps - " + reps;
            }
        }
    }
}
