import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private  ArrayList<Filter> filters ;
    MatchAllFilter(){
        filters = new ArrayList<>();
    }
    public void addFilters(Filter f){
        filters.add(f);
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        for (int i = 0; i < filters.size(); i++) {
           if(!filters.get(i).satisfies(qe)){
               return false;
           }
        }
        return true;
    }

    @Override
    public String getName() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
            s.append(filters.get(i).getName());
        }
        return s.toString();
    }
}
