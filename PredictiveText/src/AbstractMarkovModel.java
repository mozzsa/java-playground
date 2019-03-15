
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<>();
        int pos =0;
        while (true){
            int index = myText.indexOf(key, pos);
            int tempIndex = index+key.length();
            if(index==-1||tempIndex == myText.length()){
                break;
            }
            follows.add(myText.substring(tempIndex,tempIndex+1));
            pos = tempIndex+1;
        }
        return follows;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

}
