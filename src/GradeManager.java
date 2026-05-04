import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeManager {
    private Scanner scanner;
    private Course currentCourse;

    public GradeManager() throws IOException {
        this.scanner = new Scanner(System.in);
    }

    public void start() throws FileNotFoundException {
        File savedData = new File("grades.dat");
        if (savedData.exists()){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedData))){
                currentCourse = (Course) ois.readObject();
                System.out.println("Welcome back! Loaded " + currentCourse.getName() + currentCourse.getCourseNumber());
            }catch (Exception e){
                System.out.println("Welcome, started fresh-no previous data found.");
            }
        }
        if (currentCourse == null){
        System.out.println("Welcome to the Smart Grade Calculator!");

        System.out.println("Enter the name of your Course: ");
        String name = scanner.nextLine();

        System.out.println("Enter the Course number: ");
        int number = Integer.parseInt(scanner.nextLine());

        currentCourse = new Course(name,number);}

        boolean running = true;

        while (running){
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1: addCategory();
                break;
                case 2: addGrade();
                break;
                case 3: showResults();
                break;
                case 4: runWhatIfAnalysis();
                break;
                case 5: saveSerializedData();
                        //exportHumanReadable();
                        System.out.println("Progress saved. See you later!");
                        running = false;
                break;
            }

        }
    }

    private void displayMenu(){
        System.out.println("\n----" + currentCourse.getName() + currentCourse.getCourseNumber() + " Menu---");
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
        double categoryWeight = Double.parseDouble(scanner.nextLine());

        currentCourse.addCategory(new Category(categoryName,categoryWeight));
    }


    private void addGrade(){
        System.out.println("Enter category name: ");
        String categoryName = scanner.nextLine();

        Category found = currentCourse.findCategory(categoryName);
        if ( found != null ) {

            System.out.println("Enter assignment name: ");
            String assignmentName = scanner.nextLine();

            System.out.println(" Enter score: ");
            double score = Double.parseDouble(scanner.nextLine());

            System.out.println(" Enter max score: ");
            double maxScore = Double.parseDouble(scanner.nextLine());

           found.addAssignment(assignmentName, score, maxScore);
        }else{
            System.out.println("Category " + categoryName + " not found");
        }
    }

    private void showResults(){
        double result = currentCourse.calculateFinalGrade();
        System.out.println(" Your current grade is: " + result);
    }

    private void runWhatIfAnalysis(){
        System.out.println(" What is your target grade: ");
        double targetGrade = Double.parseDouble(scanner.nextLine());
        double neededScore = currentCourse.calculateRequiredScore( targetGrade );

        if (neededScore > 100){
            System.out.println("To get a " + targetGrade + ", you need a " + neededScore+"  (Better start extra credit!)\n");
        } else if (neededScore < 0) {
            System.out.println("You have already reached your target, nice!");

        } else {
            System.out.printf("To get a %.1f, you need a %.1f%% on your remaining work\n", targetGrade, neededScore);

        }
    }

    private void saveSerializedData(){
    try ( ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( "grades.dat"));){
        oos.writeObject(currentCourse);
    }catch(IOException e){
        System.out.println( "Error saving binary data: " + e.getMessage());
    }
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
