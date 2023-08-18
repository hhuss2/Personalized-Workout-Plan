import java.util.Scanner;

public class UserInput {

    private Scanner scanner;

    public UserInput() {
        scanner = new Scanner(System.in);
    }

    public String getGoalFromUser() {
        System.out.println("Select your goal (Muscle Building / Fat Loss):");
        return scanner.nextLine();
    }

    public String getMuscleGroupFromUser() {
        System.out.println("Select the muscle group you want to work on:");
        return scanner.nextLine();
    }

    // Close the scanner when done
    public void closeScanner() {
        scanner.close();
    }
}