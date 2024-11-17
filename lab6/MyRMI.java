import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyRMI {
    public static void main(String[] args) {
        try {
            Registry r = LocateRegistry.createRegistry(5001);
            r.rebind("Hello", new Hello("Hello from the RMI server!"));
            System.out.println("The RMI server is up!");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
