import numpy as np
import os 
from sympy import Matrix

alphabet = "abcdefghijklmnopqrstuvwxyz"

#Change the name of the file below to test other files
with open('ChandramoulihillCustom.txt', 'r') as file:
    data = file.read().replace(' ', '')

key = np.array([[9, 4],[5, 7]])

def encrypt(contents):
    #encrypted = np.array()
    message = []
    for x in range (0,len(contents)-1,2): 
        temp1 = contents[x]
        temp2 = contents[x+1]
        number1=-1
        number2=-1

        for i in range (0,len(alphabet)):
            if (temp1==alphabet[i]):
                number1 = i
        
        for j in range (0,len(alphabet)):
            if (temp2==alphabet[j]):
                number2 = j

        toEncrypt = np.array([number1,number2])
        todo = np.dot(key,toEncrypt) % 26
        message.append(todo)
        
    message = np.array(message)
    message = message.flatten()

    eMessage = []
    for i in range (0,len(message)):
        a = message[i]
        eMessage.append(alphabet[a])
    return eMessage

def toString(message):
    str1 = ""  
    
    for ele in message:  
        str1 += ele   
    
    return str1  

def decrypt(contents):
    message = []
    for x in range (0,len(contents),2): 
        temp1 = contents[x]
        temp2 = contents[x+1]

        for i in range (0,len(alphabet)):
            if (temp1==alphabet[i]):
                number1 = i
        
        for j in range (0,len(alphabet)):
            if (temp2==alphabet[j]):
                number2 = j

        toEncrypt = np.array([number1,number2])
        invKey=Matrix(key).inv_mod(26)
        invKey = np.array(invKey)
        todo = (np.dot(invKey,toEncrypt) % 26+26)%26
        message.append(todo)
        
    message = np.array(message)
    message = message.flatten()

    eMessage = []
    for i in range (0,len(message)):
        a = message[i]
        eMessage.append(alphabet[a])
    return eMessage

inList = encrypt(data)
inStr = toString(inList)
#Change file name for input here 
f = open("ChandramoulihillEncrypt.txt","w")
f.write(inStr)

nList=decrypt(inStr)
nStr=toString(nList)
#Change file name for decrypt here
f = open("ChandramoulihillDecrypt.txt","w")
f.write(nStr)