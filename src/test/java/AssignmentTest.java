import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssignmentTest {

    @Test
    void testAssignmentInitialization(){
        Assignment assignment = new Assignment("Exam1", 60,100);
        assertEquals("Exam1", assignment.getName());
        assertEquals(60, assignment.getScore());
        assertEquals(100, assignment.getMaxScore());
    }

    @Test
    void testGetPercentage(){
        Assignment assignment = new Assignment("Exam1", 60,100); //60%
        assertEquals(60,assignment.getPercentage());
    }

    @Test
    void testZeroMaxScoreHandling(){
        Assignment assignment = new Assignment("Exam1", 60,0);
        assertEquals(1, assignment.getMaxScore(),"Constructor should convert a max score of 0 to 1");

    }


}