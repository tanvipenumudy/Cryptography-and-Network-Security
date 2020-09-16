package ClientServerRSA;
import java.net.*;
import java.io.*;
import java.math.BigInteger; 

public class Server {
	public byte[] Encrypt(String p, BigInteger e, BigInteger n){
		byte[] m = p.getBytes();
		BigInteger message = new BigInteger(m);
		BigInteger a = message.modPow(e,n);
        	return a.toByteArray();
	}
	
	public byte[] Decrypt(String c, BigInteger d, BigInteger n){
		byte[] m = c.getBytes();
		BigInteger message = new BigInteger(m);
		BigInteger a = message.modPow(d,n);
		return a.toByteArray();
	}
	public static void main(String args[])throws Exception{
		 ServerSocket ss=new ServerSocket(3333);
		 Socket s=ss.accept();
		 DataInputStream din=new DataInputStream(s.getInputStream());
		 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 Server cipher = new Server();
		 Client c = new Client();
		 BigInteger e = c.getE();
		 BigInteger d = c.getD();
		 BigInteger n = c.getN();

		 String str="",str2="";
		 while(!str.equals("stop")){
		 str=din.readUTF();
		 byte[] decrypted = cipher.Decrypt(str,d,n);
		 System.out.println("client says: "+ new String(decrypted));
		 str2=br.readLine();
		 byte[] encrypted = cipher.Encrypt(str2,e,n);
		 dout.writeUTF(new String(encrypted));
		 dout.flush();
		 }
		 din.close();
		 s.close();
		 ss.close();
		 } 

}
