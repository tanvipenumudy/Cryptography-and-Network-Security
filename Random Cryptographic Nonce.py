"""
Created on Wed Aug 19 11:12:11 2020

@author: TANVI
"""
import random
list=[]
for i in range(10):
  list.append(random.randint(1,10000))
print("10 Randomly Generated Cryptographic Nonce:")
for i in list:
  print(i)