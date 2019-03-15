public class MinMagFilter implements Filter {
    private double magMin;
    MinMagFilter(double MinMag){
        magMin = MinMag;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getMagnitude() >= magMin){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getName() {
        return "MinMagFilter";
    }
}
