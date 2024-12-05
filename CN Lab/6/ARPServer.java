// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;
// import java.util.HashMap;
// import java.util.Map;
import java.util.*;
import java.net.*;

public class ARPServer {
    private static final int PORT = 9876;
    private static Map<String, String> arpTable = new HashMap<>();

    public static void main(String[] args) {
        // Predefined ARP table with IP-MAC mappings
        arpTable.put("192.168.1.1", "00-14-22-01-23-45");
        arpTable.put("192.168.1.2", "00-14-22-01-23-46");
        arpTable.put("192.168.1.3", "00-14-22-01-23-47");

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
           
            System.out.println("ARP/RARP Server is running...");
            byte[] receiveData = new byte[1024];
            byte[] sendData;


            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received request: " + request);

                String response;
                if (request.contains(".")) { // Assume it's an IP, so perform ARP
                    response = arpTable.getOrDefault(request, "MAC address not found");
                } else { // Assume it's a MAC address, so perform RARP
                    response = getIPFromMAC(request);
                }
                sendData = response.getBytes();
             
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getIPFromMAC(String macAddress) {
        for (Map.Entry<String, String> entry : arpTable.entrySet()) {
            if (entry.getValue().equals(macAddress)) {
                return entry.getKey();
            }
        }
        return "IP address not found";
    }
}