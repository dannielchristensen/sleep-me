import java.util.*;
public class SleepLog {
    private int numDays;
    private double[] numHours;
    private String[][] hours;

    //This doesn't have to be like this but it was an idea I was having, anyone can make changes.
    //Maybe make it so that the user must input a number between 0 and 2400.
    public SleepLog(int numDays){
        Scanner s = new Scanner(System.in);
        this.numDays = numDays;
        hours = new String[numDays][2];
        System.out.println("Enter in standard military time. Ex: 1735");
        for(int k = 0; k<numDays; k++){
            System.out.print("When did you fall asleep " + k + " days ago?");
            hours[k][0] = s.nextLine();
            System.out.print("When did you wake up " + k + " days ago?");
            hours[k][1]= s.nextLine();
        }
        numHours = getNumHours();
    }
    //number of hours slept each night put into an array.
    private double[] getNumHours() {
        numHours = new double[numDays];
        for(int k =0; k<numDays; k++){
            double sum = 0;
            int shour = Integer.parseInt(hours[k][0])/100;
            int sminute = Integer.parseInt(hours[k][0])%100;
            int whour = Integer.parseInt(hours[k][1])/100;
            int wminute = Integer.parseInt(hours[k][1])%100;
            if(shour>whour){
                sum+=(24-shour)+whour;
            }else{
                sum += (whour-shour);
            }
            if(sminute>wminute){
                sum += ((60-sminute) + wminute)/60.0;
            }
            numHours[k] = sum;
        }
        return numHours;
    }
    //number of sleep cycles went through in total.
    private int sCycles(){
        int sleepC = 0;
        for (int k =0; k<numDays; k++){
            sleepC += (numHours[k] * 60)/90;
        }
        return sleepC;
    }

    //how good their circadian rhythms were.
    private String cCycle(){
        int[] difference = {0,0};

        for (int k = 0; k<numDays-1; k++){
            for(int i = 0; k<2; k++) {
                int hour1 = Integer.parseInt(hours[k][i]) / 100;
                int hour2 = Integer.parseInt(hours[k + 1][i]) / 100;
                if(hour1>12 && hour2>12 || hour1<12 && hour2<12){
                    difference[i] += Math.abs(hour1-hour2);
                }else if(hour1>12){
                    difference[i] += (24-hour1) + hour2;
                }else{
                    difference[i] += (24-hour2) + hour1;
                }
            }
        }
        int recommended = numDays*2;
        if(difference[0]>recommended && difference[1]>recommended){
            return "Circadian Rhythm is not very good. Try going to sleep and waking up at a scheduled time.";
        }else if (difference[0] > recommended){
            return "Circadian Rhythm is almost good. Try going to sleep at a scheduled time";
        }else if (difference[1] > recommended){
            return "Circadian Rhythm is almost good. Try waking up at a scheduled time.";
        }else{
            return "Circadian Rhythm is very good! Keep sleeping at a scheduled time!";
        }
    }
    // will rate the sleep from that week, use statistics to rate the sleep as good or bad.
    private String rateOfSleep(){
        int recommended = numDays*5;
        if(sCycles()<recommended){
            return "Not enough sleep cycles were completed for healthy sleep, try sleeping a bit more.";
        }else if (sCycles() == recommended){
            return "You had the perfect amount of sleep cycles!";
        }else{
            return "You had more sleep cycles than recommended, try sleeping a bit less.";
        }

    }
    // This could potentially be where the chart John wanted could go. Otherwise, John go wild on how you want to
    //display the info. Has to include the sCycle, cCycle, and rateOfSleep method plus the numHours has the hours
    //they slept written as 7.4 or 4.8 so if you want to change it to h hours and m minutes you can.
    public String toString(){
        return "";
    }
}
