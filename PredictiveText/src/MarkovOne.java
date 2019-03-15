import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {


    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length());
        String key = myText.substring(index,index+1);
        sb.append(key);
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            int ind = myRandom.nextInt(follows.size());
            sb.append(follows.get(ind));
            key = follows.get(ind);
        }
        return sb.toString();
    }

}
