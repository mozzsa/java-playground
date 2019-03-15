import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    private ArrayList<String> getFollows(String key1,String key2) {
        ArrayList<String> follows = new ArrayList<>();
        int pos =0;
        while (true){
            int index = indexof(key1,key2, pos);

            if(index==-1||index == myText.length-1){
                break;
            }
            follows.add(myText[index+1]);
            pos = index+2;
        }
        return follows;

    }

    private int indexof(String key1,String key2,int from){
        for (int i = from; i < myText.length-1 ; i++) {
            if (myText[i].equals(key1)&&myText[i+1].equals(key1)) {
                return i+1;
            }
        }
        return -1;
    }
}
