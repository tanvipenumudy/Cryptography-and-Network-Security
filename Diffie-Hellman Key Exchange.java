package CNSLab6;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class CNSLab6
{
    BigInteger a,q,Hs,Fs,Hp,Fp,KfhHa,KfhFa,temp,A;
    int bitlength = 12;
    Random r;
 
    public CNSLab6()
    {
        r = new Random();
        a = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        int compareValue = a.compareTo(q); 
        if(compareValue == 1){
        	temp = a;
        	a = q;
        	q = temp;
        }
        BigInteger upperLimit = q;
        do{
            Hs = new BigInteger(upperLimit.bitLength(), r); 
        }
        while(Hs.compareTo(upperLimit) >= 0);
        do{
            Fs = new BigInteger(upperLimit.bitLength(), r); 
        }
        while(Fs.compareTo(upperLimit) >= 0);
        while(Fs.compareTo(Hs)==0){
        	do {
                Fs = new BigInteger(upperLimit.bitLength(), r); 
            }while(Fs.compareTo(upperLimit) >= 0);
        }
        Hp = a.modPow(Hs,q);
        Fp = a.modPow(Fs,q);
        KfhHa = Hp.modPow(Fs,q);
        KfhFa = Fp.modPow(Hs,q);
        A = KfhHa.mod(new BigInteger("128"));
    }
    
    String byteToStr(byte[] text)
    {
        String str = "";
        for (byte b : text) {
            str += Byte.toString(b);
        }
        return str;
    }

    @SuppressWarnings("deprecation")
	byte[] encrypt(byte[] message){
    	int[] arr = new int[message.length];
    	for(int i = 0; i<message.length; i++){
    		arr[i]= message[i];
    	}
    	for(int i = 0; i<message.length; i++){
    		arr[i]=BigInteger.valueOf(arr[i]).add(A).intValue();
    	}
    	for(int i = 0; i<message.length; i++) {
    		message[i] = new Integer(arr[i]).byteValue();
    	}
        return message;
    }

    @SuppressWarnings("deprecation")
	byte[] decrypt(byte[] message){
    	int[] arr = new int[message.length];
    	for(int i = 0; i<message.length; i++){
    		arr[i]= message[i];
    	}
    	for(int i = 0; i<message.length; i++){
    		arr[i]=BigInteger.valueOf(arr[i]).subtract(A).intValue();
    	}
    	for(int i = 0; i<message.length; i++) {
    		message[i] = new Integer(arr[i]).byteValue();
    	}
        return message;
    }
 
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException
    {
    	CNSLab6 DHKE = new CNSLab6();
    	System.out.println("a: "+DHKE.a);
    	System.out.println("q: "+DHKE.q);
    	System.out.println("Private Key Hs: " + DHKE.Hs);
    	System.out.println("Private Key Fs: " + DHKE.Fs);
    	System.out.println("Public Key Hp: " + DHKE.Hp);
    	System.out.println("Public Key Fp: " + DHKE.Fp);
    	System.out.println("Shared Session Key Kfh: " + DHKE.KfhFa);
        DataInputStream in = new DataInputStream(System.in);
        Scanner input = new Scanner(System.in);
        int c = 0;
        while(c!=3){
        	System.out.println("Choose the following options\n1: Encrypt the message\n2: Decrypt the message\n3: Exit ");
        	c = input.nextInt();
        	if(c==1){
        		String str;
                System.out.println("Enter Plain Text: ");
                str = in.readLine();
                System.out.println("Plain Text: " + str);
                System.out.println("Plain Byte Array: "+Arrays.toString(str.getBytes()));
                //System.out.println("Plain Text in Bytes: " + DHKE.byteToStr(str.getBytes()));
                byte[] encrypted = DHKE.encrypt(str.getBytes());
                //System.out.println("Cipher Text in Bytes: " + DHKE.byteToStr(encrypted));
                System.out.println("Cipher Byte Array: "+Arrays.toString(encrypted));
                System.out.println("Cipher Text: " + new String(encrypted));
        	}
        	else if(c==2){
        		String str;
                System.out.println("Enter Cipher Text: ");
                str = in.readLine();
                System.out.println("Cipher Text: " + str);
                System.out.println("Cipher Byte Array: "+Arrays.toString(str.getBytes()));
                //System.out.println("Cipher Text in Bytes: " + DHKE.byteToStr(str.getBytes()));
                byte[] decrypted = DHKE.decrypt(str.getBytes());
                //System.out.println("Plain Text in Bytes " + DHKE.byteToStr(decrypted));
                System.out.println("Plain Byte Array: "+Arrays.toString(decrypted));
                System.out.println("Plain Text: " + new String(decrypted));
        	}
        }
        
        input.close();
    }
 
    
}