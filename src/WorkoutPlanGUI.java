import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class WorkoutPlanGUI {

    private static JTextArea workoutTextArea;
    private static WorkoutPlan generatedWorkoutPlan;
    private static ProgressTracker progressTracker = new ProgressTracker();

    private static final String PROGRESS_FILE_PATH = "progress.txt";

    public static void main(String[] args) {
        loadProgress();
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Workout Plan Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel goalLabel = new JLabel("Select your goal:");
        JLabel muscleGroupLabel = new JLabel("Select the muscle group:");
        workoutTextArea = new JTextArea(10, 30);

        JComboBox<String> goalComboBox = new JComboBox<>(new String[]{"Muscle Building", "Fat Loss"});
        JComboBox<String> muscleGroupComboBox = new JComboBox<>(new String[]{"Chest", "Back", "Legs", "Biceps", "Triceps", "Shoulders", "Abs"});

        JButton generateButton = new JButton("Generate Workout");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateWorkout(goalComboBox.getSelectedItem().toString(), muscleGroupComboBox.getSelectedItem().toString());
            }
        });

        JButton showProgressButton = new JButton("Show Progress");
        showProgressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showProgress();
            }
        });

        panel.add(goalLabel);
        panel.add(goalComboBox);
        panel.add(muscleGroupLabel);
        panel.add(muscleGroupComboBox);
        panel.add(workoutTextArea);
        panel.add(generateButton);
        panel.add(showProgressButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void generateWorkout(String selectedGoal, String selectedMuscleGroup) {
        ExerciseList exerciseList = new ExerciseList();
        WorkoutPlanGenerator workoutPlanGenerator = new WorkoutPlanGenerator(exerciseList);
        Randomization randomization = new Randomization();

        generatedWorkoutPlan = workoutPlanGenerator.generateWorkoutPlan(selectedGoal, selectedMuscleGroup, selectedGoal.equalsIgnoreCase("Fat Loss"));

        if (generatedWorkoutPlan != null) {
            StringBuilder workoutPlanText = new StringBuilder("Generated Workout Plan:\n");
            workoutPlanText.append(generatedWorkoutPlan.toString()).append("\n");

            for (Exercise exercise : generatedWorkoutPlan.getExercises()) {
                if (!exercise.getName().equalsIgnoreCase("Cardio")) {
                    int sets = exercise.getSets();
                    int reps = randomization.getRandomRepRange(generatedWorkoutPlan.getMinReps(), generatedWorkoutPlan.getMaxReps());
                    workoutPlanText.append(exercise.getName()).append(": Sets - ").append(sets).append(" , Reps - ").append(reps).append("\n");

                    progressTracker.trackExerciseCompletion(exercise.getName(), sets, reps, false);
                } else {
                    int cardioTime = randomization.getRandomCardioTime(30, 45);
                    workoutPlanText.append("Cardio: Minutes - ").append(cardioTime).append("\n");

                    progressTracker.trackExerciseCompletion("Cardio", 0, cardioTime, true);
                }
            }

            workoutTextArea.setText(workoutPlanText.toString());
            saveProgress();
        } else {
            workoutTextArea.setText("Invalid goal selected.");
        }
    }

    private static void showProgress() {
        if (generatedWorkoutPlan != null) {
            workoutTextArea.setText(progressTracker.toString());
        } else {
            workoutTextArea.setText("No workout plan generated.");
        }
    }

    private static void saveProgress() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROGRESS_FILE_PATH, true))) {
            writer.write(progressTracker.toString());
            writer.newLine(); // New line for separation
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROGRESS_FILE_PATH))) {
            String line;
            StringBuilder progressText = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                progressText.append(line).append("\n");
            }
            progressTracker.loadProgress(progressText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
