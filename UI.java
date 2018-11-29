public class UI {
	private Scanner s;
	private String dream;

	public UI(){
		s = new Scanner();
	}

    public static void printLogo(){

        System.out.println("    _____ _                                      \n" +
                        "   / ____| |                                     \n" +
                        "  | (___ | | ___  ___ _ __        _ __ ___   ___ \n" +
                        "   \\___ \\| |/ _ \\/ _ \\ '_ \\      | '_ ` _ \\ / _ \\\n" +
                        "   ____) | |  __/  __/ |_) |  _  | | | | | |  __/\n" +
                        "  |_____/|_|\\___|\\___| .__/  (_) |_| |_| |_|\\___|\n" +
                        "                     | |                         \n" +
                        "                     |_|                         ");
        //System.out.println("A Glasscock-Chapple-Herrera-Christensen enterprise");
    }

    public static String getDream(){
    	System.out.println("Enter Dream Below...");
    	dream = s.nextLine();
    	return dream;
    }

    public static void displayDream (String a){
    	System.out.println("Displaying Dream Below...");
    	System.out.println(a);
    }

}
