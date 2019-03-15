import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.Collections;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreq;

    WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreq = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreq.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreq.add(1);
            } else{
                int inc = myFreq.get(index)+1;
                myFreq.set(inc,index);
            }

        }}

    public void tester(){
        findUnique();
        for (int i = 0;i<myWords.size();i++){
            System.out.println("Number of unique words :"+myWords.size());
            System.out.println(myFreq.get(i)+" "+ myWords.get(i));

        }
        System.out.println("The word that occurs most often and its count are : "+findIndexOfMax());
    }

    public int   findIndexOfMax(){
        return Collections.max(myFreq);
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }}
