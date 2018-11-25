public class Main {

    public static void main(String[] args){
        final String DATABASE_NAME = "sleep-me";
        final String ACCT_PATH = "sleep-me.json";

        Firebase db = new Firebase(DATABASE_NAME, ACCT_PATH);


        db.setPath("posts");

        db.write(new Post("name", "stuff"));

        System.out.println(db.getPath());

        //System.out.println(db.read());

    }
}
