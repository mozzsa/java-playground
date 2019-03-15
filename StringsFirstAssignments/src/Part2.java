public class Part2 {

    public String findSimpleGene(String dna,String startCodon,String stopCodon){

        int startLength = startCodon.length();
        int stopLength =  stopCodon.length();
        String tempDna = dna.toLowerCase();
        String tempScodon = startCodon.toLowerCase();
        String tempEcodon = stopCodon.toLowerCase();
        int ind_start = tempDna.indexOf(tempScodon);
        int ind_stop = tempDna.indexOf(tempEcodon,(ind_start+startLength));
        if (ind_start == -1|| ind_stop == -1){
            return "";
        }

        else if((ind_start-ind_stop)%3 == 0){
            return dna.substring(ind_start,(ind_stop+stopLength));
        }
        else{ return  "";}
    }

    public void testSimpleGene(){

        String testDNA1 = "ATGGGTTAAGTC";
        String testDNA2 = "gatgctataat";

        System.out.println(testDNA1);
        System.out.println(findSimpleGene(testDNA1,"ATG","TAA"));
        System.out.println(findSimpleGene(testDNA1,"atg","TAA"));
        System.out.println(findSimpleGene(testDNA1,"atg","TAA"));
        System.out.println(testDNA2);
        System.out.println(findSimpleGene(testDNA2,"ATG","TAA"));
        System.out.println(findSimpleGene(testDNA2,"atg","TAA"));
        System.out.println(findSimpleGene(testDNA2,"atg","TAA"));


    }

   /* public static void main (String[] args) {
        Part2 pt = new Part2();
        pt.testSimpleGene();

    }*/
}
