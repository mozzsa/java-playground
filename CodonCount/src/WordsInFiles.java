import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;

    WordsInFiles(){
        map = new HashMap<>();
    }

    private void addWordsFromFile(File f){
        FileResource fl = new FileResource(f);
        for(String word : fl.words()){
            if(map.containsKey(word)){
                ArrayList<String> fflist = map.get(word);
                fflist.add(f.getName());
            }else{
                ArrayList<String>  flist_ = new ArrayList<>();
                flist_.add(f.getName());
                map.put(word,flist_); }
        }}

     private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
     }

     private int maxNumber(){
        int max = 0;
        for(String key : map.keySet()){
           int size = map.get(key).size();
           if(size>max){
               max = size;
           }
        }
        return max;
     }

     private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> worldlist  = new ArrayList<>();
        for(String key :map.keySet()){
            if(map.get(key).size()==number){
                worldlist.add(key);
            }
        }
        return worldlist;
     }

     private void printFilesIn(String word){
        ArrayList<String> flist = map.get(word);
        for(int i=0;i<flist.size();i++){
            System.out.println(flist.get(i));
        }
     }

     public void tester(){
        buildWordFileMap();
        int maxnum = maxNumber();
        ArrayList<String> list = wordsInNumFiles(maxnum);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
            printFilesIn(list.get(i));
        }
    }

    public static void main(String[] args) {
        WordsInFiles wf = new WordsInFiles();
        wf.tester();
    }






}
