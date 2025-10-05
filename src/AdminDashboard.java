import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 182, 193));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton addStudentBtn = new JButton("Add Student");
        JButton addInstructorBtn = new JButton("Add Instructor");
        JButton addCourseBtn = new JButton("Add Course");
        JButton enrollStudentBtn = new JButton("Enroll Student");

        styleButton(addStudentBtn);
        styleButton(addInstructorBtn);
        styleButton(addCourseBtn);
        styleButton(enrollStudentBtn);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(addStudentBtn, gbc);

        gbc.gridx = 1;
        panel.add(addInstructorBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(addCourseBtn, gbc);

        gbc.gridx = 1;
        panel.add(enrollStudentBtn, gbc);

        add(panel);

        addStudentBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
            String name = JOptionPane.showInputDialog(this, "Enter Student Name:");
            String dept = JOptionPane.showInputDialog(this, "Enter Department:");
            if (id != null && name != null && dept != null) {
                FileManager.addStudent(id, name, dept);
                JOptionPane.showMessageDialog(this, "Student added successfully!");
            }
        });

        addInstructorBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Enter Instructor ID:");
            String name = JOptionPane.showInputDialog(this, "Enter Instructor Name:");
            String dept = JOptionPane.showInputDialog(this, "Enter Department:");
            if (id != null && name != null && dept != null) {
                FileManager.addInstructor(id, name, dept);
                JOptionPane.showMessageDialog(this, "Instructor added successfully!");
            }
        });

        addCourseBtn.addActionListener(e -> {
            String code = JOptionPane.showInputDialog(this, "Enter Course Code:");
            String title = JOptionPane.showInputDialog(this, "Enter Course Title:");
            String credits = JOptionPane.showInputDialog(this, "Enter Credits:");
            String instructorId = JOptionPane.showInputDialog(this, "Enter Instructor ID:");
            if (code != null && title != null && credits != null && instructorId != null) {
                FileManager.addCourse(code, title, credits, instructorId);
                JOptionPane.showMessageDialog(this, "Course created successfully!");
            }
        });

        enrollStudentBtn.addActionListener(e -> {
            String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:");
            String courseCode = JOptionPane.showInputDialog(this, "Enter Course Code:");
            String semester = JOptionPane.showInputDialog(this, "Enter Semester:");
            if (studentId != null && courseCode != null && semester != null) {
                FileManager.enrollStudent(studentId, courseCode, semester);
                JOptionPane.showMessageDialog(this, "Student enrolled successfully!");
            }
        });
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(255, 105, 180));
        btn.setForeground(new Color(255, 182, 193));
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}
