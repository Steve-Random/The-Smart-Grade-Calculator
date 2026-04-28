import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private int courseNumber;
    private List<Category> categories;

    public Course(String name, int courseNumber) {
        this.name = name;
        this.courseNumber = courseNumber;
        this.categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    //Here is the Grand Total logic
    public double calculateFinalGrade() {
        double finalGrade = 0;

        for (Category category : categories) { //Pulling the weighted score from each category
            finalGrade += category.getPercentageContributionOfCategory();
        }
        return finalGrade;
    }

    //What makes the calculator smart
    //The method to calculate required points for a target grade
    public double calculateRequiredScore( double targetGrade ){
        double currentPoints = calculateFinalGrade();
        double totalWeightUsed = 0;
        for( Category category: categories){
            if (!category.getAssignments().isEmpty()){
                totalWeightUsed += category.getWeight();
            }
        }
        double remainingWeight = 1.0 - totalWeightUsed;

        if( remainingWeight <= 0){
            System.out.println("No weight remaining in the course");
            return -1;
        }

        return (targetGrade - currentPoints)/remainingWeight;
    }

    //Get Methods
    public String getName() {
        return name;
    }

    public int getCourseNumber() {
        return courseNumber;
    }
}
