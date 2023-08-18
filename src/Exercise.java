public class Exercise {
    private String name;
    private String muscleGroup;
    private String equipment;
    private int sets; // Add this field

    public Exercise(String name, String muscleGroup, String equipment, int sets) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
        this.sets = sets; // Initialize the sets
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public String getEquipment() {
        return equipment;
    }

    public int getSets() {
        return sets;
    }

    @Override
    public String toString() {
        return "Exercise: " + name + "\nMuscle Group: " + muscleGroup + "\nEquipment: " + equipment;
    }
}
