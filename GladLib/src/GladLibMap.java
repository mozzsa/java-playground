import edu.duke.FileResource;
import edu.duke.URLResource;
import edu.duke.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class GladLibMap {

        private HashMap<String,ArrayList<String > map;
        private ArrayList<String> usedlabel;
        private Random myRandom;

        private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
        private static String dataSourceDirectory = "data";

        public GladLib(){
            map = new HashMap<>();
            usedlabel = new ArrayList<>();
            initializeFromSource(dataSourceDirectory);
            myRandom = new Random();
        }

        public GladLib(String source){
            initializeFromSource(source);
            myRandom = new Random();
        }

        private void initializeFromSource(String source) {
            String[] arr = {"adjective","noun","color","country","name","animal","timeframe","verbs","fruit"};
            for (int i = 0; i < arr.length; i++) {
                map.put(arr[i],readIt(source+"/"+arr[i]+".txt"));

            }}

        private String randomFrom(ArrayList<String> source){
            int index = myRandom.nextInt(source.size());
            return source.get(index);
        }

        private String getSubstitute(String label) {
            if(map.containsKey(label)){
                usedlabel.add(label);
               return randomFrom(map.get(label));
            }
            else{
            return "**UNKNOWN**";}
        }

        private String processWord(String w){
            int first = w.indexOf("<");
            int last = w.indexOf(">",first);
            if (first == -1 || last == -1){
                return w;
            }
            String prefix = w.substring(0,first);
            String suffix = w.substring(last+1);
            String sub = getSubstitute(w.substring(first+1,last));
            return prefix+sub+suffix;
        }

        private void printOut(String s, int lineWidth){
            int charsWritten = 0;
            for(String w : s.split("\\s+")){
                if (charsWritten + w.length() > lineWidth){
                    System.out.println();
                    charsWritten = 0;
                }
                System.out.print(w+" ");
                charsWritten += w.length() + 1;
            }
        }

        private String fromTemplate(String source){
            String story = "";
            usedlabel.clear();
            if (source.startsWith("http")) {
                URLResource resource = new URLResource(source);
                for(String word : resource.words()){
                    story = story + processWord(word) + " ";
                }
            }
            else {
                FileResource resource = new FileResource(source);
                for(String word : resource.words()){
                    story = story + processWord(word) + " ";
                }
            }
            return story;
        }

        private ArrayList<String> readIt(String source){
            ArrayList<String> list = new ArrayList<String>();
            if (source.startsWith("http")) {
                URLResource resource = new URLResource(source);
                for(String line : resource.lines()){
                    list.add(line);
                }
            }
            else {
                FileResource resource = new FileResource(source);
                for(String line : resource.lines()){
                    list.add(line);
                }
            }
            return list;
        }

        public total totalWordsConsidered(){
            int total = 0;
            for (int i = 0; i <usedlabel.size() ; i++) {
               total += map.get(usedlabel.get(i)).size();
            }
        }

        public int totalWordsInMap(){
            int total = 0;
            for (String key : map.keySet()) {
                total += map.get(key).size();
            }
            return total;
        }

        public void makeStory(){
            System.out.println("\n");
            String story = fromTemplate("data/madtemplate2.txt");
            printOut(story, 60);
        }

        public static void main(String[] args) {
            GladLib gl = new GladLib();
            gl.makeStory();
        }

}


