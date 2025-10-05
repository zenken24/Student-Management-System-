import java.util.*;

public class GpaCalculator {
    //grade finding 
    public static double getGradePoint(String grade){
        switch(grade.toUpperCase()){
            case "A+": return 4.0;
            case "A": return 3.9; 
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }

    //credit value for given course code 
    private static double getCourseCredits(String code){
        List<String> courses = FileManager.readLines("courses.txt");
        for (String course : courses) {
            String[] parts = course.split(",");
            if (parts.length >= 3 && parts[0].equalsIgnoreCase(code)) {
                try {
                    return Double.parseDouble(parts[2]);
                } catch (NumberFormatException e) {
                    return 0.0;
                }
            }
        }
        return 0.0; // Return 0 if course not found or credits invalid
    }

    //gpa calculation for specific sem 
public static double calculateSemesterGpa(String ID, String sem) {
    List<String> enrollments = FileManager.readLines("enrollments.txt");
    double totalPoints = 0;
    double totalCredits = 0;

    for (String enrollment : enrollments) { 
        String[] parts = enrollment.split(",");
        // ID,code,semester,grade
        if (parts.length == 4 && parts[0].equals(ID) && parts[2].equals(sem)) {
            double credits = getCourseCredits(parts[1]);
            double gradePoint = getGradePoint(parts[3]);

            if (!parts[3].equalsIgnoreCase("N/A")) {
                totalPoints += gradePoint * credits;
                totalCredits += credits;
            }
        }
    }
    return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
}
        //calculate across all sem 
        public static double calculateGpa(String ID){
        List<String> enrollments = FileManager.readLines("enrollments.txt");
        double totalPoints = 0;
        double totalCredits = 0;

        for (String enrollment : enrollments) {
            String[] parts = enrollment.split(",");
            // studentId,courseCode,semester,grade
            if (parts.length == 4 && parts[0].equals(ID)) {
                double credits = getCourseCredits(parts[1]);
                double gradePoint = getGradePoint(parts[3]);

                if (!parts[3].equalsIgnoreCase("N/A")) {
                    totalPoints += gradePoint * credits;
                    totalCredits += credits;
                }
            }
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }
}            
        





