def AffineEncrypt(a,b,p):
    p=p.replace(' ','-').lower()
    P=[char for char in p]
    string='abcdefghijklmnopqrstuvwxyz'
    for n in range(len(P)):
        i=P[n]
        if i in string:
            ind=string.index(i)
            ind=(a*ind)+b
            ind=ind%26
            P[n]=string[ind]
    P=''.join(P)
    P=P.replace("-"," ")
    return P

def AffineDecrypt(a,b,c):
    a=a%26
    for i in range(1,26):
        if((a*i)%26==1):
            a1=i
            break
    c=c.replace(' ','-').lower()
    C=[char for char in c]
    string='abcdefghijklmnopqrstuvwxyz'
    for n in range(len(C)):
        i=C[n]
        if i in string:
            ind=string.index(i)
            ind=a1*(ind-b)
            ind=ind%26
            C[n]=string[ind]
    C=''.join(C)
    C=C.replace("-"," ")
    return C

#print(AffineEncrypt(5,7,'bu'))
#print(AffineDecrypt(5,7,'md'))

i=0
while(i!=3):
    i=int(input("\nChoose the following options\n1: Encrypt the message\n2: Decrypt the message\n3: Exit "))
    if(i==1):
        print("Encrypted Message is:", AffineEncrypt(int(input("Enter a: ")),int(input("Enter b: ")),input("Enter Plain Text: ")))
    elif(i==2):
        print("Decrypted Message is:", AffineDecrypt(int(input("Enter a: ")),int(input("Enter b: ")),input("Enter Cipher Text: ")))