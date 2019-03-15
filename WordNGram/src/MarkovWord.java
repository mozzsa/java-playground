import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    String [] myText;
    Random myRandom;
    int myOrder;

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-4);  // random word to start with
        WordGram kgram = new WordGram(myText,index,4);
        kgram.buildText(sb);
        for(int k=0; k < numWords-4; k++){
            ArrayList<String> follows = getFollows(kgram);
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
    private int indexof(String[] words,WordGram target,int start){
        int len = target.length();
        int ind = 0;
        boolean found;
        for (int i = start; i <= words.length-len ; i++) {
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

}
