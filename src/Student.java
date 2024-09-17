import java.io.*;
import java.util.Arrays;

// Lớp Student
class Student implements Serializable {
    private String personID;
    private String personName;
    private String dateOfBirth;
    private double markAvg;

    public Student(String personID, String personName, String dateOfBirth, double markAvg) {
        this.personID = personID;
        this.personName = personName;
        this.dateOfBirth = dateOfBirth;
        this.markAvg = markAvg;
    }

    @Override
    public String toString() {
        return "Student ID: " + personID + ", Name: " + personName + ", Date of Birth: " + dateOfBirth + ", Average Mark: " + markAvg;
    }
}

// Lớp CRUDStudent
class CRUDStudent {
    // Mảng đối tượng Student: Lưu danh sách sinh viên
    private Student[] studentList;

    // Thuộc tính static numberOfStudent: Lưu số sinh viên hiện tại
    public static int numberOfStudent = 0;

    // Phương thức khởi tạo CRUDStudent() không tham số
    public CRUDStudent() {
        // Khởi tạo mảng chứa 100 sinh viên
        studentList = new Student[100];
        // Khởi tạo số lượng sinh viên là 0
        numberOfStudent = 0;
    }

    // Phương thức thêm 1 sinh viên vào danh sách
    public boolean addStudent(Student objStudent) {
        if (numberOfStudent < 100) {
            studentList[numberOfStudent] = objStudent;
            numberOfStudent++;
            return true;
        }
        return false;
    }

    // Phương thức lưu một sinh viên vào file
    public boolean addStudentToFile(Student objStudent, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename, true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(objStudent);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức trả về đối tượng sinh viên từ file
    public Student getStudentFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Student) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức lưu danh sách sinh viên vào file
    public boolean addStudentListToFile(Student[] listStudent, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listStudent);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức trả về danh sách sinh viên từ file
    public Student[] getAllStudentFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Student[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Lớp AppStudent
class AppStudent {
    public static void main(String[] args) {
        CRUDStudent crud = new CRUDStudent();

        // Tạo 3 đối tượng sinh viên
        Student student1 = new Student("S001", "Nguyen Van A", "01-01-2000", 8.5);
        Student student2 = new Student("S002", "Le Thi B", "02-02-2001", 7.8);
        Student student3 = new Student("S003", "Tran Van C", "03-03-2002", 9.2);

        // Lưu từng đối tượng vào mảng danh sách sinh viên
        crud.addStudent(student1);
        crud.addStudent(student2);
        crud.addStudent(student3);

        // Lưu từng đối tượng vào file
        String filename = "studentData.txt";
        crud.addStudentToFile(student1, filename);
        crud.addStudentToFile(student2, filename);
        crud.addStudentToFile(student3, filename);

        // Hiển thị thông tin sinh viên từ file
        System.out.println("Thông tin sinh viên từ file:");
        Student studentFromFile = crud.getStudentFromFile(filename);
        if (studentFromFile != null) {
            System.out.println(studentFromFile);
        }

        // Lưu mảng đối tượng sinh viên vào file
        crud.addStudentListToFile(new Student[]{student1, student2, student3}, filename);

        // Hiển thị danh sách sinh viên từ file
        System.out.println("Danh sách sinh viên từ file:");
        Student[] studentsFromFile = crud.getAllStudentFromFile(filename);
        if (studentsFromFile != null) {
            Arrays.stream(studentsFromFile).forEach(System.out::println);
        }
    }
}
