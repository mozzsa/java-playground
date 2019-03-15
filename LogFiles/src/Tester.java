import java.util.ArrayList;

public class Tester {

    public static void testLogAnalyzer(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int countIps = la.countUniqueIps();
        System.out.println("number of Unique IPs "+ countIps);
        la.printAll();
        la.printAllHigherThanNum(200);
        int countUniIPs = la.countUniqueIPsRange(200,300);
        System.out.println(countUniIPs);
    }

    public static void main (String[] args) {
        testLogAnalyzer();
    }

    }

