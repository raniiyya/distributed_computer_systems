import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;   

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;   

public class NetworkInterfaces {

    public static void main(String[] args) throws SocketException, UnknownHostException {

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        NetworkInterface thisInterface;
        while (networkInterfaces.hasMoreElements()) {
            thisInterface = networkInterfaces.nextElement();
            byte[] macAddress = thisInterface.getHardwareAddress();
            if (macAddress != null) {
                StringBuilder macAddressHex = new StringBuilder();
                for (byte b : macAddress) {
                    macAddressHex.append(String.format("%02X", b & 0xFF) + ":");
                }
                macAddressHex.deleteCharAt(macAddressHex.length() - 1);
                System.out.println(thisInterface.getDisplayName() + ": " + macAddressHex.toString());
            } else {
                System.out.println(thisInterface.getDisplayName() + ": No MAC address available");
            }
        }

        if (args.length == 1) {
            String interfaceName = args[0];
            NetworkInterface selectedInterface = NetworkInterface.getByName(interfaceName);
            if (selectedInterface != null) {
                System.out.println("Details for interface: " + interfaceName);
                byte[] macAddress = selectedInterface.getHardwareAddress();
                if (macAddress != null) {
                    System.out.println("  MAC address: " + Arrays.toString(macAddress).replaceAll(" ", ":"));
                } else {
                    System.out.println("  MAC address: Not available");
                }
                Enumeration<InetAddress> inetAddresses = selectedInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress address = inetAddresses.nextElement();
                    if (address instanceof   
 Inet4Address) {
                        System.out.println("  IP address: " + address.getHostAddress());
                    }
                }
            } else {
                System.out.println("Interface not found: " + interfaceName);
            }
        }

        System.out.println("\nDetails of all non-virtual interfaces:");
        networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            thisInterface = networkInterfaces.nextElement();
            if (!thisInterface.isVirtual() || thisInterface.getName().equals("lo")) {
                System.out.println("  Interface name: " + thisInterface.getName());
                System.out.println("    MTU: " + thisInterface.getMTU());

                for (InterfaceAddress interfaceAddress : thisInterface.getInterfaceAddresses()) {
                    System.out.println("    IP address: " + interfaceAddress.getAddress().getHostAddress());
                    System.out.println("      Subnet mask: " + interfaceAddress.getNetworkPrefixLength() + " bits");
                    if (interfaceAddress.getBroadcast() != null) {
                        System.out.println("      Broadcast address: " + interfaceAddress.getBroadcast().getHostAddress());
                    }
                }
            }
        }
    }
}