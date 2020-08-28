import numpy as np
def PlayFairEncrypt(k,p):
    k=k.lower()
    p.lower()
    p=p.replace('j','i')
    if((len(p)%2)!=0):
        p=p+'z'
    P=[]
    for i in range(1,len(p),2):
        a=p[i-1]+p[i]
        P.append(a)      
    string='abcdefghiklmnopqrstruvwxyz'
    K1=''
    for i in string:
        if i not in k:
            K1=K1+i
    m=k+K1
    M=[char for char in m]
    n=np.array(M)
    n=n.reshape(5,5)
    for i in range(len(P)):
        a1=np.argwhere(n==P[i][0])
        a2=np.argwhere(n==P[i][1])
        a=a1-a2
        if(a[0][1]==0):
            a1=a1+[[1,0]]
            a2=a2+[[1,0]]
            a1=a1%5
            a2=a2%5
            a1,a2=a1.flatten(),a2.flatten()
            P[i]=n[a1[0],a1[1]]+n[a2[0],a2[1]]
        elif(a[0][0]==0):
            a1=a1+[[0,1]]
            a2=a2+[[0,1]]
            a1=a1%5
            a2=a2%5
            a1,a2=a1.flatten(),a2.flatten()
            P[i]=n[a1[0],a1[1]]+n[a2[0],a2[1]]
        else:
            a1,a2=a1.flatten(),a2.flatten()
            P[i]=n[a1[0],a2[1]]+n[a2[0],a1[1]]
    P=''.join(P)
    return P

def PlayFairDecrypt(k,c):
    k=k.lower()
    c.lower()
    P=[]
    for i in range(1,len(c),2):
        a=c[i-1]+c[i]
        P.append(a)      
    string='abcdefghiklmnopqrstruvwxyz'
    K1=''
    for i in string:
        if i not in k:
            K1=K1+i
    m=k+K1
    M=[char for char in m]
    n=np.array(M)
    n=n.reshape(5,5)
    for i in range(len(P)):
        a1=np.argwhere(n==P[i][0])
        a2=np.argwhere(n==P[i][1])
        a=a1-a2
        if(a[0][1]==0):
            a1=a1-[[1,0]]
            a2=a2-[[1,0]]
            a1=a1%5
            a2=a2%5
            a1,a2=a1.flatten(),a2.flatten()
            P[i]=n[a1[0],a1[1]]+n[a2[0],a2[1]]
        elif(a[0][0]==0):
            a1=a1-[[0,1]]
            a2=a2-[[0,1]]
            a1=a1%5
            a2=a2%5
            a1,a2=a1.flatten(),a2.flatten()
            P[i]=n[a1[0],a1[1]]+n[a2[0],a2[1]]
        else:
            a1,a2=a1.flatten(),a2.flatten()
            P[i]=n[a1[0],a2[1]]+n[a2[0],a1[1]]
    P=''.join(P)
    return P

#print(PlayFairEncrypt('monarchy','instruments'))
#print(PlayFairDecrypt('monarchy','gatlmzclrqtx'))

i=0
while(i!=3):
    i=int(input("\nChoose the following options\n1: Encrypt the message\n2: Decrypt the message\n3: Exit "))
    if(i==1):
        print("Encrypted Message is:", PlayFairEncrypt(input("Enter Key: "),input("Enter Plain Text: ")))
    elif(i==2):
        print("Decrypted Message is:", PlayFairDecrypt(input("Enter Key: "),input("Enter Cipher Text: ")))