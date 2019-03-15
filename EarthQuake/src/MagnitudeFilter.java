public class MagnitudeFilter implements Filter {
    private double minmag;
    private double maxmag;

    MagnitudeFilter(double min,double max){
        minmag = min;
        maxmag = max;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if((qe.getMagnitude() >= minmag)&&(qe.getMagnitude() <= maxmag)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getName() {
        return "MagnitudeFilter";
    }
}
