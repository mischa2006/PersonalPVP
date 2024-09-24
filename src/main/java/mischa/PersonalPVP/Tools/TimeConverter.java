package mischa.PersonalPVP.Tools;

import java.util.ArrayList;
import java.util.List;

public class TimeConverter {
    public static List<Integer> convertMilliseconds(Double milliseconds) {
        List<Integer> time = new ArrayList<>();
        System.out.println(milliseconds);
        // Convert milliseconds to minutes and hours
        int minutes = (int) (milliseconds / 1000 / 60);
        int hours = minutes / 60;
        minutes = minutes % 60;

        // Add hours and minutes to the list
        time.add(hours);
        time.add(minutes);

        return time;
    }

}

