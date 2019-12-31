import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.lang.Thread;

public class UserController {

    private Metrics stats = new Metrics();

    public UserController() {
        stats.startRepeatedReport(30, TimeUnit.SECONDS);
    }

    public void register() {

        long t1 = new Date().getTime();
        stats.recordTimestamp("login", t1);

        // logic
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long t2 = new Date().getTime();
        stats.recordResponseTime("register", t2-t1);
  }

    public void login() {

        long t1 = new Date().getTime();
        stats.recordTimestamp("login", t1);

        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long t2 = new Date().getTime();
    // logic
        stats.recordResponseTime("login", t2-t1);
  }
}
