import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows(){
        MarkovOne one = new MarkovOne();
        one.setTraining("this is ab test yes this is a test.");
        ArrayList<String> s = one.getFollows("a");
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i));
        }
    }

    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markovOne = new MarkovOne();
        markovOne.setTraining(st);
        markovOne.setRandom(42);
        for(int k=0; k < 3; k++){
            String text = markovOne.getRandomText(500);
            System.out.println(text);

        }

    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testGetFollowsWithFile();
    }
}
