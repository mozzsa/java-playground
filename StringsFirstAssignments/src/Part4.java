import java.util.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Part4 {

    public void findYoutubefromUrl ()

    {
        String search = "Youtube.com";
        int len = search.length();
        BufferedReader reader = null;

        try {
            URL source = new URL("http://www.dukelearntoprogram.com/course2/data/manylinks.html ");
            reader= new BufferedReader(new InputStreamReader(source.openStream()));
            String  line =  reader.readLine();

            while(line != null){
                int ind = line.toLowerCase().indexOf(search.toLowerCase());
                if(ind!=-1){
                    String first = line.substring(0,ind);
                    String last = line.substring((ind+len));
                   int intStart = first.lastIndexOf("\"");
                   int intStop = last.indexOf("\"")+(line.length()-last.length());
                   String urlYout  = line.substring((intStart+1),intStop);
                   System.out.println(urlYout);
                }

                line = reader.readLine();
            }
            reader.close();
        }
        catch (MalformedURLException urlEx){
            urlEx.printStackTrace();
            throw  new RuntimeException("Malformed URL Exception",urlEx);
        }
        catch (IOException ioEx){
            ioEx.printStackTrace();
            throw  new  RuntimeException("IO Exception",ioEx);
        }

    }

    public static void main (String[] args) {
        Part4 pt = new Part4();
        pt.findYoutubefromUrl();
    }







}
