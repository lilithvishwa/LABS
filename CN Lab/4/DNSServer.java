// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;
// import java.util.HashMap;
// import java.util.Map;
import java.util.*;
import java.net.*;

public class DNSServer 
{
    private static final int PORT = 9876;
    private static Map<String, String> dnsTable = new HashMap<>();

    public static void main(String[] args)
  {
        dnsTable.put("example.com", "93.184.216.34");
        dnsTable.put("google.com", "142.250.190.78");
        dnsTable.put("yahoo.com", "98.137.246.7");

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) 
       {
           
           System.out.println("DNS Server is running...");
           byte[] receiveData = new byte[1024];
           byte[] sendData;
           
            while (true) 
{
                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
                serverSocket.receive(receivePacket);

                String domainName = new String(receivePacket.getData(), 0, 
                                                                       receivePacket.getLength());
                System.out.println("Received request for: " + domainName);

                String ipAddress = dnsTable.getOrDefault(domainName, "Domain not found");
                sendData = ipAddress.getBytes();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent response: " + ipAddress);
            }
        } 
catch (Exception e) 
{
            e.printStackTrace();
        }
    }
}
