import java.util.regex.*;

public class Part3 {
    public  Boolean  twoOccurrence(String stringa,String stringb){
        //Pattern pattern = Pattern.compile("\\b"+stringa+"\\b");
        Pattern pattern = Pattern.compile(stringa);
        Matcher matcher = pattern.matcher(stringb);
        //matcher.find() checks for all occurrences
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        if(count >= 2){
           return true;
        }
        else{
            return false;
        }

    }

    public  String  lastPart(String stringa,String stringb){
       int ind = stringb.indexOf(stringa);
       int len = stringa.length();
       if(ind!=-1){
           return stringb.substring((ind+len));
       }
       else { return stringb;}

    }

    public  void testing(){
        String a = "Bugun gun gun geldi gun";
        String b = "gun";
        String c = "Bugun gun geldi";
        String d = "Bugun geldi";
        System.out.println(lastPart(b,a));
        System.out.println(twoOccurrence(b,a));
        System.out.println(twoOccurrence(b,c));
        System.out.println(twoOccurrence(b,d));
    }

     /*public static void main (String[] args) {
        Part3 pt = new Part3();
        pt.testing();

    }*/



}
