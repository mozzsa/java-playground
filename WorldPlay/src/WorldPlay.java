import java.util.regex.*;
import java.lang.*;
public class WorldPlay {

    public boolean isVowel(char ch ){
       String isVovel = String.valueOf(ch);
       String regex = "[aeiou]";
       Pattern r = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
       Matcher m =r.matcher(isVovel);
       if(m.find()){
           return  true;
       }
       else{ return  false;}
    }

    public String replaceVowels(String phrase , char ch){
       String ph = String.valueOf(ch);
        return phrase.replaceAll("(?i)[aeiou]",ph);
    }

    public String emphasize(String phrase,char ch){
        StringBuilder str = new StringBuilder(phrase.toLowerCase());
        char tempch = Character.toLowerCase(ch);

        for (int i=0;i<str.length();i++){
           if(i%2==0){
               if(str.charAt(i)==tempch){
                   str.setCharAt(i,'+');
               }
           }
           else {
               if(str.charAt(i)==tempch){
                   str.setCharAt(i,'*');
               }

           }
       }
        return str.toString();
    }

    public static void main(String[] args) {
        WorldPlay wp = new WorldPlay();
        String a  = wp.replaceVowels("BUGUN GELDILER BANA",'c');
        System.out.println(a);
        String  b = wp.emphasize("LcOOcOO",'C');
        System.out.println(b);
    }
}
