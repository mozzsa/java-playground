public class DepthFilter implements Filter {
    double depthmin ;
    double depthmax ;
    DepthFilter(double dmin,double dmax){
        depthmin = dmin;
        depthmax = dmax;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getDepth()>=depthmin&&qe.getDepth()<=depthmax){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getName() {
        return "DepthFilter";
    }
}
