import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private int courseNumber;
    private List<Category> categories;

    public Course(String name, int courseNumber, List categories) {
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

    //Get Methods
    public String getName() {
        return name;
    }

    public int getCourseNumber() {
        return courseNumber;
    }
}
