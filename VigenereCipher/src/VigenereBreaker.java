import java.io.File;
import java.util.*;
import edu.duke.*;



public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        char chlist[] = message.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = whichSlice; i <chlist.length ; i+=totalSlices) {
            builder.append(chlist[i]);
        }
        return  builder.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i <key.length ; i++) {
            CaesarCracker cc = new CaesarCracker(mostCommon);
            String sliced = sliceString(encrypted,i,klength);
            key[i]=cc.getKey(sliced);
        }
        return key;
    }

    public void breakVigenere() {
        FileResource f = new FileResource();
        String encrypted = f.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String,HashSet<String>> map = new HashMap<String,HashSet<String>>();
        for(File file :dr.selectedFiles()){
            FileResource dicfile = new FileResource(file);
            HashSet<String> dict = readDictionary(dicfile);
            map.put(file.getName(),dict);
        }
        breakForAllLanguage(encrypted,map);

    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<>();
        for (String line : fr.lines()) {
            dict.add(line);
        }
        return dict;
    }

    //to see how many of them is the real words
    public int countWords(String message,HashSet<String> set){
        int count = 0 ;
        String[] array = message.split("//W+");
        for (int i = 0; i <array.length ; i++) {
            if(set.contains(array[i])){
               count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted,HashSet<String> dictionary,char mostCommon){
        int max=0 ;
        String bdecrypted = "";
        for (int i = 0; i <=100 ; i++) {
            int key[] = tryKeyLength( encrypted, i,mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted,dictionary);
            if(count>max){
                max = count;
                bdecrypted= decrypted;
            }
        }
        return bdecrypted;
    }

    public void breakForAllLanguage(String encrypted,HashMap<String,HashSet<String>> languages ){
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            char mostcommon = mostCommonCharIn(dictionary);
            String decrypted = breakForLanguage(encrypted,dictionary,mostcommon);
            System.out.println(decrypted+"  "+ lang);
        }
    }



    public char mostCommonCharIn(HashSet<String> dictionary){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(String dic : dictionary){
            for(int k=0; k < dic.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(dic.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            } }

        int maxDex = 0;
        for(int k=0; k < counts.length; k++){
            if (counts[k] > counts[maxDex]){
                maxDex = k;
            }
        }
        return alph.charAt(maxDex);
    }


    }

