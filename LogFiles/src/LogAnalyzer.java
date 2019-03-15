import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer(){
        records= new ArrayList<>();
    }
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for (String line: fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public int countUniqueIps(){
        ArrayList<String> uniqueIPs = getUniqueIPAddress();
        return uniqueIPs.size();
    }

    public ArrayList<String> getUniqueIPAddress(){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for(LogEntry le : records){
            String ipAddress =le.getIpAddress();
            if(!uniqueIPs.contains(ipAddress)){
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs;
    }

    public void printAll(){

        for (int i = 0; i <records.size() ; i++) {
            System.out.println(records.get(i));
        }
    }

    public void printAllHigherThanNum(int num) {

        for (int i = 0; i < records.size(); i++) {
            int code = records.get(i).getStatusCode();
            if (code > num) {
                System.out.println(records.get(i).getStatusCode());
            }
        }
    }

    //someday MMM DD -> DEC 11
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for(int i = 0; i < records.size(); i++) {
            Date date  = records.get(i).getRequestTime();
            int ind = date.toString().indexOf(someday);
            String ip = records.get(i).getIpAddress();
            if((ind != -1) && !uniqueIPs.contains(ip)) {
               uniqueIPs.add(ip);
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for(int i = 0; i < records.size(); i++) {
            int status = records.get(i).getStatusCode();
            String ip = records.get(i).getIpAddress();
            if(status>=low&&status<=high){
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }

    public ArrayList<LogEntry> getRecords(){
        return records;
    }

    public HashMap<String,Integer> countWebvisitsPerIp(){
         HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if(map.containsKey(ip)){
                map.put(ip,map.get(ip)+1);
            }
            else{
                map.put(ip,1);
            }
        }
        return map;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> map){
        int max;
        for (String key: map.keySet()) {
            int count = map.get(key);
            if(count>max){
                max = count;

            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map){
        int max = mostNumberVisitsByIP(map);
        ArrayList<String> ipS =new ArrayList<>();
        for(String key : map.keySet()){
            int count =map.get(key);
            if(count==max){
                ipS.add(key);
            }
        }
        return ipS;
    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>>  map = new HashMap<>();
        for(LogEntry entry : records){
            String date = entry.getRequestTime().toString();
            String ip = entry.getIpAddress();
            if(map.containsKey(date)){
                map.get(date).add(ip);
            }
            else{
                ArrayList<String> str = new ArrayList<>();
                str.add(ip);
                map.put(date,str);
            }
        }
        return map;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
        int max = 0;
        String date = "";
        for(String key : map.keySet()){
            int count = map.get(key).size();
            if(count>max){
                max = count;
                date = key ;
            }

        }
        return date ;
    }

    public ArrayList<String> ipsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,String sday){
        ArrayList<String> ipS = new ArrayList<>();
        for(String key : map.keySet()) {
           if(key.equals(sday)){
               ipS = map.get(key);
           }
        }
        return ipS;
    }

}
