public static void main(String[] args) throws IOException {
    /*Course myCourse = new Course("Computer Science", 101);

    Category homework = new Category("HomeWork", 0.4);
    Category exams = new Category("Exams", 0.6);

    homework.addAssignment("hw1", 100, 100);
    exams.addAssignment("Midterm", 50, 100);

    myCourse.addCategory(homework);
    myCourse.addCategory(exams);

    double finalGrade = myCourse.calculateFinalGrade();
    System.out.println("Course:" + myCourse.getName() + " " + myCourse.getCourseNumber());
    System.out.println("Final Calculated Grade:" + finalGrade + "%");

    double targetScore = 80.0;
    double neededScore = myCourse.calculateRequiredScore( targetScore );

    if (neededScore > 100){
        System.out.printf("To get a %.1f, you need a %.1f%%. (Better start extra credit!)\n", targetScore, neededScore);
    } else if (neededScore < 0) {
        System.out.println("You have already reached your target, nice!");

    } else {
        System.out.printf("To get a %.1f, you need a %.1f%% on your remaining work\n", targetScore, neededScore);

    }*/

    GradeManager manager = new GradeManager();
    manager.start();
}
