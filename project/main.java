
import java.util.*;
import java.io.*;

public class main {
       static Scanner sc = new Scanner(System.in);
       static ArrayList<student> students=new ArrayList<>();
       static String fileName="students.txt";
       public static void main(String[] args) 
       {
           loadFromFile();
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
                            saveToFile();
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
        
        public static void saveToFile()
        {
            File obj=new File(fileName);
            try
            {
                BufferedWriter bw=new BufferedWriter(new FileWriter(obj));
                for(student s:students)
                {
                    bw.write(s.getName() + "," + s.getTotal() + "," + s.getAverage() + "," + s.getGrade());
                    bw.newLine();
                    bw.close();
                }
            }
            catch(Exception e)
            {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }
        
        public static void loadFromFile()
        {
            File obj=new File(fileName);
            if(!obj.exists()) return;
            try
            {
                BufferedReader br=new BufferedReader(new FileReader(obj));
                String line;
                while((line=br.readLine())!=null)
                {
                    String []parts=line.split(",");
                    if(parts.length==4)
                    {
                        String name = parts[0];
                        int total = Integer.parseInt(parts[1]);
                        double average = Double.parseDouble(parts[2]);
                        char grade = parts[3].charAt(0);
                        student s=new student(name,new int[]{});
/*We just need something to satisfy the constructor — 
                        because it expects an int[].
                        That’s why we pass an empty array:
          */            
                        s.setTotal(total);
                        s.setAverage(average);
                        s.setGrade(grade);
                        students.add(s);
                        br.close();
                    }
                }
            }
            catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
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