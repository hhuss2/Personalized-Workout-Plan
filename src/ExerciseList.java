import java.util.ArrayList;
import java.util.List;

public class ExerciseList {
    private List<Exercise> exercises;

    public ExerciseList() {
        exercises = new ArrayList<>();
        initializeExercises(); // Call a method to populate the exercise list
    }

    private void initializeExercises() {
        exercises.add(new Exercise("Push-up", "Chest", "Bodyweight", 3));
        exercises.add(new Exercise("Bench Press", "Chest", "Barbell", 3));
        exercises.add(new Exercise("Dumbbell Fly", "Chest", "Dumbbells", 3));

        exercises.add(new Exercise("Pull-up", "Back", "Bar", 3));
        exercises.add(new Exercise("Bent Over Row", "Back", "Barbell", 3));
        exercises.add(new Exercise("Lat Pulldown", "Back", "Machine", 3));

        exercises.add(new Exercise("Squat", "Legs", "Barbell", 3));
        exercises.add(new Exercise("Leg Press", "Legs", "Machine", 3));
        exercises.add(new Exercise("Lunges", "Legs", "Bodyweight", 3));

        exercises.add(new Exercise("Bicep Curl", "Biceps", "Dumbbells", 3));
        exercises.add(new Exercise("Preacher Curl", "Biceps", "Barbell", 3));

        exercises.add(new Exercise("Tricep Dip", "Triceps", "Parallel Bars", 3));
        exercises.add(new Exercise("Tricep Extension", "Triceps", "Dumbbells", 3));
        exercises.add(new Exercise("Skullcrushers", "Triceps", "Barbell", 3));

        exercises.add(new Exercise("Shoulder Press", "Shoulders", "Dumbbells", 3));
        exercises.add(new Exercise("Lateral Raise", "Shoulders", "Dumbbells", 3));

        exercises.add(new Exercise("Crunches", "Abs", "Bodyweight", 3));
        exercises.add(new Exercise("Leg raises", "Abs", "Bodyweight", 3));

        exercises.add(new Exercise("Running", "Cardio", "Treadmill", 0)); // Cardio exercises have 0 sets
        exercises.add(new Exercise("Cycling", "Cardio", "Stationary Bike", 0));
        exercises.add(new Exercise("Jump Rope", "Cardio", "Jump Rope", 0));
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
