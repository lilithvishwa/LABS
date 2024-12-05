public class NetworkSimulation {
 public static void main(String[] args) {

 int bandwidth = 100; // in Mbps
 int delay = 10; // in ms
 double packetLossRate = 0.02; // 2% packet loss rate
 // Simulate TCP
 simulateTCP(bandwidth, delay, packetLossRate);
 // Simulate UDP
 simulateUDP(bandwidth, delay, packetLossRate);
 }
 private static void simulateTCP(int bandwidth, int delay, double packetLossRate) {
 // TCP parameters
int dataSize = 1000; // in MB, data to be sent
 int mtu = 1; // in MB, Maximum Transmission Unit (size of each packet)
 
 // Simulation of TCP
 double timeTaken = 0; // in seconds
 double throughput = 0; // in Mbps
 int packetsSent = dataSize / mtu;
 int packetsLost = 0;
 // Simulate transmission with packet loss and retransmission
 for (int i = 0; i < packetsSent; i++) {
 if (Math.random() < packetLossRate) {
 // Packet loss, retransmit
 packetsLost++;
 i--; // Retransmit the same packet
 }
 timeTaken += (mtu * 8.0) / (bandwidth * 1000.0) + (delay / 1000.0); // Transmission + Delay
 }
 throughput = (dataSize * 8.0) / timeTaken; // Throughput in Mbps
 // Print TCP performance metrics
 System.out.println("TCP Performance Metrics:");
 System.out.println("Data Size: " + dataSize + " MB");
 System.out.println("Packets Sent: " + packetsSent);
 System.out.println("Packets Lost: " + packetsLost);
 System.out.println("Time Taken: " + timeTaken + " seconds");
 System.out.println("Throughput: " + throughput + " Mbps");
 System.out.println("Packet Loss Rate: " + packetLossRate * 100 + "%");
 }
 private static void simulateUDP(int bandwidth, int delay, double packetLossRate) {
 // UDP parameters
 int dataSize = 1000; // in MB, data to be sent
int mtu = 1; // in MB, Maximum Transmission Unit (size of each packet)
 
 // Simulation of UDP
 double timeTaken = 0; // in seconds
 double throughput = 0; // in Mbps
 int packetsSent = dataSize / mtu;
 int packetsLost = 0;
 // Simulate transmission with packet loss (no retransmission)
 for (int i = 0; i < packetsSent; i++) {
 if (Math.random() < packetLossRate) {
 // Packet loss, no retransmission in UDP
 packetsLost++;
 }
 timeTaken += (mtu * 8.0) / (bandwidth * 1000.0) + (delay / 1000.0); // Transmission + Delay
 }
 throughput = (dataSize * 8.0) / timeTaken; // Throughput in Mbps
 // Print UDP performance metrics
 System.out.println("UDP Performance Metrics:");
 System.out.println("Data Size: " + dataSize + " MB");
 System.out.println("Packets Sent: " + packetsSent);
 System.out.println("Packets Lost: " + packetsLost);
 System.out.println("Time Taken: " + timeTaken + " seconds");
 System.out.println("Throughput: " + throughput + " Mbps");
 System.out.println("Packet Loss Rate: " + packetLossRate * 100 + "%");
 }
}