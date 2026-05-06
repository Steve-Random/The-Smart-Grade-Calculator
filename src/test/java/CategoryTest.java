
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

      Category  homework = new Category("Homework", 0.30);

    @Test
    void testCategoryInitialization(){
        assertEquals("Homework", homework.getName());
        assertEquals(0.30, homework.getWeight());
    }

    @Test
    void testAverageWithMultipleAssignments(){
        homework.addAssignment("Hw1", 10,10); //100%
        homework.addAssignment("Hw2", 5,10); //50%
        assertEquals(75, homework.calculateCategoryAverage());
    }

    @Test
    void testEmptyCategoryHandling(){
        assertEquals(0,homework.calculateCategoryAverage(),"Empty category should return 0");
    }


}