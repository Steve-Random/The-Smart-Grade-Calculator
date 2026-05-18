import java.io.*;
import java.util.Scanner;

public class GradeManager {
    private Scanner scanner;
    private Course currentCourse;

    public GradeManager() throws IOException {
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        File savedData = new File("grades.dat");
        if (savedData.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedData))) {
                currentCourse = (Course) ois.readObject();
                System.out.println("Welcome back! Loaded " + currentCourse.getName() + currentCourse.getCourseNumber());
            } catch (Exception e) {
                System.out.println("Welcome, started fresh-no previous data found.");
            }
        }
        if (currentCourse == null) {
            System.out.println("Welcome to the Smart Grade Calculator!");

            System.out.println("Enter the name of your Course: ");
            String name = scanner.nextLine();

            System.out.println("Enter the Course number: ");
            int number = Integer.parseInt(scanner.nextLine());

            currentCourse = new Course(name, number);
        }

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    addGrade();
                    break;
                case 3:
                    updatePendingAssignment();
                    break;
                case 4:
                    showResults();
                    break;
                case 5:
                    runWhatIfAnalysis();
                    break;

                case 6:
                    exportHumanReadable();
                    running = false;
                    break;
                case 7:
                    saveSerializedData();
                    System.out.println("Progress saved. See you later!");
                    running = false;
                    break;
            }

        }
    }

    private void displayMenu() {
        System.out.println("\n----" + currentCourse.getName() + currentCourse.getCourseNumber() + " Menu---");
        System.out.println("1.Add a category (Exams, Homework, Quiz etc)");
        System.out.println("2.Add an Assignment Grade");
        System.out.println("3.View current grade");
        System.out.println("4.What-if Analysis");
        System.out.println("5.Create a Report and Exit");
        System.out.println("6.Exit");
        System.out.println("Choice: ");
    }

    private void addCategory() {
        System.out.println("Please enter the category name: ");
        String categoryName = scanner.nextLine();

        System.out.println("Please enter the category weight: ");
        double categoryWeight = Double.parseDouble(scanner.nextLine());

        currentCourse.addCategory(new Category(categoryName, categoryWeight));
    }


    private void addGrade() {
        System.out.println("Enter category name: ");
        String categoryName = scanner.nextLine();

        Category found = currentCourse.findCategory(categoryName);
        if (found != null) {

            System.out.println("Enter assignment name: ");
            String assignmentName = scanner.nextLine();

            System.out.println("Is this an upcoming assignment? ('y' for yes 'n' for no) :  ");
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("n")) {

                System.out.println(" Enter score: ");
                double score = Double.parseDouble(scanner.nextLine());

                System.out.println(" Enter max score: ");
                double maxScore = Double.parseDouble(scanner.nextLine());

                found.addAssignment(assignmentName, score, maxScore);
                System.out.println("Grade added!");
            } else {
                System.out.println(" Enter max score: ");
                double maxScore = Double.parseDouble(scanner.nextLine());

                found.addAssignment(assignmentName, 0, maxScore, true);
                System.out.println("Upcoming assignment added!");
            }
        } else {
            System.out.println("Category " + categoryName + " not found");
        }
    }

    private void updatePendingAssignment() {
        // Listing all pending assignments first
        System.out.println("\n----Current Pending Assignments---");
        boolean foundPendingAssignment = false;
        for( Category category: currentCourse.getCategories()){
            for (Assignment assignment: category.getAssignments()){
                if ( assignment.isPending()){
                    System.out.println("[" + category.getName() + "] " + assignment.getName());
                    foundPendingAssignment = true;
                }
            }
        }

        if(foundPendingAssignment){
            System.out.println("No pending assignments found, everything is already graded.");
            return;
        }
        System.out.println("Enter category name ( Where the assignment is found ) : ");
        String categoryName = scanner.nextLine();
        Category category = currentCourse.findCategory(categoryName);

        if (category != null) {
            System.out.println("Enter the name of the pending assignment : ");
            String assignmentName = scanner.nextLine();
            Assignment assignment = category.findAssignment(assignmentName);

            if ((assignment != null) && (assignment.isPending())) {
                System.out.println("Enter your earned score: ");
                double score = Double.parseDouble(scanner.nextLine());

                assignment.setScore(score);
                assignment.setPending(false);

                System.out.println("Success! " + assignmentName + " is now graded.");
            } else {
                System.out.println("Assignment not found or it is already graded");
            }
        } else {
            System.out.println("Category not found.");
        }
    }

    private void showResults() {
        double result = currentCourse.calculateFinalGrade();
        System.out.println(" Your current grade is: " + result);
    }

    private void runWhatIfAnalysis() {
        System.out.println(" What is your target grade: ");
        double targetGrade = Double.parseDouble(scanner.nextLine());
        double neededScore = currentCourse.calculateRequiredScore(targetGrade);

        if (neededScore > 100) {
            System.out.println("To get a " + targetGrade + ", you need a " + neededScore + "  (Better start extra credit!)\n");
        } else if (neededScore < 0) {
            System.out.println("You have already reached your target, nice!");

        } else {
            System.out.printf("To get a %.1f, you need a %.1f%% on your remaining work\n", targetGrade, neededScore);

        }
    }

    private void saveSerializedData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("grades.dat"));) {
            oos.writeObject(currentCourse);
        } catch (IOException e) {
            System.out.println("Error saving binary data: " + e.getMessage());
        }
    }

    private void exportHumanReadable() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("My_Grades_Report.txt"))) {
            writer.println("  ====================================================== ");
            writer.println("      ----SMART GRADE CALCULATOR REPORT----    ");
            writer.println("  ====================================================== ");
            writer.println("Course: " + currentCourse.getName() + " " + currentCourse.getCourseNumber());
            writer.printf("Overall Current Grade: %.2f%%\n", currentCourse.calculateFinalGrade());
            writer.println("-----------------------------------------------------------");

            for (Category category : currentCourse.getCategories()) {
                writer.println(" CATEGORY: " + category.getName());
                writer.println(" Weight: " + category.getWeight() * 100 + "%");
                writer.printf(" Category Average: %.2f%%\n", category.calculateCategoryAverage());
                if (category.getAssignments() != null) {
                    writer.println(" Assignments: ");
                    for (Assignment assignment : category.getAssignments()) {
                        writer.printf("  -%s: %.2f/%.2f\n", assignment.getName(), assignment.getScore(), assignment.getMaxScore());
                        writer.println();
                    }
                }
                writer.println();
                writer.println(" Report Generated on: " + new java.util.Date());
                System.out.println("Success!, Your Readable Report was saved to 'My_Grades_Report.txt'.");

            }
        } catch (IOException e) {
            System.out.println("Error, your text report could not be generated: " + e.getMessage());
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

