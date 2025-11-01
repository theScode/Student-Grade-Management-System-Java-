import java.util.*;
import java.io.*;

public class main {
       static Scanner sc = new Scanner(System.in);
       static ArrayList<student> students=new ArrayList<>();
       public static void main(String[] args) 
       {
           loadFromDatabase();
           int choice;
           do
           {
                System.out.println("\n=== STUDENT GRADE MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student by Name");
                System.out.println("4. Save and Exit");
                System.out.println("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1: addStudent();
                            break;
                    case 2:
                            viewStudents();
                            break;
                    case 3:
                            searchStudent();
                            break;
                    case 4:
                            saveToDatabase();
                            System.out.println("Data saved successfully. Exiting...");
                            break;
                    default:
                            System.out.println("Invalid choice! Please try again.");        
                }
               
           }while(choice!=4);
       }
       
       public static void addStudent()
       {
           System.out.println("\nEnter student name: ");
           String name = sc.nextLine();

            System.out.println("Enter number of subjects: ");
            int subjects = sc.nextInt();
            
            int []marks=new int[subjects];
            for (int i = 0; i < subjects; i++)
            {
                System.out.println("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }
            student s=new student(name,marks);
            students.add(s);
            System.out.println("Student added successfully!");
       }
        public static void viewStudents() 
        {
            if (students.isEmpty()) {
                System.out.println("\nNo students to display!");
                return;
            }

            System.out.println("\n=== STUDENT DETAILS ===");
            for (student s : students) {
                s.display();
            }
        }
        
    public static void saveToDatabase() {
    try {
        java.sql.Connection con = DBConnect.getConnection();

        String query = "INSERT INTO students(name, total, average, grade) VALUES (?, ?, ?, ?)";
        java.sql.PreparedStatement ps = con.prepareStatement(query);

        for (student s : students) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getTotal());
            ps.setDouble(3, s.getAverage());
            ps.setString(4, String.valueOf(s.getGrade()));
            ps.executeUpdate();
        }

        System.out.println("✅ Data saved to MySQL successfully!");
    } catch (Exception e) {
        System.out.println("❌ Error saving to database: " + e.getMessage());
    }
}

        
        public static void loadFromDatabase() {
    try {
        java.sql.Connection con = DBConnect.getConnection();
        java.sql.Statement st = con.createStatement();
        java.sql.ResultSet rs = st.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            String name = rs.getString("name");
            int total = rs.getInt("total");
            double average = rs.getDouble("average");
            char grade = rs.getString("grade").charAt(0);

            student s = new student(name, new int[]{}); // empty marks just to construct
            s.setTotal(total);
            s.setAverage(average);
            s.setGrade(grade);

            students.add(s);
        }

        System.out.println(" Data loaded from MySQL successfully!");
    } catch (Exception e) {
        System.out.println(" Error loading from database: " + e.getMessage());
    }
}

        
        public static void searchStudent()
        {
            if (students.isEmpty()) 
            {
                System.out.println("\nNo records found!");
                return;
            }
            System.out.print("\nEnter name to search: ");
            String searchName = sc.nextLine();
            boolean found = false;
            
            for(student s:students)
            {
                if(s.getName().equalsIgnoreCase(searchName))
                {
                    s.display();
                    found=true;
                    break;
                }
            }
            if (!found)
            System.out.println("Student not found!");
        }
}

//LEARNINGS

/*
nextInt() reads only the 3 (the number).
But it leaves the Enter key (the \n) sitting inside memory (the input buffer).

So when nextLine() runs next,
it sees that leftover Enter key and thinks —
“Oh, the user already pressed Enter — so I’ll return an empty line.” 😅

That’s why it skips without waiting for you to actually type the name.
*/

/*Imagine you’re writing a letter
You don’t go to the post office after every sentence, right?
You finish the full letter, seal it — then post it.

That’s exactly what Java does!
It keeps your output in a buffer (like a half-written letter) —
and only when you say:

bw.close() actually send that data to the file (the “post office”).*/