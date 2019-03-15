public class Part1 {

    /*
    Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
    Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. If there is no such “TAA”, return the empty string.
    If the length of the substring between the “ATG” and “TAA” is a multiple of 3, then return the substring that starts with that “ATG” and ends with that “TAA”.
    */
    public String findSimpleGene(String dna){

       int ind_ATG = dna.indexOf("ATG");
       int ind_TAA = dna.indexOf("TAA",(ind_ATG+3));
       if (ind_ATG == -1|| ind_TAA == -1){
           return "";
       }

       else if((ind_ATG-ind_TAA)%3 == 0){
            return dna.substring(ind_ATG,(ind_TAA+3));
        }
       else{ return  "";}
    }

    public void testSimpleGene(){

        String testDNA1 = "DNAwithnoatgwithnotaa";
        String testDNA2 = "DNAwithnoatgwithTAA";
        String testDNA3 = "DNAwithnATGwithnotaa";
        String testDNA4 = "DNAwithATGnoTAA";
        String testDNA5 = "DNAwithATGwitTAA";
        String testDNA6 = "DNAwithnoatgwithTAA";
        String testDNA7 = "DNAwithnoATGwitwitTAA";
        System.out.println(testDNA1);
        System.out.println(findSimpleGene(testDNA1));
        System.out.println(testDNA2);
        System.out.println(findSimpleGene(testDNA2));
        System.out.println(testDNA3);
        System.out.println(findSimpleGene(testDNA3));
        System.out.println(testDNA4);
        System.out.println(findSimpleGene(testDNA4));
        System.out.println(testDNA5);
        System.out.println(findSimpleGene(testDNA5));
        System.out.println(testDNA6);
        System.out.println(findSimpleGene(testDNA6));
        System.out.println(testDNA7);
        System.out.println(findSimpleGene(testDNA7));

    }

   /* public static void main (String[] args) {
        Part1 pt = new Part1();
        pt.testSimpleGene();

    }*/

}
