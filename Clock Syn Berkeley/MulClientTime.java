//Berkely Algorithm Client
import java.net.*;
import java.util.*;

class  MulClientTime
{   public static int serverPort=998;
     public static int clientPort;
	  public static int buffer_size=1024;
	   public  static DatagramSocket ds;
      public static byte buffer[]=new byte[buffer_size];
	  
	
	public static void theclient() throws Exception
	{

	  Date d=new Date();
	  long l=d.getTime();
	  String str=Long.toString(l);
	  System.out.println("Current client TIME:"+str+"-"+d.toString());
	  buffer=str.getBytes();//convert string to bytes
	 ds.send(new DatagramPacket(buffer,buffer.length,InetAddress.getByName("Mayuresh"),serverPort));
  System.out.println("Time Sent to server");
	 //recieve new time from server program
	 DatagramPacket p=new DatagramPacket(buffer,buffer.length);
	 ds.receive(p);
	 String newtimest=new String(p.getData(),0,p.getLength());
	 System.out.println("New time recieved:"+newtimest);
	 long newtime=Long.parseLong(newtimest);

	 //set clent machine to new value
	 d.setTime(newtime);
     System.out.println("Client time set to new value-"+d.toString());
	 } 
	  
	public static void main(String[] args) throws Exception
	{
		if(args.length==1)
		{ try
			{
			clientPort=Integer.parseInt(args[0]);
			}
		  catch(Exception e){System.out.println("exception Occured in main:"+e);}
	      ds=new DatagramSocket(clientPort);
		  theclient();
		}
		else
           System.out.println("Check command line arguments it should be Port no. of client");
		
	}
}



