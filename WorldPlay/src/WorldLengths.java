import edu.duke.FileResource;
import java.util.ArrayList;

public class WorldLengths {

    //this method counts the number of words of each length for all the words in resource,storing these counts in the array counts #the comma,double quotes not counted but hyphen is
   public void countWorldLengths(FileResource resource, int[] array){
       for(String word :resource.words()){
           int count = 0;
          for (int i = 0; i<word.length();i++){
              if(Character.isLetter(word.charAt(i))){
                 count++;
              }else{
                  if(word.charAt(i)=='-'){ count++;}
              }}
          array[count] +=1;
       }}


   public void  testCountWordLengths(){
       CaesarCipher cs = new CaesarCipher();
       FileResource resource = new FileResource();
       int[] array = new int[31];
       countWorldLengths(resource,array);
       for (int i=0;i<array.length;i++){
           System.out.println("length : "+i+"count : "+array[i]);
       }
       System.out.println("max value of lengths: "+array[cs.indexOfMax(array)]);
   }

   //decrypt the word encrypted with one key
   public String decrypt(String encrypted){
       CaesarCipher cc = new CaesarCipher();
       int dkey = cc.getKey(encrypted);
       return cc.encrypt(encrypted,26-dkey);
   }

    //decrypt the word encrypted with two key
    public String decrypttwo(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String firsthalf = halfOfString(encrypted,0);
        String secondhalf = halfOfString(encrypted,1);
        int dkey1 = cc.getKey(firsthalf);
        int dkey2 = cc.getKey(secondhalf);
        String firsthalfd = cc.encrypt(firsthalf,26-dkey1);
        String secondhalfd = cc.encrypt(secondhalf,26-dkey2);
        String decrypted = concathalfOfString(firsthalfd,secondhalfd);
        return decrypted;
    }

    public String halfOfString(String word,int start){
        StringBuilder str = new StringBuilder();

        for (int i=start;i<word.length();i=+2){
            str.append(word.charAt(i));
        }
        return  str.toString();
    }

    public  String concathalfOfString(String firsthalf,String secondhalf){
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
