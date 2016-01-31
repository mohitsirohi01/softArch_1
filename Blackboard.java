import java.net.*;
import java.io.*;
import java.util.regex.*;

public class Blackboard extends Thread
{
   private ServerSocket serverSocket;
   
   public Blackboard(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
   }

   public void run()
   {
      while(true)
      {
         try
         {
            //System.out.println("Waiting for client on port " +serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "+ server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            
            String reqType = in.readUTF();
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
          
            if(reqType.substring(0,3).equals("KS1")){
				out.writeUTF(readfile("mytext1.txt")+ server.getLocalSocketAddress() + "\nGoodbye!"); //path of the file containig text
			}else if(reqType.substring(0,3).equals("KS2")){
				 out.writeUTF(readfile("mytext2.txt")+ server.getLocalSocketAddress() + "\nGoodbye!"); //path of the file containig partial result
			}else{
				System.out.println("Wrong Request");
			}    
            server.close();
         }
         catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public String readfile (String loc_file)
   {
	   BufferedReader br = null;

		try {

			String sCurrentLine,text="";

			br = new BufferedReader(new FileReader(loc_file));

			while ((sCurrentLine = br.readLine()) != null) {
				text+=sCurrentLine;
			}
			return text;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return "";
   }
   
   
   public static void main(String [] args)
   {
      int port1 = Integer.parseInt(args[0]);
      int port2 = Integer.parseInt(args[1]);
      try
      {
         Thread t1 = new Blackboard(port1);
         t1.start();
         Thread t2 = new Blackboard(port2);
         t2.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
