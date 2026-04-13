import java.util.ArrayList;

public class Category {
    private String name;
    private double weight; // (use decimals example: 0.35 for 35%
    private ArrayList<Assignment> assignments;

    public Category (String name, double weight){
        this.name = name;
        this.weight = weight;
        this.assignments = new ArrayList<>();
    }

    //Adds a new grade to this specific category
    public void addAssignment( String assignmentName, double score, double maxScore){
        assignments.add( new Assignment(assignmentName, score, maxScore));
    }

    //Method to return the mean of all assignments for a category
    public double calculateCategoryAverage() {
        if(assignments.isEmpty()){
            return 0;
        }

        double sumOfScores = 0, sumOfTotals = 0, mean = 0;

        for (Assignment currentAssignment : assignments) {
            sumOfScores += currentAssignment.getScore();
            sumOfTotals += currentAssignment.getMaxScore();
        }
        return (sumOfScores / sumOfTotals)*100;
    }

    public double getPercentageContributionOfCategory(){
        return calculateCategoryAverage() * weight;
    }

    // Get Methods
    public String getName(){
        return name;
    }
    public double getWeight(){
        return weight;
    }
    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

}
