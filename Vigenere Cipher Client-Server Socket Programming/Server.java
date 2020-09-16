package ClientServerCNS;
import java.net.*;
import java.io.*; 
import java.util.ArrayList;

public class Server {
	public String Encrypt(String p, String k){
		p=p.replace(" ", "-").toLowerCase();
		k=k.toLowerCase();
		char[] P=new char[p.length()];
		for(int i=0; i<p.length(); i++){
			P[i]=p.charAt(i);}
	    char[] K1=new char[k.length()];
	    for(int i=0; i<k.length(); i++){
	    	K1[i]=k.charAt(i);}
	    int c=0;
	    ArrayList<Character> K = new ArrayList<Character>();
	    for(int i=0; i<P.length; i++){
	    	if(P[i]!='-'){
	    		K.add(i,K1[c%k.length()]);
	    		c++;
	    	}
	    	else {
	    		K.add('-');
	    	}	
	    }
	    String str="abcdefghijklmnopqrstuvwxyz";
	    for(int n=0; n<p.length();n++){
	    	char i=P[n];
	    	char j=K.get(n);
	    	int a=str.indexOf(i);
	    	int b=str.indexOf(j);
	    	if(a!=-1 && b!=-1){
	    		int ind=a;
	    		int ind1=b;
	    		ind=ind+ind1;
	    		ind=ind%26;
	    		P[n]=str.charAt(ind);
	    	}
	    }
	    String C = new String(P);
	    C=C.replace("-", " ");
	    return C;
	}
	public String Decrypt(String c, String k){
		c=c.replace(" ", "-").toLowerCase();
		k=k.toLowerCase();
		char[] C=new char[c.length()];
		for(int i=0; i<c.length(); i++){
			C[i]=c.charAt(i);}
	    char[] K1=new char[k.length()];
	    for(int i=0; i<k.length(); i++){
	    	K1[i]=k.charAt(i);}
	    int count=0;
	    ArrayList<Character> K = new ArrayList<Character>();
	    for(int i=0; i<C.length; i++){
	    	if(C[i]!='-'){
	    		K.add(i,K1[count%k.length()]);
	    		count++;
	    	}
	    	else {
	    		K.add('-');
	    	}	
	    }
	    String str="abcdefghijklmnopqrstuvwxyz";
	    for(int n=0; n<c.length();n++){
	    	char i=C[n];
	    	char j=K.get(n);
	    	int a=str.indexOf(i);
	    	int b=str.indexOf(j);
	    	if(a!=-1 && b!=-1){
	    		int ind=a;
	    		int ind1=b;
	    		ind=(ind-ind1)+26;
	    		ind=ind%26;
	    		C[n]=str.charAt(ind);
	    	}
	    }
	    String P = new String(C);
	    P=P.replace("-", " ");
		return P;
	}
	public static void main(String args[])throws Exception{
		 Server cipher = new Server();
		 ServerSocket ss=new ServerSocket(3333);
		 Socket s=ss.accept();
		 DataInputStream din=new DataInputStream(s.getInputStream());
		 DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		 String str="",str2="";
		 while(!str.equals("stop")){
		 str=din.readUTF();
		 System.out.println("Client says: "+cipher.Decrypt(str,"tanvi"));
		 str2=br.readLine();
		 dout.writeUTF(cipher.Encrypt(str2,"tanvi"));
		 dout.flush();
		 }
		 din.close();
		 s.close();
		 ss.close();
		 }
	} 