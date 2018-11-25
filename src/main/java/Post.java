import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

    protected long epoch;
    private String name, body, timestamp;
    private SleepLog log;


    public Post(String name, String body){
        this(name, body, null);
    }

    public Post(String name, String body, SleepLog log){
        this(System.currentTimeMillis() / 1000L, name, body, log,
             DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
    }

    public Post(long epoch, String name, String body, SleepLog log, String timestamp){
        this.epoch = epoch;
        this.name = name;
        this.body = body;
        this.log = log;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public SleepLog getLog(){
        return log;
    }


    public String toString(){
        return name + " said:\n" + body + "\n" + timestamp;
    }

}
