import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanGenerator {
    private List<GoalCategory> goalCategories;
    private ExerciseList exerciseList;

    public WorkoutPlanGenerator(ExerciseList exerciseList) {
        this.goalCategories = new ArrayList<>();
        this.exerciseList = exerciseList;
        initializeGoalCategories(); // Populate the goal categories
    }

    private void initializeGoalCategories() {
        goalCategories.add(new GoalCategory("Muscle Building",
                List.of("Chest", "Back", "Legs", "Biceps", "Triceps", "Shoulders"),
                6, 12));

        goalCategories.add(new GoalCategory("Fat Loss",
                List.of("Chest", "Back", "Legs", "Biceps", "Triceps", "Shoulders", "Abs"),
                12, 15));

    }

    public WorkoutPlan generateWorkoutPlan(String selectedGoal, String selectedMuscleGroup, boolean includeCardio) {
        GoalCategory goalCategory = findGoalCategory(selectedGoal);

        if (goalCategory == null) {
            return null; // Invalid goal selected
        }

        int minReps = goalCategory.getMinReps();
        int maxReps = goalCategory.getMaxReps();

        List<Exercise> selectedExercises = selectExercisesForMuscleGroup(selectedMuscleGroup);

        if (includeCardio && selectedGoal.equalsIgnoreCase("Fat Loss")) {
            selectedExercises.add(new Exercise("Cardio", "Cardio", "None", 0)); // Cardio exercise tracked by time
        }

        WorkoutPlan workoutPlan = new WorkoutPlan(selectedGoal, selectedExercises, minReps, maxReps);
        return workoutPlan;
    }

    private GoalCategory findGoalCategory(String goalName) {
        for (GoalCategory goalCategory : goalCategories) {
            if (goalCategory.getName().equalsIgnoreCase(goalName)) {
                return goalCategory;
            }
        }
        return null; // Goal category not found
    }

    private List<Exercise> selectExercisesForMuscleGroup(String muscleGroup) {
        List<Exercise> selectedExercises = new ArrayList<>();
        List<Exercise> allExercises = exerciseList.getExercises();

        for (Exercise exercise : allExercises) {
            if (exercise.getMuscleGroup().equalsIgnoreCase(muscleGroup)) {
                selectedExercises.add(exercise);
            }
        }

        return selectedExercises;
    }
}
