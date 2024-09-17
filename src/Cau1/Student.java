package Cau1;
class Person {
   private String personID;
   private String personName;
   private String dateOfBirth;

   public Person() {}

    public Person(String personID, String personName, String dateOfBirth) {
       this.personID = personID;
       this.personName = personName;
       this.dateOfBirth = dateOfBirth;
    }
    public String getPersonID() {
       return personID;
    }
    public void setPersonID(String personID) {
       this.personID = personID;
    }
    public String getPersonName() {
       return personName;
    }
    public void setPersonName(String personName) {
       this.personName = personName;
    }
    public String getDateOfBirth() {
       return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
       this.dateOfBirth = dateOfBirth;
    }
    public String toString() {
       return "PersonID:"+personID + ", Name: "+personName+", Date Of Birth:"+dateOfBirth;
    }
}
class Student extends Person {
    private double markAvg;

    public Student(String personID, String personName, String dateOfBirth, double markAvg) {
        super(personID, personName, dateOfBirth);
        this.markAvg = markAvg;
    }
    public double getMarkAvg() {
        return markAvg;
    }
    public void setMarkAvg(double markAvg) {
        this.markAvg = markAvg;
    }
    public String toString() {
        return super.toString() + ", Average Mark: "+markAvg;
    }
}



