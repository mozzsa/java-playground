import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collections;

public class CharactersInPlay {

    private ArrayList<String> names ;
    private ArrayList<Integer> counts;

    CharactersInPlay(){
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    public void update(String name){
        int index = names.indexOf(name);
        if(index != -1){
            int count = counts.get(index);
            counts.set(index,count++);
        } else{
            names.add(name);
            counts.add(1);
        }}

    public void findAllCharacters(){
        FileResource fr = new FileResource();
        CharactersInPlay in = new CharactersInPlay();
        for(String line : fr.lines()){
           String name = line.split(".")[0];
           in.update(name);
        }}

    public void tester(){

        for(int i=0;i<names.size();i++){
            System.out.println(names.get(i)+" "+counts.get(i));
        }
        int max = Collections.max(counts);
        int index = counts.indexOf(max);
        System.out.println("Main Character is : "+ names.get(index));
    }

    public void charactersWithNumParts(int num1,int num2){
       if(num1<=num2){
           for(int i=num1;i<=num2;i++){
               int index = counts.indexOf(i);
               if(index != -1){
                  System.out.println(names.get(index) +" "+counts.get(index));}
           }
       }}
}
