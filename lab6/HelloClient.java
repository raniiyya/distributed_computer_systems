import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class HelloClient {
    public static void main(String[] argv) {
        Scanner keyb = new Scanner(System.in);
        int choice;
        String firstName, lastName;

        try {
            HelloInterface hi = (HelloInterface) Naming.lookup("//localhost:5001/Hello");

            while (true) {
                System.out.println("1. Add User");
                System.out.println("2. Delete User");
                System.out.println("3. Get User Details");
                System.out.println("4. Get All Users");
                System.out.println("5. Modify User");
                System.out.println("6. Shutdown Server");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                choice = keyb.nextInt();
                keyb.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter First Name: ");
                        firstName = keyb.nextLine();
                        System.out.print("Enter Last Name: ");
                        lastName = keyb.nextLine();
                        System.out.print("Enter Birthdate (yyyy-mm-dd): ");
                        String birthdate = keyb.nextLine();
                        System.out.print("Enter Salary: ");
                        double salary = keyb.nextDouble();
                        keyb.nextLine();
                        System.out.print("Enter Gender (MALE/FEMALE): ");
                        String gender = keyb.nextLine().toUpperCase();
                        System.out.print("Enter Division: ");
                        String division = keyb.nextLine();
                        System.out.print("Enter Position: ");
                        String position = keyb.nextLine();
                        User newUser = new User(firstName, lastName, birthdate, salary, Gender.valueOf(gender), division, position);
                        hi.addUser(newUser);
                        break;

                    case 2:
                        System.out.print("Enter First Name to delete: ");
                        firstName = keyb.nextLine();
                        System.out.print("Enter Last Name to delete: ");
                        lastName = keyb.nextLine();
                        hi.deleteUser(firstName, lastName);
                        break;

                    case 3:
                        System.out.print("Enter First Name: ");
                        firstName = keyb.nextLine();
                        System.out.print("Enter Last Name: ");
                        lastName = keyb.nextLine();
                        User user = hi.getUser(firstName, lastName);
                        if (user != null) {
                            System.out.println(user);
                        } else {
                            System.out.println("User not found!");
                        }
                        break;

                    case 4:
                        List<User> users = hi.getAllUsers();
                        for (User u : users) {
                            System.out.println(u);
                        }
                        break;

                    case 5:
                        System.out.print("Enter First Name of user to modify: ");
                        firstName = keyb.nextLine();
                        System.out.print("Enter Last Name of user to modify: ");
                        lastName = keyb.nextLine();
                        System.out.print("Enter new Birthdate (yyyy-mm-dd): ");
                        birthdate = keyb.nextLine();
                        System.out.print("Enter new Salary: ");
                        salary = keyb.nextDouble();
                        keyb.nextLine();
                        System.out.print("Enter new Gender (MALE/FEMALE): ");
                        gender = keyb.nextLine().toUpperCase();
                        System.out.print("Enter new Division: ");
                        division = keyb.nextLine();
                        System.out.print("Enter new Position: ");
                        position = keyb.nextLine();
                        User modifiedUser = new User(firstName, lastName, birthdate, salary, Gender.valueOf(gender), division, position);
                        hi.modifyUser(firstName, lastName, modifiedUser);
                        break;

                    case 6:
                        hi.shutdownServer();
                        break;

                    case 7:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice, try again.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
