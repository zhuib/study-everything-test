package demo.light;

/**
 * Date: 2021/7/28 21:40
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {

    ArrayList<String> vechicles = new ArrayList<>();
    public String name = null;

    public Road(String name){
        this.name = name;

        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable(){
            //每1--10秒内随机产生车(不超过999辆)*/
            @Override
            public void run() {
                try {
                    Thread.sleep((new Random().nextInt(10)+1)*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=1;i<1000;i++){
                    vechicles.add(Road.this.name+"_"+i);
                }
            }
        });

        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(
                new Runnable(){
                    public void run(){
                        if(vechicles.size()>0){
                            boolean lighted = Lamp.valueOf(Road.this.name).isLighted();
                            if(lighted){
                                System.out.println(vechicles.remove(0)+" is traverting!");
                            }
                        }
                    }
                },
                1,
                1,
                TimeUnit.SECONDS);
    }
}