import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorDashboard extends JFrame {
    protected String instructorId;

    public InstructorDashboard(String instructorId) {
        this.instructorId = instructorId;
        setTitle("Instructor Dashboard"); 
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        // Add buttons for instructor features 
        JButton viewCoursesBtn = new JButton("View My Courses"); 
        JButton viewEnrolledBtn = new JButton("View Enrolled Students"); 
        JButton assignGradesBtn = new JButton("Assign/Update Grades"); 

        panel.add(viewCoursesBtn);
        panel.add(viewEnrolledBtn);
        panel.add(assignGradesBtn);
        
        add(panel, BorderLayout.CENTER);

        viewCoursesBtn.addActionListener(e -> { 
            List<String> courses = FileManager.readLines("courses.txt");
            String myCourses = courses.stream()
                .filter(line -> line.split(",").length == 4 && line.split(",")[3].equals(instructorId))
                .map(line -> "Code: " + line.split(",")[0] + ", Title: " + line.split(",")[1])
                .collect(Collectors.joining("\n"));
            
            JTextArea textArea = new JTextArea(myCourses);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "My Courses", JOptionPane.INFORMATION_MESSAGE);
        });

        viewEnrolledBtn.addActionListener(e -> { 
            String courseCode = JOptionPane.showInputDialog(this, "Enter Course Code to view students:");
            if (courseCode == null || courseCode.trim().isEmpty()) return;
            
            List<String> enrollments = FileManager.readLines("enrollments.txt");
            String enrolledStudents = enrollments.stream()
                .filter(line -> line.split(",").length >= 2 && line.split(",")[1].equalsIgnoreCase(courseCode))
                .map(line -> "Student ID: " + line.split(",")[0])
                .collect(Collectors.joining("\n"));
            
            JTextArea textArea = new JTextArea(enrolledStudents.isEmpty() ? "No students enrolled." : enrolledStudents);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Enrolled Students in " + courseCode, JOptionPane.INFORMATION_MESSAGE);
        });

        assignGradesBtn.addActionListener(e -> { 
            String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:");
            String courseCode = JOptionPane.showInputDialog(this, "Enter Course Code:");
            String grade = JOptionPane.showInputDialog(this, "Enter Grade (A, B, C, etc.):");
            if (studentId != null && courseCode != null && grade != null) {
                FileManager.updateGrade(studentId, courseCode, grade);
                JOptionPane.showMessageDialog(this, "Grade updated successfully!");
            }
        });
    }
}