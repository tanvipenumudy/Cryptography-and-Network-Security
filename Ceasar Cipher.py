"""
Created on Wed Aug 19 11:05:55 2020
@author: TANVI
"""
def encrypt(n,k):
    n=n.replace(' ','-').lower()
    p=[char for char in n]
    string="abcdefghijklmnopqrstuvwxyz"
    for n in range(len(p)):
        i=p[n]
        if i in string:
            ind=string.index(i)
            ind=ind+k
            ind=ind%26  
            p[n]=string[ind]
    p=''.join(p)
    p=p.replace("-"," ")
    return p

def decrypt(p,k):
    p=p.replace(' ', '-').lower()
    n=[char for char in p]
    string="abcdefghijklmnopqrstuvwxyz"
    for i in range(len(n)):
        j=n[i]
        if j in string:
            ind=string.index(j)
            ind=ind-k
            ind=ind%26  
            n[i]=string[ind]
    n=''.join(n)
    n=n.replace("-"," ")
    return n

i=0
while(i!=3):
    i=int(input("Choose the following options\n1: Encrypt the message\n2: Decrypt the message\n3: Exit "))
    if(i==1):
        print("Encrypted Message is:", encrypt(input("Enter the Message: "),int(input("Enter Key: "))))
    elif(i==2):
        print("Decrypted Message is:", decrypt(input("Enter the Message: "),int(input("Enter Key: "))))
