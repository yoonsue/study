package observer;

import org.junit.jupiter.api.Test;

import java.util.Date;

class ObserverTest {

    @Test
    void myTimerTest() throws InterruptedException{
        MyTimer timer = new MyTimer(NAME1);

        timer.scheduleAtFixedRate(new MyTimerTask()
            {
            public void run()
                {
                    System.out.println(new Date().toString());
                }}, 0, 500 );
    }

    private static final String NAME1 = "Norm D. Plume";

}