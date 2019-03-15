public class DistanceFilter implements Filter {
    private int distance;
    private Location location;
    DistanceFilter(Location loc , int dis){
       location = loc ;
       distance = dis;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getLocation().distanceTo(location)>= distance){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getName() {
        return "DistanceFilter";
    }
}
