import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

public class Weather {

    public double coldestHourInFile(CSVParser parser) {
        double cWeather = 0.0;
        double temp = 0.0;
        for (CSVRecord record : parser) {
            String co = record.get("TemperatureF");
            if (!co.equals("-9999")) {
                temp = Float.parseFloat(co);
                if (temp > cWeather) {
                    cWeather = temp;
                }
            }
        }
        return temp;
    }


    public void  testColdestHourInFile() {
       FileResource fr = new FileResource();
       double temperature = coldestHourInFile(fr.getCSVParser());
       System.out.println(temperature);
    }

    public CSVRecord  lowestHumidityInFile(CSVParser parser) {
        double humudity = 0.0;
        double min = Integer.MAX_VALUE;
        CSVRecord temprec = null;
        for (CSVRecord record : parser) {
            String co = record.get("Humidity");
            if(!co.equals("N/A")){
                humudity =Float.parseFloat(co);
                if(humudity<min){
                    humudity= min;
                    temprec = record;
                }}
        }
        return temprec;
    }

    public void  testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
        System.out.println(record.get("Humidity"));
        System.out.println(record.get("DateUTC"));
        System.out.println(record.get("TimeEST"));
    }

    public void  testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println(record.get("Humidity"));
        System.out.println(record.get("DateUTC"));
        System.out.println(record.get("TimeEST"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        double min =Integer.MAX_VALUE;
        CSVRecord record = null;

        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord temp = lowestHumidityInFile(fr.getCSVParser());
            double humidity = Float.parseFloat(temp.get("Humidity"));
            if(humidity<min){
                record=temp;
                min = humidity;
            }
        }
        return record;
    }

    public void fileWithColdestTemperature(){
       DirectoryResource dr = new DirectoryResource();
       double coolesttemp = 0.0;
       double max =0.0;
       String sfile = "";
       for(File f : dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           coolesttemp = coldestHourInFile(fr.getCSVParser());
           if(coolesttemp>max){
               max = coolesttemp;
               sfile = f.getName();
           }
       }
        System.out.println("Coldest day was in file weather"+sfile);
        System.out.println("Coldest temperature on that day was" +max);

   }



   public double averageTemperatureInFile(CSVParser parser){
       int count =0;
       double total = 0.0;
       double temp = 0.0;
       for (CSVRecord record : parser) {
           String co = record.get("TemperatureF");
           if (!co.equals("-9999")) {
               temp = Float.parseFloat(co);
               total +=temp;
               count++;
           }
       }
       return total/count;
    }

    public  double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        int count =0;
        double total = 0.0;
        double temp = 0.0;
        for (CSVRecord record : parser) {
            String co = record.get("TemperatureF");
            double humidity = Float.parseFloat(record.get("Humidity"));
            if (!co.equals("-9999")&&humidity>=value) {
                temp = Float.parseFloat(co);
                total +=temp;
                count++;
            }
        }
        return total/count;
    }

    public void  testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double avrTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if(avrTemp>0){
            System.out.println(avrTemp);
        }else{
            System.out.println("No temperature with that humadity");
        }

    }



    public static void main(String[] args) {
        Weather wt = new Weather();
        //wt.testColdestHourInFile();
        //wt.fileWithColdestTemperature();
        // wt.testLowestHumidityInFile();
        //wt.testLowestHumidityInManyFiles();
        wt.testAverageTemperatureWithHighHumidityInFile();
    }









}
