public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainkey1;
    private int mainkey2;

    CaesarCipherTwo(int key1,int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        mainkey1= key1;
        mainkey2= key2;
    }


    //In this method  every other character encrypted by using two keys
    public String encryptTwoKeys(String input){
        String firststring = halfOfString(input,0);
        String secondstring = halfOfString(input,1);
        CeasarCipher first=new CeasarCipher(mainkey1);
        CeasarCipher second=new CeasarCipher(mainkey2);
        String encrypted = concatHalfOfString(first.encrypt(firststring),second.encrypt(secondstring));
        return  encrypted;
    }

    public String decryptTwoKeys(String encrypted){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainkey1,26-mainkey2);
        String decrypted = cc.encryptTwoKeys(encrypted);
        return decrypted;
    }

    public String halfOfString(String word,int start){
        StringBuilder str = new StringBuilder();

        for (int i=start;i<word.length();i+=2){
            str.append(word.charAt(i));
        }
        return  str.toString(); }

    public  String concatHalfOfString(String firsthalf,String secondhalf){
        StringBuilder str =new StringBuilder();
        str.append(firsthalf.charAt(0));
        int ind1 =0;
        int ind2 = 0;
        for (int i=0;i<firsthalf.length()+secondhalf.length();i++){
            if(i%2==0){
                str.append(firsthalf.charAt(ind1));
                ind1++;
            }
            else{
                str.append(secondhalf.charAt(ind2));
                ind2++;
            }
        }
        return str.toString();
    }




}
