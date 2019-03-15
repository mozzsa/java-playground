import edu.duke.*;
public class CaesarCipher {

    public String encrypt(String input,int key){
       StringBuilder encrypted = new StringBuilder(input);
       String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
       for (int i =0; i< encrypted.length();i++){
           char newChar='\0';
           char currChar = encrypted.charAt(i);
           int idx = alphabet.indexOf(currChar);
           if(idx != -1){
               if(Character.isLowerCase(currChar)){
                  newChar = shiftedAlphabet.toLowerCase().charAt(idx);}
               else{
                   newChar = shiftedAlphabet.charAt(idx);}
               }
               encrypted.setCharAt(i,newChar);
           }

       return encrypted.toString();
    }

    //This method read a file and encrypt the complete file using the Caesar Cipher Algorithm
    public  void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, 5);
        System.out.println("key is " + 5 + "\n" + encrypted);

    }

    //In this method  every other character encrypted by using two keys
    public String encryptTwoKeys(String input,int key1,int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);
        for (int i =0; i< encrypted.length();i++){
            char newChar=' ';
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                if(Character.isLowerCase(currChar)||i%2==0){
                    newChar = shiftedAlphabet1.toLowerCase().charAt(idx);}
                else if(Character.isLowerCase(currChar)||i%2!=0){
                    newChar = shiftedAlphabet2.toLowerCase().charAt(idx);}

                else if(Character.isUpperCase(currChar)||i%2==0){
                    newChar = shiftedAlphabet1.charAt(idx);}

                else{
                    newChar = shiftedAlphabet1.charAt(idx);}
            }
            encrypted.setCharAt(i,newChar);
        }
       return encrypted.toString();
    }

    //this method returns the index position of the largest element in values
    public int indexOfMax(int[] array){
        int max= 0;
        int index = 0;
        for(int i=0;i<array.length;i++){
            if(array[i]>max){
                max =array[i];
                index = 0;
            }}
        return index; }

    //return the frequency of letter in a String
    public int[] countLetters(String str){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] arr = new int[26];
        for (int i =0;i<str.length();i++){
            int index = alphabet.indexOf(str.toUpperCase().charAt(i));
            if(index!=-1){
                arr[index]+=1;
            }
        }
        return arr;
    }

    public int getKey(String a){
        int[] freq = countLetters(a);
        int maxDex =indexOfMax(freq);
        int dkey =maxDex-4;
        if(maxDex<4) {
            dkey = 26-(4-maxDex);
        }
        return  dkey;
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        // cc.testCaesar();
        String a = cc.encrypt("FIRST LEGION ATTACK EAST FLANK", 23);
        System.out.println(a);
    }
    }
