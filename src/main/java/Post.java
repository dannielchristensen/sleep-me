import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

    protected long epoch;
    private String name, timestamp, body;
    private SleepLog log;


    public Post(String name, String body){
        this(name, body, null);
    }

    public Post(String name, String body, SleepLog log){
        this.name = name;
        this.body = body;
        this.log = log;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        timestamp = dtf.format(now);

        epoch = System.currentTimeMillis() / 1000L;
    }

    public String getName() {
        return name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBody() {
        return body;
    }

    public String toString(){
        return name + "\n" + timestamp + "\n" + body;
    }

}
