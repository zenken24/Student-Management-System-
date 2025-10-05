# Student Management System

A simple, GUI-based Student Management System built with **Java Swing**. This application manages students, instructors, and courses for a 4-year bachelor program, with distinct roles and permissions for Admins, Instructors, and Students. All data is persisted locally in `.txt` files.

-----

## Features 🚀

The system is divided into three user roles, each with a dedicated dashboard and functionalities.

### 👨‍💼 Admin

  * **User Management:** Register new students and instructors, automatically creating their login credentials.
  * **Course Creation:** Create new courses with details like course code, title, credits, and an assigned instructor.
  * **Enrollment:** Enroll students into various courses for a specific semester.
  * **System Maintenance:** (Future Scope) Edit or delete student, instructor, or course records.

### 👩‍🏫 Instructor

  * **View Courses:** See a list of all courses assigned to them.
  * **View Enrolled Students:** Check the list of students enrolled in any of their courses.
  * **Grade Management:** Assign or update grades for students in their courses.

### 🧑‍🎓 Student

  * **View Profile:** Check their personal information (ID, Name, Department).
  * **View Enrolled Courses:** See a list of all courses they are currently enrolled in, along with the assigned grades.
  * **View Transcript:** Generate a full academic transcript, showing a semester-wise GPA breakdown and the final Cumulative GPA (CGPA).

-----

## Tech Stack 🛠️

  * **Language:** Java
  * **GUI Toolkit:** Java Swing (for the graphical user interface)
  * **Data Storage:** Plain `.txt` files for simplicity and portability.

-----

## Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

You must have the **Java Development Kit (JDK)** installed on your computer.

### Installation & Running the Project

You can run this project in two ways:

#### 1\. Using an IDE (Recommended)

1.  Clone or download the project folder.
2.  Open the folder in your favorite Java IDE (e.g., VS Code, IntelliJ IDEA, Eclipse).
3.  Locate the `Main.java` file.
4.  Right-click on `Main.java` and select **"Run"**.

#### 2\. Using the Command Line

1.  Open a terminal or command prompt.
2.  Navigate to the root directory of the project folder using the `cd` command.
    ```bash
    cd path/to/your/StudentSystem
    ```
3.  Compile all the Java source files:
    ```bash
    javac *.java
    ```
4.  Run the main application:
    ```bash
    java Main
    ```

-----

## ⚠️ Important: First-Time Setup

When you run the application for the first time, the `login.txt` file will be empty. You must **manually create an admin user** to log in and start using the system.

1.  Run the program once to let it generate the `.txt` files.

2.  Open the `login.txt` file in a text editor.

3.  Add the following line and save the file:

    ```
    admin,admin123,admin
    ```

4.  Now, re-run the application. You can log in with:

      * **Username:** `admin`
      * **Password:** `admin123`

Once logged in as an admin, you can register students and instructors, who will be given default credentials to log in with.

-----

## Data File Structure 🗃️

The application uses the following `.txt` files in the root directory to store data:

  * `login.txt`: Stores user credentials (`username,password,role`).
  * `students.txt`: Stores student records (`id,name,department`).
  * `instructors.txt`: Stores instructor records (`id,name,department`).
  * `courses.txt`: Stores course details (`courseCode,title,credits,instructorId`).
  * `enrollments.txt`: Stores student enrollment records (`studentId,courseCode,semester,grade`).
