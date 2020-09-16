package ClientServerRSA;
import java.net.*;
import java.io.*; 
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Client {
	BigInteger p,q,e,d,n,phi;
	Random r;
        int bitlength = 1024;
    
	public Client(BigInteger e, BigInteger d, BigInteger n) {
	this.e = e;
        this.d = d;
        this.n = n;
	}
	
	public Client(){
		p = BigInteger.probablePrime(bitlength, r);
		q = BigInteger.probablePrime(bitlength, r);
		n = p.multiply(q);
		BigInteger p_1 = p.subtract(BigInteger.ONE);
		BigInteger q_1 = q.subtract(BigInteger.ONE);
		phi = p_1.multiply(q_1);
		e = BigInteger.probablePrime(bitlength/2, r);
		while(phi.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(phi)<0){
			e.add(BigInteger.ONE);
		} 
		d = e.modInverse(phi);
	}
	BigInteger getE(){ 
	       return e; 
	    } 
	
	BigInteger getD(){ 
	       return d; 
	    } 

	BigInteger getN(){
			return n;
	}
	Random getR(){
			return r;
	}
	public byte[] Encrypt(String p) {
		byte[] m = p.getBytes();
		BigInteger message = new BigInteger(m);
		BigInteger a = message.modPow(e,n);
        	return a.toByteArray();
	}
	
	public byte[] Decrypt(String c) {
		byte[] m = c.getBytes();
		BigInteger message = new BigInteger(m);
		BigInteger a = message.modPow(d,n);
		return a.toByteArray();
	}
	public static void main(String args[])throws Exception{ 
		Socket s=new Socket("localhost",3333);
		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Choose:\n1 - Generate Random Values\n2 - Enter Own Values ");
		String choice = br.readLine();
		if(choice.equals("2")){
			System.out.print("Enter P: ");
			Scanner in = new Scanner(System.in);
			BigInteger p = in.nextBigInteger();
			System.out.print("Enter Q: ");
			BigInteger q = in.nextBigInteger();
			in.close();
			int bitlength = 1024;
			BigInteger n = p.multiply(q);
			BigInteger p_1 = p.subtract(BigInteger.ONE);
			BigInteger q_1 = q.subtract(BigInteger.ONE);
			BigInteger phi = p_1.multiply(q_1);
			Client c = new Client();
			BigInteger e = BigInteger.probablePrime(bitlength/2, c.getR());
			while(phi.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(phi)<0){
				e.add(BigInteger.ONE);
			} 
			BigInteger d = e.modInverse(phi);
			
			Client cipher = new Client(e,d,n);
			String str="",str2="";
			while(!str.equals("stop")){
			str=br.readLine();
			byte[] encrypted = cipher.Encrypt(str);
			dout.writeUTF(new String(encrypted));
			dout.flush();
			str2=din.readUTF();
			byte[] decrypted = cipher.Decrypt(str2);
			System.out.println("Server says: "+ new String(decrypted));
			}
			dout.close();
			s.close();
		}
		else{
			Client cipher = new Client();
			String str="",str2="";
			while(!str.equals("stop")){
			str=br.readLine();
			byte[] encrypted = cipher.Encrypt(str);
			dout.writeUTF(new String(encrypted));
			dout.flush();
			str2=din.readUTF();
			byte[] decrypted = cipher.Decrypt(str2);
			System.out.println("Server says: "+ new String(decrypted));
			}
			dout.close();
			s.close();
		}}
}