import socket
import random
import math
import sympy
import hashlib

p=random.choice(list(sympy.primerange(500,1000)))    
q=random.choice(list(sympy.primerange(500,1000)))
n=p*q 
phi=(p-1)*(q-1)
for e in range(2,phi): 
    if (math.gcd(e,phi)==1): 
        break
for i in range(1,10): 
    x=1+i*phi
    if(x%e==0): 
        d=int(x/e) 
        break   

publicK = (e,n)
privateK = (d,n)

def encrypt(m, publicK):
    e,n = publicK
    m=m.replace(' ','-').lower()
    p=[char for char in m]
    str1="abcdefghijklmnopqrstuvwxyz"
    for i in range(len(p)):
        j=p[i]
        if j in str1:
            ind=str1.index(j)
            ind=pow(ind,e)%n
            p[i]=ind
    return p

client=socket.socket()
HOST = '127.0.0.1'  
PORT = 65432    
client.connect((HOST,PORT))
m=input("Enter Message: ").lower()
hash=hashlib.sha256(m.encode()) 
hash=hash.hexdigest()
privateK = str(privateK)
HM = encrypt(hash,publicK)
HM = str(HM)
privateK = privateK.encode()
HM = HM.encode()
m = m.encode()
client.send(privateK)
client.send(HM)
client.send(m)