import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args){
        final String DATABASE_NAME = "sleep-me";
        final String ACCT_PATH = "sleep-me.json";

        Firebase db = new Firebase(DATABASE_NAME, ACCT_PATH);

        UI.printLogo();

        db.setPath("posts");

        //db.write(new Post("Abe Lincoln", "Four score and seven years ago our fathers brought forth on this continent a new nation.")); //43 chars line?


        //System.out.println(db.getPath() + "\n");
        ArrayList<Post> posts = db.readPosts();
        Collections.reverse(posts);
        for(Post post : posts) System.out.println(post + "\n");

    }
}
