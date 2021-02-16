import java.util.Arrays;
import java.io.File;
import java.util.*;
import java.io.PrintWriter;  
import java.io.*;

class CaesarCipher{

    public static final char alphabet [] = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public static void main(String[] args)throws IOException {
        //System.out.println("Hello World");
        Scanner scanner = new Scanner(System.in);
        File f1 = new File("ChandramoulicesarInput2.txt");

     
        File fKey = new File ("ChandramoulicaesarKey.txt");
        Scanner reader = new Scanner (fKey);
        int key = reader.nextInt();
        System.out.println(key);

        //Process of cypher

        //1) put contents of file into array
        String[] fileContents = fileArray(f1);

        String[] encrypted = encrypt(fileContents, key);

        writeFile("ChandramouliEncrypted2.txt", encrypted);

        //Decryption
        File f2 = new File("ChandramouliEncrypted2.txt");
        String[] decrypt = fileArray(f2);

        String[] decrypted = decrypt(decrypt,key);

        writeFile("ChandramouliDecrypted2.txt",decrypted);
        
    }

    //printing out a file 
    public static void printFile(File f) {
        if (f != null && f.exists()) {
            try (Scanner reader = new Scanner(f)) {
                while (reader.hasNextLine()) {
                    System.out.println(reader.nextLine());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Writing to a file 
    public static void writeFile(String file, String[] output) throws IOException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        for (int i = 0; i < output.length; i++) {
            if (i == output.length - 1) {
                writer.print(output[i]);
            }
            else {
                writer.print(output[i] + " ");
            }
        }
        writer.close();
    }

    //taking file contents to array
    public static String[] fileArray(File f) throws IOException{
        String contents = "";
        Scanner reader = new Scanner(f);

        while (reader.hasNextLine()) {
            contents += reader.nextLine();
        }

        //contents = contents.replaceAll("\\s+", "");
        //contents = contents.replaceAll("[.?!]","");

        String[] fromFile = contents.split(" ");

        for (int i = 0; i < fromFile.length; i++) {
            fromFile[i] = fromFile[i].toUpperCase();
        }

        return fromFile;
    }

    //ceasar cypher encryption
    public static String[] encrypt(String[] fileContents, int key) {
        String[] encrypted = new String[fileContents.length];

        for (int i = 0; i < fileContents.length; i++) {

            String s = fileContents[i];
            String replacement = "";
            
            for (int j = 0; j < s.length(); j++) {
                char c = encodeChar(s.charAt(j), key);
                replacement += c;
            }

            encrypted[i] = replacement;
        }

        return encrypted;
    }

    public static char encodeChar(char c, int keyIn) {
        int index = -1;
        for (int i=0;i<alphabet.length;i++){
            if (alphabet[i]==c){
                index = i;
            }
        }

        if (index <= -1) {
            throw new RuntimeException("Invalid character! '" + c + "'");
        }

        index += keyIn;
        index = (index % 26 + 26) % 26;

        return alphabet[index];
    }

        //ceasar cypher encryption
    public static String[] decrypt(String[] fileContents, int key) {
        String[] decrypted = new String[fileContents.length];

        for (int i = 0; i < fileContents.length; i++) {

            String s = fileContents[i];
            String replacement = "";
            
            for (int j = 0; j < s.length(); j++) {
                char c = decodeChar(s.charAt(j), key);
                replacement += c;
            }

            decrypted[i] = replacement;
        }

        return decrypted;
    }

    public static char decodeChar(char c, int keyIn) {
        int index = -1;
        for (int i=0;i<alphabet.length;i++){
            if (alphabet[i]==c){
                index = i;
            }
        }

        if (index <= -1) {
            throw new RuntimeException("Invalid character! '" + c + "'");
        }

        index -= keyIn;
        index = (index % 26 + 26) % 26;
        return alphabet[index];
    }

}