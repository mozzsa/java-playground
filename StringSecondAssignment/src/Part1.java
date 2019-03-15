public class Part1 {

   /* method returns the index of the first occurrence
    of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex */

    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int fistOccur = dna.indexOf(stopCodon,startIndex+1);
        if (fistOccur!=-1){
            if((fistOccur-startIndex)%3==0){
                return fistOccur;
            }
            else{ return dna.length();}
        }
        else{
            return dna.length();
        }

    }

    public void testFindStopCodon(){

        String testDNA1 = "DNAATGnoTAA";
        String testDNA2 = "DNAATGnoOTAA";
        String testDNA3 = "DNAATGnoOnooTAA";

        System.out.println(testDNA1);
        System.out.println(findStopCodon(testDNA1,3,"TAA"));
        System.out.println(testDNA2);
        System.out.println(findStopCodon(testDNA2,3,"TAA"));
        System.out.println(testDNA3);
        System.out.println(findStopCodon(testDNA3,3,"TAA"));

    }

    /* returns the gene formed from "ATG" and the closest stop codon that is a multiple of three away*/
    public String findGene(String dna){
        int len = dna.length();
        int firstOccur = dna.indexOf("ATG");
        if(firstOccur!=-1){
            int findStop1 = findStopCodon(dna,firstOccur,"TAA");
            int findStop2 = findStopCodon(dna,firstOccur,"TAG");
            int findStop3 = findStopCodon(dna,firstOccur,"TGA");
            if(findStop1==len&findStop2==len&findStop3==len){
                return "";
            }
            else{
                int smallest = Math.min(findStop1, Math.min(findStop2, findStop3));
                return dna.substring(firstOccur,smallest+3);
            }
        }
        else{ return "";}
    }

    public void testFindGene(){

        String testDNA1 = "DNAATGnoTAA";
        String testDNA2 = "DNAATGnoOTAA";
        String testDNA3 = "DNAATGnoO";
        String testDNA4 = "DNAATGnoTAG";
        String testDNA5 = "DNAATGnoOTAG";
        String testDNA6 = "DNAATGnoOn";

        System.out.println(testDNA1);
        System.out.println(findGene(testDNA1));
        System.out.println(testDNA2);
        System.out.println(findGene(testDNA2));
        System.out.println(testDNA3);
        System.out.println(findGene(testDNA3));
        System.out.println(testDNA4);
        System.out.println(findGene(testDNA4));
        System.out.println(testDNA5);
        System.out.println(findGene(testDNA5));
        System.out.println(testDNA6);
        System.out.println(findGene(testDNA6));

    }


    public void printAllGenes(){
        String dna = "DNAATGnoOTAAATGTAGTTTTTTATGTTTTTTTGA";
        while(true) {
            String gene = findGene(dna);
            if(gene!=""){
                System.out.println(gene);
                dna = dna.substring(gene.length());
            }
            else{
                break;
            }
        }
    }

    /*public static void main (String[] args) {
        Part1 pt = new Part1();
        //pt.testFindStopCodon();
        pt.testFindGene();
        pt.printAllGenes();
    }*/
}
