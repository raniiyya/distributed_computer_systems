import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String birthdate;
    private double salary;
    private Gender gender;
    private String division;
    private String position;

    public User(String firstName, String lastName, String birthdate, double salary, Gender gender, String division, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.salary = salary;
        this.gender = gender;
        this.division = division;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public double getSalary() {
        return salary;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDivision() {
        return division;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate + ", salary=" + salary
                + ", gender=" + gender + ", division=" + division + ", position=" + position + "]";
    }
}
