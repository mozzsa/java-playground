import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonmap;

    CodonCount() {
        codonmap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        codonmap.clear();
        for (int i = start; i <= dna.length() - 3; i += 3) {
            String codon = dna.substring(i, i + 3);
            if (codonmap.containsKey(codon)) {
                codonmap.put(codon, codonmap.get(codon) + 1);
            } else {
                codonmap.put(codon, 1);
            }
        }
    }

    public String getMostCommonCodon() {
        int max = 0;
        String maxkey = "";
        for (String key : codonmap.keySet()) {
            int tempmax = codonmap.get(key);
            if (tempmax > max) {
                max = tempmax;
                maxkey = key;
            }
        }
        return maxkey;
    }

    public void printCodonCounts(int start, int end) {
        for (String key : codonmap.keySet()) {
            int count = codonmap.get(key);
            if(count>=start&&count<=end){
                System.out.println(key);
            }}
    }

    public static void tester(){
        FileResource rs = new FileResource();
        String dna = rs.asString();
        dna = dna.toUpperCase();
        CodonCount cc = new CodonCount();
        cc.buildCodonMap(0,dna);
        String mostCommon0 = cc.getMostCommonCodon();
        System.out.println("Most comon codon for reading frame 0 : " + mostCommon0);
        cc.printCodonCounts(1,3);
        cc.buildCodonMap(1,dna);
        String mostCommon1 = cc.getMostCommonCodon();
        System.out.println("Most comon codon for reading frame 1 : " + mostCommon1);
        cc.printCodonCounts(1,3);
        cc.buildCodonMap(2,dna);
        String mostCommon2 = cc.getMostCommonCodon();
        System.out.println("Most comon codon for reading frame 2 : " + mostCommon2);
        cc.printCodonCounts(1,3);

    }

    public static void main(String[] args) {
        tester();
    }



}







