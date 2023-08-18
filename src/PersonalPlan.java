public class PersonalPlan {

    public static void main(String[] args) {
        ExerciseList exerciseList = new ExerciseList();
        WorkoutPlanGenerator workoutPlanGenerator = new WorkoutPlanGenerator(exerciseList);

        UserInput userInput = new UserInput();
        Randomization randomization = new Randomization();
        ProgressTracker progressTracker = new ProgressTracker();

        System.out.println("Welcome to the Workout Plan Generator!");

        String selectedGoal = userInput.getGoalFromUser();
        String selectedMuscleGroup = userInput.getMuscleGroupFromUser();
        boolean includeCardio = selectedGoal.equalsIgnoreCase("Fat Loss");

        WorkoutPlan workoutPlan = workoutPlanGenerator.generateWorkoutPlan(selectedGoal, selectedMuscleGroup, includeCardio);

        if (workoutPlan != null) {
            System.out.println("Generated Workout Plan:");
            System.out.println(workoutPlan);
            System.out.println();

            for (Exercise exercise : workoutPlan.getExercises()) {
                if (exercise.getName().equalsIgnoreCase("Cardio")) {
                    int cardioTime = randomization.getRandomCardioTime(20, 60); // Random time between 20 to 60 minutes
                    System.out.println("Completing " + cardioTime + " minutes of " + exercise.getName() + " (" + exercise.getEquipment() + ")");
                    progressTracker.trackExerciseCompletion(exercise.getName(), 0, cardioTime, true);
                } else {
                    int sets = 3;
                    int reps = randomization.getRandomRepRange(workoutPlan.getMinReps(), workoutPlan.getMaxReps());
                    System.out.println("Completing " + sets + " sets of " + reps + " reps of " + exercise.getName());
                    progressTracker.trackExerciseCompletion(exercise.getName(), sets, reps, false);
                }
            }

            System.out.println();
            System.out.println("Exercise Progress:");
            System.out.println(progressTracker);
        } else {
            System.out.println("Invalid goal selected.");
        }

        userInput.closeScanner();
    }
}
