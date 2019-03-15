import edu.duke.StorageResource;
import edu.duke.FileResource;


public class Part1 {

   /* method returns the index of the first occurrence
    of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex */

    public int findStopCodon(String dna, int startIndex, String stopCodon) {

        int fistOccur = dna.indexOf(stopCodon, startIndex);
        while (fistOccur != -1){
            if ((fistOccur - startIndex) % 3 == 0) {
                  break;
            }
            else {
                fistOccur = dna.indexOf(stopCodon,fistOccur+1);
            }
           }
        if(fistOccur==-1){
           return dna.length();
        } else{ return fistOccur;}
    }

    public void testFindStopCodon() {

        String testDNA1 = "DNAATGnoTAA";
        String testDNA2 = "DNAATGnoOTAA";
        String testDNA3 = "DNAATGnoOnooTAA";

        System.out.println(testDNA1);
        System.out.println(findStopCodon(testDNA1, 3, "TAA"));
        System.out.println(testDNA2);
        System.out.println(findStopCodon(testDNA2, 3, "TAA"));
        System.out.println(testDNA3);
        System.out.println(findStopCodon(testDNA3, 3, "TAA"));

    }

    /* returns the gene formed from "ATG" and the closest stop codon that is a multiple of three away*/
    public String findGene(String dna) {
        String tempdna = dna.toUpperCase();
        int len = dna.length();
        int firstOccur = tempdna.indexOf("ATG");

        if (firstOccur != -1) {
            int findStop1 = findStopCodon(tempdna, firstOccur, "TAA");

            int findStop2 = findStopCodon(tempdna, firstOccur, "TAG");

            int findStop3 = findStopCodon(tempdna, firstOccur, "TGA");

            if (findStop1 == len & findStop2 == len & findStop3 == len) {
                return "";
            } else {
                int smallest = Math.min(findStop1, Math.min(findStop2, findStop3));
                return dna.substring(firstOccur, smallest + 3);
            }
        } else {
            return "";
        }
    }

    public void testFindGene() {

        String testDNA1 = "DNAATGnooTAAjjjjjfjjfjffjjfjfjfDNAATGnoOTAG";
        String testDNA2 = "DNAATGnoOTAA";
        String testDNA3 = "DNAATGnoO";
        String testDNA4 = "DNAATGnoTAG";
        String testDNA5 = "DNAATGnoOTAG";
        String testDNA6 = "DNAATGnoOn";

        System.out.println(testDNA1);
        System.out.println(findGene(testDNA1));
       /* System.out.println(testDNA2);
        System.out.println(findGene(testDNA2));
        System.out.println(testDNA3);
        System.out.println(findGene(testDNA3));
        System.out.println(testDNA4);
        System.out.println(findGene(testDNA4));
        System.out.println(testDNA5);
        System.out.println(findGene(testDNA5));
        System.out.println(testDNA6);
        System.out.println(findGene(testDNA6));*/

    }


    public void printAllGenes() {
        String dna = "DNAATGnoOTAAATGTAGTTTTTTATGTTTTTTTGAjjjjjjjjjatgnootaa";
        while (true) {


            String gene = findGene(dna);
            if (gene != "") {
                System.out.println(gene);
                dna = dna.substring(dna.indexOf(gene)+gene.length());
            } else {

                break;
            }
        }
    }

    public StorageResource getAllGenes(String dna) {

        StorageResource storage = new StorageResource();
        while (true) {
            String gene = findGene(dna);

            if (gene != "") {
                storage.add(gene);
                dna = dna.substring(dna.indexOf(gene)+gene.length());
            } else {
                break;
            }
        }

        return storage;
    }

    public void testGetAllGenes() {
        StorageResource storage = getAllGenes( "DNAATGnoOTAAATGTAGTTTTTTATGTTTTTTTGA");
        for (String i : storage.data()) {
            System.out.println(i);
        }
    }

    /*
    returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA
     */
    public Double cgRatio(String dna) {
        double ratio = 0.0;
        double len = dna.length();
        int count = 0;
        int startC = dna.indexOf("C");
        int startG = dna.indexOf("G");
        while (true) {
            if (startC != -1) {
                count++;
                startC = dna.indexOf(startC);
            } else {
                break;
            }
        }
        while (true) {
            if (startG != -1) {
                count++;
                startG = dna.indexOf(startG);
            } else {
                break;
            }
        }
        return count / len;
    }

    public void processGenes(StorageResource sr) {
        int maxlen = 0;
        int countcg = 0;
        int len60 = 0;
        for (String dna : sr.data()) {
            System.out.println(dna);
            int len = dna.length();
            if (len > maxlen) {
                maxlen = len;
            }
            if (len > 60) {
                len60++;
                System.out.println("DNA length >60" + dna);
                double ratio = cgRatio(dna);
                if (ratio > 0.35) {
                    countcg++;
                    System.out.println("DNA with ratio >0.35 with len>60 " + dna);
                }
            }
        }
        System.out.println("number of dna with cgratio >0.35 and len>60 " + countcg);
        System.out.println("number of dna with len>60 " + len60);
        System.out.println("maxlen"+maxlen);

    }

    public void testProcessGenes() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        StorageResource st = getAllGenes(dna);

        processGenes(st);
    }

    public static void main(String[] args) {
        Part1 pt = new Part1();
        pt.testFindStopCodon();
       // pt.testFindGene();
        pt.testProcessGenes();
       // pt.printAllGenes();
    }


}





