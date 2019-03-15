
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int named){
        int maxIdx = named;
        for (int i=named+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public ArrayList<QuakeEntry> sortByLargestDepth(ArrayList<QuakeEntry> quakes){
        for (int i = 0; i < quakes.size(); i++) {
            int maxIdx = getLargestDepth(quakes,i);
            QuakeEntry max = quakes.get(maxIdx);
            QuakeEntry curr = quakes.get(i);
            quakes.set(i,max);
            quakes.set(maxIdx,curr);
        }
        return quakes;
    }

    public ArrayList<QuakeEntry> onePassBubbleSort(ArrayList<QuakeEntry> quakes,int named){
        for (int i = 0; i < quakes.size()-named   ; i++) {
           double mag1 = quakes.get(i).getMagnitude();
           double mag2 = quakes.get(i+1).getMagnitude();
           if (mag2<mag1){
               quakes.set(i,quakes.get(i+1));
               quakes.set(i+1,quakes.get(i));
           }
        }
        return quakes;
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakes){
        for (int i = 0; i <quakes.size()-1 ; i++) {
            quakes =onePassBubbleSort(quakes,i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for (int i = 0; i <quakes.size()-1 ; i++) {
            if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public ArrayList<QuakeEntry> sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakes){
        if(!checkInSortedOrder(quakes)){
            sortByMagnitude(quakes);
        }
        return quakes;
    }

    public ArrayList<QuakeEntry> sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakes){
        if(!checkInSortedOrder(quakes)){
            sortByMagnitudeWithBubbleSort(quakes);
        }
        return quakes;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    

        /* sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/

        sortByLargestDepth(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
