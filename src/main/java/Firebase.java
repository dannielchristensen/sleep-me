import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

public class Firebase {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private CountDownLatch counter;
    private ValueEventListener readListener;
    private DatabaseReference.CompletionListener writeListener;
    private Post tempValue;

    public Firebase(String dbName, String acctPath){
        try {
            FileInputStream serviceAccount = new FileInputStream(acctPath);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://" + dbName + ".firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        }
        catch (IOException e){
            System.out.println("Error in database initialization.");
            System.out.print(e.getMessage());
            System.exit(1);
        }

        writeListener = (de, dr) -> counter.countDown();

        //TODO: use to detect new posts
        readListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tempValue = dataSnapshot.getValue(Post.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };


        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
    }

    public void child(String path){
        //TODO: check for recursion
        ref = ref.child(path);
    }

    public void parent(){
        ref = ref.getParent();
    }

    public void root(){
        ref = ref.getRoot();
    }

    public void setPath(String path){
        // Ex: folder/subfolder/key
        ref = database.getReference(path);
    }

    public String getPath(){
        return "Path: " + ref;
    }

    public Object read(){
        //TODO: fix null output via asynch listener

        tempValue = null;
        ref.addListenerForSingleValueEvent(readListener);
        //while(tempValue == null) {}
        return tempValue;
    }

    public Post read(String path){
        database.getReference(path).addListenerForSingleValueEvent(readListener);
        return tempValue;
    }

    public void write(Post post){
        counter = new CountDownLatch(1);
        ref.child(Long.toString(post.epoch)).setValue(post, writeListener);
        try {
            counter.await();
        }
        catch (InterruptedException e) {
            System.out.print("Connection to database interrupted. Data not saved.");
        }
    }

    public void write(String path, Post post){
        counter = new CountDownLatch(1);
        database.getReference(path).child(Long.toString(post.epoch)).setValue(post, writeListener);
        try {
            counter.await();
        }
        catch (InterruptedException e) {
            System.out.print("Connection to database interrupted. Data not saved.");
        }
    }

    public void write(ArrayList<Post> list){
        //TODO: improve efficiency
        for(Post post : list) {
            counter = new CountDownLatch(1);
            ref.child(Long.toString(post.epoch)).setValue(post, writeListener);
            try {
                counter.await();
            } catch (InterruptedException e) {
                System.out.print("Connection to database interrupted. Data not saved.");
            }
        }
    }

    public void write(HashMap<String, Post> map){
        //TODO: improve efficiency
        for(String path : map.keySet()) {
            counter = new CountDownLatch(1);
            database.getReference(path).child(Long.toString(map.get(path).epoch)).setValue(map.get(path), writeListener);
            try {
                counter.await();
            } catch (InterruptedException e) {
                System.out.print("Connection to database interrupted. Data not saved.");
            }
        }
    }
}
