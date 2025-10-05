import java.io.*;
import java.util.*;
import java.util.stream.Collectors; 
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private static final String users_file = "login.txt";
    private static final String students_file = "students.txt";
    private static final String instructors_file = "instructors.txt";
    private static final String courses_file = "courses.txt";
    private static final String enrollments_file = "enrollments.txt";

    public static void initializeFiles(){
        try{
            //base directory exist kore naki na bujhay 
            Files.createDirectories(Paths.get("."));
            //paths.get(fhwfe) == address of the file; used to make the file less error prone
            if(!Files.exists(Paths.get(users_file))){
                Files.createFile(Paths.get(users_file));
            }
            if(!Files.exists(Paths.get(students_file))){
                Files.createFile(Paths.get(students_file));
            }
            if(!Files.exists(Paths.get(instructors_file))){
                Files.createFile(Paths.get(instructors_file));
            }
            if(!Files.exists(Paths.get(courses_file))){
                Files.createFile(Paths.get(courses_file));
            }
            if(!Files.exists(Paths.get(enrollments_file))){
                Files.createFile(Paths.get(enrollments_file));
            }
        } catch(IOException e){
            System.err.println("Error Initializing Files: " + e.getMessage());
        }
    }
    //reads line from the files
    public static List<String> readLines(String fileName){
        try{
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e){
            System.err.println("Error Reading File " + fileName + ": "+ e.getMessage());
            return new ArrayList<>();
            //if theres an error, it returns empty array 
        }
    }

    public static void writeLine(String fileName, String line){
        try{
            try (//true for appending instead of erasing
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                //writes line
                writer.write(line);
                // /n new line 
                writer.newLine();
            }
        } catch(IOException e){
            System.err.println("Error Writing Lines to File " + fileName + " : " + e.getMessage());
        }
    }
    //for overwriting the lines and updating info 
    public static void overwriteFile(String fileName, List<String> lines){
        try{
            Files.write(Paths.get(fileName), lines);
        } catch(IOException e){
            System.err.println("Error Overwriting Files in File " + fileName + ": " + e.getMessage());
        }
    }
    //add student-record
    public static void addStudent(String ID, String name, String dept){
        writeLine(students_file, String.join(",", ID, name, dept));
    }

    //add instructor-record
    public static void addInstructor(String ID, String name, String dept){
        writeLine(instructors_file, String.join(",", ID, name, dept));
    }  

    //add course-record
    public static void addCourse(String code, String title, String credit, String instructorID){
        writeLine(courses_file, String.join(",", code, title, credit, instructorID));
    }

    //add enrollstudent-record
    public static void enrollStudent(String ID, String code, String sem){
        writeLine(enrollments_file, String.join(",", ID, code, sem, "N/A"));
    }

    //add grade/ update grade
    public static void updateGrade(String ID, String code, String newGrade){
        List<String> lines = readLines(enrollments_file);
        List<String> updatedLines = lines.stream()
            .map(line -> {
                String[] parts = line.split(",");
                if(parts.length >= 2 && parts[0].equals(ID) && parts[1].equals(code)){
                    return String.join(",", parts[0], parts[1], parts[2], newGrade);
                }
                return line;
            })
            .collect(Collectors.toList());
        overwriteFile(enrollments_file, updatedLines);
    }

}
