import socket
import random
import math
import sympy
import hashlib

def decrypt(p, privateK):
    d,n = privateK
    str1="abcdefghijklmnopqrstuvwxyz"
    for i in range(len(p)):
        if(isinstance(p[i],int)):
            p[i]=pow(p[i],d)%n
            p[i]=str1[p[i]]
    p=''.join(p)
    p=p.replace("-"," ")        
    return p

server=socket.socket() 
HOST = '127.0.0.1' 
PORT = 65432        
server.bind((HOST,PORT))
privateK = eval(client.recv(1024).decode('utf-8'))
HM = client.recv(1024).decode('utf-8')
HM = eval(HM)
m = client.recv(1024).decode('utf-8')
D = decrypt(m,privateK)
print("Decrypted Hash: ",D)
print("Authentication is Ensured")
hash=hashlib.sha256(m.encode()) 
hash=hash.hexdigest()
print("Message Hashed: ",hash)
if(hash==D):
  print("Message Integrity is Ensured")
else:
  print("Message has been changed")