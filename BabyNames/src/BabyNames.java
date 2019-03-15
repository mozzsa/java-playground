import edu.duke.*;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
public class BabyNames {


    public void totalBirths(FileResource fr){
       int totalBirthsF = 0;
       int totalBirthsM = 0;
       int count = 0;
       for(CSVRecord rec : fr.getCSVParser(false)){
           int numBorn = Integer.parseInt(rec.get(2));
           if (rec.get(1).equals("M")){
               totalBirthsM+=numBorn;
           }
           else{
               totalBirthsF+=numBorn;
           }
           count++;

       }
       System.out.println("Total names in the file : "+count);
       System.out.println("Number of girls name : "+totalBirthsF);
       System.out.println("Number of boys name : " +totalBirthsM);
    }

    public int getRank(int year,String name,String gender){
        String file = "yob"+String.valueOf(year)+"short"+".csv";
        int count = 0;
        FileResource fr = new FileResource(file);
        for (CSVRecord rec : fr.getCSVParser(false)){
            count++;
            if(rec.get(1).equals(gender)&&rec.get(0).equals(name)){
                break;
            } }
        if(count==0){ return -1;}
        else{return count;}

    }

    public String getName(int year,int rank,String gender){
        String file = "yob"+String.valueOf(year)+"short"+".csv";
        int count = 0;
        String name ="";
        FileResource fr = new FileResource(file);
        for (CSVRecord rec : fr.getCSVParser(false)){
            count++;
            if(rec.get(1).equals(gender)&&count==rank){
                name = rec.get(0);
                break;
            } }
        if(name.equals("")){ return "NO NAME"}
        else{return name;}

    }

    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rank = getRank(year,name,gender);
        String newname = getName(newYear,rank,gender);
        System.out.println(name+" "+"is born in "+year+" "+"would be"+newname+"if she was born in "+newYear);
    }

    public int yearOfHighestRank(String name,String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        int year = 0;
        for (File f : dr.selectedFiles()) {
            int tempyear = Integer.parseInt(f.getName().substring(3,6));
            int temprank = getRank(tempyear,name,gender);
            if(temprank>rank){
                    rank= temprank;
                    year = tempyear;
                }

        }
        if(rank>0){ return year ;}
        else{ return -1 ;}

    }

    public  double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        int count =0;
        for (File f : dr.selectedFiles()) {
            int tempyear = Integer.parseInt(f.getName().substring(3,6));
            int temprank=  getRank(tempyear,name,gender);
            if(temprank!=-1){
                rank += temprank;
                count++;
            }
        }
        return rank/count;
    }

    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        String file = "yob"+String.valueOf(year)+"short"+".csv";
        int numberBirth = 0;
        FileResource fr = new FileResource(file);
        for (CSVRecord rec : fr.getCSVParser(false)){
            numberBirth = Integer.parseInt(rec.get(2));
            if(rec.get(1).equals(gender)&&rec.get(0).equals(name)){
                break; }
            numberBirth += Integer.parseInt(rec.get(2));
        }

        return numberBirth;
    }

}
