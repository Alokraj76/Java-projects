import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Student
{
    private int rollNo;
    private String grade;
    private String name;

    //parameterized constructor
    public Student(String name,int rollNo,String grade)
    {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }
    // public void setName(String name)
    // {
    //     this.name = name;
    // }
    public String getName()
    {
        return name;
    }
    // public void setRollNo(int rollNo)
    // {
    //     this.rollNo = rollNo;
    // }
    public int getRollNo()
    {
        return rollNo;
    }
    // public void setGrade(String grade)
    // {
    //     this.grade = grade;
    // }
    public String getGrade()
    {
        return grade;
    }
    //toString method to represent student object as a string
    public String toString()
    {
        return "Student{" + "name='" + name + '\'' +",rollNo'=" + rollNo + '\'' + ",grade='" + grade + '\''+"}";
    }
}

class StudentManagementSystem
{
    private List<Student> students;
    private static final String FILE_NAME = "students.txt";
    
    //constructor to initialize the student list and load students from the file
    public StudentManagementSystem()
    {
        this.students = new ArrayList<>();
        loadStudentsFromFile();
    }

    //method to add a students and save to file
    public void addStudent(Student st)
    {
        students.add(st);
        saveStudentsToFile();
    }
    //method to remove a students and save to fille
    public void removeStudent(int rollNo)
    {
        students.removeIf(st -> st.getRollNo()==rollNo);
        saveStudentsToFile();
    }
    //method to search student by the roll number
    public Student searchStudent(int rollNo)
    {
        for(Student st : students)
        {
            if(st.getRollNo()==rollNo)
            {
                return st; // return the student object if found
            }
        }
        return null; // return null if student not found
    }
    //method to display all students;
    public void display()
    {
        for(Student st : students)
        {
            System.out.println(st);
        }
    }
    // private method to save the list of students to a file
    private void saveStudentsToFile() 
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME)))
        {
            for(Student st : students)
            {
                writer.write(st.getName() + "," + st.getRollNo()+ "," + st.getGrade());
                writer.newLine();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace(); // print any input/output errors that occur
        }
    }
    //private method to load students fromfile into the ssytem
    private void loadStudentsFromFile()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME)))
        {
            String line;
            while((line = reader.readLine())!=null)
            {
                String[] parts = line.split(",");
                if(parts.length == 3)
                {
                    String name = parts[0];
                    int rollNo = Integer.parseInt(parts[1]);
                    String grade = parts[2];
                    students.add(new Student(name,rollNo,grade));
                }
            }
        }
        catch(FileNotFoundException e)
        {
            //file not found is expected when the system starts fresh with no existing data
        }
        catch(IOException e)
        {
            e.printStackTrace(); // print any input/output errors that occur
        }
    }
}
// main class for running the student management system
public class StudentManage 
{
    public static void main(String args[]) 
    {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc= new Scanner(System.in);
        int choice;
        int rollNo;
        do
        {
            //menu for user interaction
            System.out.println("1. Add student");
            System.out.println("2. Remove student");
            System.out.println("3. Search student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.println("Enter your choice");

            while(!sc.hasNextInt())
            {
                System.out.println("Invalid input: Enter correct choice");
                sc.next(); // clear invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); //consume new line left over

            switch(choice)
            {
                case 1 -> {
                    String name;
                    do
                    {
                        System.out.println("Enter student name: ");
                        name = sc.nextLine().trim();// read and trim whitespaces
                    }while(name.isEmpty()); // repeat until a non - empty name is provided

                    do
                    {
                        System.out.println("Enter student roll number: ");
                        while(!sc.hasNextInt())
                        {
                            System.out.println("Invalid: Enter the roll number");
                            sc.next(); // clear invalid input
                        }
                        rollNo = sc.nextInt();
                        sc.nextLine();// consume new line left over
                    }while(rollNo<=0);
                    
                    String grade;
                    do
                    {
                        System.out.print("Enter student grade: ");
                        grade = sc.nextLine().trim();// read and trim white spaces
                    }while(grade.isEmpty());//repeat until a non empty grade is provided
                    Student st = new Student(name,rollNo,grade); // create Student object
                    sms.addStudent(st); //add student to the system
                }
                case 2 -> {
                    System.out.println("Enter roll number to remove the student: ");
                    while(!sc.hasNextInt())
                    {
                        System.out.println("Invalic input: Enter roll number");
                        sc.next();
                    }
                    rollNo = sc.nextInt(); // read rollnumber
                    sc.nextLine();// consume new line left over
                    sms.removeStudent(rollNo);
                }
                case 3 ->{
                    System.out.println("Enter roll number to search student");
                    while(!sc.hasNextInt())
                    {
                        System.out.println("Invalid input: Enter roll number");
                        sc.next(); // clear invalid input
                    }
                    rollNo = sc.nextInt(); // read roll number
                    Student searchStudent = sms.searchStudent(rollNo); // searc for student
                    if(searchStudent!=null)
                    System.out.println(searchStudent); // print student details if found
                    else
                    System.out.println("student not found"); // print if student not found
                }
                case 4 -> sms.display(); // display all students
                case 5 -> System.out.println("Exit..."); //exit messege
                default -> System.out.println("Invalid choice please try again");
            }
        }while(choice != 5);// repeat until user choose to exit
        sc.close();
    }
}
