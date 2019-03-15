import edu.duke.*;
public class TestCaesarCipherTwo {



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

    public void simpleTests(){
        FileResource sr = new FileResource();
        String str = sr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encryptTwoKeys(str);
        System.out.println(encrypted);
        String firstenc = cc.halfOfString(encrypted,0);
        String secondenc = cc.halfOfString(encrypted,1);
        int key1 = breakCaesarCipher(firstenc);
        int key2 = breakCaesarCipher(secondenc);
        CaesarCipherTwo de = new CaesarCipherTwo(key1,key2);
        System.out.println(de.decryptTwoKeys(encrypted));
    }


    public int  breakCaesarCipher(String a){
        int[] freq = countLetters(a);
        int maxDex =indexOfMax(freq);
        int dkey =maxDex-4;
        if(maxDex<4) {
            dkey = 26-(4-maxDex);
        }
        return  dkey;
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo t = new TestCaesarCipherTwo();
        t.simpleTests();
    }


}
