
import java.util.*;
import java.net.*;


public class ARPClient {
    private static final int SERVER_PORT = 9876;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            
            System.out.print("Enter IP address or MAC address: ");
            String domainName = scanner.nextLine();
            byte[] sendData = domainName.getBytes();
            
            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
