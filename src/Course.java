import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Course implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private int courseNumber;
    private List<Category> categories;

    public Course(String name, int courseNumber) {
        this.name = name;
        this.courseNumber = courseNumber;
        this.categories = new ArrayList<Category>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Category findCategory(String categoryName ){
        for ( Category category : categories){
            if (category.getName().equalsIgnoreCase(categoryName)){
                return category;
            }
        }
        return null;
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
