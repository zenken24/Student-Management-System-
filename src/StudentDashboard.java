import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDashboard extends JFrame {
    protected String ID;

    public StudentDashboard(String ID) {
        this.ID = ID;
        setTitle("Student Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 182, 193));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton viewProfileBtn = new JButton("View Profile");
        JButton viewCoursesBtn = new JButton("View Courses");
        JButton viewTranscriptBtn = new JButton("View Transcript");

        styleButton(viewProfileBtn);
        styleButton(viewCoursesBtn);
        styleButton(viewTranscriptBtn);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(viewProfileBtn, gbc);

        gbc.gridy = 1;
        panel.add(viewCoursesBtn, gbc);

        gbc.gridy = 2;
        panel.add(viewTranscriptBtn, gbc);

        add(panel);

        viewProfileBtn.addActionListener(e -> {
            List<String> students = FileManager.readLines("students.txt");
            String profileInfo = "Profile not found.";
            for (String student : students) {
                String[] parts = student.split(",");
                if (parts.length == 3 && parts[0].equals(ID)) {
                    profileInfo = "ID: " + parts[0] + "\nName: " + parts[1] + "\nDepartment: " + parts[2];
                    break;
                }
            }
            JOptionPane.showMessageDialog(this, profileInfo, "My Profile", JOptionPane.INFORMATION_MESSAGE);
        });

        viewCoursesBtn.addActionListener(e -> {
            List<String> enrollments = FileManager.readLines("enrollments.txt");
            String myCourses = enrollments.stream()
                    .filter(line -> line.split(",")[0].equals(ID))
                    .map(line -> {
                        String[] parts = line.split(",");
                        return "Course: " + parts[1] + ", Semester: " + parts[2] + ", Grade: " + parts[3];
                    })
                    .collect(Collectors.joining("\n"));

            JTextArea textArea = new JTextArea(myCourses.isEmpty() ? "No courses found." : myCourses);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "My Courses", JOptionPane.INFORMATION_MESSAGE);
        });

        viewTranscriptBtn.addActionListener(e -> {
            List<String> enrollments = FileManager.readLines("enrollments.txt");
            List<String> semesters = enrollments.stream()
                    .filter(line -> line.split(",")[0].equals(ID))
                    .map(line -> line.split(",")[2])
                    .distinct()
                    .collect(Collectors.toList());

            StringBuilder transcript = new StringBuilder("Transcript for " + ID + "\n\n");
            for (String sem : semesters) {
                double gpa = GpaCalculator.calculateSemesterGpa(ID, sem);
                transcript.append("Semester: ").append(sem).append(" - GPA: ").append(String.format("%.2f", gpa)).append("\n");
            }
            double cgpa = GpaCalculator.calculateGpa(ID);
            transcript.append("\nCumulative GPA: ").append(String.format("%.2f", cgpa));

            JTextArea textArea = new JTextArea(transcript.toString());
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "My Transcript", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(255, 105, 180));
        btn.setForeground(new Color(255, 105, 180));
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
    }
}
