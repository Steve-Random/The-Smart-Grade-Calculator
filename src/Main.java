public static void main (String[] args){
    Course myCourse = new Course("Computer Science", 101);

    Category homework = new Category("HomeWork",0.4);
    Category exams = new Category("Exams",0.6);

    homework.addAssignment("hw1", 100,100);
    exams.addAssignment("Midterm",50,100);

    myCourse.addCategory(homework);
    myCourse.addCategory(exams);

    double finalGrade = myCourse.calculateFinalGrade();
    System.out.println("Course:" + myCourse.getName() + " " + myCourse.getCourseNumber());
    System.out.println("Final Calculated Grade:" + finalGrade + "%");
}
