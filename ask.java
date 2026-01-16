import java.util.Scanner;
interface Printable{
    void printInfo();
}
abstract class Person implements Printable {
    protected String name;
    protected int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public int getID(){
        return id;
    }
}
class Student extends Person{
    private final String RegNum;
    private final Course[] courses;
    private int courseCount;
    public Student(String name, int id, String RegNum, int maxCourses){
        super(name,id);
        this.RegNum = RegNum;
        courses = new Course[maxCourses];
        courseCount = 0;
    }
    public boolean addCourse(Course c){
        if(courseCount < courses.length){
            courses[courseCount++] = c;
            return true;
        }
        else{
            System.out.println("Maximum courses reached for Student");
            return false;
        }
    }
@Override
public void printInfo() {
        System.out.println("Student | ID: "+id+" | Name: "+ name +" | Reg: "+RegNum);
        if (courseCount == 0){
            System.out.println("No Courses");
        }
        else{
            for(int i = 0; i <courseCount; i++){
                System.out.println(" - "+ courses[i].getTitle());
            }
        }
    }
}
class Professor extends Person{
    private final String specialty;
    private final Course[] courses;
    private int courseCount;
    public Professor(String name, int id, String specialty, int maxCourses){
        super(name,id);
        this.specialty = specialty;
        courses = new Course[maxCourses];
        courseCount = 0;
    }
    public boolean assignCourse(Course c){
        if(courseCount < courses.length){
            courses[courseCount++] = c;
            return true;
        }
        else{
            System.out.println("Maximum courses reached for Professor");
            return false;
        }
    }
    @Override
    public void printInfo() {
        System.out.println("Professor | ID: "+id+" | Name: "+ name +" | Specialty: "+specialty);
        if (courseCount == 0){
            System.out.println("No Courses assigned");
        }
        else{
            for(int i = 0; i <courseCount; i++){
                System.out.println(" - "+ courses[i].getTitle());
            }
        }
    }
}
class Course implements Printable{
    private final int code;
    private final String title;
    public Course(int code, String title){
        this.code = code;
        this.title = title;
    }
    public int getCode(){
        return code;
    }
    @Override
    public void printInfo() {
        System.out.println("Course | Code: " + code + " | Title: " + title );
    }
    public String getTitle(){
        return title;
    }
}
public class ask {
    static Scanner sc = new Scanner(System.in);
    static final int Max_Students = 100;
    static final int Max_Professors = 50;
    static final int Max_Courses = 50;
    static final int Max_Courses_PP = 10;
    static Student[] students = new Student[Max_Students];
    static int student_count = 0;
    static Professor[] professors = new Professor[Max_Professors];
    static int professor_count = 0;
    static Course[] courses = new Course[Max_Courses];
    static int course_count = 0;
    public static void main(String[] args){
        int choice;
        do{
            menu();
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1 -> addStudent();
                case 2 -> addProf();
                case 3 -> addCourse();
                case 4 -> enroll();
                case 5 -> assign();
                case 6 -> delete();
                case 7 -> showall();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid Input");
            }
        }while(choice!=0);
    }
    static void menu(){
        System.out.println("============MENU============");
        System.out.println("(1) Add Student ");
        System.out.println("(2) Add Professor ");
        System.out.println("(3) Add Course ");
        System.out.println("(4) Enroll Student to Course ");
        System.out.println("(5) Assign Professor to Course ");
        System.out.println("(6) Delete ID ");
        System.out.println("(7) Show All ");
        System.out.println("(0) Exit ");
        System.out.println("Choice: ");
    }
    static void addStudent(){
        if (student_count >= Max_Students){
            System.out.println("Maximum number of students reached!");
            return;
        }
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Reg Number: ");
        String RegNum = sc.nextLine();
        students[student_count++] = new Student(name, id, RegNum, Max_Courses);
        System.out.println("Student Added!");
    }
    static Student findStudentById(){
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        for(int i = 0; i < student_count;i++){
            if(students[i].getID() == id)
                return students[i];
        }
        System.out.println("Student Not Found.");
        return null;
    }
    static void addProf(){
        if(professor_count>= Max_Professors){
            System.out.println("Maximum number of Professors Reached!");
            return;
        }
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Specialty: ");
        String specialty = sc.nextLine();
        professors[professor_count++] = new Professor(name, id, specialty, Max_Courses);
        System.out.println("Professor Added!");
    }
    static Professor findProfessorById(){
        System.out.print("Professor ID: ");
        int id = sc.nextInt();
        for(int i = 0; i < professor_count; i++){
            if(professors[i].getID() == id)
                return professors[i];
        }
        System.out.println("Professor Not Found.");
        return null;
    }
    static void addCourse(){
        if(course_count >=Max_Courses){
            System.out.println("Maximum number of Courses Reached!");
            return;
        }
        System.out.print("Course Code: ");
        int code = sc.nextInt();
        sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        courses[course_count++] = new Course(code, title);
        System.out.println("Course Added!");
    }
    static Course findCourseByCode(){
        System.out.print("Course Code: ");
        int code = sc.nextInt();
        for (int i = 0; i <course_count; i++){
            if(courses[i].getCode() == code)
                return courses[i];
        }
        System.out.println("Course not Found.");
        return null;
    }
    static void enroll(){
        Student s = findStudentById();
        if(s == null) return;
        Course c = findCourseByCode();
        if(c == null) return;
        if(s.addCourse(c)){
            System.out.println("Student Has Been Enrolled to the Course.");
        }
    }
    static void assign(){
        Professor p = findProfessorById();
        if(p==null) return;
        Course c = findCourseByCode();
        if(c==null) return;
        if (p.assignCourse(c)){
            System.out.println("Course Has Been Assigned to Professor.");
        }
    }
    static void delete(){
        System.out.print("Delete ID: ");
        int id = sc.nextInt();
        for(int i = 0; i <student_count; i++){
            if(students[i].getID() == id){
                for(int j = i; j <student_count - 1; j++) students[j] = students[j+1];
                students[--student_count] = null;
                System.out.println("Student Has Been Deleted.");
                return;
            }
        }
        for(int i = 0; i<professor_count;i++){
            if(professors[i].getID() == id){
                for(int j = i; j < professor_count -1; j++) professors[j] = professors[j+1];
                professors[--professor_count] = null;
                System.out.println("Professor Has Been Deleted.");
                return;
            }
        }
        System.out.println("ID not Found.");
    }
    static void showall(){
        System.out.println("\n---- Students ----");
        for(int i = 0; i<student_count;i++){
            students[i].printInfo();
        }
        System.out.println("\n---- Professors ----");
        for(int i = 0; i<professor_count;i++){
            professors[i].printInfo();
        }
        System.out.println("\n---- Courses ----");
        for(int i = 0; i<course_count;i++){
            courses[i].printInfo();
        }
    }
}
