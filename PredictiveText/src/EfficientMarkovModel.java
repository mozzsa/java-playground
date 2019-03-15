import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{

    private int many;
    private HashMap<String,ArrayList<String>> map;
    public EfficientMarkovModel(int howMany) {
            many = howMany;
            map = new HashMap<>();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length());
        String key = myText.substring(index, index + many);
        sb.append(key);
        ArrayList<String> follows;
        for (int k = 0; k < numChars - many; k++) {
            if(!map.containsKey(key)){
                follows = getFollows(key);
                map.put(key,follows);
            }
            else {
                follows = map.get(key);
            }
            int ind = myRandom.nextInt(follows.size());
            sb.append(follows.get(ind));
            key = key.substring(1) + follows.get(ind);
        }
        return sb.toString();
        }

        public void printHashMapInfo(){
           for(String key : map.keySet()){
               System.out.println(map.get(key));
           }
           System.out.println("Number of keys :" + map.size());

        }
        public String toString(){
            String s = "Number " + String.valueOf(many);
            return s;
        }



}
