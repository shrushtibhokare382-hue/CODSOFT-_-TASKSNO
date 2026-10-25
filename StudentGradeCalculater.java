
import java.util.Scanner;

public class StudentGradeCalculater {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int subjects = sc.nextInt();

        if (subjects <= 0) {
            System.out.println("Number of subjects must be positive.");
            sc.close();
            return;
        }

        int[] marks = new int[subjects];
        int total = 0;

        for (int i = 0; i < subjects; i++) {
            while (true) {
                System.out.print("Enter marks for subject "
                        + (i + 1) + " (0-100): ");

                int mark = sc.nextInt();

                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    total += mark;
                    break;
                } else {
                    System.out.println(
                            "Invalid marks. Enter a value from 0 to 100.");
                }
            }
        }

        double percentage = (double) total / subjects;
        String grade;

        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 40) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("\n=== STUDENT RESULT ===");
        System.out.println("Total Marks: " + total
                + " / " + (subjects * 100));
        System.out.printf("Average Percentage: %.2f%%%n", percentage);
        System.out.println("Grade: " + grade);

        if (percentage >= 40) {
            System.out.println("Result: PASS");
        } else {
            System.out.println("Result: FAIL");
        }

        sc.close();
    }
}
