import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {


    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length());
        String key = myText.substring(index,index+4);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            int ind = myRandom.nextInt(follows.size());
            sb.append(follows.get(ind));
            key = key.substring(1)+follows.get(ind);
        }
        return sb.toString();
    }


}
