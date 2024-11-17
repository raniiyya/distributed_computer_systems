import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Hello extends UnicastRemoteObject implements HelloInterface {
    private final List<User> userList = new ArrayList<>();

    public Hello(String msg) throws RemoteException {
        super();
    }

    public String say() throws RemoteException {
        return "Server is running!";
    }

    public int addNumbers(int a, int b) throws RemoteException {
        return a + b;
    }

    public int calculateVolume(MyObject o) throws RemoteException {
        return o.getBreadth() * o.getHeight() * o.getLength();
    }

    public void addUser(User user) throws RemoteException {
        userList.add(user);
    }

    public void deleteUser(String firstName, String lastName) throws RemoteException {
        userList.removeIf(user -> user.getFirstName().equals(firstName) && user.getLastName().equals(lastName));
    }

    public User getUser(String firstName, String lastName) throws RemoteException {
        for (User user : userList) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws RemoteException {
        return new ArrayList<>(userList);
    }

    public void modifyUser(String firstName, String lastName, User updatedUser) throws RemoteException {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                userList.set(i, updatedUser);
                break;
            }
        }
    }

    public void shutdownServer() throws RemoteException {
        System.out.println("Shutting down the server...");
        System.exit(0);
    }
}
