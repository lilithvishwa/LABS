import java.net.*;
import java.io.*;

public class chatserver
{
public static void main(String args[])
{
try(ServerSocket serverSocket=new ServerSocket(2000))
{ 
Socket clientSocket=serverSocket.accept();
BufferedReader in=new BufferedReader(new
InputStreamReader(clientSocket.getInputStream()));
PrintStream out=new PrintStream(clientSocket.getOutputStream());
BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
 String s;
while ( true )
{
s=in.readLine();
System. out.print("Client : "+s+"\n"); 
System.out.print("Server : ");
 s=stdin.readLine();
out.println(s);
if (s.equalsIgnoreCase("END"))
{
out.println("BYE");
 break;
}
} serverSocket.close();
 clientSocket.close(); 
 in.close();
  out.close();
stdin.close();
}
catch(IOException e){
  e.printStackTrace();
}
}
}

