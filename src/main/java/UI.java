// I didnt know what kind of interface we wanted, so this is all console, if we decide to switch to GUI it wont be hard
import java.util.*;
public class UI {
  private Scanner s;
  public UI(){
    s = new Scanner();
  }
  //reads in the dream info (will only take one paragraph at a time)
  public String takeDream(){
     String para = s.nextLine();
     return para;
  }
  //displays one paragraph
  public displayDream(String a){
     System.out.println("DREAM: ");
     System.out.println(a); 
  }
  
}
