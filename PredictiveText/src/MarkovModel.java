import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {

    private int many;

    public MarkovModel(int howMany) {
        many = howMany;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length());
        String key = myText.substring(index,index+many);
        sb.append(key);
        for(int k=0; k < numChars-many; k++){
            ArrayList<String> follows = getFollows(key);
            int ind = myRandom.nextInt(follows.size());
            sb.append(follows.get(ind));
            key = key.substring(1)+follows.get(ind);
        }
        return sb.toString();
    }

}
