import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeManager {

    private Scanner scanner;
    private Course currentCourse;

    public GradeManager() {
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Welcome to the Smart Grade Calculator!");

        System.out.println("Enter the name of your Course: ");
        String name = scanner.nextLine();

        System.out.println("Enter the Course number: ");
        int number = scanner.nextInt();

        currentCourse = new Course(name,number);

        boolean running = true;

        while (running){
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1: addCategory();
                case 2: addGrade();
                case 3: //TODO : Implement a showResults() method
                case 4: //TODO : Implement a runWhatIfAnalysis() method
                case 5: running = false;
            }

        }
    }

    private void displayMenu(){
        System.out.println("\n----" + currentCourse.getName() + currentCourse.getCourseNumber()+ "Menu---");
        System.out.println("1.Add a category (Exams, Homework, Quiz etc)");
        System.out.println("2.Add an Assignment Grade");
        System.out.println("3.View current grade");
        System.out.println("4.What-if Analysis");
        System.out.println("5.Exit");
        System.out.println("Choice: ");
    }

    private void addCategory(){
        System.out.println("Please enter the category name: ");
        String categoryName = scanner.nextLine();

        System.out.println("Please enter the category weight: ");
        double categoryWeight = scanner.nextDouble();

        currentCourse.addCategory(new Category(categoryName,categoryWeight));
    }


    private void addGrade(){
        System.out.println(" Enter category name: ");
        String categoryName = scanner.nextLine();

        System.out.println(" Enter assignment name: ");
        String assignmentName = scanner.nextLine();

        System.out.println(" Enter score: ");
        double score = scanner.nextDouble();

        System.out.println(" Enter max score: ");
        double maxScore = scanner.nextDouble();

        currentCourse.findCategory(categoryName).addAssignment(assignmentName,score,maxScore);
    }

    public String calculateTotalGrade() {
    return "";
    }

    public double calculateGoalScore() {
    return 0;
    }

    public void saveToFile() {

    }
}
