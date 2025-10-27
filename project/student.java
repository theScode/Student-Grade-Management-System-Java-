public class student 
{
    private String name;
    private int [] marks;
    private int total;
    private double average;
    private char grade;

    public String getName() {
        return name;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getTotal() {
        return total;
    }

    public double getAverage() {
        return average;
    }

    public char getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
    
    
    
    public student(String n,int []m)
    {
        name=n;
        marks=m;
        calculateresult();
    }
    
    void calculateresult()
    {
        total=0;
        for(int m:marks)
            total+=m;
        average=total/((double)marks.length);
        if (average >= 90)
            grade = 'A';
        else if (average >= 75)
            grade = 'B';
        else if (average >= 60)
            grade = 'C';
        else if (average >= 40)
            grade = 'D';
        else
            grade = 'F';
    }

    // Method to display student info
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Total Marks: " + total);
        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
        System.out.println("-----------------------------");
    }
}