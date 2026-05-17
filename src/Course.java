import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
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

    public Category findCategory(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }


    //Here is the Grand Total logic
    public double calculateFinalGrade() {
        double finalGrade = 0;

        for (Category category : categories) { //Pulling the weighted score from each category
            if (category.getAssignments().isEmpty())
                continue;
            double gradedCount = 0;
            for (Assignment assignment : category.getAssignments()) {
                if (!assignment.isPending()) {
                    gradedCount++;
                }
            }

            double proportionDone = gradedCount / category.getAssignments().size();
            // ths the contribution odf the category = category average * its weight * proportion of the category completed
            finalGrade += category.calculateCategoryAverage() * category.getWeight() * proportionDone;
        }
        return finalGrade;
    }

    //What makes the calculator smart
    //The method to calculate required points for a target grade
    public double calculateRequiredScore(double targetGrade) {
        double currentPoints = calculateFinalGrade();
        double upComingWeight = 0;
        int totalAssignments = 0;
        double weightPerAssignment = 0;

        for (Category category : categories) {
            if (category.getAssignments().isEmpty()) {
                upComingWeight += category.getWeight();
            } else {
                totalAssignments = category.getAssignments().size();
                weightPerAssignment = category.getWeight() / totalAssignments;
                for (Assignment assignment : category.getAssignments()) {
                    if (assignment.isPending()) {
                        upComingWeight += weightPerAssignment;
                    }
                }
            }
        }

        if (upComingWeight <= 0) {
            System.out.println("No weight remaining in the course");
            return -1;
        }

        return (targetGrade - currentPoints) / upComingWeight;
    }

    //Get Methods
    public String getName() {
        return name;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
