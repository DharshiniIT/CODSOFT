import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class CourseInfo {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int enrolledStudents;

    public CourseInfo(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public boolean hasAvailableSlots() {
        return enrolledStudents < capacity;
    }

    public void registerStudent() {
        enrolledStudents++;
    }

    public void dropStudent() {
        enrolledStudents--;
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled: " + enrolledStudents);
        System.out.println("Available Slots: " + (capacity - enrolledStudents));
        System.out.println("Schedule: " + schedule);
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<CourseInfo> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(CourseInfo course) {
        if (course.hasAvailableSlots()) {
            registeredCourses.add(course);
            course.registerStudent();
            System.out.println("Successfully registered for course: " + course.title);
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }

    public void dropCourse(CourseInfo course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Successfully dropped course: " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("Courses registered by " + name + ":");
        for (CourseInfo course : registeredCourses) {
            System.out.println("- " + course.title);
        }
    }
}

public class Course {
    private static HashMap<String, CourseInfo> courseCatalog = new HashMap<>();
    private static HashMap<String, Student> studentDatabase = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample courses
        courseCatalog.put("CS101", new CourseInfo("CS101", "Introduction to Programming", "Learn the basics of programming", 30, "MWF 10:00 AM - 11:00 AM"));
        courseCatalog.put("MA102", new CourseInfo("MA102", "Calculus", "Study of calculus concepts", 25, "TTh 1:00 PM - 2:30 PM"));
        courseCatalog.put("PHY103", new CourseInfo("PHY103", "Physics", "Fundamentals of physics", 20, "MWF 2:00 PM - 3:00 PM"));

        // Sample students
        studentDatabase.put("S001", new Student("S001", "Alice"));
        studentDatabase.put("S002", new Student("S002", "Bob"));

        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    listAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    displayRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void listAvailableCourses() {
        System.out.println("\n--- Available Courses ---");
        for (CourseInfo course : courseCatalog.values()) {
            course.displayCourseDetails();
            System.out.println();
        }
    }

    private static void registerForCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = sc.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Invalid Student ID.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine();
        CourseInfo course = courseCatalog.get(courseCode);

        if (course == null) {
            System.out.println("Invalid Course Code.");
            return;
        }

        student.registerCourse(course);
    }

    private static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = sc.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Invalid Student ID.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = sc.nextLine();
        CourseInfo course = courseCatalog.get(courseCode);

        if (course == null) {
            System.out.println("Invalid Course Code.");
            return;
        }

        student.dropCourse(course);
    }

    private static void displayRegisteredCourses() {
        System.out.print("Enter Student ID: ");
        String studentID = sc.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Invalid Student ID.");
            return;
        }

        student.displayRegisteredCourses();
    }
}
