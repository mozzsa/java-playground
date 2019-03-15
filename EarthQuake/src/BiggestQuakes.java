import java.util.ArrayList;

public class BiggestQuakes {

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        if(howMany>quakeData.size()){
            return quakeData;
        }
        else{
            ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
            ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();

            for(int j=0; j < howMany; j++) {
                int maxIndex = indexOfLargest(copy);
                ret.add(copy.get(maxIndex));
                copy.remove(maxIndex);
            }
            return ret;
        }

    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int maxIndex = 0;
        for(int k=1; k < quakeData.size(); k++){
            QuakeEntry quake = quakeData.get(k);
            if (quake.getMagnitude() > quakeData.get(maxIndex).getMagnitude()){
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size());
        ArrayList<QuakeEntry> largest = getLargest(list, 10);
        for(int k=0; k < largest.size(); k++){
            QuakeEntry entry = largest.get(k);
            System.out.println(entry);
        }
        System.out.println("number found: " + largest.size());
    }







}
