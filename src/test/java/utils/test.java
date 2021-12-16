package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class test {
    public static void main(String[] args) {

        String date = getRandomDate();
        System.out.println(date);

    }


    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static String getRandomDate()
    {
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1970, 2019);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        String Day   = gc.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + gc.get(Calendar.DAY_OF_MONTH) : "" + gc.get(Calendar.DAY_OF_MONTH);
        String Month = (gc.get(Calendar.MONTH) + 1) < 10 ? "0" + (gc.get(Calendar.MONTH) + 1) : "" + (gc.get(Calendar.MONTH) + 1);

        return gc.get(Calendar.YEAR) + "-" + Month + "-" + Day;
    }
}
