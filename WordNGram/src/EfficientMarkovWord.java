import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    String [] myText;
    Random myRandom;
    int myOrder;
    HashMap<WordGram,ArrayList<String>> map;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kgram = new WordGram(myText,index,myOrder);
        kgram.buildText(sb);
        ArrayList<String> follows;
        for(int k=0; k < numWords-myOrder; k++){
            if(buildHashMap(kgram)){
                follows = getFollows(kgram);
                map.put(kgram,follows);
            }
            else {
                follows = map.get(kgram) ;
            }
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kgram = kgram.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(WordGram kgram) {
        ArrayList<String> follows = new ArrayList<>();
        int pos =0;
        while (true){
            int index = indexof(myText,kgram,pos);
            if(index==-1){
                break;
            }
            follows.add(myText[index+1]);
            pos = index+1;
        }
        return follows;

    }
    //returns the last position
    private int indexof(String[] words,WordGram target,int from){
        int len = target.length();
        int ind = 0;
        boolean found;
        for (int i = from; i <= words.length-len ; i++) {
            found = true;
            if(words[i].equals(target.wordAt(ind))){
                for (int j = 1; j < target.length(); j++) {
                    if(!words[i+j].equals(target.wordAt(j))){
                        found = false;
                        break;
                    }
                }
                if(found){
                    return i+len-1;
                }
            }
        }

        return -1;
    }

    private boolean buildHashMap(WordGram kgram){
        for (WordGram key : map.keySet()) {
            if(key.equals(kgram)){
                return false;
            }
        }
        return true;
    }

}
