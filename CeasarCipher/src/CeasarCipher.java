public class CeasarCipher {
    private String alphabet ;
    private String shiftedAlphabet;
    private int mainkey;
    CeasarCipher(int key){
       alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
       mainkey = key ;
    }

    public String encrypt(String input){

        StringBuilder encrypted = new StringBuilder(input);
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

    //decrypt the word encrypted with one key
    public String decrypt(String encrypted){
        CeasarCipher cc = new CeasarCipher(26-mainkey);
        return cc.encrypt(encrypted);
    }
}
