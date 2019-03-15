import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1,QuakeEntry q2){
      if(q1.getInfo().compareTo(q2.getInfo())<0){
         return -1;
      }
      if(q1.getInfo().compareTo(q2.getInfo())>0){
         return -1;
      }
      if(q1.getInfo().compareTo(q2.getInfo())==0){
         double depth1 = q1.getDepth();
         double depth2 = q2.getDepth();
         Double.compare(depth1,depth2);
      }
      return 0;
    }
}
