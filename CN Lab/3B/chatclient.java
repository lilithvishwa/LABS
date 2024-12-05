import java.net.*;
import java.io.*;

public class chatclient
{
public static void main(String args[]) 
{
try(Socket socket=new Socket("127.0.0.1",2000))
{
 BufferedReader in=new BufferedReader(new
InputStreamReader(socket.getInputStream()));
PrintStream out=new PrintStream(socket.getOutputStream());
BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
 String s;
while ( true )
{
System.out.print("Client : ");
s=stdin.readLine();
out.println(s); 
 s = in.readLine();
  System.out.print("Server : "+s+"\n"); 
  if ( s.equalsIgnoreCase("BYE") )
break;

}


socket.close();
in.close();
out.close();
stdin.close();

}catch(IOException e){
  e.printStackTrace();
}

}
}