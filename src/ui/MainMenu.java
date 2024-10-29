package ui;

import exception.WrongInputException;
import model.*;
import service.*;
import service.impls.*;
import util.InputUtility;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainMenu {
    private static final List<String> MAIN_MENU_ITEMS = new ArrayList<>(Arrays.asList("Login", "Exit"));
    private static final List<String> ADMIN_MENU_ITEMS = new ArrayList<>(Arrays.asList("Course Operations", "Teacher Operations", "Student Operations", "Back"));
    private static final List<String> MASTER_MENU_ITEMS = new ArrayList<>(Arrays.asList("Admin Operations", "Course Operations", "Teacher Operations", "Student Operations", "Back"));
    private static final List<String> ADMIN_OPERATION_MENU_ITEMS = new ArrayList<>(Arrays.asList("Add Admin", "Update Admin", "Delete admin", "Find admin", "Show all Admins", "Back"));
    private static final List<String> COURSE_OPERATION_MENU_ITEMS = new ArrayList<>(Arrays.asList("Add Course", "Update Course", "Delete Course", "Find Course", "Show all Courses", "Back"));
    private static final List<String> TEACHER_OPERATION_MENU_ITEMS = new ArrayList<>(Arrays.asList("Add Teacher", "Update Teacher", "Delete Teacher", "Find Teacher", "Show all Teachers", "Give Course to Teacher", "Back"));
    private static final List<String> STUDENT_OPERATION_MENU_ITEMS = new ArrayList<>(Arrays.asList("Add Student", "Update Student", "Delete Student", "Find Student", "Show all Students", "Sign Student to Course", "Back"));
    private static final List<String> TEACHER_MENU_ITEMS = new ArrayList<>(Arrays.asList("Set Exam", "Give Exam score to Student", "Back"));
    private static final List<String> STUDENT_MENU_ITEMS = new ArrayList<>(Arrays.asList("Show scores report", "Back"));

    private static final AdminService adminService = new AdminServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final TeacherService teacherService = new TeacherServiceImpl();
    private static final StudentService studentService = new StudentServiceImpl();
    private static final ExamService examService = new ExamServiceImpl();
    private static final UserService userService = new UserServiceImpl();

    public static void runProgram() {
        boolean exit = false;
        while (!exit) {
            exit = loginMenu();
        }
    }

    private static boolean loginMenu() throws WrongInputException {
        while (true) {
            System.out.println("=====================================================");
            printMenu(MAIN_MENU_ITEMS, "Main Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            if (input == 1) {
                String username = InputUtility.getStringInput("Enter your username: ");
                String password = InputUtility.getStringInput("Enter your password: ");
                Role login = userService.login(username, password);
                switch (login) {
                    case MASTER -> masterMenu();
                    case ADMIN -> adminMenu();
                    case TEACHER -> teacherMenu(username);
                    case STUDENT -> studentMenu(username);
                    default -> throw new WrongInputException("Invalid choice!");
                }
            } else if (input == 2) {
                return false;
            } else {
                throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void masterMenu() throws WrongInputException {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(MASTER_MENU_ITEMS, "Master Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> adminOperationMenu();
                case 2 -> courseOperationMenu();
                case 3 -> teacherOperationMenu();
                case 4 -> studentOperationMenu();
                case 5 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void adminMenu() throws WrongInputException {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(ADMIN_MENU_ITEMS, "Admin Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> courseOperationMenu();
                case 2 -> teacherOperationMenu();
                case 3 -> studentOperationMenu();
                case 4 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void studentMenu(String username) throws WrongInputException {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(STUDENT_MENU_ITEMS, "Student Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> showScoresReport(username);
                case 2 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void adminOperationMenu() throws WrongInputException {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(ADMIN_OPERATION_MENU_ITEMS, "Admin Operation Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> addAdmin();
                case 2 -> updateAdmin();
                case 3 -> deleteAdmin();
                case 4 -> findAdminById();
                case 5 -> showAllAdmins();
                case 6 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void courseOperationMenu() throws WrongInputException {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(COURSE_OPERATION_MENU_ITEMS, "Course Operation Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> addCourse();
                case 2 -> updateCourse();
                case 3 -> deleteCourse();
                case 4 -> findCourseById();
                case 5 -> showAllCourses();
                case 6 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void teacherOperationMenu() throws WrongInputException {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(TEACHER_OPERATION_MENU_ITEMS, "Teacher Operation Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> addTeacher();
                case 2 -> updateTeacher();
                case 3 -> deleteTeacher();
                case 4 -> findTeacherById();
                case 5 -> showAllTeachers();
                case 6 -> giveCourseToTeacher();
                case 7 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void studentOperationMenu() throws WrongInputException {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(STUDENT_OPERATION_MENU_ITEMS, "Student Operation Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> findStudentById();
                case 5 -> showAllStudents();
                case 6 -> signStudentToCourse();
                case 7 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void addAdmin() {
        String firstName = InputUtility.getStringInput("Enter Admin First Name: ");
        String lastName = InputUtility.getStringInput("Enter Admin Last Name: ");
        Date dob = InputUtility.getDateInput("Enter Admin Date of Birth (yyyy-mm-dd): ");
        String nationalCode = InputUtility.getStringInput("Enter Admin National Code: ");
        String password = InputUtility.getStringInput("Enter Admin Password: ");
        Role role = Role.ADMIN;
        User user = new User(null, nationalCode, password, role);
        Admin admin = new Admin(null, firstName, lastName, dob, nationalCode, user);
        adminService.addAdmin(admin);
        userService.addUser(user);
        System.out.println("Admin added successfully!");
    }

    private static void updateAdmin() {
        Long courseId = InputUtility.getLongInput("Enter Admin ID to update: ");
        String firstName = InputUtility.getStringInput("Enter New Admin First Name: ");
        String lastName = InputUtility.getStringInput("Enter New Admin Last Name: ");
        Date dob = InputUtility.getDateInput("Enter New Admin Date of Birth (yyyy-mm-dd): ");
        String nationalCode = InputUtility.getStringInput("Enter New Admin National Code: ");
        String password = InputUtility.getStringInput("Enter New Admin Password: ");
        Long userId = userService.findUserByNationalCode(nationalCode).get().getUserId();
        User user = new User(userId, nationalCode, password, Role.ADMIN);
        Admin admin = new Admin(courseId, firstName, lastName, dob, nationalCode, user);
        adminService.updateAdmin(admin);
        userService.updateUser(user);
        System.out.println("Admin updated successfully!");
    }

    private static void deleteAdmin() {
        Long adminId = InputUtility.getLongInput("Enter Admin ID to delete: ");
        adminService.deleteAdmin(adminId);
        System.out.println("Admin deleted successfully!");
    }

    private static void findAdminById() {
        Long adminId = InputUtility.getLongInput("Enter Admin ID to find: ");
        Optional<Admin> admin = adminService.findAdminById(adminId);
        if (admin.isPresent()) {
            System.out.println(admin.get());
        } else {
            System.out.println("Admin not found!");
        }
    }

    private static void showAllAdmins() {
        adminService.getAllAdmins().forEach(System.out::println);
    }

    private static void addCourse() {
        String title = InputUtility.getStringInput("Enter Course Title: ");
        int unit = InputUtility.getIntInput("Enter Course Unit: ");
        Course course = new Course(null, title, unit);
        courseService.addCourse(course);
        System.out.println("Course added successfully!");
    }

    private static void updateCourse() {
        Long courseId = InputUtility.getLongInput("Enter Course ID to update: ");
        String title = InputUtility.getStringInput("Enter New Course Title: ");
        int unit = InputUtility.getIntInput("Enter New Course Unit: ");
        Course course = new Course(courseId, title, unit);
        courseService.updateCourse(course);
        System.out.println("Course updated successfully!");
    }

    private static void deleteCourse() {
        Long courseId = InputUtility.getLongInput("Enter Course ID to delete: ");
        courseService.deleteCourse(courseId);
        System.out.println("Course deleted successfully!");
    }

    private static void findCourseById() {
        Long courseId = InputUtility.getLongInput("Enter Course ID to find: ");
        Optional<Course> course = courseService.findCourseById(courseId);
        if (course.isPresent()) {
            System.out.println(course.get());
        } else {
            System.out.println("Course not found!");
        }
    }

    private static void showAllCourses() {
        courseService.getAllCourses().forEach(System.out::println);
    }

    private static void addTeacher() {
        String firstName = InputUtility.getStringInput("Enter Teacher First Name: ");
        String lastName = InputUtility.getStringInput("Enter Teacher Last Name: ");
        Date dob = InputUtility.getDateInput("Enter Teacher Date of Birth (yyyy-mm-dd): ");
        String nationalCode = InputUtility.getStringInput("Enter Teacher National Code: ");
        String password = InputUtility.getStringInput("Enter Teacher Password: ");
        User user = new User(null, nationalCode, password, Role.TEACHER);
        Teacher teacher = new Teacher(null, firstName, lastName, dob, nationalCode, null, user);
        teacherService.addTeacher(teacher);
        userService.addUser(user);
        System.out.println("Teacher added successfully!");
    }

    private static void updateTeacher() {
        Long teacherId = InputUtility.getLongInput("Enter Teacher ID to update: ");
        String firstName = InputUtility.getStringInput("Enter New Teacher First Name: ");
        String lastName = InputUtility.getStringInput("Enter New Teacher Last Name: ");
        Date dob = InputUtility.getDateInput("Enter New Teacher Date of Birth (yyyy-mm-dd): ");
        String nationalCode = InputUtility.getStringInput("Enter New Teacher National Code: ");
        Long courseId = InputUtility.getLongInput("Enter New Teacher Course ID: ");
        String password = InputUtility.getStringInput("Enter New Teacher Password: ");
        Long userId = userService.findUserByNationalCode(nationalCode).get().getUserId();
        User user = new User(userId, nationalCode, password, Role.TEACHER);
        Teacher teacher = new Teacher(teacherId, firstName, lastName, dob, nationalCode, courseId, user);
        teacherService.updateTeacher(teacher);
        userService.updateUser(user);
        System.out.println("Teacher updated successfully!");
    }

    private static void deleteTeacher() {
        Long teacherId = InputUtility.getLongInput("Enter Teacher ID to delete: ");
        teacherService.deleteTeacher(teacherId);
        System.out.println("Teacher deleted successfully!");
    }

    private static void findTeacherById() {
        Long teacherId = InputUtility.getLongInput("Enter Teacher ID to find: ");
        Optional<Teacher> teacher = teacherService.findTeacherById(teacherId);
        if (teacher.isPresent()) {
            System.out.println(teacher.get());
        } else {
            System.out.println("Teacher not found!");
        }
    }

    private static void showAllTeachers() {
        teacherService.getAllTeachers().forEach(System.out::println);
    }

    private static void giveCourseToTeacher() {
        Long courseId = InputUtility.getLongInput("Enter Course ID: ");
        Long teacherId = InputUtility.getLongInput("Enter Teacher ID: ");

        System.out.println("Course assigned to teacher successfully!");
    }

    private static void addStudent() {
        String firstName = InputUtility.getStringInput("Enter Student First Name: ");
        String lastName = InputUtility.getStringInput("Enter Student Last Name: ");
        Date dob = InputUtility.getDateInput("Enter Student Date of Birth (yyyy-mm-dd): ");
        String nationalCode = InputUtility.getStringInput("Enter Student National Code: ");
        Double gpu = InputUtility.getDoubleInput("Enter Student GPU: ");
        String password = InputUtility.getStringInput("Enter Student Password: ");
        User user = new User(null, nationalCode, password, Role.STUDENT);
        Student student = new Student(null, firstName, lastName, dob, nationalCode, gpu, user);
        studentService.addStudent(student);
        userService.addUser(user);
        System.out.println("Student added successfully!");
    }

    private static void updateStudent() {
        Long studentId = InputUtility.getLongInput("Enter Student ID to update: ");
        String firstName = InputUtility.getStringInput("Enter New Student First Name: ");
        String lastName = InputUtility.getStringInput("Enter New Student Last Name: ");
        Date dob = InputUtility.getDateInput("Enter New Student Date of Birth (yyyy-mm-dd): ");
        String nationalCode = InputUtility.getStringInput("Enter New Student National Code: ");
        Double gpu = InputUtility.getDoubleInput("Enter New Student GPU: ");
        String password = InputUtility.getStringInput("Enter New Student Password: ");
        Long userId = userService.findUserByNationalCode(nationalCode).get().getUserId();
        User user = new User(userId, nationalCode, password, Role.STUDENT);
        Student student = new Student(studentId, firstName, lastName, dob, nationalCode, gpu, user);
        studentService.updateStudent(student);
        userService.updateUser(user);
        System.out.println("Student updated successfully!");
    }

    private static void deleteStudent() {
        Long studentId = InputUtility.getLongInput("Enter Student ID to delete: ");
        studentService.deleteStudent(studentId);
        System.out.println("Student deleted successfully!");
    }

    private static void findStudentById() {
        Long studentId = InputUtility.getLongInput("Enter Student ID to find: ");
        Optional<Student> student = studentService.findStudentById(studentId);
        if (student.isPresent()) {
            System.out.println(student.get());
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void showAllStudents() {
        studentService.getAllStudents().forEach(System.out::println);
    }

    private static void signStudentToCourse() {
        Long studentId = InputUtility.getLongInput("Enter Student ID: ");
        Long courseId = InputUtility.getLongInput("Enter Course ID: ");
        // Implement logic to sign student to course
        System.out.println("Student signed to course successfully!");
    }

    private static void teacherMenu(String username) {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("=====================================================");
            printMenu(TEACHER_MENU_ITEMS, "Teacher Menu");
            int input = InputUtility.getIntInput("\nChoice: ");
            switch (input) {
                case 1 -> setExam(username);
                case 2 -> giveExamScoreToStudent(username);
                case 3 -> isDone = true;
                default -> throw new WrongInputException("Invalid choice!");
            }
        }
    }

    private static void setExam(String username) {
        Date examDate = InputUtility.getDateInput("Enter Date of Exam (yyyy-mm-dd): ");
        Long courseId = teacherService.findTeacherByNationalCode(username).get().getCourseId();
        Exam exam = new Exam();
        exam.setExamDate(examDate);
        exam.setCourseId(courseId);
        examService.addExam(exam);
        System.out.println("Exam set successfully!");
    }

    private static void giveExamScoreToStudent(String username) {
        Long examId = InputUtility.getLongInput("Enter Exam ID: ");
        Double grade = InputUtility.getDoubleInput("Enter Exam Grade: ");
        Long courseId = teacherService.findTeacherByNationalCode(username).get().getCourseId();
        Optional<Exam> exam = examService.findExamByIdAndCourseId(examId, courseId);
        if (exam.isPresent()) {
            exam.get().setGrade(grade);
            examService.updateExam(exam.get());
            System.out.println("Exam score updated successfully!");
        } else {
            System.out.println("Exam not found!");
        }
    }

    private static void showScoresReport(String username) {
        System.out.println("Scores Report:");
        examService.getScoresReport(username).forEach(System.out::println);
    }

    private static void printMenu(List<String> menuItems, String title) {
        System.out.println(title);
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("    %d. %s%n", i + 1, menuItems.get(i));
        }
    }
}
