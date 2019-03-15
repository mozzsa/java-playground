
import edu.duke.*;
import org.apache.commons.csv.*;

public class Export {


    public String countryInfo(CSVParser parser,String country){
        String countryIn = "NOT FOUND";
        for (CSVRecord record : parser) {
            String co = record.get("Country");
            if(co.equals(country)){
                countryIn =  country + ":"+ record.get("Exports")+":"+record.get("Value (dollars)");
               break;
             }
         }
        return countryIn;
    }

    public void testing(){

        FileResource fr = new FileResource();
        String info = countryInfo(fr.getCSVParser(),"Germany");
        System.out.println(info);
        listExportersTwoProducts(fr.getCSVParser(),"gold","diamonds");
        int count = numberOfExporters(fr.getCSVParser(),"gold");
        System.out.println(count);
        bigExporters(fr.getCSVParser(),"$999,999,999");
    }

    public void listExportersTwoProducts(CSVParser parser,String  exportItem1,String exportItem2){

        for (CSVRecord record : parser) {
            String co = record.get("Exports");
            if(co.indexOf(exportItem1)!=-1&&co.indexOf(exportItem2)!=-1){
               System.out.println(record.get("Country"));
            }
        }
    }

    public  int numberOfExporters(CSVParser parser,String exportItem){
        int count = 0;
        for (CSVRecord record : parser) {
            String co = record.get("Exports");
            if(co.indexOf(exportItem)!=-1){
                count++;
            }
        }
        return count;
    }

    public void  bigExporters(CSVParser parser,String value){
        for (CSVRecord record : parser) {
            String co = record.get("Value (dollars)");
            if(co.length()>value.length()){
                System.out.println(record.get(0)+" " +record.get(2));
            }
        }

    }

    public static void main(String[] args) {
            Export ex = new Export();
            ex.testing();
        }


}
