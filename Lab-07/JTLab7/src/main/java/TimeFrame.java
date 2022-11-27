
import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Home pc
 */
public class TimeFrame 
{
    public static Calendar min;
    public static Calendar max;
    public static int minValue = 8;
    public static int maxValue = 20;
    
    public static void setTimeFrame(int start, int end)
    {
        min = Calendar.getInstance();
        min.set(Calendar.HOUR_OF_DAY, start - 1);
        max = Calendar.getInstance();
        max.set(Calendar.HOUR_OF_DAY, end - 1);
    }
}
