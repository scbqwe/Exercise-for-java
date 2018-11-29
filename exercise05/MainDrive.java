package scb.test.waterPool;

import java.util.Calendar;
import java.util.Timer;
/**
 * Created by captain on 2017/7/27.
 */
public class MainDrive {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        //此处设置每天晚上八点启动一次
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                20,0,0
        );
        WaterTimeTask task = new WaterTimeTask();
        Timer timer = new Timer();
        timer.schedule(task, 4000);
        //timer.schedule(task,calendar.getTime(),4000);
    }
}
