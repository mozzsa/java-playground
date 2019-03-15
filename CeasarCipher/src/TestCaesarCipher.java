import edu.duke.*;
public class TestCaesarCipher {

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

    public void simpleTests(){
        FileResource sr = new FileResource();
        String str = sr.asString();
        CeasarCipher cc = new CeasarCipher(18);
        String encrypted = cc.encrypt(str);
        System.out.println(cc.decrypt(encrypted));
        int key = breakCaesarCipher(encrypted);
        CeasarCipher br = new CeasarCipher(key);
        String decrypted = br.decrypt(encrypted);
        System.out.println(decrypted);
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
        TestCaesarCipher t = new TestCaesarCipher();
        t.simpleTests();
    }



}
