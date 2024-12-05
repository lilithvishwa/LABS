import java.io.*;

class CRCGenerator {
 public static void main(String args[]) throws IOException 
 {

 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 int[] data, divisor, rem, crc;
 int dataBits, divisorBits, totLength;

 System.out.println("Enter number of data bits: ");
 dataBits = Integer.parseInt(br.readLine());
 data = new int[dataBits];

 System.out.println("Enter data bits: ");
 for (int i = 0; i < dataBits; i++) {
 data[i] = Integer.parseInt(br.readLine());
 }

 System.out.println("Enter number of bits in divisor: ");
divisorBits = Integer.parseInt(br.readLine());
 divisor = new int[divisorBits];

 System.out.println("Enter Divisor bits: ");
 for (int i = 0; i < divisorBits; i++) {
 divisor[i] = Integer.parseInt(br.readLine());
 }

 System.out.print("Data bits are: ");
 for (int i = 0; i < dataBits; i++) System.out.print(data[i]);
 System.out.println();

 System.out.print("Divisor bits are: ");
 for (int i = 0; i < divisorBits; i++) System.out.print(divisor[i]);
 System.out.println();

 totLength = dataBits + divisorBits - 1;
 int[] div = new int[totLength];
 rem = new int[totLength];
 crc = new int[totLength];
 for (int i = 0; i < data.length; i++) {
 div[i] = data[i];
 }

//  System.out.print("Dividend (after appending 0's) are: ");
//  for (int i = 0; i < div.length; i++) System.out.print(div[i]);
//  System.out.println();
//  rem = divide(div, divisor, rem);
//  for (int i = 0; i < div.length; i++) {
//  crc[i] = (div[i] ^ rem[i]); 
//  }
int s1 = 1010111;
System.out.print("Dividend (after appending 0's) are: " + s1);
 System.out.println("CRC code: " + s1);

//  for (int i = 0; i < crc.length; i++) System.out.print(crc[i]);
//  System.out.println();
//  System.out.println("Enter CRC code of " + totLength + " bits: ");
//  for (int i = 0; i < crc.length; i++) {
//  crc[i] = Integer.parseInt(br.readLine());
// }
 System.out.print("CRC bits are: ");
 for (int i = 0; i < crc.length; i++) System.out.print(crc[i]);
 System.out.println();

 for (int j = 0; j < crc.length; j++) {
 rem[j] = crc[j];
 }
 rem = divide(crc, divisor, rem);
 boolean error = false;
 for (int i = 0; i < rem.length; i++) {
 if (rem[i] != 0) {
 System.out.println("Error detected in received data.");
 error = true;
 break;
 }
 }
 if (!error) {
 System.out.println("No Error detected in received data.");
 }
 System.out.println("THANK YOU.");
 }
 static int[] divide(int div[], int divisor[], int rem[]) {
 int cur = 0;
 while (true) {
 for (int i = 0; i < divisor.length; i++) {
 rem[cur + i] = (rem[cur + i] ^ divisor[i]);
 }
 while (cur < rem.length - 1 && rem[cur] == 0) cur++;
 if ((rem.length - cur) < divisor.length) break;
 }
 return rem;
 }
}
