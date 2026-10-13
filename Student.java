public class Student {
    private String name;
    private String id;
    private String sex;
    private String course;
    private String grade;
    private String department;
    private int age;

    public Student(String name, String id, String sex, String course, String grade, String department, int age) {
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.course = course;
        this.grade = grade;
        this.department = department;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getId() { return id; }
    public String getSex() { return sex; }
    public String getCourse() { return course; }
    public String getGrade() { return grade; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setSex(String sex) { this.sex = sex; }
    public void setCourse(String course) { this.course = course; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setDepartment(String department) { this.department = department; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return String.format("Name: %-15s | ID: %-8s | Sex: %-6s | Course: %-10s | Grade: %-5s | Dept: %-10s | Age: %-3d",
                name, id, sex, course, grade, department, age);
    }
}