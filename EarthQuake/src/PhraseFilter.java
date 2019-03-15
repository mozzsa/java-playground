public class PhraseFilter implements Filter {
    private String phrase;
    private String request;

    PhraseFilter(String ph, String req) {
        phrase = ph;
        request = req;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {

        if (request.equals("start")) {
            if (qe.getInfo().startsWith(phrase)) {
                return true;
            }
        } else if (request.equals("end")) {
            if (qe.getInfo().endsWith(phrase)) {
                return true;
            }
        } else if (request.equals("any")) {
            if (qe.getInfo().indexOf(phrase) != -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "PhraseFilter";
    }
}
