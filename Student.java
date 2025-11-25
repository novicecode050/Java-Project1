package MINI_PROJECT;

public class Student {
    private int id;
    private String name;
    private String department;
    private double marks;
    private String address;
    private String fatherName;
    private String fatherPhone;
    private String motherName;
    private String motherPhone;
    private String studentPhone;
    private String section;
    private String branch;

    public Student(int id, String name, String department, double marks,
                   String address, String fatherName, String fatherPhone,
                   String motherName, String motherPhone, String studentPhone,
                   String section, String branch) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.marks = marks;
        this.address = address;
        this.fatherName = fatherName;
        this.fatherPhone = fatherPhone;
        this.motherName = motherName;
        this.motherPhone = motherPhone;
        this.studentPhone = studentPhone;
        this.section = section;
        this.branch = branch;
    }

    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getMarks() { return marks; }
    public String getAddress() { return address; }
    public String getFatherName() { return fatherName; }
    public String getFatherPhone() { return fatherPhone; }
    public String getMotherName() { return motherName; }
    public String getMotherPhone() { return motherPhone; }
    public String getStudentPhone() { return studentPhone; }
    public String getSection() { return section; }
    public String getBranch() { return branch; }
    
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setMarks(double marks) { this.marks = marks; }
    public void setAddress(String address) { this.address = address; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    public void setFatherPhone(String fatherPhone) { this.fatherPhone = fatherPhone; }
    public void setMotherName(String motherName) { this.motherName = motherName; }
    public void setMotherPhone(String motherPhone) { this.motherPhone = motherPhone; }
    public void setStudentPhone(String studentPhone) { this.studentPhone = studentPhone; }
    public void setSection(String section) { this.section = section; }
    public void setBranch(String branch) { this.branch = branch; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Dept: " + department +
               ", Marks: " + marks + ", Address: " + address +
               ", Father: " + fatherName + " (" + fatherPhone + ")" +
               ", Mother: " + motherName + " (" + motherPhone + ")" +
               ", Student Phone: " + studentPhone +
               ", Section: " + section + ", Branch: " + branch;
    }
}