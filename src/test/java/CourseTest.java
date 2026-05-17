import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class
CourseTest {

    Course myCourse = new Course("Computer Science", 202);

    @Test
    void calculateFinalGrade() {
        Category exams = new Category("Exams", 0.5);
        Category homework = new Category("Homework", 0.5);

        exams.addAssignment("Exam1", 80, 100);
        homework.addAssignment("Homework1", 10, 10);

        myCourse.addCategory(exams);
        myCourse.addCategory(homework);
        //Weighted Average: (80*0.5) + (100*0.5) = 90
        assertEquals(90, myCourse.calculateFinalGrade());
    }

    @Test
    void calculateRequiredScore() { // What-If Analysis
        Category exams = new Category("Exams", 1.0);
        exams.addAssignment("Exam1", 100, 100);
        exams.addAssignment("Exam2", 0, 100,true);

        myCourse.addCategory(exams);
        //Exam is the only category and I want an 80 overall, what should I get on the next exam?
        assertEquals(60, myCourse.calculateRequiredScore(80));

    }

    @Test
    void testEmptyCourseGrade() {
        assertEquals(0, myCourse.calculateFinalGrade(), "An Empty course should have a 0 as final grade");
    }

}