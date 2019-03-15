import java.util.Date;

public class LogEntry {

    private String ip;
    private Date date;
    private String request;
    private int status;
    private int bytes;

    LogEntry(String ipAddress,Date accessTime,String req,int statusCode,int bytesReturned){
        ip = ipAddress;
        date = accessTime;
        request = req;
        status = statusCode;
        bytes = bytesReturned;
    }

    public String getIpAddress(){
        return ip;
    }
    public Date getRequestTime(){
        return date;
    }
    public String getRequesttype(){
        return request;
    }
    public int getStatusCode(){
        return status;
    }
    public int getBytesReturned(){
        return bytes;
    }

    public String  toString(){
        return ip+" "+date+" "+request+" "+status+" "+bytes;
    }
}
