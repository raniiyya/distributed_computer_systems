import java.rmi.*;
import java.util.List;

public interface HelloInterface extends Remote {
    public String say() throws RemoteException;
    public int addNumbers(int a, int b) throws RemoteException;
    public int calculateVolume(MyObject o) throws RemoteException;

    public void addUser(User user) throws RemoteException;
    public void deleteUser(String firstName, String lastName) throws RemoteException;
    public User getUser(String firstName, String lastName) throws RemoteException;
    public List<User> getAllUsers() throws RemoteException;
    public void modifyUser(String firstName, String lastName, User updatedUser) throws RemoteException;
    public void shutdownServer() throws RemoteException;
}
