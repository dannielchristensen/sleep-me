import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        final String DATABASE_NAME = "sleep-me";
        final String ACCT_PATH = "sleep-me.json";

        Firebase db = new Firebase(DATABASE_NAME, ACCT_PATH);

        db.setPath("posts");

        //db.write(new Post("Creighton", "This is another test post."));


        System.out.println(db.getPath() + "\n");
        ArrayList<Post> posts = db.readPosts();
        for(Post post : posts) System.out.println(post + "\n");

    }
}
