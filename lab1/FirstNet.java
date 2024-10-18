import java.net.InetAddress;
import java.net.UnknownHostException;

public class FirstNet {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java FirstNet <network address> <range>");
            return;
        }

        String networkAddress = args[0];
        int range = Integer.parseInt(args[1]);

        try {
            InetAddress baseAddress = InetAddress.getByName(networkAddress);

            for (int i = 0; i <= range; i++) {
                byte[] addressBytes = baseAddress.getAddress();
                addressBytes[3] += i;
                InetAddress target = InetAddress.getByAddress(addressBytes);

                System.out.println("Target address: " + target.getHostAddress());
                System.out.println("Target hostname: " + target.getHostName());
                System.out.println("Canonical hostname: " + target.getCanonicalHostName());

                byte[] ipBytes = target.getAddress();
                int hashCode = 0;
                for (byte b : ipBytes) {
                    hashCode = (hashCode << 8) | (b & 0xFF);
                }
                System.out.println("IP address hashcode: " + hashCode);

                boolean isMulticast = target.isMulticastAddress();
                System.out.println("Is multicast: " + isMulticast);

                if (target.isReachable(1000)) {
                    System.out.println("Host " + target.getHostAddress() + " is reachable.");
                } else {
                    System.out.println("Host " + target.getHostAddress() + " is unreachable.");
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}