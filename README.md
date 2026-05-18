# The-Smart-Grade-Calculator(Java)
A robust, object-oriented Java application designed to help students manage academic performance through weighted calculations and predictive "what-if" scenarios.
This tool goes beyond simple averaging by providing data persistence and analytical-grade forecasting.

## Key Features
- **Weighted Average Calculation:**
Handles complex grading scales by managing distinct categories (example: Exams, Homework, Quizzes) with individual percentage weights.
- **Predictive "What-If" Analysis:**
Includes a back-calculation engine that determines the minimum score required on remaining coursework to reach a specific target grade.
- **Predictive "What-If" Analysis:**
Supports both Graded and Pending assignments. Method overloading allows users to "pre-load" a syllabus without affecting their current weighted average.
- **Dual-Stream Data Persistence:**
  ### Serialization:
  Uses Java's ObjectOutputStream to save the entire Course state to a binary file ( grades.dat ), allowing users to resume their session later.
  ### Formatted Reporting:
  Generates a human-readable text summary ( My_Grades_Report.txt ) using PrintWriter for easy sharing and review.
- **Robust Input Handling:**
- Implements a buffered scanner pattern to prevent terminal input skipping and ensures data integrity with ternary-based edge-case validation.

## How the "What-if" Math Works
Unlike basic calculators, this tool uses a "Playable Territory" formula:
Required score = (Target Grade - Current banked points)/Remaining weighted opportunity
###Example Scenario
If you have 100% on Exam1 (worth 50% worth 80% of your grade) and want an 80% overall:
1. Current Points: 100*0.50 = 50
2. Gap to target: 80-50 = 30
3. Upcoming Weight: 0.50 (Exam2)
4. Result: 30/0.50 = 60% required on Exam 2.

## Technical Stack
- Language: Java 26
- Concepts: Object-Oriented Programming, File I/O, Binary Serialization, Exception Handling, Java Collections Framework (ArrayList)
- Testing: JUnit5 (Unit Testing & Test-Driven Development), focusing on math engine reliability and edge-case validation.
- IDE: IntelliJ IDEA
- Version Control: Git

## Project Structure
- Main.java: The entry point of the application.
- GradeManager.java: The controller and UI layer; handles user input, menu loops, and File I/O.
- Course.java: The top-level data container representing a specific class/semester.
- Category.java: Logic layer that manages assignments and calculates weighted contributions.
- Assignment.java: The base data model for individual scores and maximum point values.

## Getting Started
### Prerequisites
- Java Development Kit 17 or higher
- An IDE
### Installation
1. Clone the repository:
  - https://github.com/Steve-Random/The-Smart-Grade-Calculator.git
2. Open the project in your IDE.
3. Run Main.java to start the application.
