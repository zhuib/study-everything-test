package demo.light;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2021/7/28 21:41
 */
public class LampController {
    private Lamp currentLamp;

    public LampController(){
        currentLamp = Lamp.S2N;
        currentLamp.turnOn();

        //每个xx时间灯变红(关掉)
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(
                new Runnable(){
                    public void run(){
                        currentLamp = currentLamp.turnOff();
                    }
                },
                10,
                10,
                TimeUnit.SECONDS);
    }
}

