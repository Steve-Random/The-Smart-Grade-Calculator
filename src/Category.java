import java.util.ArrayList;

public class Category {
    private String categoryName;
    private double weight; // (example: 0.35 for 35%

    ArrayList<Assignment> assignments;

    //Method to return the mean of all assignments for a category
    public double calculateCategoryAverage() {
        double sumOfScores = 0, sumOfTotals = 0, mean = 0;

        for (Assignment currentAssignment : assignments) {
            sumOfScores += currentAssignment.score;
            sumOfTotals += currentAssignment.maxScore;
        }
        mean = sumOfScores / sumOfTotals;
        return mean;
    }
}
